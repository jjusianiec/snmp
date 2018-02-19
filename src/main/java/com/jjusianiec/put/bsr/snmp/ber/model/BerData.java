package com.jjusianiec.put.bsr.snmp.ber.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BerData {
	private String name;
	private String value;
	private DataType dataType;
	private Integer typeId;
	private ClassType classType;
	private DeclarationVisibility declarationVisibility;
	private ValueRange valueRange;
	private List<BerData> values;
}
