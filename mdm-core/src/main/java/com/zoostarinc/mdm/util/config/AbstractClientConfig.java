package com.zoostarinc.mdm.util.config;

import java.util.Objects;
import java.util.function.Function;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public abstract class AbstractClientConfig implements Function<String, AbstractMasterDataSupplier> {

	private final String clientId;
	
	private final String type;

	private final String baseUrl;
	
	@Override
	public int hashCode() {
		return Objects.hash(clientId, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AbstractClientConfig)) {
			return false;
		}
		AbstractClientConfig other = (AbstractClientConfig) obj;
		return Objects.equals(clientId, other.clientId) && Objects.equals(type, other.type);
	}
	
}
