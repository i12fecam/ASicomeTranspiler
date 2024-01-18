package Lexer;


public class Token {
    private TokenType type;
    private String value;
    private int line;
    private int col;



    public Token(TokenType type, String value, int line, int col) {
        this.type = type;
        this.value = value;
        this.line = line;
        this.col = col;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Lexer.Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", line=" + line +
                ", col=" + col +
                '}';
    }
}
