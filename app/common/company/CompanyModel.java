package common.company;

import common.base.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "company_details")
public class CompanyModel extends BaseModel {

	@Column(name = "name")
	public String name;
	@Column(name = "address")
	public String address;
	@Column(name = "city")
	public String city;
	@Column(name = "state")
	public String state;
	@Column(name = "country")
	public String country;
	@Column(name = "gst_number")
	public String gstNo;
	@Column(name = "category")
	public String category;

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


	public CompanyModel(String name, String address, String city, String state, String country, String gstNo, String category) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.gstNo = gstNo;
		this.category = category;
	}

	@Override
	public String toString() {
		return "CompanyModel{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", country='" + country + '\'' +
				", gstNo='" + gstNo + '\'' +
				", category='" + category + '\'' +
				", id=" + id +
				", createdAt=" + createdAt +
				", createdBy='" + createdBy + '\'' +
				", updatedAt=" + updatedAt +
				", updatedBy='" + updatedBy + '\'' +
				'}';
	}
}
