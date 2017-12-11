package com.jjusianiec.put.bsr.snmp.parser;

import java.util.List;
import java.util.Map;

import com.jjusianiec.put.bsr.snmp.parser.model.MibTree;
import com.jjusianiec.put.bsr.snmp.parser.model.OIdRaw;

public class MibParser {

	private final ImportsReader importsReader = new ImportsReader();
	private final MibFileWithoutCommentsReader mibFileWithoutCommentsReader = new MibFileWithoutCommentsReader();
	private final OIdReader oIdReader = new OIdReader();
	private final ObjectTypesReader objectTypeReader = new ObjectTypesReader();

	public MibTree parseMib(String path) {
		String content = mibFileWithoutCommentsReader.readFileWithoutComments(path);
		Map<String, List<String>> fileToImports = importsReader.readImports(content);
		Map<String, OIdRaw> identifierToOId = oIdReader.readIdentifiers(content);
		Map<String, OIdRaw> objectTypeIdToOId = objectTypeReader.readObjectTypes(content);
		return null;
	}
}
