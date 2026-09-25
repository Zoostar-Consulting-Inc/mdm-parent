package com.zoostarinc.mdm.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MasterDataUUID implements MasterData<UUID> {

	private UUID id;
	
	private String sourceId;
	
	private String type;
	
	private String clientId;
	
	private String title;
	
}
