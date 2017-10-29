package parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class ExportExtractorTest {

	private static final String CONTENT = "RFC1155-SMI DEFINITIONS ::= BEGIN\n" + "\n" + "EXPORTS\n"
			+ "        internet, directory, mgmt,\n"
			+ "        experimental, private, enterprises,\n"
			+ "        OBJECT-TYPE, ObjectName, ObjectSyntax, SimpleSyntax,\n"
			+ "        ApplicationSyntax, NetworkAddress, IpAddress,\n"
			+ "        Counter, Gauge, TimeTicks, Opaque;\n" + "\n" + " -- the path to the root\n"
			+ "\n" + " internet      OBJECT IDENTIFIER ::= { iso org(3) dod(6) 1 }\n" + "\n"
			+ " directory     OBJECT IDENTIFIER ::= { internet 1 }\n" + "\n"
			+ " mgmt          OBJECT IDENTIFIER ::= { internet 2 }\n" + "\n"
			+ " experimental  OBJECT IDENTIFIER ::= { internet 3 }\n";
	private ExportExtractor tested = new ExportExtractor();

	@Test
	public void shouldExportList() throws Exception {
		//when
		List<String> actual = tested.getExportList(CONTENT);
		//then
		assertThat(actual).hasSize(17);
		actual.forEach(a -> assertThat(a).doesNotMatch("\\s+"));
	}
}