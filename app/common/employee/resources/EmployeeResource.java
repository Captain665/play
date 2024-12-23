package common.employee.resources;

import common.assets.AssetModel;
import common.assets.resources.AssetResource;
import common.company.CompanyModel;
import common.company.resources.CompanyResource;
import common.employee.EmployeeModel;
import common.enums.Gender;

import java.time.LocalDateTime;
import java.util.List;

public class EmployeeResource {

	public Long id;
	public String fullName;
	public String mobile;
	public String emailId;
	public Gender gender;
	public String joiningDate;
	public String resignDate;
	public String role;
	public String location;
	public CompanyModel companyId;
	public List<AssetModel> assets;


	public EmployeeResource(Long id, String fullName, String mobile, String emailId, Gender gender, String joiningDate, String resignDate, String role, String location, CompanyModel companyId, List<AssetModel> assets) {
		this.id = id;
		this.fullName = fullName;
		this.mobile = mobile;
		this.emailId = emailId;
		this.gender = gender;
		this.joiningDate = joiningDate;
		this.resignDate = resignDate;
		this.role = role;
		this.location = location;
		this.companyId = companyId;
		this.assets = assets;
	}

	public EmployeeResource(EmployeeModel model) {
		this.id = model.getId();
		this.fullName = model.getFullName();
		this.mobile = model.getMobile();
		this.emailId = model.getEmailId();
		this.gender = model.getGender();
		this.joiningDate = model.getJoiningDate();
		this.resignDate = model.getResignDate();
		this.role = model.getRole();
		this.location = model.getLocation();
		this.companyId = model.getCompanyId();
		this.assets = model.getAsset();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public CompanyModel getCompanyId() {
		return companyId;
	}

	public void setCompanyId(CompanyModel companyId) {
		this.companyId = companyId;
	}

	public List<AssetModel> getAssets() {
		return assets;
	}

	public void setAssets(List<AssetModel> assets) {
		this.assets = assets;
	}

	@Override
	public String toString() {
		return "EmployeeResource{" +
				"id=" + id +
				", fullName='" + fullName + '\'' +
				", mobile='" + mobile + '\'' +
				", emailId='" + emailId + '\'' +
				", gender=" + gender +
				", joiningDate='" + joiningDate + '\'' +
				", resignDate='" + resignDate + '\'' +
				", role='" + role + '\'' +
				", location='" + location + '\'' +
				", companyDetails=" + companyId +
				", assets=" + assets +
				'}';
	}
}
