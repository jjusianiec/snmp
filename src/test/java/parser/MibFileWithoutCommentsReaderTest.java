package parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class MibFileWithoutCommentsReaderTest {
	private MibFileWithoutCommentsReader tested = new MibFileWithoutCommentsReader();

	@Test
	public void shouldReadFileWithoutComments() throws Exception {
		//when
		String actual = tested.readFileWithoutComments("comments/APPC-MIB");
		//then
		assertThat(actual).isEqualTo(getFileWithoutComments());
	}

	private String getFileWithoutComments() throws IOException {
		InputStream resourceAsStream = getClass()
				.getResourceAsStream("/comments/APPC-MIB-NO-COMMENTS");
		return IOUtils.toString(resourceAsStream);
	}
}