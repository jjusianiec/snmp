package com.jjusianiec.put.bsr.snmp.ber;

import org.junit.Test;

public class BerEncoderTest {

	private BerEncoder tested = new BerEncoder();

	@Test
	public void shouldEncode() throws Exception {
		//given
		//when
		//then
	}

}

// DayOfYear ::= [APPLICATION 17] IMPLICIT INTEGER
// Birthday ::= SEQUENCE {
//   name VisibleString,
//   day DayOfYear
//}