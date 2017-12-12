package com.jjusianiec.put.bsr.snmp.ber.encoder;

import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.APPLICATION;
import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.UNIVERSAL;
import static com.jjusianiec.put.bsr.snmp.ber.model.DataType.INTEGER;
import static com.jjusianiec.put.bsr.snmp.ber.model.DataType.OBJECT_IDENTIFIER;
import static com.jjusianiec.put.bsr.snmp.ber.model.DataType.OCTET_STRING;
import static com.jjusianiec.put.bsr.snmp.ber.model.DeclarationVisibility.EXPLICIT;
import static com.jjusianiec.put.bsr.snmp.ber.model.DeclarationVisibility.IMPLICIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.util.Lists.newArrayList;

import org.junit.Test;

import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;
import com.jjusianiec.put.bsr.snmp.ber.model.ClassType;
import com.jjusianiec.put.bsr.snmp.ber.model.DataType;
import com.jjusianiec.put.bsr.snmp.ber.model.DeclarationVisibility;
import com.jjusianiec.put.bsr.snmp.ber.model.ValueRange;
import com.jjusianiec.put.bsr.snmp.ber.util.ByteArrayToHexString;
import com.jjusianiec.put.bsr.snmp.ber.util.HexStringToByteArray;

public class BerEncoderIntegrationTest {

	private BerEncoder tested = new BerEncoder();

	@Test
	public void shouldEncodeSmallInteger() throws Exception {
		//when
		BerEncodeInput input = BerEncodeInput.builder().value("1").dataType(INTEGER)
				.classType(ClassType.UNIVERSAL).build();
		byte[] actual = tested.encode(input);
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("020101"));
	}

	@Test
	public void shouldEncodeMediumInteger() throws Exception {
		BerEncodeInput input = BerEncodeInput.builder().value("10000000").dataType(INTEGER)
				.classType(ClassType.UNIVERSAL).build();
		//when
		byte[] actual = tested.encode(input);
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("020400989680"));
	}

	@Test
	public void shouldEncodeBigInteger() throws Exception {
		BerEncodeInput input = BerEncodeInput.builder().value("100000000000000").dataType(INTEGER)
				.classType(ClassType.UNIVERSAL).build();
		//when
		byte[] actual = tested.encode(input);
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("02065AF3107A4000"));
	}

	@Test
	public void shouldThrowExceptionWhenNotInRange() throws Exception {
		//given
		BerEncodeInput input = BerEncodeInput.builder().value("100000000000000").dataType(INTEGER)
				.classType(ClassType.UNIVERSAL).build();
		input.setValueRange(ValueRange.builder().minimum("100").maximum("500").build());
		//when
		assertThatThrownBy(() -> tested.encode(input)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void shouldEncodeObjectIdentifier() throws Exception {
		BerEncodeInput input = BerEncodeInput.builder().value("1.3.6.1.4.1")
				.dataType(OBJECT_IDENTIFIER).build();
		//when
		byte[] actual = tested.encode(input);
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("06052B06010401"));
	}

	@Test
	public void shouldEncodeSequence() throws Exception {
		//given
		BerEncodeInput valueOne = BerEncodeInput.builder().dataType(DataType.OCTET_STRING)
				.classType(ClassType.UNIVERSAL).value("01020304").build();
		BerEncodeInput valueTwo = BerEncodeInput.builder().dataType(DataType.INTEGER)
				.classType(ClassType.UNIVERSAL).value("666").build();
		BerEncodeInput input = BerEncodeInput.builder().dataType(DataType.SEQUENCE)
				.values(newArrayList(valueOne, valueTwo)).build();
		//when
		byte[] actual = tested.encode(input);
		//then
		String apply = ByteArrayToHexString.apply(actual);
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("300A8004010203048102029A"));
	}

	@Test
	public void shouldEncodeSmall() throws Exception {
		//given
		BerEncodeInput input = BerEncodeInput.builder().dataType(OCTET_STRING).classType(UNIVERSAL)
				.value("01020304").build();
		//when
		byte[] actual = tested.encode(input);
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("040401020304"));
	}

	@Test
	public void shouldEncodeBig() throws Exception {
		//given
		BerEncodeInput input = BerEncodeInput.builder().dataType(OCTET_STRING).classType(UNIVERSAL)
				.value("5050505050505050A0A0A0A0A0A0A0A0A0A0"
						+ "A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0").build();
		//when
		byte[] actual = tested.encode(input);
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("042B5050 50505050 "
				+ "5050A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0"
				+ " A0A0A0A0 A0A0A0A0 A0"));
	}

	@Test
	public void shouldEncodeApplicationIntegerExplicit() throws Exception {
		//given
		//when
		byte[] actual = tested
				.encode(BerEncodeInput.builder().value("5").dataType(INTEGER).classType(APPLICATION)
						.typeId(5).declarationVisibility(EXPLICIT).build());
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("6503020105"));
	}

	@Test
	public void shouldEncodeApplicationIntegerImplicit() throws Exception {
		//given
		//when
		byte[] actual = tested
				.encode(BerEncodeInput.builder().value("5").dataType(INTEGER).classType(APPLICATION)
						.typeId(4).declarationVisibility(IMPLICIT).build());
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("440105"));
	}

	@Test
	public void shouldEncode() throws Exception {
		//given

		//when

		//then
	}

}