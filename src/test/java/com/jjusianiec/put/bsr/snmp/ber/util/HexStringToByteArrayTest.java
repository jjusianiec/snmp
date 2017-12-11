package com.jjusianiec.put.bsr.snmp.ber.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HexStringToByteArrayTest {

	@Test
	public void shouldApply() throws Exception {
		//when
		byte[] actual = HexStringToByteArray.apply("02072386 F26FC100 00");
		//then
		Assertions.assertThat(actual).isEqualTo(new byte[] {
				(byte)0x02,
				(byte)0x07,
				(byte)0x23,
				(byte)0x86,
				(byte)0xF2,
				(byte)0x6F,
				(byte)0xC1,
				(byte)0x00,
				(byte)0x00
		});
	}
}