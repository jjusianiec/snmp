package com.jjusianiec.put.bsr.snmp.ber.decoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.BiFunction;

public class BytesToIntegerBerData implements BiFunction<byte[], BerData, BerData> {

    private BytesToLength bytesToLength = new BytesToLength();

    public BerData apply(byte[] input, BerData berDataWithTypes) {
        Pair<Integer, BigInteger> countOfLengthAndLength = bytesToLength
                .getCountOfLengthAndLength(Arrays.copyOfRange(input, 1, input.length));
        return null;
    }
}
