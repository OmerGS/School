import factory.FileParserFactory;
import factory.ImageFileParserFactory;
import factory.PdfFileParserFactory;
import factory.TextFileParserFactory;

public class Main{
    public static void main(String[] args){
        FileParserFactory txt = new TextFileParserFactory();
        txt.createParser().parse("document.txt");


        FileParserFactory pdf = new PdfFileParserFactory();
        pdf.createParser().parse("rapport.pdf");

        FileParserFactory img = new ImageFileParserFactory();
        img.createParser().parse("photo.jpg");

    }
}