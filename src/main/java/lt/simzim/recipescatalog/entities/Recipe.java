package lt.simzim.recipescatalog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="recipes")
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Integer duration;
	
	@Column
	private Integer serving;

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

	public Recipe(String name, String description, Integer duration, Integer serving) {

		this.name = name;
		this.description = description;
		this.duration = duration;
		this.serving = serving;
	}

	public Recipe() {
	}
}
