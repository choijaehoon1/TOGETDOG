package com.togetdog.desertion;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.DTO;

public class DesertionService {
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	private DesertionDao desertionDao;
	
	public DesertionService() {
		desertionDao = new DesertionDao();
	}
	
	public DTO doSelectOne(DTO dto) {
		//int readCnt = communityDao.readCount(dto);
		return desertionDao.doSelectOne(dto);
		
	}
	
	public int doInsert(DTO dto) {
		return desertionDao.doInsert(dto);
	}
	
	public int doDelete(DTO dto) {
		return desertionDao.doDelete(dto);
	}
	
	public int doUpdate(DTO dto) {
		return desertionDao.doUpdate(dto);
	}
	
	public List<?> doRetrieve(DTO dto){
		return desertionDao.doRetrieve(dto);
	}
	
}
