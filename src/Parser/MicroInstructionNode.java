package Parser;

public class MicroInstructionNode implements SyntaxTree{
    MicroInstruction mi;

    public MicroInstructionNode(MicroInstruction microInstruction) {
        mi=microInstruction;
    }

    public SyntaxTreeType getType() {
        return SyntaxTreeType.MicroInstruction;
    }

    public SyntaxTree getChildren(SyntaxTreeType type){ return null;
    }
}
