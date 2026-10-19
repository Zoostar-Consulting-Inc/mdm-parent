package com.zoostarinc.mdm.dao.repository.impl;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.zoostarinc.mdm.dao.entity.MasterDataEntity;
import com.zoostarinc.mdm.dao.repository.MasterDataRepository;
import com.zoostarinc.mdm.model.MasterDataKey;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DefaultMasterDataRepository implements MasterDataRepository {

	private final Map<UUID, MasterDataEntity> recordsById = new ConcurrentHashMap<>();
	private final Map<MasterDataKey, MasterDataEntity> recordsByKey = new ConcurrentHashMap<>();
	
	@Override
	public <T extends MasterDataEntity> T save(T data) {
		if(data == null || data.getKey() == null) {
			throw new IllegalArgumentException("Data object may not be empty!");
		}
		
		var entity = recordsByKey.put(data.getKey(), data);
		if(entity == null) {
			log.info("Creating new entity {}...", data);
			data.setId(UUID.randomUUID());
		} else {
			log.info("Updating existing entity {}...", data);
		}

		recordsById.put(data.getId(), data);
		return data;
	}

	@Override
	public Optional<MasterDataEntity> findByKey(MasterDataKey key) {
		return Optional.ofNullable(recordsByKey.get(key));
	}

	@Override
	public void deleteById(UUID id) {
		var entity = recordsById.remove(id);
		if(entity == null) {
			throw new EmptyResultDataAccessException("No record found for Id: " + id, 1);
		}
		log.info("Deleted entity: {}.", entity);
		recordsByKey.remove(entity.getKey());
	}
	
}
