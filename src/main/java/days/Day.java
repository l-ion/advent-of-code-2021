package days;

import utils.Utils;

import java.util.List;

public class Day {

    private final int dayNo;
    private final String resourceFileName;

    public Day(int dayNo) {
        this.dayNo = dayNo;
        this.resourceFileName = "day" + dayNo + ".txt";
    }

    public List<String> getTodaysInput() {
        return Utils.getInput(resourceFileName);
    }

    public void printSolvingPart1() {
        System.out.print("Solving day " + dayNo + " - part 1... => ");
    }

    public void printSolvingPart2() {
        System.out.print("Solving day " + dayNo + " - part 2... => ");

    }
}
