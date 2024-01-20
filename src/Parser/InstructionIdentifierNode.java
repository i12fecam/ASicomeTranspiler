package Parser;

import Lexer.Token;

import java.lang.reflect.Type;

public class InstructionIdentifierNode implements SyntaxTree{

    Token identfier;
    public InstructionIdentifierNode(Token tk) {
        identfier=tk;
    }

    @Override
    public SyntaxTreeType getType() {
        return SyntaxTreeType.InstructionIdentifier;
    }

    @Override
    public SyntaxTree getChildren(SyntaxTreeType type) {
        return null;
    }
}
