package com.neok.commonSpringboot.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TempMapper {
	int temp();
}
