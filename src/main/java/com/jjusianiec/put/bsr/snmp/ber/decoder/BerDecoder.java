package com.jjusianiec.put.bsr.snmp.ber.decoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;

public class BerDecoder {

    private BytesToBerDataWithTypesFunction bytesToBerDataWithTypesFunction = new BytesToBerDataWithTypesFunction();
    private BytesToIntegerBerData berDataAndBytesToIntegerBerData = new BytesToIntegerBerData();

    public BerData decode(byte[] input) {
        BerData berDataWithTypes = bytesToBerDataWithTypesFunction.apply(input);
        switch (berDataWithTypes.getDataType()){
            case INTEGER:
                return berDataAndBytesToIntegerBerData.apply(input, berDataWithTypes);
            case OCTET_STRING:
                break;
            case OBJECT_IDENTIFIER:
                break;
            case NULL:
                break;
            case SEQUENCE:
                break;
        }
        return null;
    }
}
