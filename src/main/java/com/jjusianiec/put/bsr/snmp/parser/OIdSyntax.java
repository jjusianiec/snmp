package com.jjusianiec.put.bsr.snmp.parser;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OIdSyntax {
	private String type;
	private Map<String, Object> availableValues;
}
