from src.app.services.database.Couchbase.CouchbaseAdapter import CouchbaseAdapter

class Database:
    # Database class is a wrapper for the database that is being used
    def __init__(self):
        self.adapter = CouchbaseAdapter()
        self.adapter.connect()
    
    def query(self, query: str):
        return self.adapter.query(query)
    
    def disconnect(self):
        self.adapter.disconnect()

    def __del__(self):
        self.disconnect()