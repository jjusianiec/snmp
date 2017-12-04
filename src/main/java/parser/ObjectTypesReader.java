package parser;

import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectTypesReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectTypesReader.class);
	private static final Pattern OBJECT_TYPE_PATTERN = Pattern.
			compile("(\\S+)\\s+?OBJECT-TYPE\\s+?SYNTAX\\s+?(.*?)\\s+?ACCESS\\s+?(\\S+?)"
					+ "\\s+?STATUS\\s+?(\\S+?)\\s+?DESCRIPTION\\s+?\"(.*?)\"\\s*?::=\\s*?"
					+ "\\{\\s*?(.*?)\\s*?}", Pattern.DOTALL);

	private final ObjectTypeExtractor objectTypeListExtractor = new ObjectTypeExtractor();

	public Map<String, OIdRaw> readObjectTypes(String content) {
		List<String> objectTypes = objectTypeListExtractor.getObjectTypeList(content);
		Matcher matches = OBJECT_TYPE_PATTERN.matcher(content);
		Map<String, OIdRaw> map = newHashMap();
		while (matches.find()) {
			OIdRaw oIdRaw = OIdRaw.builder().type(OIdType.TYPE).build();
			oIdRaw.setRawId(matches.group(1));
			oIdRaw.setSyntax(matches.group(2));
			oIdRaw.setAccess(matches.group(3));
			oIdRaw.setStatus(matches.group(4));
			oIdRaw.setDescription(matches.group(5));
			oIdRaw.setType(OIdType.TYPE);
			map.put(oIdRaw.getRawId(), oIdRaw);
		}
		return map;
	}
}
