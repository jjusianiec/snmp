package com.jjusianiec.put.bsr.snmp.ber.encoder;

import org.junit.Test;

import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;
import com.jjusianiec.put.bsr.snmp.ber.util.HexStringToByteArray;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectIdentifierEncoderTest {
	private ObjectIdentifierEncoder tested = new ObjectIdentifierEncoder();

	@Test
	public void shouldEncode() throws Exception {
		//when
		byte[] actual = tested.encode(getInput("1.3.6.1.4.1"));
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("06052B06010401"));
	}

	private BerEncodeInput getInput(String s) {
		return BerEncodeInput.builder().value(s).build();
	}
}