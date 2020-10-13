package com.example.domain;

import java.io.Serializable;
import java.util.Date;

public class Inventory implements Serializable {

	/**
	 * @Author Chester_Zheng
	 * @Date 2020年2月18日上午11:43:13
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String productId;

	private Long amount;

	private Date createdAt;

	private Date updatedAt;

	public Inventory() {
		super();
	}

	public Inventory(Long id, String productId, Long amount, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.productId = productId;
		this.amount = amount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", productId=" + productId + ", amount=" + amount + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
