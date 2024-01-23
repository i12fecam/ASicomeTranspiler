package Lexer;

public class CharPos {
    int line;
    int col;
    int absolutepos;

    public CharPos(int line, int col, int absolutepos) {
        this.line = line;
        this.col = col;
        this.absolutepos = absolutepos;
    }

    public int getAbsolutepos() {
        return absolutepos;
    }

    public void setAbsolutepos(int absolutepos) {
        this.absolutepos = absolutepos;
    }

    public CharPos(int line, int col) {
        this.line = line;
        this.col = col;
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }

    public void advanceChar() {
        col++;
        absolutepos++;
    }

    public void registerNewLine() {
        col=0;
        line+=1;
        absolutepos++;
    }
}
