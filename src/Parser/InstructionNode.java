package Parser;

import java.util.Vector;

public class InstructionNode {

    InstructionIdentifierNode name;
    InstructionArgumentNode args;
    Vector<InstructionLineNode> lines;
}
