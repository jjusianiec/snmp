package com.jjusianiec.put.bsr.snmp.ber.encoder;

import org.junit.Test;

import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;
import com.jjusianiec.put.bsr.snmp.ber.model.ClassType;
import com.jjusianiec.put.bsr.snmp.ber.model.DataType;
import com.jjusianiec.put.bsr.snmp.ber.model.ValueRange;
import com.jjusianiec.put.bsr.snmp.ber.util.HexStringToByteArray;

import static com.jjusianiec.put.bsr.snmp.ber.model.DataType.INTEGER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IntegerBerEncoderTest {
	private IntegerBerEncoder tested = new IntegerBerEncoder();

	@Test
	public void shouldEncodeSmallInteger() throws Exception {
		//when
		byte[] actual = tested.encode(getInput("1"));
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("020101"));
	}

	@Test
	public void shouldEncodeMediumInteger() throws Exception {
		//when
		byte[] actual = tested.encode(getInput("10000000"));
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("020400989680"));
	}

	@Test
	public void shouldEncodeBigInteger() throws Exception {
		//when
		byte[] actual = tested.encode(getInput("100000000000000"));
		//then
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("02065AF3107A4000"));
	}


	@Test
	public void shouldThrowExceptionWhenNotInRange() throws Exception {
		//given
		BerEncodeInput input = getInput("100000000000000");
		input.setValueRange(ValueRange.builder().minimum("100").maximum("500").build());
		//when
		assertThatThrownBy(() -> tested.encode(input))
				.isInstanceOf(IllegalArgumentException.class);
	}

	private BerEncodeInput getInput(String value) {
		return BerEncodeInput.builder().value(value).dataType(INTEGER).classType(ClassType.UNIVERSAL).build();
	}
}