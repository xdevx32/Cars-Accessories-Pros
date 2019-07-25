package acho.nbu.cars.accessories.pros.people;

import java.time.DayOfWeek;
import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



@Entity
@Table(name = "employee")
public class Employee {
	
    @Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Името е задължително")
	private String name;
    @NotNull(message = "Опита е задължителен")
	private Experience experience;
    @NotNull(message = "Детайлите на час са задължителни")
	private int detailsPerHour;
    @NotNull(message = "Заплащането е задължително")
	private double payPerHour;
    
    @DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    private int daysWorked;
    
	public Employee () {
		
	}


	public Employee(Long id, @NotBlank(message = "Името е задължително") String name,
			@NotNull(message = "Опита е задължителен") Experience experience,
			@NotNull(message = "Детайлите на час са задължителни") int detailsPerHour,
			@NotNull(message = "Заплащането е задължително") double payPerHour, LocalDate startDate) {
		super();
		this.id = id;
		this.name = name;
		this.experience = experience;
		this.detailsPerHour = detailsPerHour;
		this.payPerHour = payPerHour;
		this.startDate = startDate;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getDetailsPerHour() {
		return detailsPerHour;
	}

	public void setDetailsPerHour(int detailsPerHour) {
		this.detailsPerHour = detailsPerHour;
	}

	public double getPayPerHour() {
		return payPerHour;
	}

	public void setPayPerHour(double payPerHour) {
		this.payPerHour = payPerHour;
	}

	public Experience getExperience() {
		return experience;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public int getDaysWorked() {
		
		final LocalDate now = LocalDate.now();
	    final DayOfWeek startW = this.startDate.getDayOfWeek();
	    final DayOfWeek endW = now.getDayOfWeek();

	    final int days = (int) ChronoUnit.DAYS.between(startDate, now);
	    final int daysWithoutWeekends = days - 2 * ((days + startW.getValue())/7);
	    daysWorked = daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);

	    return daysWorked;
	}


	public void setDaysWorked(int daysWorked) {
		this.daysWorked = daysWorked;
	}
	
	public static double calculateExpensesBasedOnSalaries(List<Employee> employees) {
		double expensesBasedOnSalaries = 0;
		
		for (Employee employee : employees) {
			expensesBasedOnSalaries += employee.getDaysWorked() * 8 * employee.getPayPerHour();
		}
		
		return expensesBasedOnSalaries;	
	}
	
}
