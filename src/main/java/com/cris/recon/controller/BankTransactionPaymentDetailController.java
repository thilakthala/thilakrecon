package com.cris.recon.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cris.recon.document.BankTransactionPaymentDetail;
import com.cris.recon.repository.PaymentConfirmationRepository;
@RestController
@RequestMapping("/recon")
public class BankTransactionPaymentDetailController {
	@Autowired
	PaymentConfirmationRepository paymentConfirmationRepo;
	
	@PostMapping("/saveReconData")
	public BankTransactionPaymentDetail saveToReconDetail(@RequestBody BankTransactionPaymentDetail entity)
	{
		System.out.println(entity.getTxnRefId());
		System.out.println(entity.toString());

		System.out.println("In save method USING ID1");
		paymentConfirmationRepo.save(entity);
		return entity;
			
	}
	
	@GetMapping("/health1")
	public String getAllData()
	{
		
		return "health status OK!!!";
			
	}
	
	@GetMapping("/txnRefId/{id}")
	public Optional<BankTransactionPaymentDetail> getbyTxnRefId(@PathVariable String id)
	{
		System.out.println(id);
		return paymentConfirmationRepo.findById(id);
			
	}
	@DeleteMapping("/deletebyId/{id}")
	public String updateBy(@PathVariable String id)
	{
		System.out.println(id);
		paymentConfirmationRepo.deleteById(id);

		return id;

	}
	
	
}
