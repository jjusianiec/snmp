package com.jjusianiec.put.bsr.snmp.ber;

public class NullBerEncoder {
	public static byte[] encode() {
		return new byte[] { 0x05, 0x00 };
	}
}
