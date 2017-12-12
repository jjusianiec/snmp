package com.jjusianiec.put.bsr.snmp.ber.encoder;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeIdToEncodedTypeIdTest {
	private TypeIdToEncodedTypeId tested = new TypeIdToEncodedTypeId();

	@Test
	@Ignore
	public void shouldGetEncodedTypeIdForBig() throws Exception {
		//when
		byte[] actual = tested.getEncodedTypeId(500);
		//then
		assertThat(actual).isEqualTo(new byte[] { 0x1F, (byte) 0x83, 0x74 });
	}

	@Test
	@Ignore
	public void shouldGetEncodedTypeIdForMedium() throws Exception {
		//when
		byte[] actual = tested.getEncodedTypeId(31);
		//then
		assertThat(actual).isEqualTo(new byte[] { 0x1F, 0x1F });
	}

	@Test
	public void shouldGetEncodedTypeIdForSmall() throws Exception {
		//when
		byte[] actual = tested.getEncodedTypeId(02);
		//then
		assertThat(actual).isEqualTo(new byte[] { 0x02 });
	}

}