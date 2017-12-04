package parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Lists.newArrayList;

public class ObjectTypeExtractor {
	private static final Pattern OBJECT_TYPE_DECLARATION_REGEX = Pattern
			.compile("(\\S+\\s+?OBJECT-TYPE\\s+?SYNTAX.*?::=.*?\\{\\s*?.*?\\s*?})", Pattern.DOTALL);

	public List<String> getRawObjectTypeDeclarationStringList(String content) {
		Matcher matches = OBJECT_TYPE_DECLARATION_REGEX.matcher(content);
		List<String> toReturn = newArrayList();
		while (matches.find()) {
			toReturn.add(matches.group());
		}
		return toReturn;
	}
}
