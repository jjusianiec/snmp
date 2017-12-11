package com.jjusianiec.put.bsr.snmp.ber.model;

public enum DataType {
	INTEGER(2), OCTET_STRING(4), OBJECT_IDENTIFIER(6), NULL(5), SEQUENCE(16);

	private final int value;

	DataType(int i) {
		this.value = i;
	}

	public int getValue() {
		return value;
	}
}
