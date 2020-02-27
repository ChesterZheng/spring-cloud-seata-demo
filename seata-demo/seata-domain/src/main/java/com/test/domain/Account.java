package com.test.domain;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {

	/**
	 * @Author Chester_Zheng
	 * @Date 2020年2月18日上午11:38:55
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String orderId;

	private Integer profit;

	private Date createdAt;

	private Date updatedAt;

	public Account() {
		super();
	}

	public Account(Long id, String orderId, Integer profit, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.profit = profit;
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

	public Integer getProfit() {
		return profit;
	}

	public void setProfit(Integer profit) {
		this.profit = profit;
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
		return "Account [id=" + id + ", orderId=" + orderId + ", profit=" + profit + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
