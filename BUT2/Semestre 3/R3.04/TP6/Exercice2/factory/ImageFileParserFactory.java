package factory;
import parser.FileParser;
import parser.ImageFileParserAdapter;

public class ImageFileParserFactory extends FileParserFactory {
    public FileParser createParser(){
        return new ImageFileParserAdapter();
    }
}
