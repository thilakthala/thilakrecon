package com.cris.recon.config;

import java.time.Duration;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.couchbase.client.java.env.ClusterEnvironment;
import com.couchbase.client.java.transactions.config.TransactionsConfig;


@Configuration
@EnableTransactionManagement
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {
	@Value("${spring.couchbase.username}")
	private String user;
	@Value("${spring.couchbase.password}")
	private String password;
	@Value("${spring.data.couchbase.bucket-name}")
	private String bucket;
	@Value("${spring.couchbase.connection-string}")
	private String connectionString;

	// Usual Setup
	@Override
	public String getConnectionString() {
		return connectionString;
	}

	@Override
	public String getUserName() {
		return user;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getBucketName() {
		return bucket;
	}

	// Customization of transaction behavior is via the configureEnvironment()
	// method
	@Override
	protected void configureEnvironment(final ClusterEnvironment.Builder builder) {
		builder.transactionsConfig(TransactionsConfig.builder().timeout(Duration.ofSeconds(30)));
	}

}
