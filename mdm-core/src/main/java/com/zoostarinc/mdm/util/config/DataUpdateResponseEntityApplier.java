package com.zoostarinc.mdm.util.config;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.zoostarinc.mdm.dao.entity.MasterDataEntity;
import com.zoostarinc.mdm.model.MasterData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataUpdateResponseEntityApplier<T extends MasterDataEntity> implements Function<Optional<T>, ResponseEntity<MasterData>> {

	private final Optional<MasterDataEntity> hasEntity;
	
	private final HttpClientErrorException exception;
	
	@Override
	public ResponseEntity<MasterData> apply(Optional<T> hasData) {
		if(hasEntity.isEmpty()) {
			if(hasData.isEmpty()) {
				throw exception;
			} else {
				return new ResponseEntity<>(hasData.get(), HttpStatus.CREATED);
			}
		} else {
			if(hasData.isEmpty()) {
				return new ResponseEntity<>(hasEntity.get(), HttpStatus.NO_CONTENT);
			} else {
				return ResponseEntity.ok(hasData.get());
			}
		}
	}

}
