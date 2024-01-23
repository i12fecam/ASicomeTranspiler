package Lexer;

public class LexicalException extends Exception{

    Range errorPos;

    public LexicalException(String message, Range errorPos) {
        super(message);
        this.errorPos = errorPos;
    }
}
