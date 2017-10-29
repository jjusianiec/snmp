package parser;

import java.util.List;
import java.util.Map;

public class ImportsReader {
	private final ImportsRegexExtractor importsRegexExtractor = new ImportsRegexExtractor();

	public void readImports(String root) {
		Map<String, List<String>> imports = importsRegexExtractor.extractImports(root);
	}
}
