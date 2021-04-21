package com.neok.commonSpringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neok.commonSpringboot.mapper.TempMapper;
import com.neok.commonSpringboot.util.FileStorageProperties;

@Service
public class TempService {
	@Autowired
	private TempMapper tempMapper;
	@Autowired
    private FileStorageProperties prop;
}
