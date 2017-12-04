package parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class ObjectTypesReaderTest {
	private static final Logger LOGGER = getLogger(ObjectTypesReaderTest.class);

	ObjectTypesReader tested = new ObjectTypesReader();

	@Test
	public void shouldReadObjectTypes() throws Exception {
		//when
		String content = getContent();
		Map<String, OIdRaw> actual = tested.readObjectTypes(content);
		//then
		assertThat(actual).isNotEmpty();
		List<String> objectTypes = getObjectTypes();
		objectTypes.forEach(ot -> assertThat(ot).isIn(actual.keySet()));
		assertThat(actual).hasSize(content.split("\n").length);
	}

	private List<String> getObjectTypes() throws IOException {
		List list = FileUtils
				.readLines(new File(getClass().getResource("/object_types_rfc1213").getFile()),
						"utf-8");
		return list;
	}

	private String getContent() throws IOException {
		InputStream resourceAsStream = getClass().getResourceAsStream("/mibs/RFC1213-MIB");
		return IOUtils.toString(resourceAsStream);
	}

}