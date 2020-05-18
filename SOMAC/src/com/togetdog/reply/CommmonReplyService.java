package com.togetdog.reply;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.DTO;

public class CommmonReplyService {
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	private CommonReplyDao commonReplyDao;
	
	public CommmonReplyService() {
		commonReplyDao = new CommonReplyDao();
	}
	
	public int doInsert(DTO dto) {
		return commonReplyDao.doInsert(dto);
	}
	
	public int doDelete(DTO dto) {
		return commonReplyDao.doDelete(dto);
	}
	
	public int doUpdate(DTO dto) {
		return commonReplyDao.doUpdate(dto);
	}
	
	public List<?> doRetrieve(DTO dto){
		return commonReplyDao.doRetrieve(dto);
	}
	
}
