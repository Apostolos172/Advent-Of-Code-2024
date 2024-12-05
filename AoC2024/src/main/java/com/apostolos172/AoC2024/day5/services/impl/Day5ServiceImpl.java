package com.apostolos172.AoC2024.day5.services.impl;

import com.apostolos172.AoC2024.cm.InputService;
import com.apostolos172.AoC2024.day5.dto.Rule;
import com.apostolos172.AoC2024.day5.services.Day5Service;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Day5ServiceImpl implements Day5Service {
    private static final Logger logger = LogManager.getLogger(Day5ServiceImpl.class);

    @Autowired
    InputService inputService;

    private List<Rule> pageOrderingRules = new ArrayList<>();
    private List<List<Long>> updates = new ArrayList<>();
    private String exampleInputFileName = "day5/example.txt";
    private String inputFileName = "day5/day.txt";

    // private String inputFileName = exampleInputFileName;


    private void prepareData(String data) {
        pageOrderingRules.clear();
        updates.clear();

        String[] twoParts = data.split("\n\n");

        // --------------------------

        String firstSection = twoParts[0];

        String[] linesFirstPart = firstSection.split("\n");
        for (String line : linesFirstPart) {
            var pageOrderingRule = new Rule();
            String[] numbersInLine = line.split("\\|");
            pageOrderingRule.setNumberForCheck(Long.valueOf(numbersInLine[0]));
            pageOrderingRule.setNumberAfterNumberForCheck(Long.valueOf(numbersInLine[1]));
            pageOrderingRules.add(pageOrderingRule);
        }

        // --------------------------

        String secondSection = twoParts[1];

        String[] linesSecondPart = secondSection.split("\n");
        for (String line : linesSecondPart) {
            var update = new ArrayList<Long>();
            String[] numbersInLine = line.split(",");
            for (String number : numbersInLine) {
                update.add(Long.valueOf(number));
            }
            updates.add(update);
        }

        // ----------------------------

        logger.log(Level.INFO, "Data ready");

    }

    @Override
    public String getSolutionPart1() {
        String data = inputService.readInput(inputFileName);
        if (data.isEmpty()) {
            return "Error reading input";
        }

        prepareData(data);
        var sumOfNumbers = solvePart1();

        var solution = "Solution to day 5 - PART 1: Sum Of Numbers: " + sumOfNumbers;
        return solution;
    }

    @Override
    public String getSolutionPart2() {
        String data = inputService.readInput(inputFileName);
        if (data.isEmpty()) {
            return "Error reading input";
        }

        prepareData(data);
        var sumOfNumbers = solvePart2();

        var solution = "Solution to day 5 - PART 2: Sum Of Numbers: " + sumOfNumbers;
        return solution;
    }

    private Long solvePart1() {
        long sumOfNumbers = 0L;

        for (int i = 0; i < updates.size(); i++) {
            if(isUpdateCorrect(updates.get(i))) {
                sumOfNumbers+=getMiddleElement(updates.get(i));
            }
        }

        return sumOfNumbers;
    }

    private long getMiddleElement(List<Long> numbers) {
        int middleIndex = numbers.size() / 2;
        long middleNumber = -1;

        if (numbers.size() % 2 != 0) {
            middleNumber = numbers.get(middleIndex);
        }
        return middleNumber;
    }

    private boolean isUpdateCorrect(List<Long> update) {
        for (int numberOfUpdateForCheckIndex = 0; numberOfUpdateForCheckIndex < update.size(); numberOfUpdateForCheckIndex++) {
            var numberOfUpdateForCheck = update.get(numberOfUpdateForCheckIndex);
            for (int ruleIndex = 0; ruleIndex < pageOrderingRules.size(); ruleIndex++) {
                var currentRule = pageOrderingRules.get(ruleIndex);
                if(numberOfUpdateForCheck.equals(currentRule.getNumberForCheck())) {
                    var indexOfAfterNumber = update.indexOf(currentRule.getNumberAfterNumberForCheck());
                    if (indexOfAfterNumber!=-1 && indexOfAfterNumber<numberOfUpdateForCheckIndex) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private Long solvePart2() {
        long sumOfNumbers = 0L;

        for (int i = 0; i < updates.size(); i++) {
            if(!isUpdateCorrect(updates.get(i))) {
                sumOfNumbers+=getMiddleElement(getCorrectUpdate(updates.get(i)));
            }
        }

        return sumOfNumbers;
    }

    private List<Long> getCorrectUpdate(List<Long> update) {
        List<Long> copyList = new ArrayList<>(update);

        for (int numberOfUpdateForCheckIndex = 0; numberOfUpdateForCheckIndex < update.size(); numberOfUpdateForCheckIndex++) {
            var numberOfUpdateForCheck = update.get(numberOfUpdateForCheckIndex);
            for (int ruleIndex = 0; ruleIndex < pageOrderingRules.size(); ruleIndex++) {
                var currentRule = pageOrderingRules.get(ruleIndex);
                if(numberOfUpdateForCheck.equals(currentRule.getNumberForCheck())) {
                    var indexOfAfterNumber = copyList.indexOf(currentRule.getNumberAfterNumberForCheck());
                    var numberOfUpdateForCheckIndexInTheCopyList = copyList.indexOf(numberOfUpdateForCheck);
                    if (indexOfAfterNumber!=-1 && indexOfAfterNumber<numberOfUpdateForCheckIndexInTheCopyList) {
                        var removedNumber = copyList.remove(numberOfUpdateForCheckIndexInTheCopyList);
                        copyList.add(indexOfAfterNumber, removedNumber);
                    }
                }
            }
        }

        logger.info(copyList);
        return copyList;
    }

}
