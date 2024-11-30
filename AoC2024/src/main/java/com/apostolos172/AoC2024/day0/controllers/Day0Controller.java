package com.apostolos172.AoC2024.day0.controllers;


import com.apostolos172.AoC2024.day0.services.Day0Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Day0Controller", description = "Controller")
@RequestMapping("/day0")
public class Day0Controller {

    private static final Logger logger = LogManager.getLogger(Day0Controller.class);


    @Autowired
    private Day0Service day0Service;

    private final String getSolutionOperationSummary = "Ανάκτηση Απάντησης.";
    private final String getSolutionOperationDescription = "Ανάκτηση Απάντησης.";

    @Operation(summary = getSolutionOperationSummary, description = getSolutionOperationDescription)
    @GetMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public String getSolution() {
        return day0Service.getSolution();
    }

}
