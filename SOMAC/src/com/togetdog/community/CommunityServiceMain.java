/**
 *<pre>
 * com.togetdog
 * Class Name : CommunityServiceMain.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-11           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-11 
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

/**
 * @author sist128
 *
 */
public class CommunityServiceMain {
	
private final Logger LOG = Logger.getLogger(CommunityTestMain.class);
	
	private CommunityVO communityVO;
	private CommunityService communityService;
	
	public CommunityServiceMain() {
		communityService = new CommunityService();
		communityVO = new CommunityVO("1","최종1","제목","내용","1","1",0,"1");
	}
	
	/**
	*@Method Name:main
	*@작성일: 2020. 2. 11.
	*@작성자: sist128
	*@설명:
	*@param args
	*/
	public void doSelectOne() {
		communityVO.setTotNo("m_2");
		CommunityVO vo =(CommunityVO) communityService.doSelectOne(communityVO);
		
		LOG.debug(vo);
		
	}
	
	public void doInsert() {
		int flag = communityService.doInsert(communityVO);
		
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doDelete() {
		communityVO.setTotNo("m_2");
		int flag = communityService.doDelete(communityVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doUpdate() {
		communityVO.setTotNo("m_2");
		communityVO.setTitle("제목수정UU");
		communityVO.setContents("내용수정UU");
		
		
		int flag = communityService.doUpdate(communityVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	public void doRetrieve() {
		SearchVO searchVO = new SearchVO();
		searchVO.setPageSize(10);
		searchVO.setPageNum(1);
		searchVO.setSearchDiv("30");
		searchVO.setSearchWord("최종");
		
		List<CommunityVO> list = (List<CommunityVO>) communityService.doRetrieve(searchVO);
		
		for(CommunityVO vo:list) {
			LOG.debug("vo:" + vo);
		}
		
	}
	
	public static void main(String[] args) {
		CommunityServiceMain communityServiceMain = new CommunityServiceMain();
		//communityServiceMain.doSelectOne();
		//communityServiceMain.doInsert();
		//communityServiceMain.doDelete();
		//communityServiceMain.doUpdate();
		communityServiceMain.doRetrieve();
	}

}
