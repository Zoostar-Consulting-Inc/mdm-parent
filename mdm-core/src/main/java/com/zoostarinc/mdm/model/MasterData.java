package com.zoostarinc.mdm.model;

public interface MasterData<I> {
	I getId(); // Preferably UUID
	String getClientId(); // Integrated Client ID
	String getType(); // Type of the Record in Client System e.g. CUSTOMER, PRODUCT, etc.
	String getSourceId(); // ID of the Record in Client System
}
