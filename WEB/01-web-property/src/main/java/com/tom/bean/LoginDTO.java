package com.tom.bean;

import java.util.Date;

public record LoginDTO(
		String agencyCodingId,
	    String branchCodingId,
	    String userId,
	    String pdwA,
	    String imgCode,
	    Date loginTime,
	    String pwdNew,
	    boolean force,
	    String encPwdA
		) {
	
	public static record TempPwdDTO(
			String agencyCodingId,
		    String branchCodingId,
		    String userId,
		    String pdwA,
		    String imgCode,
		    Date loginTime,
		    String pwdNew,
		    boolean force,
		    String encPwdA
			) {
		
	}
	
	
}
