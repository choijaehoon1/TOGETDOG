package com.togetdog.adoption;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.DTO;

public class AdoptionService {
	private final Logger LOG = Logger.getLogger(AdoptionService.class);
	
	private AdoptionDao adoptionDao;
	
	public AdoptionService() {
		adoptionDao = new AdoptionDao();
	}
	
	public DTO doSelectOne(DTO dto) {
		//int readCnt = communityDao.readCount(dto);
		
		return adoptionDao.doSelectOne(dto);
	}
	
	
	public DTO dnoSelectOne(DTO dto) {
		//int readCnt = communityDao.readCount(dto);
		
		return adoptionDao.dnoSelectOne(dto);
	}
	
	
	public DTO sCheckSelectOne(DTO dto) {
		//int readCnt = communityDao.readCount(dto);
		
		return adoptionDao.sCheckSelectOne(dto);
	}
	
	public int doInsert(DTO dto) {
		return adoptionDao.doInsert(dto);
	}
	
	public int doDelete(DTO dto) {
		return adoptionDao.doDelete(dto);
	}
	
	public int doUpdate(DTO dto) {
		return adoptionDao.doUpdate(dto);
	}
	
	public int adopDoUpdate(DTO dto) {
		return adoptionDao.adopDoUpdate(dto);
	}
	
	public List<?> doRetrieve(DTO dto){
		return adoptionDao.doRetrieve(dto);
	}
	
}
