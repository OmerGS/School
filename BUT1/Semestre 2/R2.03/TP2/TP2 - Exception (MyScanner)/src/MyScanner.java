import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.BufferedReader;

public class MyScanner {
    private boolean isClosed;

    private BufferedReader br;


    public MyScanner(File source)throws FileNotFoundException{
        if(source != null){
            this.isClosed = true;
            FileReader fr = new FileReader(source);
            this.br = new BufferedReader(fr);
            this.isClosed = false;
        }
        
        else{
            throw new RuntimeException("Fichier introuvable");
        }
    }

    public void close(){
        try{
            this.br.close();
            this.isClosed = true;
        }
        catch(IOException e){
            System.out.println("Le buffer n'est pas ferme");
        }   
    }

    public String nextLine()throws IllegalStateException, NoSuchElementException{
        String ret = null;
        if(this.isClosed)throw new IllegalStateException("MyScanner est ferme");
        try{
            ret = this.br.readLine();
            if(ret == null)throw new NoSuchElementException("Rien a lire");
        }
        catch(IOException e){
            System.out.println("Lecture fichier");
        }
        return ret;
    }

    public boolean hasNextLine () throws IllegalStateException{
        boolean ret = true;
        int res = 0;
        if(this.isClosed)throw new IllegalStateException("MyScanner est ferme");
        try{
            this.br.mark(100);
            res = this.br.read();
            if(res == -1) ret = false;
            this.br.reset();
        }
        catch(IOException e){

        }
        return ret;
    }

}