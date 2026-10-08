package com.zoostarinc.mdm.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoostarinc.mdm.model.client.SourceOneCustomer;
import com.zoostarinc.mdm.util.config.SourceOneCustomerSupplier;

@RestController
@RequestMapping(path = "/sourceone")
public class ClientDataRestController {

	@GetMapping(path = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SourceOneCustomer> getClientData(@PathVariable String id) {
		return ResponseEntity.ok(new SourceOneCustomerSupplier(id).get());
	}
}
