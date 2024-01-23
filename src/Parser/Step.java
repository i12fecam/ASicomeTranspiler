package Parser;

import java.util.Vector;

public class Step {
    private ControlLogic logic;
    private Vector<MicroInstruction> mInstrs = new Vector<>();

    public Step(ControlLogic logic) {
        this.logic = logic;
    }

    public boolean Add(MicroInstruction mi){
        //TODO hacer que no haya incompatiblidades entre instrucciones
        return false;
    }

    public void setLogic(ControlLogic logic) {
        this.logic = logic;
    }


}
