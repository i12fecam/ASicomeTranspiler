package Parser;

public class InstructionLineControlNode implements SyntaxTree{
    ControlConditionsNode conditions;
    ControlResultsNode results;

    public SyntaxTreeType getType() {
        return null;
    }

    public SyntaxTree getChildren(SyntaxTreeType type) {
        return null;
    }
}
