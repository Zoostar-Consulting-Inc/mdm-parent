package com.zoostarinc.mdm.service.impl;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.zoostarinc.mdm.dao.entity.MasterDataEntity;
import com.zoostarinc.mdm.dao.repository.MasterDataRepository;
import com.zoostarinc.mdm.model.MasterData;
import com.zoostarinc.mdm.service.ClientDataService;
import com.zoostarinc.mdm.service.MasterDataService;
import com.zoostarinc.mdm.util.config.DataUpdateResponseEntityApplier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultMasterDataService implements MasterDataService {

	private final ClientDataService clientDataManager;

	private final MasterDataRepository masterDataRepository;

	@Override
	public ResponseEntity<MasterData> update(Supplier<MasterData> supplier) {
		var request = supplier.get();
		var hasEntity = masterDataRepository.findByKey(request.getKey());

		MasterData data = null;
		try {
			data = clientDataManager.retrieve(request);
			log.info("Retrieved data from client: {}", data);
			MasterDataEntity entity = null;
			if (hasEntity.isEmpty()) {
				entity = new MasterDataEntity();
				entity.setKey(data.getKey());
			} else {
				entity = hasEntity.get();
			}
			
			for(var entry : data.getStringAttributes().entrySet()) {
				entity.getStringAttributes().put(entry.getKey(), entry.getValue());
			}
			return new DataUpdateResponseEntityApplier<>(hasEntity, null).apply(Optional.of(masterDataRepository.save(entity)));
		} catch (HttpClientErrorException e) {
			return new DataUpdateResponseEntityApplier<>(hasEntity, e).apply(Optional.empty());
		}
	}

}
