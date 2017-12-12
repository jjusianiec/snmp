package com.jjusianiec.put.bsr.snmp.ber.encoder;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.jjusianiec.put.bsr.snmp.ber.model.BerEncodeInput;
import com.jjusianiec.put.bsr.snmp.ber.model.ClassType;
import com.jjusianiec.put.bsr.snmp.ber.model.DataType;
import com.jjusianiec.put.bsr.snmp.ber.util.ByteArrayToHexString;
import com.jjusianiec.put.bsr.snmp.ber.util.HexStringToByteArray;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

public class SequenceBerEncoderTest {
	private SequenceBerEncoder tested = new SequenceBerEncoder();

	@Test
	public void shouldEncode() throws Exception {
		//given
		BerEncodeInput valueOne = BerEncodeInput.builder().dataType(DataType.OCTET_STRING)
				.classType(ClassType.UNIVERSAL).value("01020304").build();
		BerEncodeInput valueTwo = BerEncodeInput.builder().dataType(DataType.INTEGER)
				.classType(ClassType.UNIVERSAL).value("666").build();
		BerEncodeInput input = BerEncodeInput.builder().dataType(DataType.SEQUENCE)
				.classType(ClassType.UNIVERSAL).values(newArrayList(valueOne, valueTwo)).build();
		//when
		byte[] actual = tested.encode(input);
		//then
		String apply = ByteArrayToHexString.apply(actual);
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("300A8004010203048102029A"));
	}

}