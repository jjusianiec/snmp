package com.jjusianiec.put.bsr.snmp.ber.encoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import org.junit.Test;

import com.jjusianiec.put.bsr.snmp.ber.util.HexStringToByteArray;

import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.UNIVERSAL;
import static com.jjusianiec.put.bsr.snmp.ber.model.DataType.OCTET_STRING;
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

	private BerData getInput(String input) {
		return BerData.builder().dataType(OCTET_STRING).classType(UNIVERSAL).value(input).build();
	}

}