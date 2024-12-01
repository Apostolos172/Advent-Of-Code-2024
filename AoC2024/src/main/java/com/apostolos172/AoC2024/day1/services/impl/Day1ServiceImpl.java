package com.apostolos172.AoC2024.day1.services.impl;

import com.apostolos172.AoC2024.cm.AoCUtils;
import com.apostolos172.AoC2024.cm.InputService;
import com.apostolos172.AoC2024.day1.services.Day1Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Day1ServiceImpl implements Day1Service {
    private static final Logger logger = LogManager.getLogger(Day1ServiceImpl.class);

    @Autowired
    InputService inputService;

    private List<Long> leftListOfLocationIds = new ArrayList<>();
    private List<Long> rightListOfLocationIds = new ArrayList<>();
    private String exampleInputFileName = "day1/example.txt";
    private String inputFileName = "day1/day1.txt";

    // private String inputFileName = exampleInputFileName;


    private void prepareData(String data) {
        leftListOfLocationIds.clear();
        rightListOfLocationIds.clear();

        String[] lines = data.split("\n");

        for (String line : lines) {
            String[] locationIdsInLine = line.split("   ");
            leftListOfLocationIds.add(Long.valueOf(locationIdsInLine[0]));
            rightListOfLocationIds.add(Long.valueOf(locationIdsInLine[1]));
        }
    }

    @Override
    public String getSolutionPart1() {
        String data = inputService.readInput(inputFileName);
        if (data.isEmpty()) {
            return "Error reading input";
        }

        prepareData(data);
        var sumOfDistances = solvePart1();

        var solution = "Solution to day 1 - PART 1: The total distance between the 2 lists: " + sumOfDistances;
        return solution;
    }

    @Override
    public String getSolutionPart2() {
        String data = inputService.readInput(inputFileName);
        if (data.isEmpty()) {
            return "Error reading input";
        }

        prepareData(data);
        var similarityScore = solvePart2();

        var solution = "Solution to day 1 - PART 2: Similarity Score of the left and right lists: " + similarityScore;
        return solution;
    }

    private Long solvePart1() {
        long sumOfDistances = 0L;

        Collections.sort(leftListOfLocationIds);
        Collections.sort(rightListOfLocationIds);

        for (int i = 0; i < leftListOfLocationIds.size(); i++) {
            var difference = Math.abs(leftListOfLocationIds.get(i) - rightListOfLocationIds.get(i));
            sumOfDistances += difference;
        }

        return sumOfDistances;
    }

    private Long solvePart2() {
        long similarityScore = 0L;

        for (Long locationIdInLeftList : leftListOfLocationIds) {
            var amountOfIncrease = locationIdInLeftList * AoCUtils.timesANumberAppearsToAList(locationIdInLeftList, rightListOfLocationIds);
            similarityScore += amountOfIncrease;
        }

        return similarityScore;
    }
}
