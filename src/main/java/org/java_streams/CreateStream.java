package org.java_streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) {
        Employee[] arrayOfEmps = {
                new Employee(1, "Avaraham", 1000.0),
                new Employee(2, "Yitzhak", 2000.0),
                new Employee(3, "Yaakov", 3000.0)
        };

        Stream.of(arrayOfEmps);

        List<Employee> empList = Arrays.asList(arrayOfEmps);
        empList.stream();

        Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);

        Stream.Builder<Employee> empStreamBuilder = Stream.builder();

        empStreamBuilder.accept(arrayOfEmps[0]);
        empStreamBuilder.accept(arrayOfEmps[1]);
        empStreamBuilder.accept(arrayOfEmps[2]);

        Stream<Employee> empStream = empStreamBuilder.build();

        StreamOperators streamOperators = new StreamOperators();
        streamOperators.streamSummarizingDouble();
        streamOperators.streamPartitioningBy();
        streamOperators.streamGroupingBy();
        streamOperators.streamMapping();
        streamOperators.streamReducing();
        streamOperators.streamParallel();
    }
}