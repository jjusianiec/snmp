package com.jjusianiec.put.bsr.snmp.ber.encoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;

public class StringBerEncoder {

	private final LengthToEncodedLength lengthToEncodedLength = new LengthToEncodedLength();

	public byte[] encode(BerEncodeInput input) {

		return new byte[0];
	}
}
