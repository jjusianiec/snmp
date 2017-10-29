package parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class OIdReaderTest {

	private OIdReader tested = new OIdReader();

	@Test
	public void shouldReadOIds() throws Exception {
		//when
		Map<String, OId> actual = tested.readIdentifiers(getContent());
		//then
		assertThat(actual).hasSize(13);
	}

	private String getContent() throws IOException {
		InputStream resourceAsStream = getClass().getResourceAsStream("/mibs/RFC1213-MIB");
		return IOUtils.toString(resourceAsStream);
	}
}