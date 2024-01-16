import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

public class Main {
    public static void main(String[] args)  {
        if(args.length != 1){
            System.out.println("Args: file_to_transpile.asi");
            return;
        }
        String src ="";
        try {
            src = Files.readString(Path.of(args[0]), java.nio.charset.Charset.defaultCharset());
        }catch (Exception e){
            System.out.println("No se pudo leer el archivo correctamente");
            return;
        }
        Tokenizer tokizer =new Tokenizer(src);
        Vector<Token> tokens = tokizer.getTokens();
        for (Token token : tokens){
            System.out.println(token);
        }
    }
}