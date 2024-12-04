package com.apostolos172.AoC2024.day4.services.impl;

import com.apostolos172.AoC2024.cm.InputService;
import com.apostolos172.AoC2024.day4.services.Day4Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Day4ServiceImpl implements Day4Service {
    private static final Logger logger = LogManager.getLogger(Day4ServiceImpl.class);

    @Autowired
    InputService inputService;

    private List<String> linesList = new ArrayList<>();
    private String exampleInputFileName = "day4/example.txt";
    private String inputFileName = "day4/day.txt";

    // private String inputFileName = exampleInputFileName;


    private void prepareData(String data) {
        linesList.clear();

        String[] lines = data.split("\n");
        linesList = new ArrayList<>(Arrays.asList(lines));
    }

    @Override
    public String getSolutionPart1() {
        String data = inputService.readInput(inputFileName);
        if (data.isEmpty()) {
            return "Error reading input";
        }

        prepareData(data);
        var sumOfXMASes = solvePart1();

        var solution = "Solution to day 4 - PART 1: Sum Of XMAS: " + sumOfXMASes;
        return solution;
    }

    @Override
    public String getSolutionPart2() {
        String data = inputService.readInput(inputFileName);
        if (data.isEmpty()) {
            return "Error reading input";
        }

        prepareData(data);
        var sumOfX_MASes = solvePart2();

        var solution = "Solution to day 4 - PART 2: Sum Of X-MAS: " + sumOfX_MASes;
        return solution;
    }

    private Long solvePart1() {
        long sumOfXMASes = 0L;

        for (int i = 0; i < linesList.size(); i++) {
            sumOfXMASes+=XMASSumFromLine(linesList.get(i), i);
        }

        return sumOfXMASes;
    }

    private Long XMASSumFromLine(String line, int lineIndex) {
        long sum = 0L;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if(c=='X') {
                sum += existsInLeft(lineIndex, i) ? 1L : 0L;
                sum += existsInLeftUpDiagonally(lineIndex, i) ? 1L : 0L;
                sum += existsInUp(lineIndex, i) ? 1L : 0L;
                sum += existsInRightUpDiagonally(lineIndex, i) ? 1L : 0L;
                sum += existsInRight(lineIndex, i) ? 1L : 0L;
                sum += existsInRightDownDiagonally(lineIndex, i) ? 1L : 0L;
                sum += existsInDown(lineIndex, i) ? 1L : 0L;
                sum += existsInLeftDownDiagonally(lineIndex, i) ? 1L : 0L;
            }
        }

        return sum;
    }

    private boolean existsInLeftDownDiagonally(int lineIndex, int XIndexInLine) {
        try {
            char possibleM = linesList.get(lineIndex + 1).charAt(XIndexInLine - 1);
            char possibleA = linesList.get(lineIndex + 2).charAt(XIndexInLine - 2);
            char possibleS = linesList.get(lineIndex + 3).charAt(XIndexInLine - 3);
            if (possibleM=='M' && possibleA=='A' && possibleS=='S') {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private boolean existsInDown(int lineIndex, int XIndexInLine) {
        try {
            char possibleM = linesList.get(lineIndex + 1).charAt(XIndexInLine);
            char possibleA = linesList.get(lineIndex + 2).charAt(XIndexInLine);
            char possibleS = linesList.get(lineIndex + 3).charAt(XIndexInLine);
            if (possibleM=='M' && possibleA=='A' && possibleS=='S') {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private boolean existsInRightDownDiagonally(int lineIndex, int XIndexInLine) {
        try {
            char possibleM = linesList.get(lineIndex + 1).charAt(XIndexInLine + 1);
            char possibleA = linesList.get(lineIndex + 2).charAt(XIndexInLine + 2);
            char possibleS = linesList.get(lineIndex + 3).charAt(XIndexInLine + 3);
            if (possibleM=='M' && possibleA=='A' && possibleS=='S') {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private boolean existsInRight(int lineIndex, int XIndexInLine) {
        try {
            char possibleM = linesList.get(lineIndex).charAt(XIndexInLine + 1);
            char possibleA = linesList.get(lineIndex).charAt(XIndexInLine + 2);
            char possibleS = linesList.get(lineIndex).charAt(XIndexInLine + 3);
            if (possibleM=='M' && possibleA=='A' && possibleS=='S') {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private boolean existsInRightUpDiagonally(int lineIndex, int XIndexInLine) {
        try {
            char possibleM = linesList.get(lineIndex - 1).charAt(XIndexInLine + 1);
            char possibleA = linesList.get(lineIndex - 2).charAt(XIndexInLine + 2);
            char possibleS = linesList.get(lineIndex - 3).charAt(XIndexInLine + 3);
            if (possibleM=='M' && possibleA=='A' && possibleS=='S') {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private boolean existsInUp(int lineIndex, int XIndexInLine) {
        try {
            char possibleM = linesList.get(lineIndex - 1).charAt(XIndexInLine);
            char possibleA = linesList.get(lineIndex - 2).charAt(XIndexInLine);
            char possibleS = linesList.get(lineIndex - 3).charAt(XIndexInLine);
            if (possibleM=='M' && possibleA=='A' && possibleS=='S') {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private boolean existsInLeftUpDiagonally(int lineIndex, int XIndexInLine) {
        try {
            char possibleM = linesList.get(lineIndex -1).charAt(XIndexInLine - 1);
            char possibleA = linesList.get(lineIndex - 2).charAt(XIndexInLine - 2);
            char possibleS = linesList.get(lineIndex - 3).charAt(XIndexInLine - 3);
            if (possibleM=='M' && possibleA=='A' && possibleS=='S') {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private boolean existsInLeft(int lineIndex, int XIndexInLine) {
        try {
            char possibleM = linesList.get(lineIndex).charAt(XIndexInLine - 1);
            char possibleA = linesList.get(lineIndex).charAt(XIndexInLine - 2);
            char possibleS = linesList.get(lineIndex).charAt(XIndexInLine - 3);
            if (possibleM=='M' && possibleA=='A' && possibleS=='S') {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }


    private Long solvePart2() {
        long sumOfX_MASes = 0L;

        for (int i = 0; i < linesList.size(); i++) {
            sumOfX_MASes+=X_MASSumFromLine(linesList.get(i), i);
        }

        return sumOfX_MASes;
    }

    private Long X_MASSumFromLine(String line, int lineIndex) {
        long sum = 0L;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if(c=='A') {
                if (isFirstValidMAS(lineIndex, i) && isSecondValidMAS(lineIndex, i)) {
                    sum++;
                }
            }
        }

        return sum;
    }

    private boolean isFirstValidMAS(int lineIndex, int XIndexInLine) {
        try {
            char leftUp = linesList.get(lineIndex - 1).charAt(XIndexInLine - 1);
            char rightDown = linesList.get(lineIndex + 1).charAt(XIndexInLine + 1);
            if ((leftUp=='M' && rightDown=='S') || (leftUp=='S' && rightDown=='M')) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private boolean isSecondValidMAS(int lineIndex, int XIndexInLine) {
        try {
            char rightUp = linesList.get(lineIndex - 1).charAt(XIndexInLine + 1);
            char leftDown = linesList.get(lineIndex + 1).charAt(XIndexInLine - 1);
            if ((rightUp=='M' && leftDown=='S') || (rightUp=='S' && leftDown=='M')) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }


}
