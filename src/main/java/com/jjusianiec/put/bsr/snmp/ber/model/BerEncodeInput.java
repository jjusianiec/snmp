package com.jjusianiec.put.bsr.snmp.ber.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.temporal.ValueRange;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BerEncodeInput {
	private String name;
	private String value;
	private DataType dataType;
	private Integer typeId;
	private ClassType classType;
	private DeclarationVisibility declarationVisibility;
	private ValueRange valueRange;
}
