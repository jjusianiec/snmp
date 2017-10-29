package parser;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OIdReader {
	private static final Pattern OBJECT_IDENTIFIER_PATTERN = Pattern.
			compile("(\\S+)\\s*?OBJECT IDENTIFIER\\s*?::=\\s*?\\{\\s*?(.*?)\\s*?}", Pattern.DOTALL);

	public Map<String, OId> readIdentifiers(String content) {
		Matcher matcher = OBJECT_IDENTIFIER_PATTERN.matcher(content);
		Map<String, OId> map = newHashMap();
		while (matcher.find()) {
			map.put(matcher.group(1),
					OId.builder().rawId(matcher.group(2)).type(OIdType.IDENTIFIER).build());
		}
		return map;
	}
}
