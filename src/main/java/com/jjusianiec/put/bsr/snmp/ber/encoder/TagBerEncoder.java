package com.jjusianiec.put.bsr.snmp.ber.encoder;

import java.util.Map;

import com.google.common.primitives.Bytes;
import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import com.jjusianiec.put.bsr.snmp.ber.model.ClassType;

import static com.google.common.collect.ImmutableMap.of;
import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.APPLICATION;
import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.CONTEXT_SPECIFIC;
import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.UNIVERSAL;
import static com.jjusianiec.put.bsr.snmp.ber.model.DataType.SEQUENCE;
import static com.jjusianiec.put.bsr.snmp.ber.model.DeclarationVisibility.EXPLICIT;
import static com.jjusianiec.put.bsr.snmp.ber.model.DeclarationVisibility.IMPLICIT;

public class TagBerEncoder {
	private final static Map<ClassType, Byte> CLASS_TYPE_TO_BYTE = of(UNIVERSAL, (byte) 0x00,
			APPLICATION, (byte) 0x40, CONTEXT_SPECIFIC, (byte) 0x80);

	private final LengthToEncodedLength lengthToEncodedLength = new LengthToEncodedLength();
	private final BarEncodeInputToTagWithoutTypeId barEncodeInputToTagWithoutTypeId = new BarEncodeInputToTagWithoutTypeId();
	//	private final TypeIdToEncodedTypeId typeIdToEncodedTypeId = new TypeIdToEncodedTypeId();

	public byte[] getTag(BerData input) {
		return getTag(input, 0);
	}

	public byte[] getTag(BerData input, Integer length) {
		byte tagWithoutTypeId = barEncodeInputToTagWithoutTypeId.getTag(input);
		Integer typeId =
				input.getTypeId() != null ? input.getTypeId() : input.getDataType().getValue();
		if (UNIVERSAL.equals(input.getClassType())) {
			return new byte[] { tagWithoutTypeId };
		}
		if (IMPLICIT.equals(input.getDeclarationVisibility())) {
			return new byte[] { (byte) (tagWithoutTypeId | typeId.byteValue()) };
		}
		if (EXPLICIT.equals(input.getDeclarationVisibility())) {
			if(length == null){
				throw new RuntimeException("No length specified");
			}
			return new byte[] { (byte) (tagWithoutTypeId | typeId.byteValue()),
					Integer.valueOf(length + 2).byteValue(), (byte)input.getDataType().getValue() };
		}
		if(SEQUENCE.equals(input.getDataType())){
			return Bytes.concat(new byte[] { typeId.byteValue() }, lengthToEncodedLength.mapToEncodedLength(length));
		}
		return new byte[] {(byte) (tagWithoutTypeId | typeId.byteValue()) };
	}

}
