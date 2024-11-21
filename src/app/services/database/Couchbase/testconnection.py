from couchbase.cluster import Cluster, ClusterOptions
from couchbase.auth import PasswordAuthenticator
from couchbase.options import ClusterTimeoutOptions
from datetime import timedelta

try:
    auth = PasswordAuthenticator("user1", "user123")
    cluster = Cluster.connect(
        "couchbase://10.64.116.67:11210",
        ClusterOptions(auth, timeout_options=ClusterTimeoutOptions(kv_timeout=timedelta(seconds=5)))
    )
    cluster.wait_until_ready(timedelta(seconds=10))
    print("Connection successful!")
except Exception as e:
    print(f"Connection failed: {e}")
