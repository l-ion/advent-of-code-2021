package days.day2;

import days.Day;

import java.util.List;

/**
 * Dive
 */
public class Day2 extends Day {

    public Day2(int dayNo) {
        super(dayNo);
    }

    public int solvePart1() {
        List<String> commands = getTodaysInput();
        int horizontalPosition = 0;
        int depth = 0;
        for (String command : commands) {
            String[] commandArray = command.split(" ");
            String actualCommand = commandArray[0];
            Integer x = Integer.valueOf(commandArray[1]);
            switch (actualCommand) {
                case "forward" -> {
                    horizontalPosition = SubmarineCommand.FORWARD.apply(horizontalPosition, x);
                    break;
                }
                case "down" -> {
                    depth = SubmarineCommand.DOWN.apply(depth, x);
                    break;
                }
                case "up" -> {
                    depth = SubmarineCommand.UP.apply(depth, x);
                    break;
                }
                default -> {
                    break;
                }
            }
        }
        return horizontalPosition * depth;
    }

    public int solvePart2() {
        List<String> commands = getTodaysInput();
        int aim = 0;
        int horizontalPosition = 0;
        int depth = 0;
        for (String command : commands) {
            String[] commandArray = command.split(" ");
            String actualCommand = commandArray[0];
            Integer x = Integer.valueOf(commandArray[1]);
            switch (actualCommand) {
                case "forward" -> {
                    horizontalPosition = SubmarineCommand.FORWARD.apply(horizontalPosition, x);
                    depth = SubmarineCommand.FORWARD.apply(depth, aim * x);
                    break;
                }
                case "down" -> {
                    aim = SubmarineCommand.DOWN.apply(aim, x);
                    break;
                }
                case "up" -> {
                    aim = SubmarineCommand.UP.apply(aim, x);
                    break;
                }
                default -> {
                    break;
                }
            }
        }
        return horizontalPosition * depth;
    }

}
