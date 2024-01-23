package Parser;

import java.util.Vector;

public class InstructionManager {
    private Vector<Instruction> instructions_= new Vector<>();

    public void add(Instruction i){
        instructions_.add(i);
    }
}
