package Parser;

public class ControlConditionNode implements SyntaxTree{
    boolean activated;
    BitEstatus Condition;


    public ControlConditionNode(boolean activated, BitEstatus bit) {
        this.activated = activated;
        Condition = bit;
    }

    public SyntaxTreeType getType() {
        return null;
    }

    public SyntaxTree getChildren(SyntaxTreeType type) {
        return null;
    }
}
