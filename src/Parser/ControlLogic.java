package Parser;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

public class ControlLogic {
    private ControlResult result;

    private Set<BitStatus> conditions = new HashSet<BitStatus>();

    public void setResult(ControlResult result) {
        this.result = result;
    }

    public void addCondition(BitStatus status){
        conditions.add(status);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlLogic that = (ControlLogic) o;
        return result == that.result && Objects.equals(conditions, that.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, conditions);
    }
}
