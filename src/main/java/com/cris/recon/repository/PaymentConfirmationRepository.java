package com.cris.recon.repository;


import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.cris.recon.document.BankTransactionPaymentDetail;

public interface PaymentConfirmationRepository extends CouchbaseRepository <BankTransactionPaymentDetail,String>{

}
