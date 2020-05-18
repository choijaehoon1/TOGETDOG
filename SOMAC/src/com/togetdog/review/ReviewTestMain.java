/**
 *<pre>
 * com.hr.board
 * Class Name : BoardTestMain.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-03           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-03 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */

package com.togetdog.review;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.SearchVO;

/**
 * @author sist130
 *
 */
public class ReviewTestMain {
	private final Logger LOG = Logger.getLogger(ReviewTestMain.class);
	
	//TestData
	private ReviewDao dao;
	private ReviewVO reviewVO01;	
	private AdoptionVO adoptVO01;
	private QnAVO qnaVO01;
	private CommuVO communityVO01;
	
	
	public ReviewTestMain() {
		reviewVO01 = new ReviewVO("r_1","입양코드","JISU","제목","내용","20200204","글등록일","글수정일",0,"Y");
		adoptVO01 = new AdoptionVO();
		qnaVO01 = new QnAVO();
		communityVO01 = new CommuVO();
		dao = new ReviewDao();
	}
	
	//등록
	public void do_insert() {
		int mainFlag = dao.doInsert(reviewVO01);
		if(mainFlag == 1) {
			LOG.debug("============================");
			LOG.debug("성공");
			LOG.debug("============================");
		} else {
			LOG.debug("============================");
			LOG.debug("실패");
			LOG.debug("============================");
		}
	}
	
	//삭제
	public void do_delete() {
		reviewVO01.setRevNo("r_21");
		
		int delFlag = dao.doDelete(reviewVO01);
		
		if(delFlag == 1) {
			LOG.debug("============================");
			LOG.debug("성공");
			LOG.debug("============================");
		} else {
			LOG.debug("============================");
			LOG.debug("실패");
			LOG.debug("============================");
		}
	}

	//수정
	public void do_update() {
		reviewVO01.setTitle("제에목_U");
		reviewVO01.setContents("내애용_U");
		reviewVO01.setFamilyDt("20200202");
		reviewVO01.setRevNo("r_22");
		
		int updateFlag = dao.doUpdate(reviewVO01);
		
		if(updateFlag == 1) {
			LOG.debug("============================");
			LOG.debug("성공");
			LOG.debug("============================");
		} else {
			LOG.debug("============================");
			LOG.debug("실패");
			LOG.debug("============================");
		}
	}
	
	//단건조회
	public void do_selectOne() {
		reviewVO01.setRevNo("r_1");
		ReviewVO outVO = (ReviewVO) dao.doSelectOne(reviewVO01);
		
		LOG.debug("============================");
		LOG.debug("outVO : "+outVO);
		LOG.debug("============================");
		
	}
	
	//다건조회
	public void do_retrieve() {		
		
		adoptVO01.setMemberId("kimjh");
		adoptVO01.setPageSize(10);
		adoptVO01.setPageNum(1);
		List<AdoptionVO> list = (List<AdoptionVO>) dao.doRetrieve(adoptVO01);
		
		LOG.debug("************************************************");
		//Data출력 - 향상된 for문 이용
		for(AdoptionVO vo : list) {
		
			LOG.debug(vo);
		}
		LOG.debug("************************************************");
	}
	
	//QNA다건조회
	public void doRetrieveQnA() {
		qnaVO01.setMemberId("cs0506");
		qnaVO01.setPageSize(10);
		qnaVO01.setPageNum(1);
		List<QnAVO> list = (List<QnAVO>) dao.doRetrieveQnA(qnaVO01);
		
		LOG.debug("************************************************");
		//Data출력 - 향상된 for문 이용
		for(QnAVO vo : list) {
		
			LOG.debug(vo);
		}
		LOG.debug("************************************************");
	}
	
	//게시판 다건조회
	public void doRetrieveCom() {
		communityVO01.setMemberId("JH");
		communityVO01.setPageSize(10);
		communityVO01.setPageNum(1);
		List<CommuVO> list = (List<CommuVO>) dao.doRetrieveCom(communityVO01);
		
		LOG.debug("************************************************");
		//Data출력 - 향상된 for문 이용
		for(CommuVO vo : list) {
		
			LOG.debug(vo);
		}
		LOG.debug("************************************************");
	}
	
	//조회수
	public void readCount() {
		reviewVO01.setRevNo("r_23");
		
		int rc = dao.readCount(reviewVO01);
		
		if(rc == 1) {
			LOG.debug("============================");
			LOG.debug("성공");
			LOG.debug("============================");
		} else {
			LOG.debug("============================");
			LOG.debug("실패");
			LOG.debug("============================");
		}
	}
	
	public static void main(String[] args) {
		ReviewTestMain main = new ReviewTestMain();
		//main.do_insert();
		main.do_delete();
		//main.do_update();
		//main.do_selectOne();
		//main.do_retrieve();
		//main.doRetrieveQnA();
		//main.doRetrieveCom();
		//main.readCount();
	}

}
