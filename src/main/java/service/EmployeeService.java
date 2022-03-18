package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Employee;
import repository.EmployeeRepository;



@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public Employee create (Employee persona) {
		return employeeRepository.save(persona);
	}
	
	public List<Employee> getAllPersonas (){
		return employeeRepository.findAll();
	}
	
	public void delete (Employee persona) {
		employeeRepository.delete(persona);
	}
	
	public Optional<Employee> findById (Long id) {
		return employeeRepository.findById(id);
	}
	

}