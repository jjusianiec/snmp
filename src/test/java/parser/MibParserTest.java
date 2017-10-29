package parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class MibParserTest {


	private MibParser tested = new MibParser();

	@Test
	public void shouldParseMib() throws Exception {
		//when
		MibTree actual = tested.parseMib("mibs/RFC1213-MIB");
		//then

	}

}