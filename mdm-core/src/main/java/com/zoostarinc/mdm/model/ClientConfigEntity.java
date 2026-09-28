package com.zoostarinc.mdm.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientConfigEntity {

	@EqualsAndHashCode.Include
	private String clientId;
	
	@EqualsAndHashCode.Include
	private String type;
	
	private String baseUrl;
	
}
