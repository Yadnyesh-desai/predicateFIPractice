import main.java.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private final Employee adam = new Employee("Adam", "Geller", 25, 20000);
    private final Employee john = new Employee("John", "Washington", 35, 30000);
    private final Employee ron = new Employee(john);

    @Test
    void compareTo() {
        assertEquals(9, john.compareTo(adam));
    }

//    @Test
//    void getId() {
//        assertEquals();
//    }

    @Test
    void getFirstName() {
        assertEquals("John", john.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Washington", john.getLastName());
    }

    @Test
    void getAge() {
        assertEquals(35, john.getAge());
    }

    @Test
    void getSalary() {
        assertEquals(30000, john.getSalary());
    }

    @Test
    @DisplayName("Equals")
    void equals() {
        assertAll(() -> assertEquals(ron, john),
                () -> assertNotEquals(adam, ron));
    }

    @Test
    @DisplayName("Hash Code")
    void hash_code() {
        assertAll(() -> assertEquals(ron.hashCode(), john.hashCode()),
                () -> assertNotEquals(john.hashCode(), adam.hashCode()));
    }
}
