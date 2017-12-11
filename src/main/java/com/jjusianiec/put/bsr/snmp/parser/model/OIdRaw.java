package com.jjusianiec.put.bsr.snmp.parser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.jjusianiec.put.bsr.snmp.parser.OIdType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OIdRaw {
	private String parentIdRaw;
	private String syntax;
	private String access;
	private String status;
	private String description;
	private String index;
	private OIdType type;
}
