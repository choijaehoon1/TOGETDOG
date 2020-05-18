package com.togetdog.reply;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.SearchVO;

public class CommonReplySerivceMain {
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	private CommonReplyVO commonReplyVO;
	private CommmonReplyService commmonReplyService;
	
	public CommonReplySerivceMain() {
		commmonReplyService = new CommmonReplyService();
//		commonReplyVO = new CommonReplyVO(1,"1234","1234","멤버아이디","123","1");
	}
	
	public void doInsert() {
		int flag = commmonReplyService.doInsert(commonReplyVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doDelete() {
//		commonReplyVO.setReplyNo(10);
		int flag = commmonReplyService.doDelete(commonReplyVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doUpate() {
//		commonReplyVO.setReplyNo(10);
//		commonReplyVO.setBoardNo("게시글번호(m_134)");
		commonReplyVO.setContents("댓글내용485");
		commonReplyVO.setRegId("멤버아이디");
		int flag = commmonReplyService.doUpdate(commonReplyVO);
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
		List<CommonReplyVO> list=(List<CommonReplyVO>) commmonReplyService.doRetrieve(commonReplyVO);
		for(CommonReplyVO vo:list) {
			LOG.debug("vo" + vo);
		}
	}
	
	
	
	public static void main(String[] args) {
		CommonReplySerivceMain commonReplySerivceMain = new CommonReplySerivceMain();
		//commonReplySerivceMain.doInsert();
		//commonReplySerivceMain.doDelete();
		//commonReplySerivceMain.doUpate();
		commonReplySerivceMain.doRetrieve();
	}

}
