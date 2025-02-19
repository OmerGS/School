package factory;
import parser.FileParser;

public abstract class FileParserFactory {
    public abstract FileParser createParser();
}
