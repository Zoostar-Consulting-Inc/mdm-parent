package com.zoostarinc.mdm.util.config;

import java.util.function.Supplier;

import com.zoostarinc.mdm.model.MasterDataUUID;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractMasterDataSupplier implements Supplier<MasterDataUUID> {

}
