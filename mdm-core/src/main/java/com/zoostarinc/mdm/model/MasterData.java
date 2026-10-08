package com.zoostarinc.mdm.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MasterData {

	private MasterDataKey key;
	
	private Map<String, String> stringAttributes;
	
	public MasterData() {
		this.stringAttributes = new HashMap<>();
	}
	
}
