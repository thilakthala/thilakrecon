package com.cris.recon.repository;

import com.cris.recon.document.BankTransactionRefundDetail;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface PaymentRefundReposiory extends CouchbaseRepository <BankTransactionRefundDetail,String>{
}
