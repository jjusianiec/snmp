package com.jjusianiec.put.bsr.snmp.ber.encoder;

public class TypeIdToEncodedTypeId {
	public byte[] getEncodedTypeId(int i) {
		if (i < 31) {
			return new byte[] { (byte) i };
		} else {
			
			return null;
		}
	}
}
