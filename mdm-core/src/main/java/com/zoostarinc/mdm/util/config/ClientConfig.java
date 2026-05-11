package com.zoostarinc.mdm.util.config;

import java.util.function.Function;

import com.zoostarinc.mdm.model.MasterDataUUID;
import com.zoostarinc.mdm.model.client.ClientData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public abstract class ClientConfig<T extends ClientData> implements Function<String, MasterDataUUID> {
	
	private final Class<T> clientDataType;
	
}
