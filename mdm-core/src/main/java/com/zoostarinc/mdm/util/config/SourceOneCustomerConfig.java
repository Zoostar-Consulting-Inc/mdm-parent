package com.zoostarinc.mdm.util.config;

import java.util.HashMap;

import com.zoostarinc.mdm.model.MasterDataUUID;
import com.zoostarinc.mdm.model.client.SourceOneCustomer;

import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SourceOneCustomerConfig extends ClientConfig<SourceOneCustomer> {

	public static final String BASE_URL = "http://localhost:8080";

	public static final String CLIENT_ID = "sourceone";

	public static final String TYPE = "customer";

	public SourceOneCustomerConfig() {
		super(SourceOneCustomer.class);
	}

	@SuppressWarnings("unused")
	@Override
	public MasterDataUUID apply(String sourceId) {
		StringBuilder url = new StringBuilder(BASE_URL).append("/").append(CLIENT_ID).append("/").append(TYPE)
				.append("/").append(sourceId);
		log.info("This is where we would call url: {}", url.toString());

		// Data response from client
		SourceOneCustomer response = new SourceOneCustomer();
		response.setId(sourceId);
		response.setName("ClientDataWithId" + sourceId);
		
		if(response == null) {
			// Possible if deleted from Client system
			throw new NoResultException(String.format("No data found for sourceId %s", sourceId));
		}

		MasterDataUUID data = new MasterDataUUID();
		data.setClientId(CLIENT_ID);
		data.setType(TYPE);
		data.setSourceId(response.getId());
		var stringAttributes = new HashMap<String, String>();
		data.setStringAttributes(stringAttributes);
		stringAttributes.put("Name", response.getName());
		return data;
	}

}
