package com.jjusianiec.put.bsr.snmp.ber.encoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;

import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.APPLICATION;
import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.CONTEXT_SPECIFIC;
import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.UNIVERSAL;
import static com.jjusianiec.put.bsr.snmp.ber.model.DeclarationVisibility.IMPLICIT;

public class BarEncodeInputToTagWithoutTypeId {

	public byte getTag(BerData input) {
		if (UNIVERSAL.equals(input.getClassType())) {
			return (byte) input.getDataType().getValue();
		} else if (APPLICATION.equals(input.getClassType())) {
			return getApplicationTag(input);
		} else if (CONTEXT_SPECIFIC.equals(input.getClassType()) || input.getClassType() == null) {
			return getContextSpecificTag(input);
		} else {
			throw new RuntimeException("Unknown class type");
		}
	}

	private byte getContextSpecificTag(BerData input) {
		if (IMPLICIT.equals(input.getDeclarationVisibility())
				|| input.getDeclarationVisibility() == null) {
			return (byte) 0x80;
		} else {
			return (byte) 0xA0;
		}
	}

	private byte getApplicationTag(BerData input) {
		if (IMPLICIT.equals(input.getDeclarationVisibility())
				|| input.getDeclarationVisibility() == null) {
			return 0x40;
		} else {
			return 0x65;
		}
	}
}
