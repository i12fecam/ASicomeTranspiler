package Parser;

import java.util.Vector;

public class InstructionLineNode extends SyntaxTree{
    InstructionLineControlNode control;
    Vector<MicroInstructionNode> microIntructions;
}
