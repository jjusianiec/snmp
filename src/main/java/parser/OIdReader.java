package parser;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OIdReader {
	private static final Pattern OBJECT_IDENTIFIER_PATTERN = Pattern.
			compile("(\\S+)\\s*?OBJECT IDENTIFIER\\s*?::=\\s*?\\{\\s*?(.*?)\\s*?}", Pattern.DOTALL);

	public Map<String, OIdRaw> readIdentifiers(String content) {
		Matcher matcher = OBJECT_IDENTIFIER_PATTERN.matcher(content);
		Map<String, OIdRaw> map = newHashMap();
		while (matcher.find()) {
			map.put(matcher.group(1),
					OIdRaw.builder().parentIdRaw(matcher.group(2)).type(OIdType.IDENTIFIER).build());
		}
		return map;
	}
}
