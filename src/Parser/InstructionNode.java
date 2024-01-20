package Parser;

import Lexer.TokenType;

import java.util.Vector;

public class InstructionNode implements SyntaxTree {

    InstructionIdentifierNode name;
    InstructionArgumentNode args;
    Vector<InstructionLineNode> lines;
    int index=0;
    public InstructionNode(InstructionIdentifierNode identifier, InstructionArgumentNode argument, Vector<InstructionLineNode> lines) {
        name=identifier;
        args=argument;
        this.lines=lines;
    }


    public SyntaxTree getChildren(SyntaxTreeType type) {
        if(type == SyntaxTreeType.InstructionIdentifier){
            return name;
        }
        else if(type == SyntaxTreeType.InstructionArgument){
            return args;
        }
        else if( type == SyntaxTreeType.InstructionLine){
            if(index >= lines.size()){
                index=0;
                return null;
            }
            else{
                SyntaxTree st = lines.get(index);
                index++;
                return st;
            }
        }
        else{
            //TODO eror
            return null;
        }
    }


   public SyntaxTreeType getType() {
        return SyntaxTreeType.InstructionNode;
    }
}
