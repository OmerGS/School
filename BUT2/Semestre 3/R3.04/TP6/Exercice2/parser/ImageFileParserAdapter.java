package parser;
import lib.ImageExternal;

public class ImageFileParserAdapter implements FileParser {
    public void parse(String file){
        new ImageExternal().parse(file);
    }
}
