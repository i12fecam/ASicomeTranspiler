package Parser;

import Lexer.Token;

public class SyntaxException extends Exception{
    Token token_;

    public SyntaxException(String message, Token token_) {
        super(message);
        this.token_ = token_;
    }

    public Token getToken_() {
        return token_;
    }
}
