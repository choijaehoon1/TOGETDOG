package com.togetdog.code;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.DTO;

public class CodeService {
	private final Logger LOG = Logger.getLogger(CodeService.class); 
	private CodeDao dao;
	
	public CodeService() {
		dao = new CodeDao();
	}
	
	public List<?> doRetrieve(DTO dto) {
		return dao.doRetrieve(dto);
	}
}
