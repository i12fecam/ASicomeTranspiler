package Lexer;

public class Range {
    CharPos start;
    CharPos end;

    public Range(CharPos start, CharPos end) {
        this.start = start;
        this.end = end;
    }
    public Range(CharPos start) {
        this.start = start;
        this.end = start;
    }
}
