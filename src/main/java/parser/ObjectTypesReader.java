package parser;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectTypesReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectTypesReader.class);
	private static final Pattern OBJECT_TYPE_PATTERN = Pattern.
			compile("(\\S+)\\s+?OBJECT-TYPE\\s+?SYNTAX\\s+?(.*?)\\s+?ACCESS\\s+?(\\S+?)"
					+ "\\s+?STATUS\\s+?(\\S+?)\\s+?DESCRIPTION\\s+?\"(.*)\"\\s*?::=\\s*?"
					+ "\\{\\s*?(.*)\\s*?}", Pattern.DOTALL);

	private final SyntaxBuilder syntaxBuilder = new SyntaxBuilder();

	public Map<String, OId> readObjectTypes(String content) {
		Matcher matches = OBJECT_TYPE_PATTERN.matcher(content);
		Map<String, OId> map = newHashMap();
		while (matches.find()) {
			OId oId = OId.builder().type(OIdType.TYPE).build();
			oId.setSyntax();
			LOGGER.info(matches.group(1) + ", " + matches.group(2) + ", " + matches.group(3) + ", "
					+ matches.group(4) + ", " + matches.group(5) + ", " + matches.group(6));
		}
		return map;
	}
}
