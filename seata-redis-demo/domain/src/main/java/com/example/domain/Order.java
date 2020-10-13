package com.example.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

	/**
	 * @Author Chester_Zheng
	 * @Date 2020年2月18日上午11:07:19
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String orderId;

	private String productId;

	private String productName;

	private Date createdAt;

	private Date updatedAt;

	public Order() {
		super();
	}

	public Order(Long id, String orderId, String productId, String productName, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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
		return "Order [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", productName=" + productName
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
