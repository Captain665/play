package common.company.resources;

import common.company.CompanyModel;
import common.employee.EmployeeModel;
import common.employee.resources.EmployeeResponseResource;

import java.math.BigInteger;
import java.util.List;

public class CompanyResponseResource {

	public Long id;
	public String name;
	public String address;
	public String city;
	public String state;
	public String country;
	public String gstNo;
	public String category;
	public BigInteger numberOfEmployee;
	public List<EmployeeResponseResource> employeeDetails;

	public CompanyResponseResource() {
	}

	public CompanyResponseResource(CompanyModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.address = model.getAddress();
		this.city = model.getCity();
		this.state = model.getState();
		this.country = model.getCountry();
		this.gstNo = model.getGstNo();
		this.category = model.getCategory();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigInteger getNumberOfEmployee() {
		return numberOfEmployee;
	}

	public void setNumberOfEmployee(BigInteger numberOfEmployee) {
		this.numberOfEmployee = numberOfEmployee;
	}

	public List<EmployeeResponseResource> getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(List<EmployeeResponseResource> employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	@Override
	public String toString() {
		return "CompanyResponseResource{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", country='" + country + '\'' +
				", gstNo='" + gstNo + '\'' +
				", category='" + category + '\'' +
				", numberOfEmployee=" + numberOfEmployee +
				", employeeDetails=" + employeeDetails +
				'}';
	}
}
