package parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectTypeExtractorTest {

	private final ObjectTypeExtractor tested = new ObjectTypeExtractor();
	private final MibFileWithoutCommentsReader mibFileWithoutCommentsReader = new MibFileWithoutCommentsReader();

	@Test
	public void shouldReadObjectTypes() throws Exception {
		//when
		List<String> actual = tested.getRawObjectTypeStringList(getContent());
		//then
		assertThat(actual).isNotEmpty();
		assertThat(actual).hasSameSizeAs(getObjectTypeNames());
	}

	private List<String> getObjectTypeNames() throws IOException {
		return FileUtils
				.readLines(new File(getClass().getResource("/object_types_rfc1213").getFile()),
						"utf-8");
	}

	private String getContent() throws IOException {
//		InputStream resourceAsStream = getClass().getResourceAsStream("/mibs/RFC1213-MIB");
//		return IOUtils.toString(resourceAsStream);
		return mibFileWithoutCommentsReader.readFileWithoutComments("./mibs/RFC1213-MIB");
	}

}