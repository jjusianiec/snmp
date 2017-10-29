package parser;

import java.util.List;
import java.util.Map;

public class MibParser {

	private final ImportsReader importsReader = new ImportsReader();
	private final MibReader mibReader = new MibReader();
	private final OIdReader oIdReader = new OIdReader();
	private final ObjectTypesReader objectTypeReader = new ObjectTypesReader();

	public MibTree parseMib(String path) {
		String content = mibReader.readFileWithoutComments(path);
		Map<String, List<String>> fileToImports = importsReader.readImports(content);
		Map<String, OId> identifierToOId = oIdReader.readIdentifiers(content);
		Map<String, OId> typeToOId = objectTypeReader.readObjectTypes(content);
		return null;
	}
}
