package com.amdocs.crm.model.enums;

public enum OrderStatus {
	CREATED(1, "Order created"), APPROVED(2, "Order Approved"), CANCELED(3, "Order canceled"), DELIVERED(4, "Order delivered"), REJECTED(5, "Order Rejected");

	private int statusId;

	private String statusDescription;

	private OrderStatus(int statusId, String statusDescription) {
		this.statusId = statusId;
		this.statusDescription = statusDescription;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
}
