package com.jjusianiec.put.bsr.snmp.ber.util;

public class ByteArrayToHexString {
	public static String apply(byte[] input) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : input) {
			stringBuilder.append(Character.forDigit((b >> 4) & 0xF, 16));
			stringBuilder.append(Character.forDigit((b & 0xF), 16));
		}
		return stringBuilder.toString().toUpperCase();
	}
}
