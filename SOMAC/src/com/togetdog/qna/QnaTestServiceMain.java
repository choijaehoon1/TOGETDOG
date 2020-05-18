/**
 *<pre>
 * com.hr.member
 * Class Name : MemberTestServiceMain.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-07           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-07 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */
 
package com.togetdog.qna;
import java.util.List;
import org.apache.log4j.Logger;

import com.togetdog.cmn.SearchVO;

/**
 * @author sist
 *
 */
public class QnaTestServiceMain {
	private QnaService  qnaSerivce;
	private QnaVO  QnaVO;
	private SearchVO  searchVO;
	private QnaDao dao;
	
	public QnaTestServiceMain() {
		qnaSerivce = new QnaService();
		searchVO = new SearchVO();
		//QnaVO = new QnaVO("","sat0506","제목입니다","","",1,"내용입니다","Y");
		dao = new QnaDao();
	}
	
	private final Logger LOG = Logger.getLogger(QnaTestServiceMain.class);
	
	public void doRetrieve() {
		searchVO.setPageSize(10);
		searchVO.setPageNum(1);
		
		
		searchVO.setSearchDiv("");
		searchVO.setSearchWord("");
		List<QnaVO> list = (List<QnaVO>) qnaSerivce.doRetrieve(searchVO);
		
		LOG.debug("***************************");
		for(QnaVO vo:list) {
			LOG.debug(vo);
		}
		LOG.debug("***************************");
		
	}
	
	//등록
	public void do_insert() {
		int mainFlag = dao.doInsert(QnaVO);
		
		if(mainFlag==1) {
			LOG.debug("=====================");
			LOG.debug("등록성공");
			LOG.debug("=====================");
		}else {
			LOG.debug("=====================");
			LOG.debug("등록실패");
			LOG.debug("=====================");			
		}
	}
	
	public void doDelete() {
		
		QnaVO.setQna_no("Q_7");
		
		int delFlag = dao.doDelete(QnaVO);

		LOG.debug("delFlag="+delFlag);
		
		if(delFlag==1) {
			LOG.debug("=====================");
			LOG.debug("딜리트성공");
			LOG.debug("=====================");
		}else {
			
			LOG.debug("=====================");
			LOG.debug("딜리트실패");
			LOG.debug("=====================");			
		}

	}
	
	public void doUpdate() {
		QnaVO.setQna_no("Q_3");
		QnaVO.setTitle("제목입니다_U");
		QnaVO.setContents("내용입니다_U");
		

		int updateFlag = dao.doUpdate(QnaVO);
		if(updateFlag==1) {
			LOG.debug("=====================");
			LOG.debug("업데이트성공");
			LOG.debug("=====================");
		}else {
			LOG.debug("=====================");
			LOG.debug("업데이트실패");
			LOG.debug("=====================");			
		}		
	}
	
	public void doSelectOne() {
		QnaVO.setQna_no("Q_15");
		QnaVO outVO = (QnaVO) dao.doSelectOne(QnaVO);
		
			}
	
	
	public void doReplyUpdate() {
		QnaVO2 vo2=new QnaVO2();
		vo2.setReplyNo("Q_133");
		vo2.setRseqNo(24);
		vo2.setRcontents("되는건가");
		dao.doReplyUpdate(vo2);
			}

	
	public static void main(String[] args) {
		QnaTestServiceMain main=new QnaTestServiceMain();
		//main.doRetrieve();
		//main.do_insert();
		//main.doDelete();
		//main.doUpdate();
		//main.doSelectOne();
		main.doRetrieve();
		//main.doReplyUpdate();
	}

}



