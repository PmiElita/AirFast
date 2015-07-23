package com.pylypchak.airfast.model;

import java.sql.Timestamp;
import java.util.Date;

import com.pylypchak.airfast.annotation.Column;

public class ForgottenPassword {
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "code")
	private String code;

	@Column(name = "is_enabled")
	private Boolean isEnabled;

	@Column(name = "creation_date")
	private Timestamp creationDate;

	public ForgottenPassword() {
		isEnabled = true;
		creationDate = new Timestamp(new Date().getTime());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

}
