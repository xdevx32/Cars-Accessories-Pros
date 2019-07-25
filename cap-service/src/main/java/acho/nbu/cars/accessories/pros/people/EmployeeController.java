package acho.nbu.cars.accessories.pros.people;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/list")
	public String getAllEmployees(@Valid Employee employee, BindingResult result, Model model) {
		
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);
		
		String title = "Списък с всички работници";
        model.addAttribute("title", title);
		
		return "list-employees";
	}
	
    @RequestMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployee(id);
    }
	
    @RequestMapping(method = RequestMethod.GET, value = "add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("title", "Добави работник");
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "add")
    public String processEmployeeForm(@ModelAttribute Employee employee) {
        employeeService.addEmployee(employee);
        return "index";
    }
	
    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }
    
    @RequestMapping(value = "/delete/{id}")
    public String deleteProgram(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }
}
