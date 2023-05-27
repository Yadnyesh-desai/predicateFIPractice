package main.java;

import java.util.Comparator;
import java.util.Objects;

enum SORTType {ID, FIRSTNAME, LASTNAME, AGE, SALARY}

public class Employee implements Comparable<Employee> {
    private static int LAST_ID = 1000;
    private final int id;
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    //  Copy Constructor
    public Employee(Employee copyEmployee) {
        this.id = copyEmployee.id;//    When using "Copy Constructor", we can assign values to fields even if the field is final. This cannot be achieved when using clone() method.
        this.firstName = copyEmployee.firstName;
        this.lastName = copyEmployee.lastName;
        this.age = copyEmployee.age;
        this.salary = copyEmployee.salary;
    }

    public Employee(String firstName, String lastName, int age, double salary) {
        this.id = ++LAST_ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee e) {
        return this.firstName.compareToIgnoreCase(e.firstName);
    }

    static class EmployeeComparator implements Comparator<Employee> {
        private String sortType;

        public EmployeeComparator() {
            this("firstName");
        }

        public EmployeeComparator(String sortType) {
            this.sortType = sortType;
        }

        @Override
        public int compare(Employee e1, Employee e2) {
            if (sortType.equalsIgnoreCase(SORTType.ID.name()))
                return Integer.compare(e1.id, e2.id);
            if (sortType.equalsIgnoreCase("lastName"))
                return e1.lastName.compareToIgnoreCase(e2.lastName);
            if (sortType.equalsIgnoreCase("age"))
                return Integer.compare(e1.age, e2.age);
            if (sortType.equalsIgnoreCase("salary"))
                return Double.compare(e1.salary, e2.salary);

            return e1.firstName.compareToIgnoreCase(e2.firstName);
        }
    }

    @Override
    public String toString() {
        return "%-15d %-15s %-15s %-15d %.2f".formatted(id, firstName, lastName, age, salary);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee e = (Employee) obj;

        return Objects.equals(this.id, e.id) &&
                Objects.equals(this.firstName, e.firstName) &&
                Objects.equals(this.lastName, e.lastName) &&
                Objects.equals(this.age, e.salary) &&
                Objects.equals(this.salary, e.salary);
    }
}
