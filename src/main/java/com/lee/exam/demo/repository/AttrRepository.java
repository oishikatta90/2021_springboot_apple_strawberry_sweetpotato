package com.lee.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.lee.exam.demo.vo.Attr;

@Mapper
public interface AttrRepository {

	Attr get(String relTypeCode, int relId, String typeCode, String type2Code);

	String getValue(String relTypeCode, int relId, String typeCode, String type2Code);

	int remove(String relTypeCode, int relId, String typeCode, String type2Code);

	void setValue(String relTypeCode, int relId, String typeCode, String type2Code, String value, String expireDate);

}
