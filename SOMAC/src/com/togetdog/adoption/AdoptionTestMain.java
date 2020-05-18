/**
 * <pre>
 * com.togetdog
 * Class Name : CommunityTestMain.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-10           최초생성
 *
 * &#64;author 개발프레임웍크 실행환경 개발팀
 * &#64;since 2020-02-10 
 * &#64;version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */
/*
 * 
 * 
 * package com.togetdog.adoption;
 * 
 * import java.util.List;
 * 
 * import org.apache.log4j.Logger;
 * 
 * import com.togetdog.cmn.SearchVO;
 * 
 *//**
	 * @author sist128
	 *
	 */
/*
 * 
 * public class AdoptionTestMain { private final Logger LOG =
 * Logger.getLogger(AdoptionTestMain.class);
 * 
 * private AdoptionVO communityVO; private AdoptionDao communityDao;
 * 
 * 
 * public AdoptionTestMain() { communityDao = new AdoptionDao(); communityVO =
 * new AdoptionVO("1","JH","제목","내용","1","1",0,"1"); }
 * 
 *//**
	 * @Method Name:main
	 * @작성일: 2020. 2. 10.
	 * @작성자: sist128
	 * @설명:
	 * @param args
	 *//*
		 * 
		 * public void doInsert() {
		 * 
		 * int flag = communityDao.doInsert(communityVO);
		 * 
		 * if(flag == 1) { LOG.debug("성공"); } else { LOG.debug("실패"); } }
		 * 
		 * public void doDelete() { communityVO.setTotNo("m_0"); int flag =
		 * communityDao.doDelete(communityVO); if(flag == 1) { LOG.debug("성공"); } else {
		 * LOG.debug("실패"); } }
		 * 
		 * public void doUpdate() { communityVO.setTotNo("m_1");
		 * communityVO.setTitle("제목수정"); communityVO.setContents("내용수정");
		 * 
		 * 
		 * int flag = communityDao.doUpdate(communityVO); if(flag == 1) {
		 * LOG.debug("성공"); } else { LOG.debug("실패"); } }
		 * 
		 * public void doSelectOne() { communityVO.setTotNo("m_1");
		 * 
		 * AdoptionVO vo = (AdoptionVO) communityDao.doSelectOne(communityVO);
		 * 
		 * LOG.debug(vo);
		 * 
		 * }
		 * 
		 * public void doRetrieve() { SearchVO searchVO = new SearchVO();
		 * searchVO.setPageSize(10); searchVO.setPageNum(1);
		 * searchVO.setSearchDiv("30"); searchVO.setSearchWord("JH");
		 * 
		 * List<AdoptionVO> list = (List<AdoptionVO>) communityDao.doRetrieve(searchVO);
		 * 
		 * for(AdoptionVO vo:list) { LOG.debug("vo:" + vo); }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * public static void main(String[] args) { AdoptionTestMain communityTestMain =
		 * new AdoptionTestMain(); //communityTestMain.doRetrieve();
		 * //communityTestMain.doInsert(); //communityTestMain.doDelete();
		 * //communityTestMain.doUpdate(); //communityTestMain.doSelectOne();
		 * 
		 * }
		 * 
		 * }
		 */