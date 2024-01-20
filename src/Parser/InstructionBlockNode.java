package Parser;

import java.util.Vector;

public class InstructionBlockNode implements SyntaxTree {

    Vector<InstructionNode> childs_ = new Vector<InstructionNode>();
    int index=0;
    InstructionBlockNode(Vector<InstructionNode> childs){

        childs_ = childs;
    }

    @Override
   public SyntaxTreeType getType() {
        return SyntaxTreeType.InstructionBlockNode;
    }

    @Override
    public SyntaxTree getChildren(SyntaxTreeType type) {
        if(type == SyntaxTreeType.InstructionNode){
            if(index>=childs_.size()){
                index=0;
                return null;
            }
            else{
                SyntaxTree ch =childs_.get(index);
                index++;
                return ch;
            }
        }
        //TODO error
        return null;
    }
}
