package com.zoostarinc.mdm.api.controller;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoostarinc.mdm.model.MasterData;
import com.zoostarinc.mdm.service.MasterDataService;
import com.zoostarinc.mdm.util.function.DataUpdateRequestSupplier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class MasterDataRestController {

	private final MasterDataService masterDataManager;

	@GetMapping(path = "/{source}/{type}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MasterData<UUID>> update(@PathVariable String source, @PathVariable String type,
			@PathVariable String id) {
		return ResponseEntity.ok(masterDataManager.update(new DataUpdateRequestSupplier(source, type, id)));
	}

}
