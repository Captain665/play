package common.assets;

import com.fasterxml.jackson.annotation.JsonBackReference;
import common.base.BaseModel;
import common.employee.EmployeeModel;
import jakarta.persistence.*;

@Entity
@Table(name = "company_assets")
public class AssetModel extends BaseModel {
	@Column(name = "name")
	public String name;
	@Column(name = "quantity")
	public String quantity;
	@Column(name = "description")
	public String description;
	@Column(name = "price")
	public String price;
	@ManyToOne(optional = false)
	@JoinColumn(name = "employee_id", nullable = false)
	@JsonBackReference
	public EmployeeModel employeeId;

	public AssetModel(String name, String quantity, String description, String price, EmployeeModel employeeId) {
		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.price = price;
		this.employeeId = employeeId;
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

	public EmployeeModel getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(EmployeeModel employeeId) {
		this.employeeId = employeeId;
	}
}
