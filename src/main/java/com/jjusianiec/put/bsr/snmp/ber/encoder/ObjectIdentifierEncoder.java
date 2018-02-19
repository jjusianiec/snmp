package com.jjusianiec.put.bsr.snmp.ber.encoder;

import java.math.BigInteger;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.primitives.Bytes;
import com.jjusianiec.put.bsr.snmp.ber.model.BerData;

import static java.util.stream.Collectors.toList;

public class ObjectIdentifierEncoder {

	public byte[] encode(BerData input) {
		String[] values = input.getValue().split("\\.");
		Integer initialValue = 40 * Integer.valueOf(values[0]) + Integer.valueOf(values[1]);
		values[1] = initialValue.toString();
		Byte[] bytes = Stream.of(values).skip(1).map(s -> new BigInteger(s).toByteArray())
				.flatMap(s -> Bytes.asList(s).stream()).collect(toList()).toArray(new Byte[0]);
		return Bytes.concat(new byte[] { 0x06, (byte) (values.length - 1) },
				ArrayUtils.toPrimitive(bytes));
	}
}
