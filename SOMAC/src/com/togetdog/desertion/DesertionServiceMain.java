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

package com.togetdog.desertion;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.SearchVO;

/**
 * @author sist128
 *
 */
public class DesertionServiceMain {
	
private final Logger LOG = Logger.getLogger(DesertionServiceMain.class);
	
	private DesertionVO desertionVO;
	private DesertionService desertionService;
	
	public DesertionServiceMain() {
		desertionService = new DesertionService();
		desertionVO = new DesertionVO("411314202000038",	
									  "r",
									  "http://www.animal.go.kr/files/shelter/2020/01/202002171302370_s.jpg",
									  "20200217",	
									  "곰달래로6길16-16부근도로변 (다음2-15)",	
									  "[개] 믹스견",	
									  "흰색",	
									  "2018(년생)",	
									  "5(Kg)",	
									  "서울-양천-2020-00037",
									  "20200217",	
									  "20200227",	
									  "http://www.animal.go.kr/files/shelter/2020/01/202002171302370.jpg",	
									  "F",	
									  "N",
									  "치석없으며온순하고잘따르고눈물자국있으며노란색목줄착용하고있음",
									  "강현림 동물병원",	
									  "02-2642-9159",
									  "서울특별시 양천구 목동 657번지 16호",
									  "서울특별시 양천구",	
									  "양천구청",
									  "02-2620-4821"
									  );  
	}
	
	/**
	*@Method Name:main
	*@작성일: 2020. 2. 11.
	*@작성자: sist128
	*@설명:
	*@param args
	*/
	public void doSelectOne() {
		desertionVO.setDesertionNo("428356202000161");
		DesertionVO vo =(DesertionVO) desertionService.doSelectOne(desertionVO);
		
		LOG.debug(vo);
		
	}
	
	public void doInsert() {
		int flag = desertionService.doInsert(desertionVO);
		
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doDelete() {
		desertionVO.setDesertionNo("4");
		int flag = desertionService.doDelete(desertionVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doUpdate() {
		desertionVO.setDesertionNo("411314202000037");
		desertionVO.setProcessState("f");
		

		int flag = desertionService.doUpdate(desertionVO);
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
		searchVO.setSearchWord("4");
		
		List<DesertionVO> list = (List<DesertionVO>) desertionService.doRetrieve(searchVO);
		
		for(DesertionVO vo:list) {
			LOG.debug("vo:" + vo);
		}
		
	}
	
	public static void main(String[] args) {
		DesertionServiceMain desertionServiceMain = new DesertionServiceMain();
		desertionServiceMain.doSelectOne();
		//desertionServiceMain.doInsert();
		//desertionServiceMain.doDelete();
		//desertionServiceMain.doUpdate();
		//desertionServiceMain.doRetrieve();
		
	}

}
