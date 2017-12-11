package com.jjusianiec.put.bsr.snmp.ber.encoder;

import org.junit.Test;

import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;

import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.APPLICATION;
import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.CONTEXT_SPECIFIC;
import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.UNIVERSAL;
import static com.jjusianiec.put.bsr.snmp.ber.model.DataType.INTEGER;
import static com.jjusianiec.put.bsr.snmp.ber.model.DeclarationVisibility.EXPLICIT;
import static com.jjusianiec.put.bsr.snmp.ber.model.DeclarationVisibility.IMPLICIT;
import static org.assertj.core.api.Assertions.assertThat;

public class TagBerEncoderTest {
	private TagBerEncoder tested = new TagBerEncoder();

	@Test
	public void shouldGetTagUniversalInteger() throws Exception {
		//when
		byte[] actual = tested
				.getTag(BerEncodeInput.builder().dataType(INTEGER).classType(UNIVERSAL).build());
		//then
		assertThat(actual).isEqualTo(new byte[] { 0x02 });
	}

	@Test
	public void shouldGetTagApplicationImplicitInteger() throws Exception {
		//when
		byte[] actual = tested
				.getTag(BerEncodeInput.builder().dataType(INTEGER).classType(APPLICATION).typeId(4)
						.declarationVisibility(IMPLICIT).build());
		//then
		assertThat(actual).isEqualTo(0x44);
	}

	@Test
	public void shouldGetTagApplicationExplicitInteger() throws Exception {
		//when
		byte[] actual = tested
				.getTag(BerEncodeInput.builder().dataType(INTEGER).classType(APPLICATION).typeId(5)
						.declarationVisibility(EXPLICIT).build());
		//then
		assertThat(actual).isEqualTo(0x65);
	}

	@Test
	public void shouldGetTagContextSpecificImplicitInteger() throws Exception {
		//when
		byte[] actual = tested
				.getTag(BerEncodeInput.builder().dataType(INTEGER).classType(CONTEXT_SPECIFIC)
						.typeId(4).declarationVisibility(IMPLICIT).build());
		//then
		assertThat(actual).isEqualTo(0x84);
	}

	@Test
	public void shouldGetTagContextSpecificExplicitInteger() throws Exception {
		//when
		byte[] actual = tested.getTag(BerEncodeInput.builder().dataType(INTEGER).typeId(5)
				.declarationVisibility(EXPLICIT).build());
		//then
		assertThat(actual).isEqualTo(0xA5);
	}
}