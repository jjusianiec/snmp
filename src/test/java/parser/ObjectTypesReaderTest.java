package parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ObjectTypesReaderTest {

	ObjectTypesReader tested = new ObjectTypesReader();

	@Test
	public void shouldReadObjectTypes() throws Exception {
		//when
		Map<String, OId> actual = tested.readObjectTypes(getContent());
		//then
		assertThat(actual).isNotEmpty();
	}

	private String getContent() throws IOException {
		InputStream resourceAsStream = getClass().getResourceAsStream("/mibs/RFC1213-MIB");
		return IOUtils.toString(resourceAsStream);
	}

}