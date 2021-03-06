package com.lee.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private int authLeve;
	private String name;
	private String nickName;
	private String cellphoneNo;
	private String email;
	private boolean delStatus;
	private String delDate;
}
