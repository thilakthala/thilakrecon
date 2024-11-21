package com.cris.recon.controller;

import com.cris.recon.document.BankTransactionRefundDetail;
import com.cris.recon.repository.PaymentConfirmationRepository;
import com.cris.recon.repository.PaymentRefundReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/recon2")
public class BankTransactionRefundDetailsController
{
@Autowired
PaymentRefundReposiory paymentRefundReposiory;
@PostMapping("/savedata2")
    public BankTransactionRefundDetail saveToReconDetail2(@RequestBody BankTransactionRefundDetail entity)
    {
    System.out.println(entity.getTxnRefId());
    System.out.println(entity.toString());

    System.out.println("Save the Data");
        paymentRefundReposiory.save(entity);
    return entity;
    }
    @GetMapping("/heath1")
    public String getAllData()
    {
    return "Health is fine";
    }
    @GetMapping("/txnRefId/{id}")
    public Optional<BankTransactionRefundDetail> getbyTxnRefId(@PathVariable String id)
    {
        System.out.println(id);
        return paymentRefundReposiory.findById(id);

    }
}
