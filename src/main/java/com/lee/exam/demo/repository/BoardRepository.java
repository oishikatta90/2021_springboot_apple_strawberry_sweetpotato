package com.lee.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lee.exam.demo.vo.Board;

@Mapper
public interface BoardRepository {

	public Board getBoardById(@Param("id") int id);
}
