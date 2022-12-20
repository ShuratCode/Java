package org.java_streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOperators {

    private static Employee[] arrayOfEmps = {new Employee(1, "Avaraham", 1000.0), new Employee(2, "Yitzhak",
                                                                                               2000.0), new Employee(3,
                                                                                                                     "Yaakov",
                                                                                                                     3000.0)};

    private static List<Employee> empList = Arrays.asList(arrayOfEmps);

    public void streamForEach() {
        empList.stream().forEach(emp -> emp.salaryIncrement(10.0));
    }

    public void streamMap() {
        Integer[] empIds = {1, 2, 3};

        List<Employee> employees = Stream.of(empIds).map(EmployeeRepository::findById).collect(Collectors.toList());
    }

    public void streamFilter() {
        Integer[] empIds = {1, 2, 3};

        List<Employee> employees = Stream.of(empIds).map(EmployeeRepository::findById).filter(e -> e != null)
                                         .filter(e -> e.salary() > 100).collect(Collectors.toList());
    }

    public void streamFindFirst() {
        Integer[] empIds = {1, 2, 3};

        Employee employee = Stream.of(empIds).map(EmployeeRepository::findById).filter(e -> e != null)
                                  .filter(e -> e.salary() > 100).findFirst().orElse(null);
    }

    public void streamToArray() {
        Employee[] employees = empList.stream().toArray(Employee[]::new);
    }

    public void streamFlatMap() {
        List<List<String>> namesNested = Arrays.asList(Arrays.asList("Jeff", "Bezos"), Arrays.asList("Bill", "Gates"),
                                                       Arrays.asList("Mark", "Zuckerberg"));

        List<String> namesFlatStream = namesNested.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public void streamPeek() {
        List<Employee> collect = empList.stream().peek(employee -> employee.salaryIncrement(10.0))
                                        .peek(System.out::println).collect(Collectors.toList());
    }

    public void streamPipeline() {
        Long empCount = empList.stream().filter(e -> e.salary() > 20000).count();
    }

    public void streamShortCircuiting() {
        Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

        List<Integer> collect = infiniteStream.skip(3).limit(5).collect(Collectors.toList());
    }

    public void streamLazyEvaluation() {
        Integer[] empIds = {1, 2, 3};

        Employee employee = Stream.of(empIds).map(EmployeeRepository::findById).filter(e -> e != null)
                                  .filter(e -> e.salary() > 1000).findFirst().orElse(null);
    }

    public void streamSorted() {
        List<Employee> employees = empList.stream().sorted((e1, e2) -> e1.name().compareTo(e2.name()))
                                          .collect(Collectors.toList());
    }

    public void streamMin() {
        Employee firstEmp = empList.stream().min(Comparator.comparingInt(Employee::id))
                                   .orElseThrow(NoSuchElementException::new);
    }

    public void streamDistinct() {
        List<Integer> integers = Arrays.asList(2, 5, 3, 2, 4, 3);
        List<Integer> distinctIntegers = integers.stream().distinct().collect(Collectors.toList());
    }

    public void steamAnyAllNoneMatch() {
        List<Integer> integers = Arrays.asList(2, 4, 5, 6, 8);

        boolean allEven = integers.stream().allMatch(i -> i % 2 == 0);
        boolean oneEven = integers.stream().anyMatch(i -> i % 2 == 0);
        boolean noneMultipleOfThree = integers.stream().noneMatch(i -> i % 3 == 0);
    }

    public void streamSpecializeStreams() {
        double average = IntStream.range(10, 20).average().orElseThrow(NoSuchElementException::new);
    }

    public void streamReduce() {
        Double sumSal = empList.stream().map(Employee::salary).reduce(0.0, Double::sum);
    }

    public void streamJoining() {
        String empNames = empList.stream().map(Employee::name).collect(Collectors.joining(", "));
    }

    public void streamToSet() {
        Set<String> empNames = empList.stream().map(Employee::name).collect(Collectors.toSet());
    }

    public void streamToCollection() {
        Vector<String> empNames = empList.stream().map(Employee::name).collect(Collectors.toCollection(Vector::new));
    }

    public void streamSummarizingDouble() {
        DoubleSummaryStatistics stats = empList.stream().collect(Collectors.summarizingDouble(Employee::salary));

        System.out.println(stats.getCount());
        System.out.println(stats.getMax());
        System.out.println(stats.getMin());
        System.out.println(stats.getAverage());
    }

    public void streamPartitioningBy() {
        List<Integer> integers = Arrays.asList(2, 4, 5, 6, 8);
        Map<Boolean, List<Integer>> isEven = integers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));

        assert (isEven.get(true).size() == 4);
        assert (isEven.get(false).size() == 1);
    }

    public void streamGroupingBy() {
        Map<Character, List<Employee>> groupByAlphabet = empList.stream().collect(
                Collectors.groupingBy(e -> e.name().charAt(0)));

        assert (groupByAlphabet.get('A').get(0).name().equals("Avraham"));
        assert (groupByAlphabet.get('Y').get(0).name().equals("Yitzhak"));
        assert (groupByAlphabet.get('Y').get(1).name().equals("Yaakov"));
    }

    public void streamMapping() {
        Map<Character, List<Integer>> idGroupByAlphabet = empList.stream().collect(
                Collectors.groupingBy(e -> e.name().charAt(0), Collectors.mapping(Employee::id, Collectors.toList())));

        assert idGroupByAlphabet.get('A').get(0) == 1;
        assert idGroupByAlphabet.get('Y').get(0) == 2;
        assert idGroupByAlphabet.get('Y').get(1) == 3;
    }

    public void streamReducing() {
        Double percentage = 10.0;
        Double salIncrOverhead = empList.stream().collect(
                Collectors.reducing(0.0, e -> e.salary() * percentage / 100, (s1, s2) -> s1 + s2));

        assert salIncrOverhead == 600.0;
    }

    public void streamParallel() {
        empList.stream().parallel().forEach(e -> e.salaryIncrement(10.0));

        assert empList.get(0).salary() == 1010.0;
        assert empList.get(1).salary() == 2010.0;
        assert empList.get(2).salary() == 3010.0;
    }
}
