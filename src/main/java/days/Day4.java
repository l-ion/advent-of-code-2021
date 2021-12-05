package days;

import java.util.*;
import java.util.stream.Collectors;

public class Day4 extends Day {
    Integer num = 0;


    public Day4(int dayNo) {
        super(dayNo);
    }

    public int solvePart1() {
//        System.out.println(findWinningBoard().toString());
//        return calculateResultPart1(findWinningBoard(), num);
        return 0;
    }

    public void findWinningBoard(Map<List<Map<Integer, Integer>>, Integer> winningBoards) {
        List<String> todaysInput = getTodaysInput();
        List<Integer> numbers = Arrays.stream(todaysInput.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        todaysInput.remove(0);
        todaysInput.remove(0);

        Map<List<String>, Integer> countScoreMap = new HashMap<>();
        List<String> game = new ArrayList<>();
        for (String line : todaysInput) {
            if (!line.equals("")) {
                game.add(line);
            } else {
                countScoreMap.put(game, 0);
                game = new ArrayList<>();
            }
        }
        List<List<Map<Integer, Integer>>> collect = countScoreMap.keySet().stream()
                .map(gameArray -> {
                    List<Map<Integer, Integer>> board = new ArrayList<>();
                    gameArray.forEach(number -> {
                        final Map<Integer, Integer> map = new LinkedHashMap<>();
                        for (String num : number.split(" ")) {
                            if (!num.equals(""))
                                map.put(Integer.parseInt(num), 0);
                        }
                        board.add(map);
                    });
                    return board;
                }).collect(Collectors.toList());


        for (Integer num : numbers) {
            for (List<Map<Integer, Integer>> board : collect) {
                for (Map<Integer, Integer> line : board) {
                    if (line.containsKey(num)) {
                        line.put(num, 1);
                    }
                    if (isWinningBoardLine(board) || isWinningBoardColumn(board)) {
                        this.num = num;
                        System.out.println(board);
                        System.out.println(num);
                        winningBoards.put(board, num);
                        //return board; // uncomment for part one
                    }
                }
            }
        }

        //return null;
    }

    public List<Map<Integer, Integer>> findWinningBoard2(List<List<Map<Integer, Integer>>> collect, List<Integer> numbers) {
//        List<Map<Integer, Integer>> lastWinBoard = new ArrayList<>();
//        List<String> todaysInput = getTodaysInput();
//        List<Integer> numbers = Arrays.stream(todaysInput.get(0).split(","))
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());
//        todaysInput.remove(0);
//        todaysInput.remove(0);
//
//        Map<List<String>, Integer> countScoreMap = new HashMap<>();
//        List<String> game = new ArrayList<>();
//        for (String line : todaysInput) {
//            if (!line.equals("")) {
//                game.add(line);
//            } else {
//                countScoreMap.put(game, 0);
//                game = new ArrayList<>();
//            }
//        }
//        List<List<Map<Integer, Integer>>> collect = countScoreMap.keySet().stream()
//                .map(gameArray -> {
//                    List<Map<Integer, Integer>> board = new ArrayList<>();
//                    gameArray.forEach(number -> {
//                        final Map<Integer, Integer> map = new LinkedHashMap<>();
//                        for (String num : number.split(" ")) {
//                            if (!num.equals(""))
//                                map.put(Integer.parseInt(num), 0);
//                        }
//                        board.add(map);
//                    });
//                    return board;
//                }).collect(Collectors.toList());
//

        for (Integer num : numbers) {
            for (List<Map<Integer, Integer>> board : collect) {
                for (Map<Integer, Integer> line : board) {
                    if (line.containsKey(num)) {
                        line.put(num, 1);
                    }
                    if (isWinningBoardLine(board) || isWinningBoardColumn(board)) {
                        this.num = num;
//                        System.out.println(board);
//                        System.out.println(num);

                        return board; // uncomment for part one
                    }
                }
            }
        }
        return null;
    }

    public boolean isWinningBoardLine(List<Map<Integer, Integer>> board) {
        for (Map<Integer, Integer> line : board) {
            int lineCount = 0;
            for (Integer value : line.values()) {
                if (value == 1) {
                    lineCount++;
                } else {
                    break;
                }
                if (lineCount == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isWinningBoardColumn(List<Map<Integer, Integer>> board) {
        for (int i = 0; i < board.get(0).size(); i++) {
            int countColumn = 0;
            for (Map<Integer, Integer> line : board) {
                int iterations = i;
                Iterator iterator = line.entrySet().iterator();
                Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iterator.next();
                while (iterations > 0 && iterator.hasNext()) {
                    entry = (Map.Entry<Integer, Integer>) iterator.next();
                    iterations--;
                }
                Integer value = entry.getValue();
                if (value == 1) {
                    countColumn++;
                }
                if (countColumn == 5) {
                    return true;
                }
            }
        }

        return false;
    }


    public int calculateResultPart1(List<Map<Integer, Integer>> board, int num) {
        int sum = 0;
        for (Map<Integer, Integer> map : board) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 0) {
                    sum += entry.getKey();
                }
            }
        }
        return sum * num;
    }

    public int solvePart2() {

//        findWinningBoard(winningBoards);
//        Iterator iterator = winningBoards.entrySet().iterator();
//        List<Map<Integer, Integer>> lastWinningBoard = new ArrayList<>();
//        int num = 0;
//        while (iterator.hasNext()) {
//            Map.Entry<Map<Integer, Integer>, Integer> lastWinningEntry = (Map.Entry<Map<Integer, Integer>, Integer>) iterator.next();
//            lastWinningBoard = (List<Map<Integer, Integer>>) lastWinningEntry.getKey();
//            num = lastWinningEntry.getValue();
//        }
        final List<String> todaysInput = getTodaysInput();
        List<Integer> numbers = Arrays.stream(todaysInput.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<List<Map<Integer, Integer>>> allBoards = findAllBoards();
        while (allBoards.size() > 1) {
            allBoards.remove(findWinningBoard2(allBoards, numbers));
        }


        System.out.println(num);
        return calculateResultPart1(findWinningBoard2(allBoards, numbers), this.num);
    }

    public List<List<Map<Integer, Integer>>> findAllBoards() {
        List<Map<Integer, Integer>> lastWinBoard = new ArrayList<>();
        List<String> todaysInput = getTodaysInput();
        List<Integer> numbers = Arrays.stream(todaysInput.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        todaysInput.remove(0);
        todaysInput.remove(0);

        Map<List<String>, Integer> countScoreMap = new HashMap<>();
        List<String> game = new ArrayList<>();
        for (String line : todaysInput) {
            if (!line.equals("")) {
                game.add(line);
            } else {
                countScoreMap.put(game, 0);
                game = new ArrayList<>();
            }
        }
        List<List<Map<Integer, Integer>>> collect = countScoreMap.keySet().stream()
                .map(gameArray -> {
                    List<Map<Integer, Integer>> board = new ArrayList<>();
                    gameArray.forEach(number -> {
                        final Map<Integer, Integer> map = new LinkedHashMap<>();
                        for (String num : number.split(" ")) {
                            if (!num.equals(""))
                                map.put(Integer.parseInt(num), 0);
                        }
                        board.add(map);
                    });
                    return board;
                }).collect(Collectors.toList());
        return collect;
    }
}
