package com.jjusianiec.put.bsr.snmp.ber.decoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import com.jjusianiec.put.bsr.snmp.ber.util.ByteArrayToHexString;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;

import static java.util.Arrays.copyOfRange;

public class BytesToOctetStringBerDate {

    private BytesToLength bytesToLength = new BytesToLength();

    public BerData apply(byte[] input, BerData berDataWithTypes) {
        Pair<Integer, BigInteger> countOfLengthAndLength =
                bytesToLength.getCountOfLengthAndLength(copyOfRange(input, 1, input.length));
        byte[] bytes = copyOfRange(input, 1 + countOfLengthAndLength.getLeft(),
                1 + countOfLengthAndLength.getLeft() + countOfLengthAndLength.getRight().intValue());
        berDataWithTypes.setValue(ByteArrayToHexString.apply(bytes));
        return berDataWithTypes;
    }
}
