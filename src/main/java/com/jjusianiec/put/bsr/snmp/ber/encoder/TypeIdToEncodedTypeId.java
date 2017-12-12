package com.jjusianiec.put.bsr.snmp.ber.encoder;

import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.primitives.Bytes;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ArrayUtils.toPrimitive;

public class TypeIdToEncodedTypeId {
	public byte[] getEncodedTypeId(Integer input) {
		if (input < 31) {
			return new byte[] { input.byteValue() };
		} else {
			byte[] bytes = ByteBuffer.allocate(4).putInt(input).array();
			BitSet bitSet = BitSet.valueOf(bytes);
			List<Byte> berTypeIdByteList = newArrayList();
			for (int i = bitSet.length() - 1; i > 0; i -= 7) {
				int start = i - 8 >= 0 ? i - 8 : 0;
				byte[] bytes1 = bitSet.get(start, i - 1).toByteArray();
				if (bytes1.length > 0) {
					berTypeIdByteList.add(bytes1[0]);
				}
			}
			for (int i = 1; i < berTypeIdByteList.size(); i++) {
				berTypeIdByteList.set(i, (byte) (berTypeIdByteList.get(i) | (1 << 7)));
			}

			return Bytes.concat(new byte[] { 0x1F },
					ArrayUtils.toPrimitive(berTypeIdByteList.toArray(new Byte[0])));
		}
	}
}

// 1101 1011 1010 1111