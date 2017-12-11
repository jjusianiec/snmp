package com.jjusianiec.put.bsr.snmp.ber.encoder;

import java.math.BigInteger;

import com.google.common.primitives.Bytes;

public class LengthToEncodedLength {
	public byte[] mapToEncodedLength(int length) {
		if (length < 128) {
			return new byte[] { (byte) length };
		} else {
			byte lengthByteCount = getCountOfByteInLength(length);
			return Bytes.concat(new byte[] { lengthByteCount },
					BigInteger.valueOf(length).toByteArray());
		}
	}

	private byte getCountOfByteInLength(int length) {
		byte ceil = (byte)((int)(Math.ceil(Math.log(length) / Math.log(2)) / 8) + 1);
		ceil |= 0x80;
		return ceil;
	}
}
