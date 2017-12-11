package com.jjusianiec.put.bsr.snmp.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.jjusianiec.put.bsr.snmp.parser.model.OIdRaw;

import static org.assertj.core.api.Assertions.assertThat;

public class OIdRawReaderTest {

	private OIdReader tested = new OIdReader();

	@Test
	public void shouldReadOIds() throws Exception {
		//when
		Map<String, OIdRaw> actual = tested.readIdentifiers(getContent());
		//then
		assertThat(actual).hasSize(13);
	}

	private String getContent() throws IOException {
		InputStream resourceAsStream = getClass().getResourceAsStream("/mibs/RFC1213-MIB");
		return IOUtils.toString(resourceAsStream);
	}
}