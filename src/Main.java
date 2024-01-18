import Lexer.Token;
import Lexer.Tokenizer;

import java.io.*;
import java.util.Vector;

public class Main {
    public static void main(String[] args)  {
        if(args.length != 1){
            System.out.println("Args: file_to_transpile.asi");
            return;
        }
        String src ="";
        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("prueba.asi");
            src = readFromInputStream(inputStream);
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
    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}