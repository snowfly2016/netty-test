package jdk8.ch01;

import java.util.List;

public class Company {
    private String name;
    private List<Employee> employeeList;

    public Company(String name, List<Employee> employeeList) {
        this.name = name;
        this.employeeList = employeeList;
    }

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
