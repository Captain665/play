package common.employee;

import common.assets.AssetModel;
import common.company.CompanyModel;
import common.enums.Gender;

import java.util.List;

public class EmployeeBuilder {
	public String fullName;
	public String mobile;
	public String emailId;
	public Gender gender;
	public String joiningDate;
	public String resignDate;
	public String role;
	public String location;
	public CompanyModel company;
	public List<AssetModel> assets;

	public String getFullName() {
		return fullName;
	}

	public EmployeeBuilder setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public EmployeeBuilder setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getEmailId() {
		return emailId;
	}

	public EmployeeBuilder setEmailId(String emailId) {
		this.emailId = emailId;
		return this;
	}

	public Gender getGender() {
		return gender;
	}

	public EmployeeBuilder setGender(Gender gender) {
		this.gender = gender;
		return this;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public EmployeeBuilder setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
		return this;
	}

	public String getResignDate() {
		return resignDate;
	}

	public EmployeeBuilder setResignDate(String resignDate) {
		this.resignDate = resignDate;
		return this;
	}

	public String getRole() {
		return role;
	}

	public EmployeeBuilder setRole(String role) {
		this.role = role;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public EmployeeBuilder setLocation(String location) {
		this.location = location;
		return this;
	}

	public CompanyModel getCompany() {
		return company;
	}

	public EmployeeBuilder setCompany(CompanyModel company) {
		this.company = company;
		return this;
	}

	public List<AssetModel> getAssets() {
		return assets;
	}

	public EmployeeBuilder setAssets(List<AssetModel> assets) {
		this.assets = assets;
		return this;
	}

	public EmployeeModel build() {
		return new EmployeeModel(
				this.fullName,
				this.mobile,
				this.emailId,
				this.gender,
				this.joiningDate,
				this.resignDate,
				this.role,
				this.location,
				this.company,
				this.assets
		);
	}
}
