package com.zoostarinc.mdm.service.impl;

import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.zoostarinc.mdm.model.MasterData;
import com.zoostarinc.mdm.service.ClientDataService;
import com.zoostarinc.mdm.service.MasterDataService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MasterDataUUIDService implements MasterDataService<UUID> {

	private final ClientDataService<UUID> clientDataManager;

	@Override
	public MasterData<UUID> update(Supplier<MasterData<UUID>> supplier) {
		var request = supplier.get();
		MasterData<UUID> response = null;
		try {
			response = clientDataManager.retrieve(request);
			if (response != null) {
				log.info("Retrieved from client: {}", response);
			} else {
				log.warn("{}", "Response still needs to be implemented");
			}
		} catch (NullPointerException e) {
			// Delete server record
		}
		return response;
	}

}
