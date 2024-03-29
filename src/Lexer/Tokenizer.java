package Lexer;

import java.util.Vector;

public class Tokenizer {
    private final String _src;
    private int _curr = 0;
    private int _currChar = 0;
    private int _currLine = 0;

    private CharPos _pos;

    //private CharPos _end;
    private final int _end;

    public Tokenizer(String src) {
        _src =src;
        _end = src.length();
    }
    private char current(){
        return _src.charAt(_curr);
    }
    private char peek(){

        return _src.charAt(_curr+1);
    }

    private boolean isFinished(){
        if (_pos.getAbsolutepos()+1>=_end){
            return true;
        }
        return false;
    }
    private void advance(){
        _pos.advanceChar();
    }
    private void foundNewLine(){
        _pos.registerNewLine();
    }

     public Vector<Token> getTokens() throws LexicalException {
        Vector<Token> res = new Vector<>();
        boolean done = false;

        while(!isFinished()){
            switch (current()){
                case ' ':
                case '\t':
                    handleWhiteSpace();
                    break;
                case '\n':
                    foundNewLine();
                    break;
                case '/':
                    if(peek() == '/'){
                        handleSingleComment();
                    }
                    else{
                        throw new LexicalException("Dangling '/' found",new Range(_pos));
                    }
                    break;
                case '{':
                    advance();
                    res.add(new Token(TokenType.LeftBracket,"{",_currLine,_currChar));
                    break;
                case '}':
                    advance();
                    res.add(new Token(TokenType.RightBracket,"}",_currLine,_currChar));
                    break;
                case '(':
                    advance();
                    res.add(new Token(TokenType.LeftParenthesis,"(",_currLine,_currChar));
                    break;
                case ')':
                    advance();
                    res.add(new Token(TokenType.RightParenthesis,")",_currLine,_currChar));
                    break;
                case ',':
                    advance();
                    res.add(new Token(TokenType.Comma,",",_currLine,_currChar));
                    break;
                case ';':
                    advance();
                    res.add(new Token(TokenType.SemiColon,";",_currLine,_currChar));
                    break;
                case '=':
                    advance();
                    res.add(new Token(TokenType.Equal,"=",_currLine,_currChar));
                    break;
                case '|':
                    advance();
                    res.add(new Token(TokenType.VerticalLine,"|",_currLine,_currChar));
                    break;
                case '!':
                    advance();
                    res.add(new Token(TokenType.ExclamationMark,"!",_currLine,_currChar));
                    break;
                case '-':
                    if(peek() == '>'){
                        advance();
                        advance();
                        res.add(new Token(TokenType.Arrow,"->",_currLine,_currChar));
                    }
                    else{
                        throw new LexicalException("Dangling '-' found",new Range(_pos));
                    }
                    break;
                case '\0' :
                    done = true;
                    break;
                default:
                    if( Character.isDigit(current()) ){
                        if(current() == '0' && peek() == 'x'){
                            handleExadecimalNumber(res);
                        }
                        else{
                            handleDecimalNumber(res);
                        }

                    }
                    else if(Character.isLetter(current())){
                        handleText(res);
                    }
                    else{
                        throw new LexicalException(String.format("Character not recognized:%c",current()),new Range(_pos));
                    }
            }
        }

        return res;
     }

    private void handleText(Vector<Token> res) {
        int start=_curr;
        int startChar=_currChar;
        int startLine=_currLine;
        int lenght=0;
        //TODO permitir caracteres mas especialitos como @ _ y numeros
        while (Character.isLetter(current()) || Character.isDigit(current()) || current() == '@' || current() == '_'){
            advance();
            lenght++;
        }
        String text= _src.substring(start,start+lenght);

        switch (text) {
            case "instrucciones" -> res.add(new Token(TokenType.instruccionesRW, text, startLine, startChar));
            case "variables" -> res.add(new Token(TokenType.variablesRW, text, startLine, startChar));
            case "program" -> res.add(new Token(TokenType.programaRW, text, startLine, startChar));
            case "dir" -> res.add(new Token(TokenType.dirRW,text,startLine,startChar));
            case "value" -> res.add(new Token(TokenType.valueRW,text,startLine,startChar));
            default -> res.add(new Token(TokenType.Word, text, startLine, startChar));
        }


    }

    private void handleDecimalNumber(Vector<Token> res) {
        int start=_curr;
        int startChar=_currChar;
        int startLine=_currLine;
        int lenght=0;
        while (Character.isDigit(current())){
            advance();
        }//TODO posible fuente de errores
        res.add(new Token(TokenType.DecimalNumber,_src.substring(start,start+lenght),startLine,startChar));
        advance();
    }

    private void handleExadecimalNumber(Vector<Token> res) {

        advance();
        advance();
        int start=_curr;
        int startChar=_currChar;
        int startLine=_currLine;
        int lenght=0;
        while(true){
            if(Character.isDigit(current())
                    || current() == 'A'
                    || current() == 'B'
                    || current() == 'C'
                    || current() == 'D'
                    || current() == 'E'
                    || current() == 'F'){
                advance();
                lenght++;
            }
            else{
                break;
            }
        }
        res.add(new Token(TokenType.HexNumber,_src.substring(start,start+lenght),startLine,startChar));
        advance();

    }

    private void handleWhiteSpace() {
        advance();
        while(true){
            if(current() == ' ' || current() == '\t'){
                advance();
            }
            else if(current() == '\n'){
                foundNewLine();
            }
            else{
                break;
            }
        }
    }

    private void handleSingleComment(){
        advance();
        advance();
        while(current() != '\n'){
            advance();
        }
     }
}
