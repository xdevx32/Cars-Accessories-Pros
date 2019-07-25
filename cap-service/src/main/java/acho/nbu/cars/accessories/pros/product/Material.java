package acho.nbu.cars.accessories.pros.product;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "material")
public class Material {
	
    @Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Името е задължително")
	private String name;
    @NotNull(message = "Размера трябва да бъде въведен")
	private double squareMeters;
    @NotNull(message = "Цвета е задължителен")
	private Color color;
    @NotNull(message = "Цената е задължителна")
	private double price;

	public Material () {
		
	}

	public Material(Long id, double squareMeters, String name, Color color, double price) {
		super();
		this.id = id;
		this.squareMeters = squareMeters;
		this.name = name;
		this.color = color;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getSquareMeters() {
		return squareMeters;
	}

	public void setSquareMeters(double squareMeters) {
		this.squareMeters = squareMeters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public static double calculateExpensesBasedOnMaterialsBought(List<Material> materials) {
		double expensesBasedOnMaterialsBought = 0;
		
		for (Material material : materials) {
			expensesBasedOnMaterialsBought += material.getPrice();
		}
		return expensesBasedOnMaterialsBought;
	}
	
}
