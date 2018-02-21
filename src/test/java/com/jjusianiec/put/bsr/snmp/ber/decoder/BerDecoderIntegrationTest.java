package com.jjusianiec.put.bsr.snmp.ber.decoder;

import com.jjusianiec.put.bsr.snmp.ber.model.BerData;
import com.jjusianiec.put.bsr.snmp.ber.model.ClassType;
import com.jjusianiec.put.bsr.snmp.ber.model.DataType;
import com.jjusianiec.put.bsr.snmp.ber.util.HexStringToByteArray;
import org.junit.Test;

import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.APPLICATION;
import static com.jjusianiec.put.bsr.snmp.ber.model.ClassType.UNIVERSAL;
import static com.jjusianiec.put.bsr.snmp.ber.model.DataType.*;
import static com.jjusianiec.put.bsr.snmp.ber.model.DeclarationVisibility.EXPLICIT;
import static com.jjusianiec.put.bsr.snmp.ber.model.DeclarationVisibility.IMPLICIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

public class BerDecoderIntegrationTest {

    private BerDecoder tested = new BerDecoder();

    @Test
    public void shouldEncodeSmallInteger() throws Exception {
        //when
        BerData actual = tested.decode(HexStringToByteArray.apply("020101"));
        //then
        assertThat(actual).isEqualTo(BerData.builder().value("1").typeId(2).dataType(INTEGER)
                .classType(ClassType.UNIVERSAL).build());
    }

    @Test
    public void shouldEncodeMediumInteger() throws Exception {
        //when
        BerData actual = tested.decode(HexStringToByteArray.apply("020400989680"));
        //then
        assertThat(actual).isEqualTo(BerData.builder().typeId(2).value("10000000").dataType(INTEGER)
                .classType(ClassType.UNIVERSAL).build());
    }

    @Test
    public void shouldDecodeBigInteger() throws Exception {
        //when
        BerData actual = tested.decode(HexStringToByteArray.apply("02065AF3107A4000"));
        //then
        assertThat(actual).isEqualTo(BerData.builder().typeId(2).value("100000000000000").dataType(INTEGER)
                .classType(ClassType.UNIVERSAL).build());
    }

//    @Test
//    public void shouldThrowExceptionWhenNotInRange() throws Exception {
//        //given
//        BerData input = BerData.builder().value("100000000000000").dataType(INTEGER)
//                .classType(ClassType.UNIVERSAL).build();
//        input.setValueRange(ValueRange.builder().minimum("100").maximum("500").build());
//        //when
//        assertThatThrownBy(() -> tested.decode(input)).isInstanceOf(IllegalArgumentException.class);
//    }

    @Test
    public void shouldDecodeObjectIdentifier() throws Exception {
        //when
        BerData actual = tested.decode(HexStringToByteArray.apply("06052B06010401"));
        //then
        assertThat(actual).isEqualTo(BerData.builder().classType(UNIVERSAL).typeId(6).value("1.3.6.1.4.1")
                .dataType(OBJECT_IDENTIFIER).build());
    }

    @Test
    public void shouldDecodeSequence() throws Exception {
        //when
        BerData actual = tested.decode(HexStringToByteArray.apply("300A8004010203048102029A"));
        //then
        BerData valueOne = BerData.builder().dataType(DataType.OCTET_STRING)
                .classType(ClassType.UNIVERSAL).value("01020304").build();
        BerData valueTwo = BerData.builder().dataType(DataType.INTEGER)
                .classType(ClassType.UNIVERSAL).value("666").build();
        BerData expected = BerData.builder().dataType(DataType.SEQUENCE)
                .values(newArrayList(valueOne, valueTwo)).build();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldDecodeSmall() throws Exception {
        //when
        BerData actual = tested.decode(HexStringToByteArray.apply("040401020304"));
        //then
        assertThat(actual).isEqualTo(BerData.builder().typeId(4).dataType(OCTET_STRING).classType(UNIVERSAL)
                .value("01020304").build());
    }

    @Test
    public void shouldDecodeNull() throws Exception {
        //when
        BerData actual = tested.decode(HexStringToByteArray.apply("0500"));
        //then
        assertThat(actual).isEqualTo(BerData.builder().typeId(5).dataType(NULL).classType(UNIVERSAL)
                .value(null).build());
    }

    @Test
    public void shouldDecodeBig() throws Exception {
        //when
        BerData actual = tested.decode(HexStringToByteArray.apply("042B5050 50505050 "
                + "5050A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0 A0A0A0A0"
                + " A0A0A0A0 A0A0A0A0 A0"));
        //then
        assertThat(actual).isEqualTo(BerData.builder().typeId(4).dataType(OCTET_STRING).classType(UNIVERSAL)
                .value("5050505050505050A0A0A0A0A0A0A0A0A0A0"
                        + "A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0A0").build());
    }

    @Test
    public void shouldDecodeApplicationIntegerExplicit() throws Exception {
        //when
        BerData actual = tested.decode(HexStringToByteArray.apply("6503020105"));
        //then
        assertThat(actual).isEqualTo(BerData.builder().value("5").dataType(INTEGER).classType(APPLICATION)
                .typeId(5).declarationVisibility(EXPLICIT).build());
    }

    @Test
    public void shouldDecodeApplicationIntegerImplicit() throws Exception {
        //when
        BerData actual = tested
                .decode(HexStringToByteArray.apply("440105"));
        //then
        assertThat(actual).isEqualTo(BerData.builder().value("5").dataType(INTEGER).classType(APPLICATION)
                .typeId(4).declarationVisibility(IMPLICIT).build());
    }

    @Test
    public void shouldDecodeSequenceFromPresentation() throws Exception {
        //when
        BerData actual = tested.decode(HexStringToByteArray.apply("30 06 80 01 03 81 01 08"));
        //then
        BerData valueOne = BerData.builder().dataType(DataType.INTEGER)
                .classType(ClassType.UNIVERSAL).value("3").build();
        BerData valueTwo = BerData.builder().dataType(DataType.INTEGER)
                .classType(ClassType.UNIVERSAL).value("8").build();
        BerData expected = BerData.builder().dataType(DataType.SEQUENCE)
                .values(newArrayList(valueOne, valueTwo)).build();
        assertThat(actual).isEqualTo(expected);
    }

}