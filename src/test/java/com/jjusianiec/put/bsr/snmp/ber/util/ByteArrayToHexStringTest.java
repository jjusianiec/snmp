package com.jjusianiec.put.bsr.snmp.ber.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ByteArrayToHexStringTest {

	@Test
	public void shouldApply() throws Exception {
		//when
		String actual = ByteArrayToHexString.apply(new byte[] {
				//@formatter:off
					(byte) 0x02,
					(byte) 0x07,
					(byte) 0x23,
					(byte) 0x86,
					(byte) 0xF2,
					(byte) 0x6F,
					(byte) 0xC1,
					(byte) 0x00,
					(byte) 0x00
				//@formatter:on
		});
		//then
		assertThat(actual).isEqualTo("02072386F26FC10000");
	}

}