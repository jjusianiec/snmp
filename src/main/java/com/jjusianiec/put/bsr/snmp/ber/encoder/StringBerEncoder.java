package com.jjusianiec.put.bsr.snmp.ber.encoder;

import com.google.common.primitives.Bytes;
import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;
import com.jjusianiec.put.bsr.snmp.ber.util.HexStringToByteArray;

public class StringBerEncoder {

	private final LengthToEncodedLength lengthToEncodedLength = new LengthToEncodedLength();

	public byte[] encode(BerEncodeInput input) {
		return Bytes.concat(new byte[] { 0x04 },
				lengthToEncodedLength.mapToEncodedLength(input.getValue().length() / 2),
				HexStringToByteArray.apply(input.getValue()));
	}
}
