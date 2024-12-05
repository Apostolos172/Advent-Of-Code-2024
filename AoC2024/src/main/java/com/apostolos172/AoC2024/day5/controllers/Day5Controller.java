package com.apostolos172.AoC2024.day5.controllers;


import com.apostolos172.AoC2024.day5.services.Day5Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Day5Controller", description = "Controller")
@RequestMapping("/day5")
public class Day5Controller {

    private static final Logger logger = LogManager.getLogger(Day5Controller.class);


    @Autowired
    private Day5Service day5Service;

    private final String getSolutionOperationSummary = "Ανάκτηση Απάντησης.";
    private final String getSolutionOperationDescription = "Ανάκτηση Απάντησης.";

    @Operation(summary = getSolutionOperationSummary, description = getSolutionOperationDescription)
    @GetMapping(value = "/part1", produces = "application/json;charset=UTF-8")
    public String getSolutionPart1() {
        return day5Service.getSolutionPart1();
    }

    @Operation(summary = getSolutionOperationSummary, description = getSolutionOperationDescription)
    @GetMapping(value = "/part2", produces = "application/json;charset=UTF-8")
    public String getSolutionPart2() {
        return day5Service.getSolutionPart2();
    }

}
