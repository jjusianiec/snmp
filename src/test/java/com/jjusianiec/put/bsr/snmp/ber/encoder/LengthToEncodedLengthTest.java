package com.jjusianiec.put.bsr.snmp.ber.encoder;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LengthToEncodedLengthTest {

	private static final byte[] IN_HEX_120 = { 0x78 };
	private LengthToEncodedLength tested = new LengthToEncodedLength();

	@Test
	public void shouldMapToEncodedLength() throws Exception {
		//when
		byte[] actual = tested.mapToEncodedLength(120);
		//then
		assertThat(actual).isEqualTo(IN_HEX_120);
	}

	@Test
	public void shouldMapToEncodedLengthBig() throws Exception {
		//when
		byte[] actual = tested.mapToEncodedLength(500000);
		//then
		assertThat(actual).isEqualTo(new byte[] {
				//@formatter:off
				(byte) 0x83,
				(byte) 0x07,
				(byte) 0xA1,
				(byte) 0x20
				//@formatter:on
		});
	}

	@Test
	public void shouldMapToEncodedLengthMedium() throws Exception {
		//when
		byte[] actual = tested.mapToEncodedLength(430);
		//then
		assertThat(actual).isEqualTo(new byte[] {
				//@formatter:off
				(byte) 0x82,
				(byte) 0x01,
				(byte) 0xAE,
				//@formatter:on
		});
	}

}