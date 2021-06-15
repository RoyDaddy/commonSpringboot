package com.neok.commonSpringboot.service;

import com.neok.commonSpringboot.util.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempService {
	@Autowired
    private FileStorageProperties prop;
}
