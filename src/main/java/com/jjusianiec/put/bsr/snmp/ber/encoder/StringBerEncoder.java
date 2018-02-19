package com.jjusianiec.put.bsr.snmp.ber.encoder;

import com.google.common.primitives.Bytes;
import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import com.jjusianiec.put.bsr.snmp.ber.util.HexStringToByteArray;

public class StringBerEncoder {

	private final LengthToEncodedLength lengthToEncodedLength = new LengthToEncodedLength();
	private final TagBerEncoder tagBerEncoder = new TagBerEncoder();

	public byte[] encode(BerData input) {
		return Bytes.concat(tagBerEncoder.getTag(input, input.getValue().length() / 2),
				lengthToEncodedLength.mapToEncodedLength(input.getValue().length() / 2),
				HexStringToByteArray.apply(input.getValue()));
	}
}
