package com.jjusianiec.put.bsr.snmp.ber.decoder;

import com.google.common.collect.ImmutableMap;
import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import com.jjusianiec.put.bsr.snmp.ber.model.ClassType;
import com.jjusianiec.put.bsr.snmp.ber.model.DataType;
import com.jjusianiec.put.bsr.snmp.ber.model.DeclarationVisibility;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class BytesToBerDataWithTypesFunction implements Function<byte[], BerData> {

    private static final byte TAG_MASK = (byte) 0x1F;
    private static final byte PC_MASK = (byte) 0x20;
    private static final byte CLASS_MASK = (byte) 0xC0;
    private static final Map<Byte, ClassType> BYTE_TO_CLASS_TYPE = ImmutableMap.of(
            (byte) 0x00, ClassType.UNIVERSAL,
            (byte) 0x40, ClassType.APPLICATION,
            (byte) 0x80, ClassType.CONTEXT_SPECIFIC,
            (byte) 0xC0, ClassType.PRIVATE
    );


    public BerData apply(byte[] input) {
        BerData berData = new BerData();
        berData.setTypeId(getTypeId(input));
        berData.setClassType(getClassType(input));
        berData.setDeclarationVisibility(getDeclarationVisibility(input));
        berData.setDataType(getDataType(input));
        return berData;
    }

    private DataType getDataType(byte[] input) {
        if (ClassType.UNIVERSAL.equals(getClassType(input))) {
            int tagId = input[0] & TAG_MASK;
            Optional<DataType> foundDataTypeOption = Stream.of(DataType.values())
                    .filter(v -> v.getValue() == tagId).findAny();
            return foundDataTypeOption.orElse(null);
        }
        return null;
    }

    private DeclarationVisibility getDeclarationVisibility(byte[] input) {
        if ((input[0] & PC_MASK) > 0 && ClassType.APPLICATION.equals(getClassType(input))) {
            return DeclarationVisibility.EXPLICIT;
        } else if (ClassType.APPLICATION.equals(getClassType(input))) {
            return DeclarationVisibility.IMPLICIT;
        } else {
            return null;
        }
    }

    private ClassType getClassType(byte[] input) {
        return BYTE_TO_CLASS_TYPE.get((byte) (input[0] & CLASS_MASK));
    }

    private Integer getTypeId(byte[] input) {
        byte b = input[0];
        if ((b & TAG_MASK) == TAG_MASK) {
            return longTypeId(input);
        } else {
            return b & TAG_MASK;
        }
    }

    private Integer longTypeId(byte[] input) {
        throw new NotImplementedException();
    }
}
