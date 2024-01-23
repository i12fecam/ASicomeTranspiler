package Parser;

import java.util.Vector;

public class ControlLogicManager {

    static ControlLogicManager instance_;

    private final Vector<ControlLogic> logics_ = new Vector<>();


    ControlLogicManager(){
        throw new RuntimeException("Uso indebido de singleton");
    }

    ControlLogicManager getInstance(){
        if(instance_==null){
            instance_ = new ControlLogicManager();
        }
        return instance_;
    }

    int addControlLogic(ControlLogic cl){
        if(logics_.size()>16){
            //TODO error de demasiadas lógicas de bifurcación
        }
        if(logics_.contains(cl)){
            logics_.add(cl);
        }//TODO hacer que devuelva el numero donde se guarda el controlador
        return 0;
    }


}
