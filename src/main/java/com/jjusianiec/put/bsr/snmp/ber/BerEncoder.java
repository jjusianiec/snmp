package com.jjusianiec.put.bsr.snmp.ber;

import com.jjusianiec.put.bsr.snmp.ber.encoder.NullBerEncoder;
import com.jjusianiec.put.bsr.snmp.ber.encoder.UniversalTypeBerEncoder;
import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;

public class BerEncoder {

	private final NullBerEncoder nullBerEncoder = new NullBerEncoder();
	private final UniversalTypeBerEncoder universalTypeBerEncoder = new UniversalTypeBerEncoder();

	public byte[] encode(BerEncodeInput input) {
		if (input.getValue() == null) {
			return nullBerEncoder.encode();
		} else if (input.getTypeId() == null) {
			return universalTypeBerEncoder.encode(input);
		} else {
			//TODO application
			return null;
		}
	}

}
