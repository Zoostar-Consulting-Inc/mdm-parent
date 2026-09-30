package com.zoostarinc.mdm.util.config;

import java.util.UUID;
import java.util.function.Supplier;

import com.zoostarinc.mdm.model.MasterData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractMasterDataMappingSupplier implements Supplier<MasterData<UUID>> {

}
