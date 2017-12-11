package com.jjusianiec.put.bsr.snmp.parser;

import org.junit.Test;

import com.jjusianiec.put.bsr.snmp.parser.model.MibTree;

public class MibParserTest {


	private MibParser tested = new MibParser();

	@Test
	public void shouldParseMib() throws Exception {
		//when
		MibTree actual = tested.parseMib("mibs/RFC1213-MIB");
		//then

	}

}