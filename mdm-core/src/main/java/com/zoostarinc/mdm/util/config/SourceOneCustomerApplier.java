package com.zoostarinc.mdm.util.config;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import com.zoostarinc.mdm.model.MasterData;
import com.zoostarinc.mdm.model.client.SourceOneCustomer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SourceOneCustomerApplier implements Function<String, Supplier<MasterData>> {

	public static final String CLIENT_ID = "sourceone";

	public static final String CLIENT_TYPE = "customer";

	private static final String BASE_URL = "http://localhost:8080/sourceone/customer/{id}";

	private final RestClient restClient;

	@Override
	public SourceOneCustomerMappingSupplier apply(String sourceId) {
		log.info("Applying: {}...", this);
		var responseEntity = restClient.get().uri(BASE_URL, sourceId).accept(MediaType.APPLICATION_JSON).retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
					if (response.getClass().equals(EmptyResultDataAccessException.class)) {
						throw new EmptyResultDataAccessException(response.getStatusText(), 1);
					}
				});

		return new SourceOneCustomerMappingSupplier(responseEntity.body(SourceOneCustomer.class));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SourceOneCustomerApplier [clientId=").append(CLIENT_ID).append(", type=").append(CLIENT_TYPE)
				.append(", baseurl=").append(BASE_URL).append("]");
		return builder.toString();
	}

}
