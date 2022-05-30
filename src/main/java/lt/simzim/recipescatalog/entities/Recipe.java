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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="recipes")
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 64)
	@NotNull(message = "Recepto pavadinimas privalomas")
	@Length(min = 3, max = 200, message = "Recepto pavadinimas turi būti ilgesnis nei 3 simboliai ir trumpesnis už 200 simbolius")
	private String name;
	
	@Column(columnDefinition = "TEXT")
	@NotNull(message = "Recepto aprašymas privalomas")
	@Length(min = 3, message = " turi būti ilgesnis nei 3 simboliai")
	private String description;
	
	@Column
	private Integer duration;
	
	@Column
	private Integer serving;
	
	@Column
	private String fileName;
	
	@OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
	private List<Component> components;


	public Recipe(String name, String description, Integer duration, Integer serving, String fileName) {
		super();
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.serving = serving;
		this.fileName = fileName;
	}

	public Recipe(String name, String description, Integer duration, Integer serving) {

		this.name = name;
		this.description = description;
		this.duration = duration;
		this.serving = serving;
	}

	public Recipe() {
	}
	
	
	
	public List<Component> getComponents() {
		return components;
	}

	public void setComponents(List<Component> components) {
		this.components = components;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getServing() {
		return serving;
	}

	public void setServing(Integer serving) {
		this.serving = serving;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
