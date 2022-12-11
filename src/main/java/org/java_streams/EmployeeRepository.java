package org.java_streams;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {

    private static final Map<Integer, Employee> repository = new HashMap<>();

    public static void addToMap(Employee employee) {
        repository.put(employee.id(), employee);
    }

    public static Employee findById(Integer id) {
        return repository.get(id);
    }
}
