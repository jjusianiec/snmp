package parser;

public class MibReader {
	private final FileReader fileReader = new FileReader();

	public String readFileWithoutComments(String path) {
		String content = fileReader.readFile(path);
		if (content == null) {
			return null;
		}
		return content.replaceAll("--.*", "");
	}
}
