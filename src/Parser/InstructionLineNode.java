package Parser;

import java.util.Vector;

public class InstructionLineNode implements SyntaxTree{
    private final InstructionLineControlNode control;
    private final Vector<MicroInstructionNode> microIntructions;
    private int index = 0;
    public InstructionLineNode(InstructionLineControlNode control, Vector<MicroInstructionNode> microInstr) {
        this.control = control;
        microIntructions = microInstr;
    }

    public SyntaxTreeType getType() {
        return SyntaxTreeType.InstructionLine;
    }

    public SyntaxTree getChildren(SyntaxTreeType type) {
        if(type == SyntaxTreeType.InstructionLineControl){
            return control;
        }
        else if(type == SyntaxTreeType.MicroInstruction){
            if(index >= microIntructions.size()+1){//TODO Comprobar si en efecto hay que poner aqui el +1
                index=0;
                return null;
            }
            else{
                MicroInstructionNode mc = microIntructions.get(index);
                index++;
                return mc;
            }
        }
        else{
            //TODO error
            return null;
        }

    }
}
