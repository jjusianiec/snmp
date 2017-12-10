package com.jjusianiec.put.bsr.snmp.parser;

public class MibFileWithoutCommentsReader {
	private final FileReader fileReader = new FileReader();

	public String readFileWithoutComments(String path) {
		String content = fileReader.readFile(path);
		if (content == null) {
			return null;
		}
		return content.replaceAll("--.*", "");
	}
}
