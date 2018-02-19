package com.jjusianiec.put.bsr.snmp.ber.decoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import com.jjusianiec.put.bsr.snmp.ber.model.ClassType;
import com.jjusianiec.put.bsr.snmp.ber.model.DataType;
import com.jjusianiec.put.bsr.snmp.ber.model.ValueRange;
import com.jjusianiec.put.bsr.snmp.ber.util.ByteArrayToHexString;
import com.jjusianiec.put.bsr.snmp.ber.util.HexStringToByteArray;
import org.junit.Test;

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

public class BerDecoderIntegrationTest {

    private BerDecoder tested = new BerDecoder();

    @Test
    public void shouldEncodeSmallInteger() throws Exception {
        //when
        BerData input = BerData.builder().value("1").dataType(INTEGER)
                .classType(ClassType.UNIVERSAL).build();
        byte[] actual = tested.encode(input);
        //then
        assertThat(actual).isEqualTo(HexStringToByteArray.apply("020101"));

        //when
        BerData actual = tested.decode(HexStringToByteArray.apply("020101"));
        //then
    }

    @Test
    public void shouldEncodeMediumInteger() throws Exception {
        BerData input = BerData.builder().value("10000000").dataType(INTEGER)
                .classType(ClassType.UNIVERSAL).build();
        //when
        byte[] actual = tested.encode(input);
        //then
        assertThat(actual).isEqualTo(HexStringToByteArray.apply("020400989680"));
    }

    @Test
    public void shouldEncodeBigInteger() throws Exception {
        BerData input = BerData.builder().value("100000000000000").dataType(INTEGER)
                .classType(ClassType.UNIVERSAL).build();
        //when
        byte[] actual = tested.encode(input);
        //then
        assertThat(actual).isEqualTo(HexStringToByteArray.apply("02065AF3107A4000"));
    }

    @Test
    public void shouldThrowExceptionWhenNotInRange() throws Exception {
        //given
        BerData input = BerData.builder().value("100000000000000").dataType(INTEGER)
                .classType(ClassType.UNIVERSAL).build();
        input.setValueRange(ValueRange.builder().minimum("100").maximum("500").build());
        //when
        assertThatThrownBy(() -> tested.encode(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldEncodeObjectIdentifier() throws Exception {
        BerData input = BerData.builder().value("1.3.6.1.4.1")
                .dataType(OBJECT_IDENTIFIER).build();
        //when
        byte[] actual = tested.encode(input);
        //then
        assertThat(actual).isEqualTo(HexStringToByteArray.apply("06052B06010401"));
    }

    @Test
    public void shouldEncodeSequence() throws Exception {
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

    @Test
    public void shouldEncodeSmall() throws Exception {
        //given
        BerData input = BerData.builder().dataType(OCTET_STRING).classType(UNIVERSAL)
                .value("01020304").build();
        //when
        byte[] actual = tested.encode(input);
        //then
        assertThat(actual).isEqualTo(HexStringToByteArray.apply("040401020304"));
    }

    @Test
    public void shouldEncodeBig() throws Exception {
        //given
        BerData input = BerData.builder().dataType(OCTET_STRING).classType(UNIVERSAL)
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
                .encode(BerData.builder().value("5").dataType(INTEGER).classType(APPLICATION)
                        .typeId(5).declarationVisibility(EXPLICIT).build());
        //then
        assertThat(actual).isEqualTo(HexStringToByteArray.apply("6503020105"));
    }

    @Test
    public void shouldEncodeApplicationIntegerImplicit() throws Exception {
        //given
        //when
        byte[] actual = tested
                .encode(BerData.builder().value("5").dataType(INTEGER).classType(APPLICATION)
                        .typeId(4).declarationVisibility(IMPLICIT).build());
        //then
        assertThat(actual).isEqualTo(HexStringToByteArray.apply("440105"));
    }

    @Test
    public void shouldEncodeSequenceFromPresentation() throws Exception {
        //given
        BerData valueOne = BerData.builder().dataType(DataType.INTEGER)
                .classType(ClassType.UNIVERSAL).value("3").build();
        BerData valueTwo = BerData.builder().dataType(DataType.INTEGER)
                .classType(ClassType.UNIVERSAL).value("8").build();
        BerData input = BerData.builder().dataType(DataType.SEQUENCE)
                .values(newArrayList(valueOne, valueTwo)).build();
        //when
        byte[] actual = tested.encode(input);
        //then
        String apply = ByteArrayToHexString.apply(actual);
        assertThat(actual).isEqualTo(HexStringToByteArray.apply("30 06 80 01 03 81 01 08"));
    }

}