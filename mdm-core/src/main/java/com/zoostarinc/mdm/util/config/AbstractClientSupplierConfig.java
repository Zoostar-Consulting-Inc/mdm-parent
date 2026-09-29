package com.zoostarinc.mdm.util.config;

import java.util.Objects;
import java.util.function.Function;

import org.springframework.web.client.RestClient;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public abstract class AbstractClientSupplierConfig implements Function<String, AbstractMasterDataSupplier> {

	private final String clientId;
	
	private final String type;

	private final String baseUrl;

	private final RestClient restClient;
	
	@Override
	public int hashCode() {
		return Objects.hash(clientId, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AbstractClientSupplierConfig)) {
			return false;
		}
		AbstractClientSupplierConfig other = (AbstractClientSupplierConfig) obj;
		return Objects.equals(clientId, other.clientId) && Objects.equals(type, other.type);
	}
	
}
