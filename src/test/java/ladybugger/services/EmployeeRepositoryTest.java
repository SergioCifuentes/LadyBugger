package ladybugger.services;

import ladybugger.controller.AdminController;
import ladybugger.model.Employee;
import ladybugger.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRepositoryTest {

    @Mock
    private EmployeeRepository userRepository;
    @InjectMocks
    private AdminController adminController;
    Employee employee;
    List<Object[]> list;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
    }

    @Test
    void getDevelopersAndReturnAList(){
        list = new ArrayList<>();
        list.add(new Object[]{});
        Mockito.when(
                userRepository.findByDevRole()
        ).thenReturn(list);
        assertEquals(adminController.getDevs(),list);
    }

}
