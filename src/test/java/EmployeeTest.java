import main.java.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private final Employee john = new Employee("John","Washington",35,30000);

    @Test
    void compareTo() {
        final Employee adam = new Employee("Adam","Geller",25,20000);
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
        assertEquals(35,john.getAge());
    }

    @Test
    void getSalary() {
        assertEquals(30000,john.getSalary());
    }
}
