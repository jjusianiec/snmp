package com.jjusianiec.put.bsr.snmp.ber.decoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.function.BiFunction;

import static java.util.Arrays.copyOfRange;

public class BytesToIntegerBerData implements BiFunction<byte[], BerData, BerData> {

    private BytesToLength bytesToLength = new BytesToLength();

    public BerData apply(byte[] input, BerData berDataWithTypes) {
        Pair<Integer, BigInteger> countOfLengthAndLength = bytesToLength
                .getCountOfLengthAndLength(copyOfRange(input, 1, input.length));
        byte[] bytes = copyOfRange(input, 1 + countOfLengthAndLength.getLeft(),
                1 + countOfLengthAndLength.getLeft() + countOfLengthAndLength.getRight().intValue());
        berDataWithTypes.setValue(new BigInteger(bytes).toString());
        return berDataWithTypes;
    }
}
