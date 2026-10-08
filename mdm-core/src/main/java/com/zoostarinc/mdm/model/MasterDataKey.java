package com.zoostarinc.mdm.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public final class MasterDataKey {

	private final String clientId;
	
	private final String type;
	
	private final String sourceId;
	
}
