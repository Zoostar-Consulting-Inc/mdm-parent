package com.zoostarinc.mdm.service.impl;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zoostarinc.mdm.model.MasterDataUUID;
import com.zoostarinc.mdm.service.ClientDataService;
import com.zoostarinc.mdm.service.MasterDataService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultMasterDataService implements MasterDataService {

	private final ClientDataService clientDataManager;

	@Override
	public ResponseEntity<MasterDataUUID> update(Supplier<MasterDataUUID> supplier) {
		var request = supplier.get();
		MasterDataUUID response = null;
		try {
			response = clientDataManager.retrieve(request.getClientId(), request.getType(), request.getSourceId());
			if (response != null) {
				log.info("Retrieved from client: {}", response);
			} else {
				log.warn("{}", "Response still needs to be implemented");
			}
		} catch (NullPointerException e) {
			// Delete server record
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
