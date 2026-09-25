package com.zoostarinc.mdm.util.config;

import com.zoostarinc.mdm.model.MasterDataUUID;
import com.zoostarinc.mdm.model.client.SourceOneCustomer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SourceOneCustomerMasterDataSupplier extends AbstractMasterDataSupplier {

	public static final String CLIENT_ID = "sourceone";

	public static final String TYPE = "customer";
	
	private final SourceOneCustomer data;
	
	@Override
	public MasterDataUUID get() {
		MasterDataUUID masterDataUUID = new MasterDataUUID();
		masterDataUUID.setClientId(CLIENT_ID);
		masterDataUUID.setSourceId(data.getId());
		masterDataUUID.setType(TYPE);
		masterDataUUID.setTitle(data.getName());
		return masterDataUUID;
	}

}
