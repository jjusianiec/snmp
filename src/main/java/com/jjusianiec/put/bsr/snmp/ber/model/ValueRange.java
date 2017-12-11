package com.jjusianiec.put.bsr.snmp.ber.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValueRange {
	private String minimum;
	private String maximum;
}
