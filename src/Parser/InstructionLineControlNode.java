package Parser;

public class InstructionLineControlNode implements SyntaxTree{
    private final ControlConditionsNode conditions;
    private final ControlResultsNode results;

    public InstructionLineControlNode(ControlConditionsNode conditions, ControlResultsNode results) {
        this.conditions = conditions;
        this.results = results;
    }

    public SyntaxTreeType getType() {
        return SyntaxTreeType.InstructionLineControl;
    }

    public SyntaxTree getChildren(SyntaxTreeType type) {

        if(type == SyntaxTreeType.ControlConditions){
            return conditions;
        }
        else if(type == SyntaxTreeType.ControlResults){
            return results;
        }
        else{
            //TODO error
            return null;
        }
    }
}
