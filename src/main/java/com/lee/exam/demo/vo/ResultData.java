package com.lee.exam.demo.vo;

import lombok.Getter;

public class ResultData {
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private Object data1;
	
	private ResultData() {
		
	}
	
	public static ResultData from(String resultCode, String msg) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;

		return rd;
	}
	public static ResultData from(String resultCode, String msg, Object data1) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1 = data1;
		
		return rd;
	}
	public static ResultData from(String resultCode, String msg, Object data1, Object data2) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1 = data1;
		
		return rd;
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	public boolean isFail() {
		return isSuccess() == false;
	}

	public static ResultData newData(ResultData joinRd, Member member) {
		return from(joinRd.getResultCode(), joinRd.getMsg());
	}
}
