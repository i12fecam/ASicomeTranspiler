package Parser;

import java.util.Vector;

public class Instruction {
    private Vector<Step> steps = new Vector<Step>();
    private String name;

    private boolean isDir;

    Instruction(String identifier,String arg){
        name = identifier;
        if(arg.equals("dir")){
            isDir = true;
        }else if(arg.equals("value")){
            isDir = false;
        }
        else{
            //TODO erorr
        }
    }
    public void Add(Step step){
        steps.add(step);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }
}
