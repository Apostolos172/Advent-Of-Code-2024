package com.apostolos172.AoC2024.day3.services.impl;

import com.apostolos172.AoC2024.cm.InputService;
import com.apostolos172.AoC2024.day3.services.Day3Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Day3ServiceImpl implements Day3Service {
    private static final Logger logger = LogManager.getLogger(Day3ServiceImpl.class);

    @Autowired
    InputService inputService;

    private List<String> splitStrings = new ArrayList<>();
    private List<Boolean> doDontStrings = new ArrayList<>();
    private String exampleInputFileName = "day3/example.txt";
    private String inputFileName = "day3/day.txt";

    // private String inputFileName = exampleInputFileName;


    private void prepareData(String data) {
        splitStrings.clear();
        doDontStrings.clear();

        int startIndex = 0;
        int endIndex = data.indexOf("mul(");

        while (endIndex != -1) {
            splitStrings.add(data.substring(startIndex, endIndex));
            updateDoDontStrings(data, startIndex);

            startIndex = endIndex + 4; // Length of "mul("
            endIndex = data.indexOf("mul(", startIndex);
        }

        // Add the remaining part of the string after the last "mul("
        splitStrings.add(data.substring(startIndex));
        updateDoDontStrings(data, startIndex);

    }

    private void updateDoDontStrings(String data, int startIndex) {
        var previousString = data.substring(0, startIndex);
        int lastIndexPositive = previousString.lastIndexOf("do()");
        int lastIndexNegative = previousString.lastIndexOf("don't()");
        if(lastIndexPositive>lastIndexNegative) {
            doDontStrings.add(true);
        }
        if(lastIndexPositive<lastIndexNegative) {
            doDontStrings.add(false);
        }
        if(lastIndexPositive==lastIndexNegative) {
            doDontStrings.add(true);
        }
    }

    @Override
    public String getSolutionPart1() {
        String data = inputService.readInput(inputFileName);
        if (data.isEmpty()) {
            return "Error reading input";
        }

        prepareData(data);
        var sumOfMultiplications = solvePart1();

        var solution = "Solution to day 3 - PART 1: Sum Of Multiplications: " + sumOfMultiplications;
        return solution;
    }

    @Override
    public String getSolutionPart2() {
        String data = inputService.readInput(inputFileName);
        if (data.isEmpty()) {
            return "Error reading input";
        }

        prepareData(data);
        var sumOfMultiplications = solvePart2();

        var solution = "Solution to day 3 - PART 2: Sum Of Multiplications: " + sumOfMultiplications;
        return solution;
    }

    private Long solvePart1() {
        long sumOfMultiplications = 0L;

        for (int i = 0; i < splitStrings.size(); i++) {
            // for each string check if it can be used as operation
            if (isValidOperation(splitStrings.get(i))) {
                var result = doTheOperation(splitStrings.get(i));
                sumOfMultiplications+=result;
            }
        }

        return sumOfMultiplications;
    }

    private boolean isValidOperation(String possibleOperation) {

        var parts = possibleOperation.split(",");
        try {
            var possible1stPart = parts[0];
            var possible2ndPart = parts[1];
            var firstPartConverted = Long.valueOf(possible1stPart);

            int startIndex = 0;
            int endIndex = possible2ndPart.indexOf(")");

            var secondPartCleared = possible2ndPart.substring(startIndex, endIndex);

            var secondPartConverted = Long.valueOf(secondPartCleared);
        } catch (Exception e) {
            // if something of the above fails, it means that this part is corrupted
            return false;
        }

        return true;
    }

    private Long doTheOperation(String possibleOperation) {

        var parts = possibleOperation.split(",");

        var possible1stPart = parts[0];
        var possible2ndPart = parts[1];
        var firstPartConverted = Long.valueOf(possible1stPart);

        int startIndex = 0;
        int endIndex = possible2ndPart.indexOf(")");

        var secondPartCleared = possible2ndPart.substring(startIndex, endIndex);

        var secondPartConverted = Long.valueOf(secondPartCleared);

        return firstPartConverted * secondPartConverted;
    }


    private Long solvePart2() {
        long sumOfMultiplications = 0L;

        for (int i = 0; i < splitStrings.size(); i++) {
            // for each string check if it can be used as operation
            if (isValidOperation(splitStrings.get(i))) {
                var result = doTheOperation(splitStrings.get(i));
                if(doDontStrings.get(i)) {
                    sumOfMultiplications+=result;
                }
            }
        }

        return sumOfMultiplications;
    }

}
