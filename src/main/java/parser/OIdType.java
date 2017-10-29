package parser;

public enum OIdType {
	TYPE("OBJECT-TYPE"), IDENTIFIER("OBJECT-IDENTIFIER");

	private final String value;

	OIdType(String s) {
		this.value = s;
	}
}
