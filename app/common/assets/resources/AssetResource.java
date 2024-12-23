package common.assets.resources;

import common.employee.EmployeeModel;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

public class AssetResource {
	public Long id;
	public String type;
	public String name;
	public String quantity;
	public String description;
	public String price;


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

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public AssetResource(Long id, String type, String name, String quantity, String description, String price) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.price = price;
	}

	@Override
	public String toString() {
		return "AssetResource{" +
				"id=" + id +
				", type='" + type + '\'' +
				", name='" + name + '\'' +
				", quantity='" + quantity + '\'' +
				", description='" + description + '\'' +
				", price='" + price + '\'' +
				'}';
	}
}
