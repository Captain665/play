package common.employee;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import common.assets.AssetModel;
import common.base.BaseModel;
import common.company.CompanyModel;
import common.employee.resources.EmployeeResource;
import common.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employee_details")
public class EmployeeModel extends BaseModel {
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "gender")
	private Gender gender;
	@Column(name = "joining_date")
	private String joiningDate;
	@Column(name = "resign_date")
	private String resignDate;
	@Column(name = "role")
	private String role;
	@Column(name = "location")
	private String location;
	@ManyToOne(targetEntity = CompanyModel.class, fetch = FetchType.EAGER, optional = false)
	private CompanyModel company;
	@OneToMany(targetEntity = AssetModel.class, fetch = FetchType.EAGER, mappedBy = "employee")
	@JsonManagedReference
	private List<AssetModel> assets;

	public EmployeeModel() {
	}

	public EmployeeModel(String fullName, String mobile, String emailId, Gender gender, String joiningDate, String resignDate, String role, String location, CompanyModel company, List<AssetModel> assets) {
		this.fullName = fullName;
		this.mobile = mobile;
		this.emailId = emailId;
		this.gender = gender;
		this.joiningDate = joiningDate;
		this.resignDate = resignDate;
		this.role = role;
		this.location = location;
		this.company = company;
		this.assets = assets;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getResignDate() {
		return resignDate;
	}

	public void setResignDate(String resignDate) {
		this.resignDate = resignDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public CompanyModel getCompany() {
		return company;
	}

	public void setCompany(CompanyModel company) {
		this.company = company;
	}

	public List<AssetModel> getAsset() {
		return assets;
	}

	public void setAsset(List<AssetModel> assets) {
		this.assets = assets;
	}

	@Override
	public String toString() {
		return "EmployeeModel{" +
				"fullName='" + fullName + '\'' +
				", mobile='" + mobile + '\'' +
				", emailId='" + emailId + '\'' +
				", gender=" + gender +
				", joiningDate=" + joiningDate +
				", resignDate=" + resignDate +
				", role='" + role + '\'' +
				", location='" + location + '\'' +
				", id=" + id +
				", createdAt=" + createdAt +
				", createdBy='" + createdBy + '\'' +
				", updatedAt=" + updatedAt +
				", updatedBy='" + updatedBy + '\'' +
				'}';
	}
}
