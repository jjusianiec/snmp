package com.jjusianiec.put.bsr.snmp.ber.encoder;

import org.junit.Test;

import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;
import com.jjusianiec.put.bsr.snmp.ber.model.ClassType;
import com.jjusianiec.put.bsr.snmp.ber.util.HexStringToByteArray;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerBerEncoderTest {
	private IntegerBerEncoder tested = new IntegerBerEncoder();

	@Test
	public void shouldEncodeSmallInteger() throws Exception {
		//when
		byte[] actual = tested.encode(getInput("10000000000000000"));
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("02072386F26FC10000"));
	}

	private BerEncodeInput getInput(String value) {
		return BerEncodeInput.builder().value(value).classType(ClassType.UNIVERSAL).build();
	}

	@Test
	public void shouldEncodeMediumInteger() throws Exception {
		//when
		//then
	}

	@Test
	public void shouldEncodeBigInteger() throws Exception {
		//when
		//then
	}

}