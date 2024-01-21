package Parser;

import java.util.Vector;

public class ControlConditionsNode implements SyntaxTree {
    Vector<ControlConditionNode> conditions;
    int index=0;
    public ControlConditionsNode(Vector<ControlConditionNode> conditions) {
        this.conditions = conditions;
    }

    public SyntaxTreeType getType() {
        return SyntaxTreeType.ControlConditions;
    }

    public SyntaxTree getChildren(SyntaxTreeType type) {

        if(type == SyntaxTreeType.ControlCondition){
            if(index>=conditions.size()){
                return null;
            }
            else{
                ControlConditionNode c =conditions.get(index);
                index++;
                return c;
            }
        }
        else{
            //TODO error
            return null;
        }
    }
}
