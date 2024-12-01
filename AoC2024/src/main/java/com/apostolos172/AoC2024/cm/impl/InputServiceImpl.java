package com.apostolos172.AoC2024.cm.impl;

import com.apostolos172.AoC2024.cm.InputService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InputServiceImpl implements InputService {
    private static final Logger logger = LogManager.getLogger(InputServiceImpl.class);


    @Override
    public String readInput(String fileName) {
        String data = "";
        Path path = null;
        try {
            path = Paths.get(getClass().getClassLoader()
                    .getResource("input/" + fileName).toURI());

            Stream<String> lines = Files.lines(path);
            data = lines.collect(Collectors.joining("\n"));
            lines.close();

        } catch (Exception e) {
            logger.log(Level.ERROR, "Error reading input");
            logger.error(e.toString());
        }

        return data;

    }
}
