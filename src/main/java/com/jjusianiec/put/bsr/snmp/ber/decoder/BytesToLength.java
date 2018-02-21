package com.jjusianiec.put.bsr.snmp.ber.decoder;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.Arrays;

public class BytesToLength {

    private static final byte LAST_BIT_MASK = (byte) 0x80;
    private static final byte ALL_EXCEPT_LAST_BIT_MASK = (byte) 0x7F;
    private static final int POSITIVE = 1;

    public Pair<Integer, BigInteger> getCountOfLengthAndLength(byte[] bytes) {
        if ((byte) (bytes[0] & LAST_BIT_MASK) <= 0) {
            return Pair.of(1, new BigInteger(POSITIVE, new byte[]{bytes[0]}));
        } else {
            int lengthCount = bytes[0] & ALL_EXCEPT_LAST_BIT_MASK;
            byte[] lengthInBytes = Arrays.copyOfRange(bytes, 1, lengthCount);
            return Pair.of(lengthCount, new BigInteger(POSITIVE, lengthInBytes));
        }
    }
}
