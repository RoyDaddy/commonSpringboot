package com.neok.commonSpringboot.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecurityMapper {
	Map<String, Object> findById(String id);
}
