package acho.nbu.cars.accessories.pros.product;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "detail")
public class Detail {
	
	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotNull(message = "Цената е задължителна")
	private double price;
	
	@NotNull
	private int quantity;

	@NotNull(message = "Време за изработка е задължително")
	private int timeToCreate;
	
	public int getTimeToCreate() {
		return timeToCreate;
	}

	public void setTimeToCreate(int timeToCreate) {
		this.timeToCreate = timeToCreate;
	}

	@ManyToMany
	List<Material> materials;
	
	 
	public Detail () {
		
	}

	public Detail(Long id, String name, double price, List<Material> materials, int timeToCreate, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.materials = materials;
		this.timeToCreate = timeToCreate;
		this.quantity = quantity;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Material> getMaterials() {
		return materials;
	}	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	
	public double calculateMaterialsCost() {
		double cost = 0.0;
		
		for (Material mat : this.materials) {
			cost += mat.getPrice();
		}
		return cost;
	}

	// За приходите
	public static double calculateIncomeBasedOnDetailsProduced(List<Detail> details) {
		double incomeBasedOnDetailsProduced = 0;
		
		for (Detail detail : details) {
			incomeBasedOnDetailsProduced += detail.getPrice() * detail.getQuantity();
		}
		
		return incomeBasedOnDetailsProduced;	
	}

	// За печалбата. Изваждам стойността на материалите
	public static double calculateProfitBasedOnDetailsProduced(List<Detail> details) {
		double profitBasedOnDetailsProduced = 0;
		
		for (Detail detail : details) {
			
			// Вадя стойността на използваните материали
			for (Material material : detail.getMaterials()) {
				profitBasedOnDetailsProduced -= material.getPrice();
			}

			// На края добавям продажната цена на детайла
			profitBasedOnDetailsProduced += detail.getPrice() * detail.getQuantity();
		}
		
		return profitBasedOnDetailsProduced;	
	}
}
