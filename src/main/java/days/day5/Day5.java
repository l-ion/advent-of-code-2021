package days.day5;

import days.Day;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day5 extends Day {

    public Day5(int dayNo) {
        super(dayNo);
    }

    public int solvePart1() {
        List<String> todaysInput = getTodaysInput();
        List<Map.Entry<Coordinate, Coordinate>> pairs = new ArrayList<>();
        for (String line : todaysInput) {
            String[] coordinatesRaw = line.split(" -> ");
            String[] c1Split = coordinatesRaw[0].split(",");
            String[] c2Split = coordinatesRaw[1].split(",");
            Coordinate c1 = new Coordinate(Integer.parseInt(c1Split[0]), Integer.parseInt(c1Split[1]));
            Coordinate c2 = new Coordinate(Integer.parseInt(c2Split[0]), Integer.parseInt(c2Split[1]));
            Map.Entry<Coordinate, Coordinate> entry = new AbstractMap.SimpleEntry<Coordinate, Coordinate>(c1, c2);
            pairs.add(entry);
        }
        List<Map.Entry<Coordinate, Coordinate>> filteredPairs = pairs.stream()
                .filter(entry -> isHorizontalLine(entry.getKey(), entry.getValue()) || isVerticalLine(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        int[][] diagram = new int[findGreatestX(filteredPairs) + 2][findGreatestY(filteredPairs) + 2];
        for (Map.Entry<Coordinate, Coordinate> entry : filteredPairs) {
            if (isHorizontalLine(entry.getKey(), entry.getValue())) {
                if (entry.getKey().getX() <= entry.getValue().getX()) {
                    int j = entry.getKey().getY();
                    for (int i = entry.getKey().getX(); i <= entry.getValue().getX(); i++) {
                        if (diagram[j][i] > 0) {
                            int value = diagram[j][i];
                            value++;
                            diagram[j][i] = value;
                        } else {
                            diagram[j][i] = 1;
                        }
                    }
                } else {
                    int j = entry.getKey().getY();
                    for (int i = entry.getKey().getX(); i >= entry.getValue().getX(); i--) {
                        if (diagram[j][i] > 0) {
                            int value = diagram[j][i];
                            value++;
                            diagram[j][i] = value;
                        } else {
                            diagram[j][i] = 1;
                        }
                    }
                }
            } else {
                if (entry.getKey().getY() <= entry.getValue().getY()) {
                    int i = entry.getKey().getX();

                    for (int j = entry.getKey().getY(); j <= entry.getValue().getY(); j++) {
                        if (diagram[j][i] > 0) {
                            int value = diagram[j][i];
                            value++;
                            diagram[j][i] = value;
                        } else {
                            diagram[j][i] = 1;
                        }
                    }
                } else {
                    int i = entry.getKey().getX();

                    for (int j = entry.getKey().getY(); j >= entry.getValue().getY(); j--) {
                        if (diagram[j][i] > 0) {
                            int value = diagram[j][i];
                            value++;
                            diagram[j][i] = value;
                        } else {
                            diagram[j][i] = 1;
                        }
                    }
                }
            }

        }

        return determineOverlappedPoints(diagram);
    }

    public int determineOverlappedPoints(int[][] diagram) {
        int result = 0;
        for (int i = 0; i < diagram.length; i++) {
            for (int j = 0; j < diagram[i].length; j++) {
                if (diagram[i][j] > 1)
                    result++;
            }
        }
        return result;
    }


    public boolean isVerticalLine(Coordinate c1, Coordinate c2) {
        return c1.getX() == c2.getX();
    }

    public boolean isHorizontalLine(Coordinate c1, Coordinate c2) {
        return c1.getY() == c2.getY();
    }

    private int findGreatestX(List<Map.Entry<Coordinate, Coordinate>> filteredPairs) {
        int x = 0;
        for (Map.Entry<Coordinate, Coordinate> entry : filteredPairs) {
            int maxXEntry = Math.max(entry.getKey().getX(), entry.getValue().getX());
            x = Math.max(x, maxXEntry);
        }
        return x;
    }

    private int findGreatestY(List<Map.Entry<Coordinate, Coordinate>> filteredPairs) {
        int y = 0;
        for (Map.Entry<Coordinate, Coordinate> entry : filteredPairs) {
            int maxYEntry = Math.max(entry.getKey().getY(), entry.getValue().getY());
            y = Math.max(y, maxYEntry);
        }
        return y;
    }

    public int solvePart2() {
        List<String> todaysInput = getTodaysInput();
        List<Map.Entry<Coordinate, Coordinate>> pairs = new ArrayList<>();
        for (String line : todaysInput) {
            String[] coordinatesRaw = line.split(" -> ");
            String[] c1Split = coordinatesRaw[0].split(",");
            String[] c2Split = coordinatesRaw[1].split(",");
            Coordinate c1 = new Coordinate(Integer.parseInt(c1Split[0]), Integer.parseInt(c1Split[1]));
            Coordinate c2 = new Coordinate(Integer.parseInt(c2Split[0]), Integer.parseInt(c2Split[1]));
            Map.Entry<Coordinate, Coordinate> entry = new AbstractMap.SimpleEntry<Coordinate, Coordinate>(c1, c2);
            pairs.add(entry);
        }

        int[][] diagram = new int[findGreatestX(pairs) + 2][findGreatestY(pairs) + 1];
        for (Map.Entry<Coordinate, Coordinate> entry : pairs) {
            if (isHorizontalLine(entry.getKey(), entry.getValue())) {
                if (entry.getKey().getX() <= entry.getValue().getX()) {
                    int j = entry.getKey().getY();
                    for (int i = entry.getKey().getX(); i <= entry.getValue().getX(); i++) {
                        if (diagram[j][i] > 0) {
                            int value = diagram[j][i];
                            value++;
                            diagram[j][i] = value;
                        } else {
                            diagram[j][i] = 1;
                        }
                    }
                } else {
                    int j = entry.getKey().getY();
                    for (int i = entry.getKey().getX(); i >= entry.getValue().getX(); i--) {
                        if (diagram[j][i] > 0) {
                            int value = diagram[j][i];
                            value++;
                            diagram[j][i] = value;
                        } else {
                            diagram[j][i] = 1;
                        }
                    }
                }
            } else if (isVerticalLine(entry.getKey(), entry.getValue())) {
                if (entry.getKey().getY() <= entry.getValue().getY()) {
                    int i = entry.getKey().getX();

                    for (int j = entry.getKey().getY(); j <= entry.getValue().getY(); j++) {
                        if (diagram[j][i] > 0) {
                            int value = diagram[j][i];
                            value++;
                            diagram[j][i] = value;
                        } else {
                            diagram[j][i] = 1;
                        }
                    }
                } else {
                    int i = entry.getKey().getX();

                    for (int j = entry.getKey().getY(); j >= entry.getValue().getY(); j--) {
                        if (diagram[j][i] > 0) {
                            int value = diagram[j][i];
                            value++;
                            diagram[j][i] = value;
                        } else {
                            diagram[j][i] = 1;
                        }
                    }
                }
            } else {
                if (entry.getKey().getX() == entry.getKey().getY() && entry.getValue().getX() == entry.getValue().getY()) {
                    if (entry.getKey().getX() < entry.getValue().getX()) {
                        for (int i = entry.getKey().getX(); i <= entry.getValue().getX(); i++) {
                            if (diagram[i][i] > 0) {
                                int val = diagram[i][i];
                                val++;
                                diagram[i][i] = val;
                            } else {
                                diagram[i][i] = 1;
                            }
                        }
                    } else {
                        for (int i = entry.getKey().getX(); i >= entry.getValue().getX(); i--) {
                            if (diagram[i][i] > 0) {
                                int val = diagram[i][i];
                                val++;
                                diagram[i][i] = val;
                            } else {
                                diagram[i][i] = 1;
                            }
                        }
                    }

                } else {
                    if (entry.getKey().getX() > entry.getValue().getX()) {
                        if (entry.getKey().getY() < entry.getValue().getY()) {
                            int j = entry.getKey().getY();
                            for (int i = entry.getKey().getX(); i >= entry.getValue().getX(); i--) {
                                if (diagram[j][i] > 0) {
                                    int value = diagram[j][i];
                                    value++;
                                    diagram[j][i] = value;
                                } else {
                                    diagram[j][i] = 1;
                                }
                                j++;
                            }
                        } else {
                            int j = entry.getKey().getY();
                            for (int i = entry.getKey().getX(); i >= entry.getValue().getX(); i--) {
                                if (diagram[j][i] > 0) {
                                    int value = diagram[j][i];
                                    value++;
                                    diagram[j][i] = value;
                                } else {
                                    diagram[j][i] = 1;
                                }
                                j--;
                            }
                        }
                    } else {
                        //
                        if (entry.getKey().getY() < entry.getValue().getY()) {
                            int j = entry.getKey().getY();
                            for (int i = entry.getKey().getX(); i <= entry.getValue().getX(); i++) {
                                if (diagram[j][i] > 0) {
                                    int value = diagram[j][i];
                                    value++;
                                    diagram[j][i] = value;
                                } else {
                                    diagram[j][i] = 1;
                                }
                                j++;
                            }
                        } else {
                            int j = entry.getKey().getY();
                            for (int i = entry.getKey().getX(); i <= entry.getValue().getX(); i++) {
                                if (diagram[j][i] > 0) {
                                    int value = diagram[j][i];
                                    value++;
                                    diagram[j][i] = value;
                                } else {
                                    diagram[j][i] = 1;
                                }
                                j--;
                            }
                        }
                        //
                    }
                }
            }
        }
        System.out.println(diagram);
        return determineOverlappedPoints(diagram);
    }


}
