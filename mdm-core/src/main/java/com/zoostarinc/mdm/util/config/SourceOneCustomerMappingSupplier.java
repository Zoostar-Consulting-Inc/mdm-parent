package com.zoostarinc.mdm.util.config;

import java.util.function.Supplier;

import com.zoostarinc.mdm.model.MasterData;
import com.zoostarinc.mdm.model.MasterDataKey;
import com.zoostarinc.mdm.model.client.SourceOneCustomer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SourceOneCustomerMappingSupplier implements Supplier<MasterData> {

	public static final String CLIENT_ID = "sourceone";

	public static final String TYPE = "customer";

	private final SourceOneCustomer data;

	@Override
	public MasterData get() {
		log.info("Applying Master Data mappings to: {}...", data);
		var key = new MasterDataKey(CLIENT_ID, TYPE, data.getId());
		var entity = new MasterData();
		entity.setKey(key);
		entity.getStringAttributes().put("Name", data.getName());
		return entity;
	}

}
