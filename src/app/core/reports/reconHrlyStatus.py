from importlib.abc import Loader
from fastapi import APIRouter, Depends, FastAPI, HTTPException # type: ignore
from fastapi import APIRouter
from pydantic import BaseModel # type: ignore
from src.app.services.database.DatabaseAdapter import DatabaseAdapter
from datetime import date, datetime
import logging
import pandas as pd # type: ignore
from src.app.services.logging.logger import initializeLogger
from src.app.services.database.Couchbase.CouchbaseAdapter import CouchbaseAdapter # type: ignore

router = APIRouter()
class HrlyStatusResponse(BaseModel):
    status: str
    data: dict  # Update this based on the actual structure of the return data


class reconHrlyStatus(DatabaseAdapter):
    """Reconciliation Status for a Zone"""
    
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

    async def GenerateReport(self, zoneCode: str, dateFrom: date):
        """
        Generates the reconciliation status report for the specified zone and date range.
        """
        try:
            # Query the database to fetch reconciliation status data
            self.bucket_name = 'payment'  # 'UTSDW'
            self.scope_name = 'payment_scope'  # 'UTSODS'
            dateStartMillis : int = int(datetime.combine(dateFrom, datetime.min.time()).timestamp() * 1000)
            # dateEndMillis : int = int(datetime.combine(dateFrom, datetime.max.time()).timestamp() * 1000)
            # Add one day in milliseconds (86,400,000 ms in a day)
            dateEndMillis = dateStartMillis + 86400000
            # timestamp_ms = 1724312214515
            # date = datetime.fromtimestamp(timestamp_ms / 1000)
            # print(date)
            # formatted_date = date.strftime('%Y-%m-%d %H:%M:%S')
            # print(formatted_date)
            query = f"""
            SELECT
                zone_code as ZONE_CODE,app_code as APP_CODE,cpg_appl_code as CPG_APP_CODE,recon_flag, 
                DATE_PART_STR(MILLIS_TO_STR(pymt_init_date, "1111-11-11T11:11:11.111Z"), "hour") AS HOUR,
                COUNT(*) AS count
            FROM
                `{self.bucket_name}`.`{self.scope_name}`.bank_txn_payment_detail  
            WHERE
                zone_code = '{zoneCode}'
                AND pymt_init_date >= {dateStartMillis} and pymt_init_date < {dateEndMillis} and cpg_pymt_status=1
            GROUP BY zone_code,app_code,cpg_appl_code,recon_flag, DATE_PART_STR(MILLIS_TO_STR(pymt_init_date, "1111-11-11T11:11:11.111Z"), "hour") 
            """
            self.__logger.info(f"Executing query: {query}")
            # Assuming query() returns a list of dictionaries that can be converted into a DataFrame
            result = pd.DataFrame(self.__couchbaseAdapter.query(query))
            
            df_result = result[['ZONE_CODE', 'CPG_APP_CODE','HOUR','recon_flag', 'count']].copy()  # Ensure a copy to avoid warnings
            # print(df_result)
            
            # Initialize new columns with zero to handle NaN values automatically
            df_result['TOTAL_TRANSACTION'] = 0 #df_result['count']
            df_result['SUCCESS_TRANSACTION'] = 0
            df_result['FAILED_TRANSACTION'] = 0
            df_result['MARKED_REFUND'] = 0
            df_result['REFUND_INITIATED'] = 0
            df_result['REFUND_VALIDATED'] = 0
            # print(df_result)
            # Assign values based on `recon_flag` conditions
            df_result.loc[df_result['recon_flag'].isin([0, 1, 2, 3, 4]), 'TOTAL_TRANSACTION'] = df_result['count']
            df_result.loc[df_result['recon_flag'] == 1, 'SUCCESS_TRANSACTION'] = df_result['count']
            df_result.loc[df_result['recon_flag'] == 0, 'FAILED_TRANSACTION'] = df_result['count']
            df_result.loc[df_result['recon_flag'] == 2, 'MARKED_REFUND'] = df_result['count']
            df_result.loc[df_result['recon_flag'] == 3, 'REFUND_INITIATED'] = df_result['count']
            df_result.loc[df_result['recon_flag'] == 4, 'REFUND_VALIDATED'] = df_result['count']
            # print(df_result)
        # Define columns and aggregation for summary
            FiguresProjection = [
            'ZONE_CODE', 'CPG_APP_CODE','HOUR','TOTAL_TRANSACTION', 'SUCCESS_TRANSACTION', 
            'FAILED_TRANSACTION', 'MARKED_REFUND', 'REFUND_INITIATED', 'REFUND_VALIDATED'
            ]
            GroupbyProjection = ['ZONE_CODE','CPG_APP_CODE','HOUR']
            FiguresAggregation = {
            'TOTAL_TRANSACTION': 'sum',
            'SUCCESS_TRANSACTION': 'sum',
            'FAILED_TRANSACTION': 'sum',
            'MARKED_REFUND': 'sum',
            'REFUND_INITIATED': 'sum',
            'REFUND_VALIDATED': 'sum'
            }

        # Perform aggregation
            reconSummary = df_result.groupby(GroupbyProjection).agg(FiguresAggregation).sort_values(by='HOUR',ascending=True) .reset_index()

            # print(reconSummary)
            # 
            return {"status": "success", "data": reconSummary.to_dict(orient="records")}
            # return {"status": "success", "data": reconSummary.to_dict(orient="records")}
        except Exception as e:
            self.__logger.error(f"Error generating report: {str(e)}")
            raise e

# Corrected call with only three arguments
@router.get("/generate/hrly/{zoneCode}/{dateFrom}", response_model=HrlyStatusResponse)
async def generate_hrly(zoneCode: str, dateFrom: date):
    service = reconHrlyStatus()
    # return await service.GenerateReport(zoneCode, dateFrom)
    report_data = await service.GenerateReport(zoneCode, dateFrom)
    # return {"status": "success", "data": report_data}
    return {"status": "Hourly Reconciliation Status", "data": report_data}
# Example usage:
# recon_status = reconHrlyStatus()
# recon_status.GenerateReport("CR", date(2024, 2, 21))
