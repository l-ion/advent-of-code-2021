package days;

import utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Day 1: Sonar Sweep
 */
public class Day1 extends Day{

    public Day1(int dayNo) {
        super(dayNo);
    }

    public int solvePart1() {
        List<Integer> depths = getTodaysInput().stream().map(Integer::parseInt).collect(Collectors.toList());
        int currentDepth = depths.get(0);
        int count = 0;
        for (int depth : depths) {
            if (currentDepth < depth) {
                count++;
            }
            currentDepth = depth;
        }
        return count;
    }

    public int solvePart2() {
        List<Integer> depths = getTodaysInput().stream().map(Integer::parseInt).collect(Collectors.toList());
        int currentSlidingWindow = depths.get(0) + depths.get(1) + depths.get(2);
        int count = 0;
        for (int i = 1; i < depths.size(); i++) {
            if (i < depths.size() - 2) {
                int nextSlidingWindow = depths.get(i) + depths.get(i + 1) + depths.get(i + 2);
                if (currentSlidingWindow < nextSlidingWindow) {
                    count++;
                }
                currentSlidingWindow = nextSlidingWindow;
            }
        }
        return count;
    }


}
