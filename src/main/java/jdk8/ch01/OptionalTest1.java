package jdk8.ch01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OptionalTest1 {


    public static void main(String[] args) {
        Employee employee1 = new Employee("zhangsan",20);

        Employee employee2 = new Employee("lisi",30);

        Company company = new Company();
        company.setName("alibaba");

        List<Employee> employeeList = Arrays.asList(employee1,employee2);
        company.setEmployeeList(employeeList);

        Optional<Company> optional =Optional.ofNullable(company);
        System.out.println(optional.map(theCompany -> theCompany.getEmployeeList())
        .orElse(Collections.emptyList()));
    }
}
