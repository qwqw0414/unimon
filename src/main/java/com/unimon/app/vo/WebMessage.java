package com.unimon.app.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebMessage implements Serializable {
	
	private static final long serialVersionUID = -8113890482444326416L;

	Account sender;
	String message;
	MessageType type;

}
