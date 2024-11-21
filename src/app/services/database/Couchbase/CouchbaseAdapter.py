from src.app.services.database.DatabaseAdapter import DatabaseAdapter
from couchbase.cluster import Cluster # type: ignore
from couchbase.options import ClusterOptions, AnalyticsOptions
from couchbase.auth import PasswordAuthenticator # type: ignore
from couchbase.result import QueryResult # type: ignore
from couchbase.bucket import Bucket # type: ignore
from src.app.services.logging.logger import initializeLogger

import os

from datetime import timedelta

class CouchbaseAdapter(DatabaseAdapter):
    """Couchbase adapter"""
    def __init__(self):
        super().__init__("Couchbase")
        self.__logger = initializeLogger(__name__)

        self.host ="10.64.116.67:11210"
        self.username = "user1"
        self.password = "user123"
        self.bucket_name = 'Recon'  #'UTSDW'
        self.scope_name='RECONCILIATION' #'UTSODS'
        if not self.connect():
            raise Exception("Error connecting to Couchbase")

        self.bucket : Bucket  = self.cluster.bucket(self.bucket_name)  # type: ignore
        self.scope = self.bucket.scope(self.scope_name)

    def connect(self) -> bool:
        try:
            self.__logger.info("Connecting to Couchbase")
            auth : PasswordAuthenticator = PasswordAuthenticator(self.username, self.password)
            self.cluster : Cluster = Cluster.connect('couchbase://'+self.host, ClusterOptions(auth)) # type: ignore
            # self.cluster.wait_until_ready(timedelta(seconds=5))
            self.__logger.info("Connected to Couchbase")
            return True
        except Exception as e:
            self.__logger.error("Error connecting to Couchbase: " + str(e))
            return False

    def disconnect(self):
        """Disconnect from Couchbase"""
        # self.cluster.disconnect()
        self.__logger.info("Disconnected from Couchbase")

    def getLocationID(self, locationCode: str) -> int | None:
        """Get location ID"""
        # select raw loc_id from static.general_scope.location where loc_code = 'NDLS'
        query = f"SELECT RAW loc_id FROM static.general_scope.location WHERE loc_code='{locationCode}'"
        result = self.query(query)
        if result:
            return int(list(result)[0]) # type: ignore
        return None

    def query(self, query: str) -> QueryResult | None:
        """Query Couchbase"""
        self.__logger.info("Executing query: " + query)
        try:
            result = self.cluster.query(query)
            # self.__logger.info("result" + list(result)) 
            if result:
                return result
            raise Exception("Error executing query")
        except Exception as e:
         self.__logger.error("Error executing query: " + str(e))
         return None
        
    def analytics_query(self, query: str):
        """Query Couchbase"""
        self.__logger.info("Executing query: " + query)
        try:
            result = self.cluster.query(query)
          
            if result:
                return result
            raise Exception("Error executing query")
        except Exception as e:
            self.__logger.error("Error executing query: " + str(e))
            return None
    
    # destructor
    def __del__(self):
        self.disconnect()
# /workspaces/uts-reports/src/app/services/database/Couchbase/CouchbaseAdapter.py
# python3 -m src.app.services.database.Couchbase.CouchbaseAdapter
if __name__ == '__main__':
    couchbaseAdapter = CouchbaseAdapter()
    # print(couchbaseAdapter.getLocationID('NDLS'))
    couchbaseAdapter.disconnect()
    print("Disconnected from Couchbase")
