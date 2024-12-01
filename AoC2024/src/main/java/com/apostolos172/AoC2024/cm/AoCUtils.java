package com.apostolos172.AoC2024.cm;

import java.util.List;

public class AoCUtils {

    public static Long timesANumberAppearsToAList(Long searchNumber, List<Long> numbers) {
        var appearances = numbers.stream().filter(number -> number.equals(searchNumber)).count();
        return appearances;
    }
}
