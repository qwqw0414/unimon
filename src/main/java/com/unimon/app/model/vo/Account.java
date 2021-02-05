package com.unimon.app.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Account implements Serializable{
	
	private static final long serialVersionUID = 129318238138L;

//	키값
	private long userNo;
	
	private String userId;
	private String userName;
	private String password;
	private Date regDate;
	
//	보유 권한
	private List<UserRole> role;
	
//	보유 권한 여부
	public boolean hasRole(UserRole role) {
		for(int i = 0; i < this.role.size(); i++) {
			if((String.valueOf(this.role.get(i))).equals(role.name()))
				return true;
		}
		return false;
	}
	
	public boolean hasRole(String role) {
		for(int i = 0; i < this.role.size(); i++) {
			if((String.valueOf(this.role.get(i))).equals(role))
				return true;
		}
		return false;
	}
	
}
