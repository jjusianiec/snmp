package com.jjusianiec.put.bsr.snmp.ber.decoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.copyOfRange;

public class BytesToObjectIdentifierBerDate {

    private BytesToLength bytesToLength = new BytesToLength();

    public BerData apply(byte[] input, BerData berDataWithTypes) {
        Pair<Integer, BigInteger> countOfLengthAndLength =
                bytesToLength.getCountOfLengthAndLength(copyOfRange(input, 1, input.length));
        byte[] bytes = copyOfRange(input, 1 + countOfLengthAndLength.getLeft(),
                1 + countOfLengthAndLength.getLeft() + countOfLengthAndLength.getRight().intValue());
        bytes[0] -= 40;
        String collect = Stream.of(ArrayUtils.toObject(bytes))
                .map(b -> new BigInteger(1, new byte[]{b}).toString())
                .collect(Collectors.joining("."));
        berDataWithTypes.setValue("1." + collect);
        return berDataWithTypes;
    }
}
