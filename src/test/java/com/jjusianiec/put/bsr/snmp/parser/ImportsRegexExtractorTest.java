package com.jjusianiec.put.bsr.snmp.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ImportsRegexExtractorTest {

	private ImportsRegexExtractor tested = new ImportsRegexExtractor();

	//	IMPORTS
	//	MODULE-IDENTITY,  experimental, Unsigned32,
	//	OBJECT-TYPE, Opaque
	//	FROM SNMPv2-SMI
	//			OwnerString
	//	FROM RMON-MIB
	//	RowStatus, StorageType, TEXTUAL-CONVENTION
	//	FROM SNMPv2-TC
	//	MODULE-COMPLIANCE, OBJECT-GROUP
	//	FROM SNMPv2-CONF
	//			SnmpAdminString
	//	FROM SNMP-FRAMEWORK-MIB;

	@Test
	public void shouldExtractImports() throws Exception {
		//when
		Map<String, List<String>> actual = tested.extractImports(getContent());
		//then
		assertThat(actual).hasSize(5);
		assertThat(actual.get("SNMPv2-SMI")).hasSize(5);
		assertThat(actual.get("RMON-MIB")).hasSize(1);
		assertThat(actual.get("SNMPv2-TC")).hasSize(3);
		assertThat(actual.get("SNMPv2-CONF")).hasSize(2);
		assertThat(actual.get("SNMP-FRAMEWORK-MIB")).hasSize(1);
		for (List<String> strings : actual.values()) {
			for (String string : strings) {
				assertThat(string).doesNotMatch(".*\\s.*");
			}
		}
	}

	private String getContent() throws IOException {
		InputStream resourceAsStream = getClass().getResourceAsStream("/mibs/AGGREGATE-MIB");
		return IOUtils.toString(resourceAsStream);
	}

}