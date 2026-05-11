package com.zoostarinc.mdm.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoostarinc.mdm.model.MasterDataUUID;
import com.zoostarinc.mdm.service.MasterDataService;
import com.zoostarinc.mdm.util.function.DataUpdateValidatorUUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class MasterDataRestController {

	private final MasterDataService masterDataManager;

	@GetMapping(path = "/{source}/{type}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MasterDataUUID> update(@PathVariable String source, @PathVariable String type,
			@PathVariable String id) {
		return masterDataManager.update(new DataUpdateValidatorUUID(source, type, id));
	}

}
