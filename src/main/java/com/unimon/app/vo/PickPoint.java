package com.unimon.app.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PickPoint implements Serializable{

	private static final long serialVersionUID = 6521111248892115080L;
	
	private long idx;
	private String code;
	private String rare;
	private int rarity;
	
}
