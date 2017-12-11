package com.jjusianiec.put.bsr.snmp.ber.encoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;

public class BerEncoder {

	private final NullBerEncoder nullBerEncoder = new NullBerEncoder();
	private final IntegerBerEncoder integerBerEncoder = new IntegerBerEncoder();
	private final StringBerEncoder stringBerEncoder = new StringBerEncoder();
	private final ObjectIdentifierEncoder objectIdentifierEncoder = new ObjectIdentifierEncoder();
	private final SequenceBerEncoder sequenceBerEncoder = new SequenceBerEncoder();

	public byte[] encode(BerEncodeInput input) {
		if (input.getValue() == null) {
			return nullBerEncoder.encode();
		}
		validateInputOrThrowRuntimeException(input);
		switch (input.getDataType()) {
		case INTEGER:
			return integerBerEncoder.encode(input);
		case OCTET_STRING:
			return stringBerEncoder.encode(input);
		case OBJECT_IDENTIFIER:
			return objectIdentifierEncoder.encode(input);
		case NULL:
			return nullBerEncoder.encode();
		case SEQUENCE:
			return sequenceBerEncoder.encode(input);
		}
		return null;
	}

	private static void validateInputOrThrowRuntimeException(BerEncodeInput input) {
		if ((input.getValue() == null && input.getValues() == null)
				|| input.getDataType() == null) {
			throw new RuntimeException("Null value(s) or datatype");
		}
	}
}
