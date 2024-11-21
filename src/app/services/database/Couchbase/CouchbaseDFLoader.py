from datetime import date, datetime
from typing import Any

from src.app.services.database.DatabaseAdapter import DatabaseAdapter
from src.app.services.database.Couchbase.CouchbaseAdapter import CouchbaseAdapter
from src.app.services.database.DFLoader import DFLoader
import json
import pandas 

class CouchbaseDFLoader(DFLoader):
    def __init__(self) :
        self.__couchbaseAdapter : DatabaseAdapter = CouchbaseAdapter() 
        self.__scope = self.__couchbaseAdapter.scope_name
        # Load static table projection
        # format : { "tableName" : ["column1", "column2", "column3"] }
        self.__staticTableProjection : dict[str, list[str]] = json.load(open("src/app/resource/database/static_table_projection.json"))
        self.__dynamicTableProjection : dict[str, dict[ str, Any ]] = json.load(open("src/app/resource/database/dynamic_table_projection.json"))
        self.__dynamicTableProjectionDetail : dict[str, dict[str, list[str] | dict[str, str]]] = json.load(open("src/app/resource/database/dynamic_table_projection_detailed.json"))
        self.__authTableProjection : dict[str, list[str]] = json.load(open("src/app/resource/database/auth_table_projection.json"))
        self.__paymentTableProjectionDetail : dict[str, list[str]] = json.load(open("src/app/resource/database/payment_table_projection_detailed.json"))

        #print(type(self.__staticTableProjection))
        #print(type(self.__dynamicTableProjection))

    async def get_static_table_data(self, tableName : str) -> pandas.DataFrame:
        columns = self.__staticTableProjection[tableName]
        #print(columns)
        self.__bucket ='static'
        self.__scope ='general_scope'

        query = f"SELECT {','.join(columns)} FROM {self.__bucket}.{self.__scope}.{tableName} "  
        result = pandas.DataFrame(self.__couchbaseAdapter.query(query)).reset_index()  

        return result
    
    async def get_dynamic_table_data(self, tableName : str, dateFrom: date, dateUpto: date, locationCode : str | None = None) -> pandas.DataFrame:
        groupByColumns : list[str] = self.__dynamicTableProjection[tableName]['groupBy']
        aggregateColumns : dict[ str, list[str]] = self.__dynamicTableProjection[tableName]['aggregate']

        self.__bucket = 'transactional'
        self.__scope = 'txn_scope'
        dateStartMillis : int = int(datetime.combine(dateFrom, datetime.min.time()).timestamp() * 1000)
        dateEndMillis : int = int(datetime.combine(dateUpto, datetime.max.time()).timestamp() * 1000)

        aggregation: list[str] = []
        aggregationcolumn : list[str] = []
        for aggregateFunction, columnNames in aggregateColumns.items():
            aggregation = aggregation + [f"{aggregateFunction}({columnName}) as {columnName}" for columnName in columnNames]
            aggregationcolumn = aggregationcolumn + [f"{columnName}" for columnName in columnNames] # To handle emply data frames.

        if not locationCode:
            query : str = f"SELECT {', '.join(groupByColumns)} ,  {', '.join(aggregation)} FROM {self.__bucket}.{self.__scope}.{tableName} WHERE txn_date >= {dateStartMillis} and txn_date <= {dateEndMillis}  GROUP BY {', '.join(groupByColumns)}"
        else :
            locationID : int | None = self.__couchbaseAdapter.getLocationID(locationCode)
            if not locationID:
                raise ValueError(f"Location code {locationCode} not found")
            
            query : str = f"SELECT {', '.join(groupByColumns)} ,  {', '.join(aggregation)} FROM {self.__bucket}.{self.__scope}.{tableName} WHERE txn_date >= {dateStartMillis} and txn_date <= {dateEndMillis} and loc_id = {locationID} GROUP BY {', '.join(groupByColumns)}"
        # print(query)

        result : pandas.DataFrame = pandas.DataFrame(self.__couchbaseAdapter.analytics_query(query))

        if  result.empty : # no data from query, then  return empty dataframe wtih column headers
            columns : list[str] = []
            columns = groupByColumns + aggregationcolumn
            result = pandas.DataFrame(columns=columns)

        return result
    
    async def get_dynamic_table_detail_data(self, tableName : str, dateFrom: date, dateUpto: date, locationCode : str | None = None) -> pandas.DataFrame:
        selectColumns : list[str] = self.__dynamicTableProjectionDetail[tableName]['select']
        filterColumns : dict[str, str] = self.__dynamicTableProjectionDetail[tableName]['filter']
        dateFilterColumn : list[str] = self.__dynamicTableProjectionDetail[tableName]['dateFilter']
        self.__bucket = 'transactional'
        self.__scope = 'txn_scope'
        dateStartMillis : int = int(datetime.combine(dateFrom, datetime.min.time()).timestamp() * 1000)
        dateEndMillis : int = int(datetime.combine(dateUpto, datetime.max.time()).timestamp() * 1000)

        selectColumn = [f'{columnName} as {columnName} ' for columnName in selectColumns]
        filterCondition : list[str] = [f" and {columnName} {filterValue}" for columnName, filterValue in filterColumns.items()]
        if not locationCode:
            query : str = f"SELECT   {', '.join(selectColumn)} FROM {self.__bucket}.{self.__scope}.{tableName} WHERE {dateFilterColumn} >= {dateStartMillis} and {dateFilterColumn} <= {dateEndMillis} {' '.join(filterCondition)}" #conc_code in {concCond} "
        else :
            locationID : int | None = self.__couchbaseAdapter.getLocationID(locationCode)
            if not locationID:
                raise ValueError(f"Location code {locationCode} not found")
            
            query : str = f"SELECT   {', '.join(selectColumn)} FROM {self.__bucket}.{self.__scope}.{tableName} WHERE {dateFilterColumn} >= {dateStartMillis} and {dateFilterColumn} <= {dateEndMillis} and loc_id = {locationID} {' '.join(filterCondition)}  " #conc_code in {concCond} "
        # print(query)

        result : pandas.DataFrame = pandas.DataFrame(self.__couchbaseAdapter.query(query))

        if  result.empty : # no data from query, then  return empty dataframe wtih column headers
            columns : list[str] = []
            columns = selectColumns
            result = pandas.DataFrame(columns=columns)

        return result

    async def get_auth_table_data(self, tableName : str,  dateFrom: date, dateUpto: date, locationCode : str | None = None ) -> pandas.DataFrame:
        #commented25/07/2024 columns = self.__authTableProjection[tableName]
        selectColumns : list[str] = self.__authTableProjection[tableName]['selectcolumn']
        filterColumns : list[str] = self.__authTableProjection[tableName]['filtercolumn']
        #print(columns)
        self.__bucket ='auth'
        self.__scope ='auth_scope'
        dateStartMillis : int = int(datetime.combine(dateFrom, datetime.min.time()).timestamp() * 1000)
        dateEndMillis : int = int(datetime.combine(dateUpto, datetime.max.time()).timestamp() * 1000)

        #commented25/07/2024 query = f"SELECT {','.join(columns)} FROM {self.__bucket}.{self.__scope}.{tableName} where login_time >= {dateStartMillis} and login_time <= {dateEndMillis}  "  
        query = f"SELECT {','.join(selectColumns)} FROM {self.__bucket}.{self.__scope}.{tableName} where {filterColumns} >= {dateStartMillis} and {filterColumns} <= {dateEndMillis}  "  
        result = pandas.DataFrame(self.__couchbaseAdapter.query(query)).reset_index()  

        if  result.empty : # no data from query, then  return empty dataframe wtih column headers
            result = pandas.DataFrame(columns=selectColumns)

        return result

    async def get_payment_table_detail_data(self, tableName : str, dateFrom: date, dateUpto: date, locationCode : str | None = None) -> pandas.DataFrame:
        selectColumns : list[str] = self.__paymentTableProjectionDetail[tableName]['select']
        filterColumns : dict[str, str] = self.__paymentTableProjectionDetail[tableName]['filter']
        dateFilterColumn : list[str] = self.__paymentTableProjectionDetail[tableName]['dateFilter']
        self.__bucket = 'payment'
        self.__scope = 'payment_scope'
        dateStartMillis : int = int(datetime.combine(dateFrom, datetime.min.time()).timestamp() * 1000)
        dateEndMillis : int = int(datetime.combine(dateUpto, datetime.max.time()).timestamp() * 1000)

        selectColumn = [f'{columnName} as {columnName} ' for columnName in selectColumns]
        filterCondition : list[str] = [f" and {columnName} {filterValue}" for columnName, filterValue in filterColumns.items()]
        if not locationCode:
            query : str = f"SELECT   {', '.join(selectColumn)} FROM {self.__bucket}.{self.__scope}.{tableName} WHERE {dateFilterColumn} >= {dateStartMillis} and {dateFilterColumn} <= {dateEndMillis} {' '.join(filterCondition)}" #conc_code in {concCond} "
        else :
            locationID : int | None = self.__couchbaseAdapter.getLocationID(locationCode)
            if not locationID:
                raise ValueError(f"Location code {locationCode} not found")
            
            query : str = f"SELECT   {', '.join(selectColumn)} FROM {self.__bucket}.{self.__scope}.{tableName} WHERE {dateFilterColumn} >= {dateStartMillis} and {dateFilterColumn} <= {dateEndMillis} and loc_id = {locationID} {' '.join(filterCondition)}  " #conc_code in {concCond} "
        # print(query)

        result : pandas.DataFrame = pandas.DataFrame(self.__couchbaseAdapter.query(query))

        if  result.empty : # no data from query, then  return empty dataframe wtih column headers
            columns : list[str] = []
            columns = selectColumns
            result = pandas.DataFrame(columns=columns)

        return result