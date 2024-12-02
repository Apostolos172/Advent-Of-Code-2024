package com.apostolos172.AoC2024.day2.services.impl;

import com.apostolos172.AoC2024.cm.InputService;
import com.apostolos172.AoC2024.day2.services.Day2Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Day2ServiceImpl implements Day2Service {
    private static final Logger logger = LogManager.getLogger(Day2ServiceImpl.class);

    @Autowired
    InputService inputService;

    private List<ArrayList<Long>> reports = new ArrayList<>();
    private String exampleInputFileName = "day2/example.txt";
    private String inputFileName = "day2/day.txt";

    // private String inputFileName = exampleInputFileName;


    private void prepareData(String data) {
        reports.clear();

        String[] lines = data.split("\n");

        for (String line : lines) {
            var levels = new ArrayList<Long>();
            String[] levelsInLine = line.split(" ");
            for (String level : levelsInLine) {
                levels.add(Long.valueOf(level));
            }
            reports.add(levels);
        }
    }

    @Override
    public String getSolutionPart1() {
        String data = inputService.readInput(inputFileName);
        if (data.isEmpty()) {
            return "Error reading input";
        }

        prepareData(data);
        var safeReports = solvePart1();

        var solution = "Solution to day 2 - PART 1: Number of safe reports: " + safeReports;
        return solution;
    }

    @Override
    public String getSolutionPart2() {
        String data = inputService.readInput(inputFileName);
        if (data.isEmpty()) {
            return "Error reading input";
        }

        prepareData(data);
        var safeReports = solvePart2();

        var solution = "Solution to day 2 - PART 2: Number of safe reports: " + safeReports;
        return solution;
    }

    private Long solvePart1() {
        long safeReports = 0L;

        for (int i = 0; i < reports.size(); i++) {
            // for each report check the 2 criteria, in order to see if it is safe
            if (isReportSafe(reports.get(i))) {
                safeReports ++;
            }
        }

        return safeReports;
    }

    private boolean isReportSafe(ArrayList<Long> levels) {

        // 1st safety criteria
        for (int i = 0; i < levels.size()-1; i++) {
            var firstAdjacentLevel = levels.get(i);
            var secondAdjacentLevel = levels.get(i+1);
            var differenceBetweenAdjacentLevels = Math.abs(firstAdjacentLevel - secondAdjacentLevel);
            if (!(differenceBetweenAdjacentLevels>=1 && differenceBetweenAdjacentLevels<=3)) {
                return false;
            }
        }

        // 2nd safety criteria
        int orderOfTheLevels = levels.get(0).compareTo(levels.get(1));

        if (orderOfTheLevels==0) {
            return false;
        }

        if (orderOfTheLevels>0) {
            // descending order
            for (int i = 0; i < levels.size()-1; i++) {
                var firstAdjacentLevel = levels.get(i);
                var secondAdjacentLevel = levels.get(i+1);
                if (!(firstAdjacentLevel > secondAdjacentLevel)) {
                    return false;
                }
            }
        }

        if (orderOfTheLevels<0) {
            // ascending order
            for (int i = 0; i < levels.size()-1; i++) {
                var firstAdjacentLevel = levels.get(i);
                var secondAdjacentLevel = levels.get(i+1);
                if (!(firstAdjacentLevel < secondAdjacentLevel)) {
                    return false;
                }
            }
        }

        return true;
    }

    private Long solvePart2() {
        long safeReports = 0L;

        for (ArrayList<Long> report : reports) {
            // for each report check the 2 criteria, in order to see if it is safe
            if (isReportSafe(report)) {
                safeReports++;
            } else {
                // if I remove any of the levels of the report, maybe I have a safe report

                for (int j = 0; j < report.size(); j++) {
                    // for each of the levels, remove it and check the safety of the report again
                    ArrayList<Long> copyOfReport = new ArrayList<>(report);
                    copyOfReport.remove(j);
                    if (isReportSafe(copyOfReport)) {
                        safeReports++;
                        // continue with the next report
                        break;
                    }
                }
            }
        }

        return safeReports;
    }
}
