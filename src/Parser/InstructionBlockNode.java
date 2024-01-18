package Parser;

import java.util.Vector;

public class InstructionBlockNode extends SyntaxTree {

    Vector<InstructionNode> childs_ = new Vector<InstructionNode>();

    InstructionBlockNode(Vector<InstructionNode> childs){
        childs_ = childs;
    }

}
