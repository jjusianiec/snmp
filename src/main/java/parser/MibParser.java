package parser;

public class MibParser {

	private final ImportsReader importsReader = new ImportsReader();
	private final MibReader mibReader = new MibReader();


	public MibTree parseMib(String path) {
		String content = mibReader.readFileWithoutComments(path);
		importsReader.readImports(content);

		return null;
	}
}
