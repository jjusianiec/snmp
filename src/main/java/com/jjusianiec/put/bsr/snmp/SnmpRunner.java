package com.jjusianiec.put.bsr.snmp;

import com.jjusianiec.put.bsr.snmp.parser.MibParser;
import com.jjusianiec.put.bsr.snmp.parser.model.MibTree;

public class SnmpRunner {

	public static void main(String[] args) {
		MibTree mibTree = new MibParser().parseMib("mibs/RFC1213-MIB");
	}
}
