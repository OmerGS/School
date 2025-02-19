package factory;
import parser.FileParser;
import parser.PdfFileParser;

public class PdfFileParserFactory extends FileParserFactory {
    public FileParser createParser(){
        return new PdfFileParser();
    }
}
