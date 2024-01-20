package Parser;

import java.util.Vector;

public class InstructionLineNode implements SyntaxTree{
    InstructionLineControlNode control;
    Vector<MicroInstructionNode> microIntructions;

    public SyntaxTreeType getType() {
        return null;
    }

    public SyntaxTree getChildren(SyntaxTreeType type) {
        return null;
    }
}
