package parser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class ObjectTypesReaderTest {
	private static final Logger LOGGER = getLogger(ObjectTypesReaderTest.class);

	private static final List<String> OBJECT_TYPES_WITH_INDEX = newArrayList("atEntry", "ifEntry",
			"ipAddrEntry", "ipRouteEntry", "ipNetToMediaEntry", "tcpConnEntry", "udpEntry",
			"egpNeighEntry");

	ObjectTypesReader tested = new ObjectTypesReader();
	private final MibFileWithoutCommentsReader mibFileWithoutCommentsReader = new MibFileWithoutCommentsReader();

	@Test
	public void shouldReadObjectTypes() throws Exception {
		//given
		String content = getContent();
		//when
		Map<String, OIdRaw> actual = tested.readObjectTypes(content);
		//then
		assertThat(actual).isNotEmpty();
		List<String> objectTypes = getObjectTypes();
		objectTypes.forEach(ot -> assertThat(ot).isIn(actual.keySet()));
		assertThat(actual).hasSize(objectTypes.size());
		actual.values().forEach(actualOIdRaw -> assertThat(actualOIdRaw)
				.hasNoNullFieldsOrPropertiesExcept("index"));
		OBJECT_TYPES_WITH_INDEX.forEach(otKey -> assertThat(actual.get(otKey).getIndex()).isNotNull());
	}

	private List<String> getObjectTypes() throws IOException {
		return FileUtils
				.readLines(new File(getClass().getResource("/object_types_rfc1213").getFile()),
						"utf-8");
	}

	private String getContent() throws IOException {
		return mibFileWithoutCommentsReader.readFileWithoutComments("./mibs/RFC1213-MIB");
	}
}