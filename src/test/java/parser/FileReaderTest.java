package parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FileReaderTest {

	private FileReader tested = new FileReader();

	@Test
	public void shouldParseMib() throws Exception {
		//when
		String actual = tested.readFile("mibs/ACCOUNTING-CONTROL-MIB");
		//then
		assertThat(actual).isNotNull();
		assertThat(actual).isNotBlank();
	}
}