package whitespace;

/**
 * An assembly instruction compiled from Whitespace source code.
 * This class provides the generic tools used by any instruction, such as the
 * label.
 *
 * @author arthu
 */
public abstract class Instruction {

    // The label associated with this instruction. -1 if not set, otherwise >=0
    private int label;

    // The name of the instruction. This shall be overriden by child classes.
    private String name;

    public Instruction() {
        label = -1;
        name = "AbstractInstruction";
    }

    public void setLabel(int newLabel) {
        this.label = newLabel;
    }

    public String toString() {
        return (label >= 0 ? label : "") + this.name;
    }

}
