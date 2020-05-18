package com.togetdog.reply;

import java.util.List;

import org.apache.log4j.Logger;

public class CommonReplyTestMain {
	private final Logger LOG = Logger.getLogger(CommonReplyTestMain.class);
	
	private CommonReplyVO commonReplyVO;
	private CommonReplyDao commonReplyDao;
	
	public CommonReplyTestMain() {
		commonReplyDao = new CommonReplyDao();
		commonReplyVO = new CommonReplyVO("m_3",1,"1","멤버아이디","22","1");
	}
	
	public void doInsert() {
		int flag = commonReplyDao.doInsert(commonReplyVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doDelete() {
//		commonReplyVO.setReplyNo(3);
		int flag = commonReplyDao.doDelete(commonReplyVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doUpate() {
//		commonReplyVO.setReplyNo(2);
//		commonReplyVO.setBoardNo("게시글번호(m_12)");
		commonReplyVO.setContents("댓글내용");
		commonReplyVO.setRegId("멤버아이디");
		int flag = commonReplyDao.doUpdate(commonReplyVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
		
	}
	
	public void doRetrieve() {
		commonReplyVO.setPageSize(10);
		commonReplyVO.setPageNum(1);
		commonReplyVO.setRegId("멤버아이디");
		List<CommonReplyVO> list=(List<CommonReplyVO>) commonReplyDao.doRetrieve(commonReplyVO);
		
		for(CommonReplyVO vo:list) {
			LOG.debug("vo" + vo);
		}
	}
	
	
	public static void main(String[] args) {
		CommonReplyTestMain commonReplyTestMain = new CommonReplyTestMain();
		commonReplyTestMain.doInsert();
		//commonReplyTestMain.doDelete();
		//commonReplyTestMain.doUpate();
		//commonReplyTestMain.doRetrieve();
	}

}
