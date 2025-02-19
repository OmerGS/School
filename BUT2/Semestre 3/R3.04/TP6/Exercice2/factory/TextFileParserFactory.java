package factory;
import parser.FileParser;
import parser.TextFileParser;

public class TextFileParserFactory extends FileParserFactory {
    public FileParser createParser(){
        return new TextFileParser();
    }
}
