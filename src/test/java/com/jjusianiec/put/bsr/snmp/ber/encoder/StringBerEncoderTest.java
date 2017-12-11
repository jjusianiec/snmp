package com.jjusianiec.put.bsr.snmp.ber.encoder;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;
import com.jjusianiec.put.bsr.snmp.ber.util.HexStringToByteArray;

import static org.assertj.core.api.Assertions.assertThat;

public class StringBerEncoderTest {
	private StringBerEncoder tested = new StringBerEncoder();

	@Test
	public void shouldEncodeSmall() throws Exception {
		//given
		//when
		byte[] actual = tested.encode(getInput("01020304"));
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("040401020304"));
	}

	@Test
	public void shouldEncodeBig() throws Exception {
		//given
		//when
		byte[] actual = tested.encode(getInput("5050505050505050A0A0A0A0A0A0A0A0A0A0"
				+ "A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0"));
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("042B5050 50505050 "
				+ "5050A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0"
				+ " A0A0A0A0 A0A0A0A0 A0"));
	}

	private BerEncodeInput getInput(String input) {
		return BerEncodeInput.builder().value(input).build();
	}

}