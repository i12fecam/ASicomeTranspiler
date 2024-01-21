package Parser;

public class ControlResultsNode implements SyntaxTree{
    ControlResult result;

    public ControlResultsNode(ControlResult res) {
        result= res;
    }

    public SyntaxTreeType getType() {
        return SyntaxTreeType.ControlResults;
    }

    public SyntaxTree getChildren(SyntaxTreeType type) {
        return null;
    }
}
