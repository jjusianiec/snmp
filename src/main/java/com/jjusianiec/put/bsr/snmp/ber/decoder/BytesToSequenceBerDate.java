package com.jjusianiec.put.bsr.snmp.ber.decoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.copyOfRange;

public class BytesToSequenceBerDate {

    private BytesToLength bytesToLength = new BytesToLength();


    public BerData apply(byte[] input, BerData berData) {
        Pair<Integer, BigInteger> countOfLengthAndLength =
                bytesToLength.getCountOfLengthAndLength(copyOfRange(input, 1, input.length));
        byte[] bytes = copyOfRange(input, 1 + countOfLengthAndLength.getLeft(),
                1 + countOfLengthAndLength.getLeft() + countOfLengthAndLength.getRight().intValue());

        List<byte[]> list = newArrayList();
        while (bytes.length > 0) {
            Pair<Integer, BigInteger> anotherCount =
                    bytesToLength.getCountOfLengthAndLength(copyOfRange(bytes, 1, bytes.length));
            list.add(copyOfRange(bytes, 0,
                    1 + anotherCount.getLeft() + anotherCount.getRight().intValue()));
            bytes = copyOfRange(bytes, 1 + anotherCount.getLeft()
                    + anotherCount.getRight().intValue(), bytes.length);
        }
        berData.setValues(list.stream().map(this::decode).collect(Collectors.toList()));
        return berData;
    }

    private BytesToBerDataWithTypesFunction bytesToBerDataWithTypesFunction =
            new BytesToBerDataWithTypesFunction();
    private BytesToIntegerBerData berDataAndBytesToIntegerBerData =
            new BytesToIntegerBerData();
    private BytesToObjectIdentifierBerDate bytesToObjectIdentifierBerDate =
            new BytesToObjectIdentifierBerDate();
    private BytesToOctetStringBerDate bytesToOctetStringBerDate =
            new BytesToOctetStringBerDate();


    private BerData decode(byte[] input) {
        BerData berDataWithTypes = bytesToBerDataWithTypesFunction.apply(input);
        switch (berDataWithTypes.getDataType()) {
            case INTEGER:
                return berDataAndBytesToIntegerBerData.apply(input, berDataWithTypes);
            case OBJECT_IDENTIFIER:
                return bytesToObjectIdentifierBerDate.apply(input, berDataWithTypes);
            case OCTET_STRING:
                return bytesToOctetStringBerDate.apply(input, berDataWithTypes);
            case NULL:
                return berDataWithTypes;
            case SEQUENCE:
                return this.apply(input, berDataWithTypes);
        }
        return null;
    }
}
