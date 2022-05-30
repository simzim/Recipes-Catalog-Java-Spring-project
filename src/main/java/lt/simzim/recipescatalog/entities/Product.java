package lt.simzim.recipescatalog.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 64)
	@NotNull(message = "Produkto pavadinimas privalomas")
	@Length(min = 3, max = 200, message = "Produkto pavadinimas turi būti ilgesnis nei 3 simboliai ir trumpesnis už 200 simbolius")
	private String name;
	
	@Column
	@NotNull(message = "Produkto svoris privalomas")
	@Positive(message = "svoris didesnis už nulį")
	private double weight;
	
	@Column
	@NotNull(message = "Produkto kaina privalomas")
	@Positive(message = "kaina didesnė už nulį")
	@DecimalMax(value = "100000", message = "Kaina turi būti skaičius")
	private double price;

	@Column
	@NotNull(message = "Produkto matavimo vienetai privalomi")
	@Length(min = 1, max = 10, message = "matavimo vienetas turi būti ilgesnis nei 1 simboliai ir trumpesnis už 10 simbolių")
	private String unit;
	
	
	
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private List<Component> components;
	
	public Product() {
	}

	public Product(String name, double weight, double price) {
		this.name = name;
		this.weight = weight;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	} 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	
	
	
}
