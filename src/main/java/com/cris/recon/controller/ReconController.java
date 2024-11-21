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

import com.cris.recon.document.CPGUPIReconDetail;
import com.cris.recon.repository.CPGUPIReconDetailRepo;

@RestController
@RequestMapping("/recon")
public class ReconController {
	
	@Autowired
	CPGUPIReconDetailRepo cpgReconRepo;
	
	@PostMapping("/saveReconData")
	public CPGUPIReconDetail saveToReconDetail(@RequestBody CPGUPIReconDetail entity)
	{
		System.out.println(entity.getCPGUPIReconDetail_id());
		System.out.println(entity.toString());

		System.out.println("In save method USING ID1");
		cpgReconRepo.save(entity);
		return entity;
			
	}
	
	@GetMapping("/health")
	public String getAllData()
	{
		
		return "health status OK!!!";
			
	}
	
	@GetMapping("/id/{id}")
	public Optional<CPGUPIReconDetail> getbyId(@PathVariable String id)
	{
		System.out.println(id);
		return cpgReconRepo.findById(id);
			
	}
	@DeleteMapping("/deletebyId/{id}")
	public String updateBy(@PathVariable String id)
	{
		System.out.println(id);
		cpgReconRepo.deleteById(id);
	 
		return id;
			
	}
	
	
	
	
	

}
