package org.java_streams;

import java.util.Arrays;
import java.util.List;

public class StreamOperators {

    private static Employee[] arrayOfEmps = {
            new Employee(1, "Avaraham", 1000.0),
            new Employee(2, "Yitzhak", 2000.0),
            new Employee(3, "Yaakov", 3000.0)
    };

    private static List<Employee> empList = Arrays.asList(arrayOfEmps);

    public void streamForEach() {
        empList.stream().forEach(emp -> emp.salaryIncrement(10.0));
    }
}
