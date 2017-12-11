package com.jjusianiec.put.bsr.snmp.ber.encoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;

class IntegerBerEncoder implements Encoder {
	public byte[] encode(BerEncodeInput input) {
		Integer value = validateValueAndReturnOrThrowException(input);

		return new byte[0];
	}

	private Integer validateValueAndReturnOrThrowException(BerEncodeInput input) {
		Integer value = Integer.valueOf(input.getValue());
		if (input.getValueRange() == null || input.getValueRange().isValidIntValue(value)) {
			return value;
		} else {
			throw new IllegalArgumentException("Input value out of range");
		}
	}
}
