package com.zoostarinc.mdm.model.client;

import java.util.HashMap;
import java.util.Map;

import com.zoostarinc.mdm.utils.ClientData;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class SourceOneCustomer implements ClientData {

	private String id;

	private Map<String, String> stringProperties;
	
	public SourceOneCustomer() {
		this(null);
	}
	
	public SourceOneCustomer(String id) {
		this(id, new HashMap<>());
	}

	public SourceOneCustomer(String id, Map<String, String> stringProperties) {
		this.id = id;
		this.stringProperties = stringProperties;
	}

}
