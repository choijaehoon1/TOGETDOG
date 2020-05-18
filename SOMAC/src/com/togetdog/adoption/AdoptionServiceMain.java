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

package com.togetdog.adoption;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.SearchVO;

/**
 * @author sist128
 *
 */
public class AdoptionServiceMain {
	
private final Logger LOG = Logger.getLogger(AdoptionServiceMain.class);
	
	private AdoptionVO adoptionVO;
	private AdoptionService adoptionService;
	
	public AdoptionServiceMain() {
		adoptionService = new AdoptionService();
		adoptionVO = new AdoptionVO("A_142","411314202000031","kimjhㅇㅇㅇ","2","y","F","외로워서 신청합니다","20/02/17","20/02/17");
	}
	
	/**
	*@Method Name:main
	*@작성일: 2020. 2. 11.
	*@작성자: sist128
	*@설명:
	*@param args
	*/
	public void doSelectOne() {
		adoptionVO.setApplyNo("A_142");
		AdoptionVO vo =(AdoptionVO) adoptionService.doSelectOne(adoptionVO);
		LOG.debug(vo);
		
	}
	
	public void doInsert() {
		int flag = adoptionService.doInsert(adoptionVO);
		
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doDelete() {
		adoptionVO.setApplyNo("A_1");
		int flag = adoptionService.doDelete(adoptionVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void doUpdate() {
		adoptionVO.setApplyNo("A_142");
		adoptionVO.setApplyState("F");
		int flag = adoptionService.doUpdate(adoptionVO);
		if(flag == 1) {
			LOG.debug("성공");
		} else {
			LOG.debug("실패");
		}
	}
	
	public void adoptDoUpdate() {
		
		adoptionVO.setMemberId("xxxx");
		adoptionVO.setFamilyCnt("1");
		adoptionVO.setApplyState("S");
		adoptionVO.setApplyReason("그냥 심심해서 그랬다규");
		adoptionVO.setApplyNo("A_142");
		
		int flag = adoptionService.adopDoUpdate(adoptionVO);
		
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
		searchVO.setSearchWord("428356202000161");

		List<AdoptionVO> list = (List<AdoptionVO>) adoptionService.doRetrieve(searchVO);

		for (AdoptionVO vo : list) {
			LOG.debug("vo:" + vo);
		}
		
	}
	

	public static void main(String[] args) {
		AdoptionServiceMain adoptionServiceMain = new AdoptionServiceMain();
		//adoptionServiceMain.doSelectOne();
		//adoptionServiceMain.doInsert();
		//adoptionServiceMain.doDelete();
		//adoptionServiceMain.doUpdate();
		//adoptionServiceMain.doRetrieve();
		
		adoptionServiceMain.adoptDoUpdate();
	}

}
