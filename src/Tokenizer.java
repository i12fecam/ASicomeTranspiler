import java.util.Currency;
import java.util.Vector;

public class Tokenizer {
    private final String _src;
    private int _curr = 0;
    private int _currChar = 0;
    private int _currLine = 0;
    private final int _end;

    public Tokenizer(String src) {
        _src =src;
        _end = src.length();
    }
    private char current(){
        return _src.charAt(_curr);
    }
    private char peek(){
        if (_curr+1>=_end){
            return '\0';
        }
        return _src.charAt(_curr+1);
    }
    private void advance(){
        _curr++;
        _currChar++;
    }
    private void foundNewLine(){
        _curr++;
        _currChar=0;
        _currLine+=1;
    }

     public Vector<Token> getTokens(){
        Vector<Token> res = new Vector<>();
        boolean done = false;

        while(!done){
            switch (current()){
                case ' ':
                case '\t':
                    handleWhiteSpace();
                case '\n':
                    foundNewLine();
                    break;
                case '\\':
                    handleSingleComment();
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
                        //TODO gestionar error
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
                        //TODO gestionar error
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
        while (Character.isLetter(current())){
            advance();
        }
        String text= _src.substring(start,start+lenght);

        if(text.equals("microinstrucciones") || text.equals("variables") || text.equals("program")){
            res.add(new Token(TokenType.ReservedWord,text,startLine,startChar));
        }else{
            res.add(new Token(TokenType.Word,text,startLine,startChar));
        }

    }

    private void handleDecimalNumber(Vector<Token> res) {
        int start=_curr;
        int startChar=_currChar;
        int startLine=_currLine;
        int lenght=0;
        while (Character.isDigit(current())){
            advance();
        }
        res.add(new Token(TokenType.DecimalNumber,_src.substring(start,start+lenght),startLine,startChar));

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
        boolean found = false;
        advance();
        while(!found){
            if(current() == '\n'){
                break;
            }
        }
     }
}
