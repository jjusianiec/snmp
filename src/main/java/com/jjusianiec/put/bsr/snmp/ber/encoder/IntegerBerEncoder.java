package com.jjusianiec.put.bsr.snmp.ber.encoder;

import java.math.BigInteger;

import com.google.common.primitives.Bytes;
import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;
import com.jjusianiec.put.bsr.snmp.ber.model.ValueRange;

class IntegerBerEncoder implements Encoder {

	private final TagBerEncoder tagBerEncoder = new TagBerEncoder();

	public byte[] encode(BerEncodeInput input) {
		byte[] bytes = validateValueAndReturnOrThrowException(input);
		return Bytes.concat(tagBerEncoder.getTag(input, bytes.length), new byte[] { (byte) bytes.length }, bytes);
	}

	private byte[] validateValueAndReturnOrThrowException(BerEncodeInput input) {
		BigInteger value = new BigInteger(input.getValue());
		if (input.getValueRange() == null || isBigIntegerInRange(value, input.getValueRange())) {
			return value.toByteArray();
		} else {
			throw new IllegalArgumentException("Input value out of range");
		}
	}

	private boolean isBigIntegerInRange(BigInteger value, ValueRange valueRange) {
		return value.compareTo(new BigInteger(valueRange.getMinimum())) > 0
				&& value.compareTo(new BigInteger(valueRange.getMaximum())) < 0;
	}
}
