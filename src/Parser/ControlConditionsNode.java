package Parser;

import java.util.Vector;

public class ControlConditionsNode implements SyntaxTree {
    Vector<ControlCondition> condtions;

    public SyntaxTreeType getType() {
        return null;
    }

    public SyntaxTree getChildren(SyntaxTreeType type) {
        return null;
    }
}
