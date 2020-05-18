package com.togetdog.community;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.DTO;

public class CommunityService {
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	private CommunityDao communityDao;
	
	public CommunityService() {
		communityDao = new CommunityDao();
	}
	
	public DTO doSelectOne(DTO dto) {
		int readCnt = communityDao.readCount(dto);
		
		return communityDao.doSelectOne(dto);
		
	}
	
	
	public DTO doRSelectOne(DTO dto) {
		int readCnt = communityDao.readRCount(dto);
		
		return communityDao.doRSelectOne(dto);
		
	}
	public DTO doGetno(DTO dto) {
		return communityDao.doGetno(dto);
	}
	
	public int doInsert(DTO dto) {
		return communityDao.doInsert(dto);
	}
	
	public int doFileInsert(DTO dto) {
		return communityDao.doFileInsert(dto);
	}
	
	public int doFileUpdate(DTO dto) {
		return communityDao.doFileUpdate(dto);
	}
	
	public int doReplyInsert(DTO dto) {
		return communityDao.doReplyInsert(dto);
	}
	
	public int doReplyDelete(DTO dto) {
		return communityDao.doReplyDelete(dto);
	}
	
	
	public DTO doreplySelectOne(DTO dto) {
		return communityDao.doreplySelectOne(dto);
	}
	
	
	public int doReplyupdate(DTO dto) {
		return communityDao.doReplyupdate(dto);
	}
	
	public int doDelete(DTO dto) {
		return communityDao.doDelete(dto);
	}
	
	public int doUpdate(DTO dto) {
		return communityDao.doUpdate(dto);
	}
	
	public List<?> doRetrieve(DTO dto){
		return communityDao.doRetrieve(dto);
	}
	
	
	public List<?> doRecentMRetrieve(DTO dto){
		return communityDao.doRecentMRetrieve(dto);
	}
	
	public List<?> doRecentJRetrieve(DTO dto){
		return communityDao.doRecentJRetrieve(dto);
	}
	
	public List<?> doRecentRRetrieve(DTO dto){
		return communityDao.doRecentRRetrieve(dto);
	}
	
	public List<?> doRecentRetrieve(DTO dto){
		return communityDao.doRecentRetrieve(dto);
	}
	
	public List<?> doMRetrieve(DTO dto){
		return communityDao.doMRetrieve(dto);
	}
	public List<?> doJRetrieve(DTO dto){
		return communityDao.doJRetrieve(dto);
	}
	
	public List<?> doRRetrieve(DTO dto){
		return communityDao.doRRetrieve(dto);
	}
	
	public List<?> doReplyRetrieve(DTO dto){
		return communityDao.doReplyRetrieve(dto);
	}

	public List<?> doRReplyRetrieve(DTO dto){
		return communityDao.doRReplyRetrieve(dto);
	}
	

}
