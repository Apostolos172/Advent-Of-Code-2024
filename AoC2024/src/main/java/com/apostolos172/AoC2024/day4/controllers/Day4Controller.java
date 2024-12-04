package com.apostolos172.AoC2024.day4.controllers;


import com.apostolos172.AoC2024.day4.services.Day4Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Day4Controller", description = "Controller")
@RequestMapping("/day4")
public class Day4Controller {

    private static final Logger logger = LogManager.getLogger(Day4Controller.class);


    @Autowired
    private Day4Service day4Service;

    private final String getSolutionOperationSummary = "Ανάκτηση Απάντησης.";
    private final String getSolutionOperationDescription = "Ανάκτηση Απάντησης.";

    @Operation(summary = getSolutionOperationSummary, description = getSolutionOperationDescription)
    @GetMapping(value = "/part1", produces = "application/json;charset=UTF-8")
    public String getSolutionPart1() {
        return day4Service.getSolutionPart1();
    }

    @Operation(summary = getSolutionOperationSummary, description = getSolutionOperationDescription)
    @GetMapping(value = "/part2", produces = "application/json;charset=UTF-8")
    public String getSolutionPart2() {
        return day4Service.getSolutionPart2();
    }

}
