package common.base;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long id;
	@Column(name = "created_at", updatable = false)
	public LocalDateTime createdAt;
	@Column(name = "created_by")
	public String createdBy;
	@Column(name = "updated_at")
	public LocalDateTime updatedAt;
	@Column(name = "updated_by")
	public String updatedBy;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@PrePersist
	public void setCreatedAt() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.createdBy = "Customer";
		this.updatedBy = "Customer";
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = "Customer";
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	@PreUpdate
	public void setUpdatedAt() {
		this.updatedAt = LocalDateTime.now();
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = "Customer";
	}
}
