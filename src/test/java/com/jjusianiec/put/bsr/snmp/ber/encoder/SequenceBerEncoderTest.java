package com.jjusianiec.put.bsr.snmp.ber.encoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import org.junit.Test;

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
		BerData valueOne = BerData.builder().dataType(DataType.OCTET_STRING)
				.classType(ClassType.UNIVERSAL).value("01020304").build();
		BerData valueTwo = BerData.builder().dataType(DataType.INTEGER)
				.classType(ClassType.UNIVERSAL).value("666").build();
		BerData input = BerData.builder().dataType(DataType.SEQUENCE)
				.values(newArrayList(valueOne, valueTwo)).build();
		//when
		byte[] actual = tested.encode(input);
		//then
		String apply = ByteArrayToHexString.apply(actual);
		assertThat(actual).isEqualTo(HexStringToByteArray.apply("300A8004010203048102029A"));
	}

}