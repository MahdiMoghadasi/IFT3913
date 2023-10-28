package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Jacoco {

    public static void main(String[] args) {
        String filePath = "TP2/src/main/resources/jacoco.csv";
        List<ClassData> classes = readCsvFile(filePath);

        // Calculate metrics for Q1
        double testCoverageRatio = classes.stream()
                .mapToDouble(c -> c.instructionCovered / (double) (c.instructionCovered + c.instructionMissed))
                .average()
                .orElse(0.0);
        long classesWithNoTests = classes.stream()
                .filter(c -> c.instructionCovered == 0)
                .count();
        double methodCoverageRatio = classes.stream()
                .mapToDouble(c -> c.methodCovered / (double) (c.methodCovered + c.methodMissed))
                .average()
                .orElse(0.0);

        // Calculate metrics for Q3
        double averageTestComplexity = classes.stream()
                .mapToDouble(c -> c.complexityCovered)
                .average()
                .orElse(0.0);
        int maxTestComplexity = classes.stream()
                .mapToInt(c -> c.complexityCovered)
                .max()
                .orElse(0);
        double averageMethodComplexityInTests = classes.stream()
                .mapToInt(c -> c.complexityCovered)
                .sum() / (double) classes.stream()
                .mapToInt(c -> c.methodCovered)
                .sum();


        double averageTestComplexityMissed = classes.stream()
                .mapToDouble(c -> c.complexityMissed)
                .average()
                .orElse(0.0);
        int maxTestComplexityMissed = classes.stream()
                .mapToInt(c -> c.complexityMissed)
                .max()
                .orElse(0);
        double averageMethodComplexityInTestsMissed = classes.stream()
                .mapToInt(c -> c.complexityMissed)
                .sum() / (double) classes.stream()
                .mapToInt(c -> c.methodMissed)
                .sum();


        // Q1
        System.out.println("Test Coverage Ratio: " + testCoverageRatio);
        System.out.println("Number of Classes with No Tests: " + classesWithNoTests);
        System.out.println("Method Coverage Ratio: " + methodCoverageRatio);


        // Q3
        System.out.println("\nAverage Test Complexity: " + averageTestComplexity);
        System.out.println("Maximum Test Complexity: " + maxTestComplexity);
        System.out.println("Average Method Complexity in Tests: " + averageMethodComplexityInTests);

        //missed complexity
        System.out.println("\nAverage Test Complexity missed: " + averageTestComplexityMissed);
        System.out.println("Maximum Test Complexity missed: " + maxTestComplexityMissed);
        System.out.println("Average Method Complexity in Tests missed: " + averageMethodComplexityInTestsMissed);

        System.out.print( "Percentage of average Complexity Covered: " + averageTestComplexity / (averageTestComplexity + averageTestComplexityMissed));

    }

    private static List<ClassData> readCsvFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.lines()
                    .skip(1) // skip header line
                    .map(line -> {
                        String[] parts = line.split(",");
                        return new ClassData(
                                parts[0],
                                parts[1],
                                parts[2],
                                Integer.parseInt(parts[3]),
                                Integer.parseInt(parts[4]),
                                Integer.parseInt(parts[5]),
                                Integer.parseInt(parts[6]),
                                Integer.parseInt(parts[7]),
                                Integer.parseInt(parts[8]),
                                Integer.parseInt(parts[9]),
                                Integer.parseInt(parts[10]),
                                Integer.parseInt(parts[11]),
                                Integer.parseInt(parts[12])
                        );
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private static class ClassData {
        String group;
        String package_;
        String class_;
        int instructionMissed;
        int instructionCovered;
        int branchMissed;
        int branchCovered;
        int lineMissed;
        int lineCovered;
        int complexityMissed;
        int complexityCovered;
        int methodMissed;
        int methodCovered;

        public ClassData(String group, String package_, String class_, int instructionMissed, int instructionCovered,
                         int branchMissed, int branchCovered, int lineMissed, int lineCovered, int complexityMissed,
                         int complexityCovered, int methodMissed, int methodCovered) {
            this.group = group;
            this.package_ = package_;
            this.class_ = class_;
            this.instructionMissed = instructionMissed;
            this.instructionCovered = instructionCovered;
            this.branchMissed = branchMissed;
            this.branchCovered = branchCovered;
            this.lineMissed = lineMissed;
            this.lineCovered = lineCovered;
            this.complexityMissed = complexityMissed;
            this.complexityCovered = complexityCovered;
            this.methodMissed = methodMissed;
            this.methodCovered = methodCovered;
        }
    }
}
