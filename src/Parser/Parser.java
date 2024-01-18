package Parser;

import Lexer.Token;
import Lexer.TokenType;

import java.util.Vector;

public class Parser {
    private Vector<Token> _tokens;
    private int _curr;
    public Parser(Vector<Token> tokens){
        _tokens = tokens;
    }

    Token Current(){
        return _tokens.get(_curr);
    }

    Token NextToken(){
        if(_curr+1>_tokens.size()){
            return _tokens.get(_curr);
        }
        _curr++;
        return _tokens.get(_curr+1);

    }

    Token Match(TokenType tokenType){
        if(Current().getType() == tokenType){
          return NextToken();
        }
        return new Token(tokenType,"",-1,-1);
    }

    Vector<SyntaxTree> Parse(){
        Vector<SyntaxTree> res = new Vector<>();
        while (Current().getType()!=TokenType.EOF){
            res.add(ParseBlock());
        }
        return res;
    }

    SyntaxTree ParseBlock(){
        if(Current().getType() == TokenType.microistruccionesRW){
            return ParseMicroInstructionBlock();
        } else if (Current().getType() == TokenType.variablesRW) {
            return ParseVariablesBlock();
        }
        else if(Current().getType() == TokenType.programaRW){
            return ParseProgramBlock();
        }

        //TODO error?
        return null;


    }

    private SyntaxTree ParseMicroInstructionBlock() {

    }


}
