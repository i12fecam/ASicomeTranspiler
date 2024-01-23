package Parser;

import java.util.Objects;

public class BitStatus {
    private SicomeBit bit;
    private boolean activated;

    public BitStatus(SicomeBit bit, boolean activated) {
        this.bit = bit;
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitStatus bitStatus = (BitStatus) o;
        return activated == bitStatus.activated && bit == bitStatus.bit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bit, activated);
    }

    public SicomeBit getBit() {
        return bit;
    }

    public boolean isActivated() {
        return activated;
    }
}
