from importlib.abc import Loader

from fastapi import APIRouter, Depends, FastAPI, HTTPException # type: ignore
from fastapi.responses import JSONResponse
from pydantic import BaseModel
from src.app.services.database.DatabaseAdapter import DatabaseAdapter
from datetime import date, datetime
import logging
import pandas as pd
import numpy as np
from src.app.services.logging.logger import initializeLogger
from src.app.services.database.Couchbase.CouchbaseAdapter import CouchbaseAdapter # type: ignore


router = APIRouter()
class ReconStatusResponse(BaseModel):
    status: str
    data: dict  # Update this based on the actual structure of the return data

class reconStatus(DatabaseAdapter):
    """Zone wise Reconciliation Status"""
    
    def __init__(self, databaseLoader: Loader = None) -> None:
        """
        Initializes an instance of reconStatus.
        """
        super().__init__(databaseLoader)
        self.__logger: logging.Logger = initializeLogger(__name__)
        # Assuming that `self.__couchbaseAdapter` should be an instance of `CouchbaseAdapter`
        self.__couchbaseAdapter = CouchbaseAdapter()
    def analytics_query(self, query: str):
        return self.__couchbaseAdapter.analytics_query(query)

    def connect(self) -> bool:
        return self.__couchbaseAdapter.connect()

    def disconnect(self):
        self.__couchbaseAdapter.disconnect()

    # def getLocationID(self, locationCode: str) -> int | None:
    #     return self.__couchbaseAdapter.getLocationID(locationCode)

    def query(self, query: str):
        return self.__couchbaseAdapter.query(query)

    # async def GenerateReconReport(self, zoneCode: str, dateFrom: date, dateUpto: date):
    async def GenerateReconReport(self,  dateFrom: date, dateUpto: date):
        """
        Generates the reconciliation status report for the specified zone and date range.
        """
        try:
            # Query the database to fetch reconciliation status data
            self.bucket_name = 'payment'  # 'UTSDW'
            self.scope_name = 'payment_scope'  # 'UTSODS'
            dateStartMillis : int = int(datetime.combine(dateFrom, datetime.min.time()).timestamp() * 1000)
            dateEndMillis : int = int(datetime.combine(dateUpto, datetime.max.time()).timestamp() * 1000)
            # timestamp_ms = 1724312214515
            # date = datetime.fromtimestamp(timestamp_ms / 1000)
            # print(date)
            # formatted_date = date.strftime('%Y-%m-%d %H:%M:%S')
            # print(formatted_date)
            query = f"""

            SELECT
                zone_code as ZONE_CODE,app_code as APP_CODE,cpg_appl_code as CPG_APP_CODE,recon_flag,txn_status,COUNT(*) AS count,0 as refund
            FROM
                `{self.bucket_name}`.`{self.scope_name}`.bank_txn_payment_detail  
            WHERE
                pymt_init_date >= {dateStartMillis} and pymt_init_date <= {dateEndMillis} and cpg_pymt_status=1
            GROUP BY zone_code,app_code,cpg_appl_code,recon_flag,txn_status
            union
            SELECT
                zone_code as ZONE_CODE,app_code as APP_CODE,cpg_appl_code as CPG_APP_CODE,recon_flag,txn_status,0 AS count,COUNT(*) as refund
            FROM
                `{self.bucket_name}`.`{self.scope_name}`.bank_txn_refund_detail  
            WHERE
                 rfnd_init_date >= {dateStartMillis} and rfnd_init_date <= {dateEndMillis} and cpg_rfnd_status=1
            GROUP BY zone_code,app_code,cpg_appl_code,recon_flag,txn_status
            """
            self.__logger.info(f"Executing query: {query}")
            # Assuming query() returns a list of dictionaries that can be converted into a DataFrame
            result = pd.DataFrame(self.__couchbaseAdapter.query(query))
            print(result)
            df_result = result[['ZONE_CODE','APP_CODE' ,'CPG_APP_CODE','recon_flag', 'count','refund','txn_status']].copy()  # Ensure a copy to avoid warnings
            
            # Initialize new columns with zero to handle NaN values automatically
            df_result['TOTAL_TXN'] = 0 #df_result['count']
            df_result['SUCCESS_TXN'] = 0
            df_result['VERIFY_PENDING'] = 0
            df_result['REFUND_PENDING'] = 0
            df_result['TOTAL_REFUND_INITIATED'] = 0
            df_result['MARKED_REFUND'] = 0
            df_result['REFUND_INITIATED'] = 0
            df_result['REFUND_VALIDATED'] = 0
            df_result['TOTAL_REFUNDED'] = 0
            # print(df_result)
            # Assign values based on `recon_flag` conditions
            df_result.loc[df_result['recon_flag'].isin([0, 1, 2, 3, 4]), 'TOTAL_TXN'] = df_result['count'] + df_result['refund']
            df_result.loc[df_result['recon_flag'] == 1, 'SUCCESS_TXN'] = df_result['count'] 
            df_result.loc[(df_result['recon_flag'] == 0) & (df_result['txn_status'] == 1), 'SUCCESS_TXN'] = df_result['count']
            # df_result.loc[(df_result['recon_flag'] == 0) & (df_result['txn_status'] == 1), 'VERIFY_PENDING'] = df_result['count']
            df_result.loc[(df_result['recon_flag'] == 0) & (df_result['txn_status'] != 1), 'REFUND_PENDING'] = df_result['count']
            df_result.loc[df_result['recon_flag'].isin([ 2, 3, 4]), 'TOTAL_REFUND_INITIATED'] = df_result['count']
            df_result.loc[df_result['recon_flag']== 1, 'TOTAL_REFUND_INITIATED'] = df_result['refund']
            df_result.loc[(df_result['recon_flag'] == 0) & (df_result['txn_status'] != 1), 'TOTAL_REFUND_INITIATED'] = df_result['count']
            df_result.loc[df_result['recon_flag'] == 2, 'MARKED_REFUND'] = df_result['count']
            df_result.loc[df_result['recon_flag'] == 1, 'MARKED_REFUND'] = df_result['refund']
            df_result.loc[df_result['recon_flag'] == 3, 'REFUND_INITIATED'] = df_result['count']
            df_result.loc[df_result['recon_flag'] == 4, 'REFUND_VALIDATED'] = df_result['count']
            df_result.loc[df_result['recon_flag'].isin([ 2,3, 4]), 'TOTAL_REFUNDED'] = df_result['count']
            df_result.loc[df_result['recon_flag'] == 1, 'TOTAL_REFUNDED'] = df_result['refund']
            # df_result['SUCCESS_PERCENTAGE'] = np.where(
            # df_result['SUCCESS_TXN'] != 0,
            # df_result['SUCCESS_TXN'] / df_result['TOTAL_TXN']* 100,0)

            
            # print(df_result)
        # Define columns and aggregation for summary
            FiguresProjection = [
            'ZONE_CODE','APP_CODE' , 'CPG_APP_CODE','TOTAL_TXN', 'SUCCESS_TXN','VERIFY_PENDING', 'TOTAL_REFUND_INITIATED', 'TOTAL_REFUNDED', 
            'REFUND_PENDING'
            ]
            GroupbyProjection = ['ZONE_CODE','APP_CODE' ,'CPG_APP_CODE']
            FiguresAggregation = {
            'TOTAL_TXN': 'sum',
            'SUCCESS_TXN': 'sum',
            #'VERIFY_PENDING':'sum',
            'TOTAL_REFUND_INITIATED': 'sum',
            # 'MARKED_REFUND': 'sum',
            # 'REFUND_INITIATED': 'sum',
            # 'REFUND_VALIDATED': 'sum',
            'TOTAL_REFUNDED':sum,
            'REFUND_PENDING': 'sum',
            }

        # Perform aggregation
            reconStatusSummary = df_result.groupby(GroupbyProjection).agg(FiguresAggregation) .reset_index()
            reconStatusSummary['SUCCESS_%AGE'] = np.where(
            reconStatusSummary['TOTAL_TXN'] != 0,
            # np.round(((reconStatusSummary['SUCCESS_TXN'] + reconStatusSummary['VERIFY_PENDING'] )/ reconStatusSummary['TOTAL_TXN']) * 100, 2),0)
            np.round(((reconStatusSummary['SUCCESS_TXN'] )/ reconStatusSummary['TOTAL_TXN']) * 100, 2),0)
            reconStatusSummary['FAILURE_%AGE'] = np.where(
            reconStatusSummary['TOTAL_TXN'] != 0,
            np.round((reconStatusSummary['TOTAL_REFUND_INITIATED'] / reconStatusSummary['TOTAL_TXN']) * 100, 2),0)
            print(reconStatusSummary)
            reconStatusSummary['REFUND_%AGE'] = np.where(
            reconStatusSummary['TOTAL_REFUND_INITIATED'] != 0,
            np.round((reconStatusSummary['TOTAL_REFUNDED'] / reconStatusSummary['TOTAL_REFUND_INITIATED']) * 100, 2),0)
            reconStatusSummary['REFUND_PENDING_%AGE'] = np.where(
            reconStatusSummary['TOTAL_REFUND_INITIATED'] != 0,
            np.round((reconStatusSummary['REFUND_PENDING'] / reconStatusSummary['TOTAL_REFUND_INITIATED']) * 100, 2),0)
            print(reconStatusSummary) 

            return {"status": "success", "data": reconStatusSummary.to_dict(orient="records")}
        except Exception as e:
            self.__logger.error(f"Error generating report: {str(e)}")
            raise e

# Corrected call with only three arguments
@router.get("/generate/status/{dateFrom}/{dateUpto}", response_model=ReconStatusResponse)
async def generate_recon( dateFrom: date, dateUpto: date):
    service = reconStatus()
    # return await service.GenerateReport(zoneCode, dateFrom)
    report_data :pd.DataFrame =  await service.GenerateReconReport(dateFrom,dateUpto)
    return {"status": "Reconcilitation Status", "data": report_data}
    # return JSONResponse(content= {"status": "Reconcilitation Status", "data": report_data})

# recon_status = reconStatus()
# recon_status.GenerateReport("CR", date(2024, 2, 21), date(2024, 12, 20))
