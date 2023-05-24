package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
                new Employee("Ross", "Geller", 35, 25000),
                new Employee("Monica", "Geller", 35, 12000),
                new Employee("Joey", "Tribbiani", 38, 25000),
                new Employee("Chandler", "Bing", 35, 25000),
                new Employee("Phoebe", "Buffay", 30, 14000),
                new Employee("Rachel", "Green", 23, 23000)
        )));
        Employee david = new Employee("David","Coulthard",27,70000);
        Employee kevin = new Employee(david);   //  Creating an object from another object using Copy Constructor defined in the main.java.Employee class.
        System.out.println("All Employees:");
        employees.forEach(System.out::println);
        System.out.println("-".repeat(75));

        Predicate<Employee> age30OrLess = (employee) -> (employee.getAge() <= 30);
        System.out.println("Employees age less than 31:");
        employees.forEach(employee -> {
            if (age30OrLess.test(employee))
                System.out.println(employee);
        });
        System.out.println("-".repeat(75));

        Predicate<Employee> salaryUpTo15 = (employee) -> (employee.getSalary() <= 15000.00);
        System.out.println("Employees salary up to 15000");
        employees.forEach(employee -> {
            if (salaryUpTo15.test(employee))
                System.out.println(employee);
        });
        System.out.println("-".repeat(75));

        System.out.println("Employees age less than 31 AND salary upto 15000");
        employees.forEach(employee -> {
            if (age30OrLess.and(salaryUpTo15).test(employee))
                System.out.println(employee);
        });
        System.out.println("-".repeat(75));

        System.out.println("Employees age less than 31 OR salary up to 15000");
        employees.forEach(employee -> {
            if (age30OrLess.or(salaryUpTo15).test(employee))
                System.out.println(employee);
        });
        System.out.println("-".repeat(75));

        System.out.println("Employees age less than 31 AND salary more than 15000");
        employees.forEach(employee -> {
            if (age30OrLess.and(salaryUpTo15.negate()).test(employee))
                System.out.println(employee);
        });
        System.out.println("-".repeat(75));

        System.out.println("Employees age less than 31 or salary more than 15000");
        employees.forEach(employee -> {
            if (age30OrLess.or(salaryUpTo15.negate()).test(employee))
                System.out.println(employee);
        });
        System.out.println("-".repeat(75));

        System.out.println("Employees age more than 30 OR salary more than 15000");
        Predicate<Employee> ageAbove30 = not(age30OrLess);
        employees.forEach(employee -> {
            if (ageAbove30.or(salaryUpTo15.negate()).test(employee))
                System.out.println(employee);
        });
        System.out.println("-".repeat(75));

        System.out.println("Employees age more than 30 AND salary more than 15000");
        employees.forEach(employee -> {
            if (ageAbove30.and(salaryUpTo15.negate()).test(employee))
                System.out.println(employee);
        });
        System.out.println("-".repeat(75));

        if (staticPredicate((Employee e) -> e.getAge() > 30, employees.get(0)))
            System.out.println(employees.get(0).getFirstName());
        System.out.println("-".repeat(75));

        System.out.println("main.java.Employee last name starts with G:");
        employees.forEach((Employee employee) -> {
            if (staticPredicate((Employee e) -> e.getLastName().startsWith("G"), employee)) {
                System.out.println(employee);
            }
        });
        System.out.println("-".repeat(75));

        System.out.println("main.java.Employee removeIf() salary more than 30000:");
        employees.removeIf((Employee e) -> e.getSalary() >= 30000);
        //  Using Anonymous class which implements the Predicate interface and its Single Abstract Method (SAM).
        employees.removeIf(new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() >= 30000;
            }
        });
        employees.forEach(System.out::println);
        System.out.println("-".repeat(75));

        System.out.println("main.java.Employee sorting with firstName using Comparable Interface:");
        Collections.sort(employees);    //  <--- main.java.Employee class must implement Comparable interface for sort() method to work.
        employees.forEach(System.out::println);
        System.out.println("-".repeat(75));

        employees.sort((new Employee.EmployeeComparator("salary"))
                .thenComparing(new Employee.EmployeeComparator("age"))
                .thenComparing(new Employee.EmployeeComparator("firstName").reversed()));
        System.out.println("Sorting on Salary and then Age:");
        employees.forEach(System.out::println);
        System.out.println("-".repeat(75));

        IntPredicate evenIntPredicate = (element) -> (element % 2 == 0);
        IntPredicate oddIntPredicate = (element) -> (element % 2 == 1);
        for (int i = 1; i < 32; i++)
            if (oddIntPredicate.test(i))
                System.out.print(i + " ");
        System.out.println();
        for (int i = 1; i < 32; i++)
            if (evenIntPredicate.test(i))
                System.out.print(i + " ");

    }

    private static boolean staticPredicate(Predicate<Employee> predicate, Employee e) {
        return predicate.test(e);
    }

}