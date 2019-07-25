package acho.nbu.cars.accessories.pros.people;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll()
		.forEach(employees::add);
		
		return employees;
	}
	
	
	public Employee getEmployee(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Невалидно ID на работник: " + id));
		return employee;
	}

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteEmployee(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Невалидно ID на работник: " + id));
        employeeRepository.delete(employee);
	}
	
}
