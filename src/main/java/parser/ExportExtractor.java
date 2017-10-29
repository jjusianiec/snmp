package parser;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExportExtractor {
	private static final Pattern EXPORTS_PATTERN = Pattern.
			compile("EXPORTS\\s*?(.*?);", Pattern.DOTALL);

	public List<String> getExportList(String content) {
		Matcher matches = EXPORTS_PATTERN.matcher(content);
		if (matches.find()) {
			String[] exports = matches.group(1).replaceAll("\\s+", "").replace(";", "").split(",");
			return newArrayList(exports);
		}
		return newArrayList();
	}
}
