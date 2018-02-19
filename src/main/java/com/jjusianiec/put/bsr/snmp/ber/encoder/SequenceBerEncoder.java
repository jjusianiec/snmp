package com.jjusianiec.put.bsr.snmp.ber.encoder;

import java.util.List;

import com.google.common.primitives.Bytes;
import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import com.jjusianiec.put.bsr.snmp.ber.model.ClassType;

import static com.google.common.collect.Lists.newArrayList;

public class SequenceBerEncoder {

	private final TagBerEncoder tagBerEncoder = new TagBerEncoder();
	private final NullBerEncoder nullBerEncoder = new NullBerEncoder();
	private final IntegerBerEncoder integerBerEncoder = new IntegerBerEncoder();
	private final StringBerEncoder stringBerEncoder = new StringBerEncoder();

	public byte[] encode(BerData input) {
		List<byte[]> toReturn = newArrayList();
		Integer typeId = 0;
		for (BerData berData : input.getValues()) {
			berData.setClassType(ClassType.CONTEXT_SPECIFIC);
			berData.setTypeId(typeId++);
			switch (berData.getDataType()) {
			case INTEGER:
				toReturn.add(integerBerEncoder.encode(berData));
				break;
			case OCTET_STRING:
				toReturn.add(stringBerEncoder.encode(berData));
				break;
			case NULL:
				toReturn.add(nullBerEncoder.encode());
				break;
			}
		}
		byte[] tag = tagBerEncoder.getTag(input, getLength(toReturn));
		tag[0] = (byte)(tag[0] | 1 << 5);
		for (byte[] bytes : toReturn) {
			tag = Bytes.concat(tag, bytes);
		}

		return tag;
	}

	private Integer getLength(List<byte[]> toReturn) {
		return toReturn.stream().mapToInt(c -> c.length).sum();
	}
}
