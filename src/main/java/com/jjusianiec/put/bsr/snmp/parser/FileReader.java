package com.jjusianiec.put.bsr.snmp.parser;

import java.io.IOException;
import java.net.URL;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class FileReader {

	public String readFile(String path) {
		URL resource = Resources.getResource(path);
		String file = null;
		try {
			file = Resources.toString(resource, Charsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
}