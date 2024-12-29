package common.employee.resources;

import common.employee.EmployeeSalary;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public class EmployeeSalaryResource {
	public Long id;
	public BigDecimal baseAmount;
	public BigDecimal hra;
	public BigDecimal pf;
	public BigDecimal medical;
	public BigDecimal tax;
	public BigDecimal totalAmount;


	public EmployeeSalaryResource() {
	}

	public EmployeeSalaryResource(EmployeeSalary model) {
		this.id = model.getId();
		this.baseAmount = model.getBaseAmount();
		this.hra = model.getHra();
		this.pf = model.getPf();
		this.medical = model.getMedical();
		this.tax = model.getTax();
		this.totalAmount = model.getTotalAmount();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	public BigDecimal getHra() {
		return hra;
	}

	public void setHra(BigDecimal hra) {
		this.hra = hra;
	}

	public BigDecimal getPf() {
		return pf;
	}

	public void setPf(BigDecimal pf) {
		this.pf = pf;
	}

	public BigDecimal getMedical() {
		return medical;
	}

	public void setMedical(BigDecimal medical) {
		this.medical = medical;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
