package acho.nbu.cars.accessories.pros.report;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import acho.nbu.cars.accessories.pros.people.Employee;
import acho.nbu.cars.accessories.pros.people.EmployeeService;
import acho.nbu.cars.accessories.pros.product.Detail;
import acho.nbu.cars.accessories.pros.product.DetailService;
import acho.nbu.cars.accessories.pros.product.Material;
import acho.nbu.cars.accessories.pros.product.MaterialService;

@Controller
@RequestMapping("/reports")
public class ReportController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DetailService detailService;
	
	@Autowired
	private MaterialService materialService;
	

	@RequestMapping("/list")
	public String getAllData(Employee employee,
							 Detail detail,
							 Material material,
							 BindingResult result,
							 Model model) {

		List<Employee> employees = employeeService.getAllEmployees();
	    List<Detail> details = detailService.getAllDetails();
		List<Material> materials = materialService.getAllMaterials();
		
		// Разходи:
		double expensesBasedOnSalaries = Employee.calculateExpensesBasedOnSalaries(employees);
		model.addAttribute("expensesBasedOnSalaries", expensesBasedOnSalaries);
		double expensesBasedOnMaterialsBought = Material.calculateExpensesBasedOnMaterialsBought(materials);
		model.addAttribute("expensesBasedOnMaterialsBought", expensesBasedOnMaterialsBought);
		// Общо разходи:
		double expensesTotal = expensesBasedOnMaterialsBought + expensesBasedOnSalaries;
		model.addAttribute("expensesTotal", expensesTotal);

		// Приходи:
		double incomeBasedOnDetailsProduced = Detail.calculateIncomeBasedOnDetailsProduced(details);
		model.addAttribute("incomeBasedOnDetailsProduced", incomeBasedOnDetailsProduced);
		// Общо приходи:
		double incomeTotal = incomeBasedOnDetailsProduced;
		model.addAttribute("incomeTotal", incomeTotal);
		
		// Печалба:
		double profitBasedOnDetailsProduced = Detail.calculateProfitBasedOnDetailsProduced(details);
		model.addAttribute("profitBasedOnDetailsProduced", profitBasedOnDetailsProduced);
		// Общо печалба:
		double profitTotal = profitBasedOnDetailsProduced - (profitBasedOnDetailsProduced * 10.0 / 100); // -10%
		model.addAttribute("profitTotal", profitTotal);
		
		// Текуща дата
		LocalDate currentDate = LocalDate.now();
		model.addAttribute("currentDate", currentDate);
		
		// Заглавие на страницата за справки
		String title = "Справка";
        model.addAttribute("title", title);
		return "report";
	}
	
}
