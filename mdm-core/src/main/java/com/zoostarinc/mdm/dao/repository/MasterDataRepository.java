package com.zoostarinc.mdm.dao.repository;

import java.util.Optional;
import java.util.UUID;

import com.zoostarinc.mdm.dao.entity.MasterDataEntity;
import com.zoostarinc.mdm.model.MasterDataKey;

public interface MasterDataRepository {

	<T extends MasterDataEntity> T save(T data);

	Optional<MasterDataEntity> findByKey(MasterDataKey key);

	void deleteById(UUID id);

}
