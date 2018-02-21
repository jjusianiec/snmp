package com.jjusianiec.put.bsr.snmp.ber.decoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;

public class BerDecoder {

    private BytesToBerDataWithTypesFunction bytesToBerDataWithTypesFunction = new BytesToBerDataWithTypesFunction();

    public BerData decode(byte[] input) {
        BerData berDataWithTypes = bytesToBerDataWithTypesFunction.apply(input);
        return berDataWithTypes;
    }
}
