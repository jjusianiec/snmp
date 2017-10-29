package parser;

import java.util.List;
import java.util.Map;

public class ImportsReader {
	private final ImportsRegexExtractor importsRegexExtractor = new ImportsRegexExtractor();

	public Map<String, List<String>> readImports(String root) {
		return importsRegexExtractor.extractImports(root);
	}
}
