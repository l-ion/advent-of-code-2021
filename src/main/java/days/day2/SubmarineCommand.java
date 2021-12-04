package days.day2;

import java.util.function.IntBinaryOperator;

public enum SubmarineCommand {

    FORWARD((x, y) -> x + y),
    DOWN((x, y) -> x + y),
    UP((x, y) -> x - y);

    private final IntBinaryOperator binaryOperator;

    SubmarineCommand(IntBinaryOperator binaryOperator) {
        this.binaryOperator = binaryOperator;
    }

    public int apply(int x, int y) {
        return binaryOperator.applyAsInt(x, y);
    }

}
