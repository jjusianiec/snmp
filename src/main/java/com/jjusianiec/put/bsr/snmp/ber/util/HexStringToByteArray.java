package com.jjusianiec.put.bsr.snmp.ber.util;

public class HexStringToByteArray {
	public static byte[] apply(String input) {
		String inputNoWhiteChars = input.replaceAll("\\s*", "");
		byte[] b = new byte[inputNoWhiteChars.length() / 2];
		for (int i = 0; i < b.length; i++) {
			int index = i * 2;
			int v = Integer.parseInt(inputNoWhiteChars.substring(index, index + 2), 16);
			b[i] = (byte) v;
		}
		return b;
	}
}
