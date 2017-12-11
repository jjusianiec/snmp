package com.jjusianiec.put.bsr.snmp.ber;

import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;

public class BerEncoder {

	private final NullBerEncoder nullBerEncoder = new NullBerEncoder();
	private final UniversalTypeBerEncoder simpleTypeBerEncoder = new UniversalTypeBerEncoder();
	private final UniversalTypeBerEncoder universalTypeBerEncoder = new UniversalTypeBerEncoder();

	public byte[] encode(BerEncodeInput input) {
		if(input.getValue() == null){
			return NullBerEncoder.encode();
		} else if (input.getTypeId() == null) {
			return UniversalTypeBerEncoder.encode(input);
		} else {
			//TODO application
			return null;
		}
	}

}
