/**
 *<pre>
 * com.togetdog
 * Class Name : CommunityTestMain.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-10           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-10 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */

package com.togetdog.community;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.SearchVO;
import com.togetdog.filemng.FileVO;
import com.togetdog.review.JoinVO;

/**
 * @author sist128
 *
 */
public class CommunityTestMain {
	private final Logger LOG = Logger.getLogger(CommunityTestMain.class);
	
	private CommunityVO communityVO;
	private CommunityDao communityDao;
	private FileVO fileVO;
	private CommunityVO1 communityVO1;
	private CommunityVO2 communityVO2;
	private JoinVO joinVO;
	
	
	public CommunityTestMain() {
		communityDao = new CommunityDao();
		communityVO = new CommunityVO("j_1","JH","제목","내용","1","1",0,"1");
		fileVO = new FileVO("j_5","원본파일","저장파일","j_5", 1,".jpg","아이디","3");
		communityVO1 = new CommunityVO1();
		communityVO2 = new CommunityVO2("j_4",1,"내용","아이디","작성일","1","1");
		joinVO = new JoinVO("1", "1", "1", "1","1","1","1","1", 1, "1", "1", "1");
	}
	
	/**
	*@Method Name:main
	*@작성일: 2020. 2. 10.
	*@작성자: sist128
	*@설명:
	*@param args
	*/
	public void doJInsert() {
		
		int flag = communityDao.doJInsert(communityVO2);
		
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doReplyInsert() {
		
		int flag = communityDao.doReplyInsert(communityVO2);
		
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}

	public void doInsert() {
		
		int flag = communityDao.doInsert(communityVO);
		
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	
	public void doFileInsert() {
		int flag = communityDao.doFileInsert(fileVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doDelete() {
		communityVO.setTotNo("m_0");
		int flag = communityDao.doDelete(communityVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void FileUpdate() {
		communityVO.setTotNo("m_165");
		communityVO.setTitle("제목수정");
		communityVO.setContents("내용수정");
		communityVO.setMemberId("내용수정");
		communityVO.setContents("내용수정");
		communityVO.setContents("내용수정");
		
		
		int flag = communityDao.doUpdate(communityVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	
	public void doUpdate() {
		communityVO.setTotNo("m_1");
		communityVO.setTitle("제목수정");
		communityVO.setContents("내용수정");
		
		
		int flag = communityDao.doUpdate(communityVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doGetno() {
		CommunityVO  vo= (CommunityVO) communityDao.doGetno(communityVO);
		LOG.debug(vo);
	}
	
	public void doSelectOne() {
		communityVO1.setTotNo("j_1");
		
		CommunityVO1 vo = (CommunityVO1) communityDao.doSelectOne(communityVO1);
		
		LOG.debug(vo);
		
	}
	
	public void doRSelectOne() {
		joinVO.setRevNo("r_21");
		
		JoinVO vo = (JoinVO) communityDao.doRSelectOne(joinVO);
		
		LOG.debug(vo);
		
	}
	
	public void doRetrieve() {
		SearchVO searchVO = new SearchVO();
		searchVO.setPageSize(10);
		searchVO.setPageNum(1);
		searchVO.setSearchDiv("30");
		searchVO.setSearchWord("JH1");
		
		List<CommunityVO1> list = (List<CommunityVO1>) communityDao.doRetrieve(searchVO);
		
		for(CommunityVO1 vo:list) {
			LOG.debug("vo:" + vo);
		}
		
	}
	
	
	
	public void doRecentRRetrieve() {
		SearchVO searchVO = new SearchVO();
		searchVO.setPageSize(10);
		searchVO.setPageNum(1);
		//searchVO.setSearchDiv("30");
		//searchVO.setSearchWord("JH");
		
		List<JoinVO> list1 = (List<JoinVO>) communityDao.doRecentRRetrieve(searchVO);
		
		for(JoinVO vo:list1) {
			LOG.debug("vo:" + vo);
		}
		
	}
	
	public void doRecentRetrieve() {
		SearchVO searchVO = new SearchVO();
		searchVO.setPageSize(10);
		searchVO.setPageNum(1);
		//searchVO.setSearchDiv("30");
		//searchVO.setSearchWord("JH");
		
		List<CommunityVO1> list1 = (List<CommunityVO1>) communityDao.doRecentRetrieve(searchVO);
		
		for(CommunityVO1 vo:list1) {
			LOG.debug("vo:" + vo);
		}
		
	}
	
	public void doReplyRetrieve() {
		SearchVO searchVO = new SearchVO();
		searchVO.setPageSize(5);
		searchVO.setPageNum(1);
		//searchVO.setSearchDiv("30");
		//searchVO.setSearchWord("JH");
		
		List<CommunityVO2> list1 = (List<CommunityVO2>) communityDao.doReplyRetrieve(searchVO);
		
		for(CommunityVO2 vo:list1) {
			LOG.debug("vo:" + vo);
		}
		
	}
	
	public void doMRetrieve() {
		SearchVO searchVO = new SearchVO();
		searchVO.setPageSize(10);
		searchVO.setPageNum(1);
		//searchVO.setSearchDiv("30");
		//searchVO.setSearchWord("JH");
		
		List<CommunityVO1> list = (List<CommunityVO1>) communityDao.doMRetrieve(searchVO);
		
		for(CommunityVO1 vo:list) {
			LOG.debug("vo:" + vo);
		}
		
	}
	
	
	public void doJRetrieve() {
		SearchVO searchVO = new SearchVO();
		searchVO.setPageSize(10);
		searchVO.setPageNum(1);
		//searchVO.setSearchDiv("30");
		//searchVO.setSearchWord("JH");
		
		List<CommunityVO1> list = (List<CommunityVO1>) communityDao.doJRetrieve(searchVO);
		
		for(CommunityVO1 vo:list) {
			LOG.debug("vo:" + vo);
		}
		
	}
	public void doRRetrieve() {
		SearchVO searchVO = new SearchVO();
		searchVO.setPageSize(10);
		searchVO.setPageNum(1);
		//searchVO.setSearchDiv("30");
		//searchVO.setSearchWord("JH");
		
		List<JoinVO> list = (List<JoinVO>) communityDao.doRRetrieve(searchVO);
		
		for(JoinVO vo:list) {
			LOG.debug("vo:" + vo);
		}
		
	}
	
	public static void main(String[] args) {
		CommunityTestMain communityTestMain = new CommunityTestMain();
		//communityTestMain.doRetrieve();
		//communityTestMain.doInsert();
		//communityTestMain.doDelete();
		//communityTestMain.doUpdate();
		//communityTestMain.doSelectOne();
		//communityTestMain.doFileInsert();
		//communityTestMain.doRecentRetrieve();
		//communityTestMain.doReplyRetrieve();
		//communityTestMain.doMRetrieve();
		//communityTestMain.doJInsert();
		//communityTestMain.doJRetrieve();
		//communityTestMain.doRRetrieve();
		//communityTestMain.doReplyInsert();
		//communityTestMain.doRecentRRetrieve();
		//communityTestMain.doGetno();
		communityTestMain.doRSelectOne();
	}

}
