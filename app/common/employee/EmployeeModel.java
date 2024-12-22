package common.employee;

import common.base.BaseModel;
import common.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
public class EmployeeModel extends BaseModel {
	@Column(name = "full_name")
	public String fullName;
	@Column(name = "mobile")
	public String mobile;
	@Column(name = "email_id")
	public String emailId;
	@Column(name = "gender")
	public Gender gender;
	@Column(name = "joining_date")
	public LocalDateTime joiningDate;
	@Column(name = "resign_date")
	public LocalDateTime resignDate;
	@Column(name = "role")
	public String role;
	@Column(name = "location")
	public String location;


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

	public LocalDateTime getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDateTime joiningDate) {
		this.joiningDate = joiningDate;
	}

	public LocalDateTime getResignDate() {
		return resignDate;
	}

	public void setResignDate(LocalDateTime resignDate) {
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

	public EmployeeModel(String fullName, String mobile, String emailId, Gender gender, LocalDateTime joiningDate, LocalDateTime resignDate, String role, String location) {
		this.fullName = fullName;
		this.mobile = mobile;
		this.emailId = emailId;
		this.gender = gender;
		this.joiningDate = joiningDate;
		this.resignDate = resignDate;
		this.role = role;
		this.location = location;
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
