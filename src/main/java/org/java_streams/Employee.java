package org.java_streams;

public class Employee {

    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public double salary() {
        return salary;
    }

    public int id() {
        return id;
    }

    public void salaryIncrement(double raise) {
        salary += raise;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "{ \"id\": " + id + ", \"name\": " + name + ", \"salary\": " + salary + "}";
    }
}
