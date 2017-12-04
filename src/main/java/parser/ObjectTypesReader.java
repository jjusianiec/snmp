package parser;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Maps.newHashMap;
import static java.util.regex.Pattern.DOTALL;

public class ObjectTypesReader {
	private static final Pattern ID_PATTERN = Pattern.compile("(\\S+)\\s+?OBJECT-TYPE", DOTALL);
	private static final Pattern SYNTAX_PATTERN = Pattern.compile("SYNTAX\\s+?(.*?)\\r\\n", DOTALL);
	private static final Pattern ACCESS_PATTERN = Pattern.compile("ACCESS\\s+?(.*?)\\r\\n", DOTALL);
	private static final Pattern STATUS_PATTERN = Pattern.compile("STATUS\\s+?(.*?)\\r\\n", DOTALL);
	private static final Pattern INDEX_PATTERN = Pattern.compile("INDEX\\s+?\\{\\s+?(.*)\\s+?}\\s+?", DOTALL);
	private static final Pattern DESCRIPTION_PATTERN = Pattern
			.compile("DESCRIPTION\\s+?\"(.*?)\"\\s+?", DOTALL);
	private static final Pattern PARENT_ID_PATTERN = Pattern
			.compile("::=\\s*?\\{\\s*?(.*?)\\s*?}", DOTALL);


	private final ObjectTypeExtractor objectTypeDeclarationListExtractor = new ObjectTypeExtractor();

	public Map<String, OIdRaw> readObjectTypes(String content) {
		List<String> objectTypes = objectTypeDeclarationListExtractor
				.getRawObjectTypeDeclarationStringList(content);
		Map<String, OIdRaw> map = newHashMap();
		for (String objectTypeDeclaration : objectTypes) {
			OIdRaw oIdRaw = new OIdRaw();
			oIdRaw.setSyntax(getValueFromMatchOrReturnNull(SYNTAX_PATTERN, objectTypeDeclaration));
			oIdRaw.setAccess(getValueFromMatchOrReturnNull(ACCESS_PATTERN, objectTypeDeclaration));
			oIdRaw.setDescription(
					getValueFromMatchOrReturnNull(DESCRIPTION_PATTERN, objectTypeDeclaration));
			oIdRaw.setParentIdRaw(
					getValueFromMatchOrReturnNull(PARENT_ID_PATTERN, objectTypeDeclaration));
			oIdRaw.setStatus(getValueFromMatchOrReturnNull(STATUS_PATTERN, objectTypeDeclaration));
			oIdRaw.setType(OIdType.TYPE);
			oIdRaw.setIndex(getValueFromMatchOrReturnNull(INDEX_PATTERN, objectTypeDeclaration));
			map.put(getValueFromMatchOrThrowException(ID_PATTERN, objectTypeDeclaration), oIdRaw);
		}
		return map;
	}

	private String getValueFromMatchOrThrowException(Pattern pattern,
			String objectTypeDeclaration) {
		Matcher matcher = pattern.matcher(objectTypeDeclaration);
		if(matcher.find()){
			return matcher.group(1);
		}
		throw new NullPointerException("No id name for object[raw]: " + objectTypeDeclaration);
	}

	private String getValueFromMatchOrReturnNull(Pattern pattern,
			String objectTypeDeclaration) {
		Matcher matcher = pattern.matcher(objectTypeDeclaration);
		return matcher.find() ? matcher.group(1) : null;
	}


}
