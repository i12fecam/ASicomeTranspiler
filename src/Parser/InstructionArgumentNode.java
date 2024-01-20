package Parser;

import Lexer.Token;
import Lexer.TokenType;

public class InstructionArgumentNode implements SyntaxTree {

    boolean isDir;
    public InstructionArgumentNode(Token tk) {
        if(tk.getType() == TokenType.dirRW){
            isDir=true;
        }
        else isDir = false;
    }

    public Boolean isDir(){
        return isDir;
    }
    public SyntaxTreeType getType() {
        return SyntaxTreeType.InstructionArgument;
    }

    public SyntaxTree getChildren(SyntaxTreeType type) {
        return null;
    }
}
