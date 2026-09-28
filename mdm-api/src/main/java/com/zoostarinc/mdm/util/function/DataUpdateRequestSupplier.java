package com.zoostarinc.mdm.util.function;

import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.util.StringUtils;

import com.zoostarinc.mdm.model.MasterData;
import com.zoostarinc.mdm.model.MasterDataUUID;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@RequiredArgsConstructor
public class DataUpdateRequestSupplier implements Supplier<MasterData<UUID>> {

	private final String source;

	private final String type;

	private final String id;
	
	@Override
	public MasterData<UUID> get() {
		if(!StringUtils.hasText(source)) {
			throw new IllegalArgumentException("Source is required!");
		}

		if(!StringUtils.hasText(type)) {
			throw new IllegalArgumentException("Type is required!");
		}
		
		if(!StringUtils.hasText(id)) {
			throw new IllegalArgumentException("Id is required!");
		}

		var data = new MasterDataUUID();
		data.setClientId(source.toUpperCase());
		data.setSourceId(id);
		data.setType(type.toUpperCase());

		log.info("Update request received from {} for {} with id: {}...", source, type, id);
		return data;
	}

}
