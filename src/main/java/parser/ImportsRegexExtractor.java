package parser;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportsRegexExtractor {
	private static final Pattern IMPORTS_FROM_FILE_PATTERN = Pattern
			.compile("IMPORTS(.*?;)", Pattern.DOTALL);
	private static final Pattern SINGLE_FROM_PATTERN = Pattern
			.compile("(.*?) FROM (\\S*)", Pattern.DOTALL);
	private static final Logger LOGGER = LoggerFactory.getLogger(ImportsRegexExtractor.class);

	public Map<String, List<String>> extractImports(String root) {
		Matcher m = IMPORTS_FROM_FILE_PATTERN.matcher(root);
		Map<String, List<String>> toReturn = newHashMap();
		if (m.find()) {
			String imports = m.group(1).replace("\n", "").replace("\r", "").replace(";", "")
					.replaceAll("\\s+", " ");
			Matcher matcher = SINGLE_FROM_PATTERN.matcher(imports);
			while (matcher.find()) {
				toReturn.put(matcher.group(2), getImportList(matcher.group(1)));
			}
		}
		return toReturn;
	}

	private List<String> getImportList(String group) {
		String[] imports = group.replaceAll("\\s+", "").split(",");
		return newArrayList(imports);
	}
}
