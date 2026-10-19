package com.zoostarinc.mdm.dao.entity;

import java.util.Objects;
import java.util.UUID;

import com.zoostarinc.mdm.model.MasterData;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class MasterDataEntity extends MasterData {

	private UUID id;
	
	public MasterDataEntity() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getKey());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MasterDataEntity)) {
			return false;
		}
		MasterDataEntity that = (MasterDataEntity) obj;
		return Objects.equals(getKey(), that.getKey());
	}
	
}
