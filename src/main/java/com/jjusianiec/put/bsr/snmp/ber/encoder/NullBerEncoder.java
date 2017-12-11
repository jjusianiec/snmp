package com.jjusianiec.put.bsr.snmp.ber.encoder;

public class NullBerEncoder {
	public byte[] encode() {
		return new byte[] { 0x05, 0x00 };
	}
}
