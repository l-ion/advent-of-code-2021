package days;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Binary Diagnostic
 */
public class Day3 extends Day {

    public Day3(int dayNo) {
        super(dayNo);
    }

    public int solvePart1() {
        List<String> todaysInput = getTodaysInput();
        String gammaRate = "";
        String epsilonRate = "";
        for (int i = 0; i < todaysInput.get(0).length(); i++) {
            gammaRate += determineMostCommonValueAtPosition(todaysInput, i);
            epsilonRate += determineLeastCommonValueAtPosition(todaysInput, i);
        }
        int powerConsumption = Integer.parseInt(gammaRate, 2) * Integer.parseInt(epsilonRate, 2);
        return powerConsumption;
    }

    public String determineMostCommonValueAtPosition(List<String> bits, int position) {
        int oneCount = 0;
        int zeroCount = 0;
        for (String line : bits) {
            if (Character.getNumericValue(line.charAt(position)) == 1) {
                oneCount++;
            } else {
                zeroCount++;
            }
        }
        return oneCount >= zeroCount ? "1" : "0";
    }

    public String determineLeastCommonValueAtPosition(List<String> bits, int position) {
        int oneCount = 0;
        int zeroCount = 0;
        for (String line : bits) {
            if (Character.getNumericValue(line.charAt(position)) == 1) {
                oneCount++;
            } else {
                zeroCount++;
            }
        }
        return zeroCount <= oneCount ? "0" : "1";
    }

    public int solvePart2() {
        List<String> bits = getTodaysInput();
        String oxygenGeneratorRating = "";
        String co2ScrubberRating = "";
        for (int i = 0; i < bits.get(0).length(); i++) {
            int position = i;
            bits = filterListForOxygenGeneratorRating(bits, position);
            if (bits.size() == 1) {
                oxygenGeneratorRating = bits.get(0);
                break;
            }
        }
        List<String> bitsRefill = getTodaysInput();
        for (int i = 0; i < bitsRefill.get(0).length(); i++) {
            int position = i;
            bitsRefill = filterListForCo2ScrubberRating(bitsRefill, position);
            if (bitsRefill.size() == 1) {
                co2ScrubberRating = bitsRefill.get(0);
                break;
            }
        }
        int lifeSupportRating = Integer.parseInt(oxygenGeneratorRating, 2) * Integer.parseInt(co2ScrubberRating, 2);
        return lifeSupportRating;
    }

    public List<String> filterListForOxygenGeneratorRating(List<String> bits, int position) {
        return bits.stream().filter(line -> String.valueOf(line.charAt(position)).equals(determineMostCommonValueAtPosition(bits, position))).collect(Collectors.toList());
    }

    public List<String> filterListForCo2ScrubberRating(List<String> bits, int position) {
        return bits.stream().filter(line -> String.valueOf(line.charAt(position)).equals(determineLeastCommonValueAtPosition(bits, position))).collect(Collectors.toList());
    }

}
