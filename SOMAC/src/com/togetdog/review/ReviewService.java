/**
 *<pre>
 * com.hr.board
 * Class Name : BoardService.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-06           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-06 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */

package com.togetdog.review;

import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.DTO;
import com.togetdog.member.MemberVO;

/**
 * @author sist130
 *
 */
public class ReviewService {
	
	private final Logger LOG = Logger.getLogger(this.getClass());
	private ReviewDao reviewDao;
	
	public ReviewService() {
		reviewDao = new ReviewDao();
	}
	
	
	public int doInsert(DTO dto) {
		return reviewDao.doInsert(dto);
	}
	
	public DTO doFindNo(DTO dto) {
		return reviewDao.doFindNo(dto);
	}
	
	public DTO doGetno(DTO dto) {		
		return reviewDao.doGetno(dto);
	}
	
	public int doInsertFile(DTO dto) {		
		return reviewDao.doInsertFile(dto);
	}
	
	public int doUpdate(DTO dto) {
		return reviewDao.doUpdate(dto);
	}
	
	public int doUpdateMem(DTO dto) {
		return reviewDao.doUpdateMem(dto);
	}
	
	
	public int doDelete(DTO dto) {
		return reviewDao.doDelete(dto);
	}
	
	public DTO doSelectOne(DTO dto) {
		//조회 Count 증가
		int rc = reviewDao.readCount(dto);
		
		//단건 조회
		return reviewDao.doSelectOne(dto);
	}
	
	public DTO doSelectOneC(DTO dto) {
		//조회 Count 증가
		int rc = reviewDao.readCount(dto);
		
		//단건 조회
		return reviewDao.doSelectOneC(dto);
	}
	
	
	public DTO doSelectOneMem(DTO dto) {
		return reviewDao.doSelectOneMem(dto);
	}
	
	public DTO doSelectOneInsert(DTO dto) {
		return reviewDao.doSelectOneInsert(dto);
	}
	
	public DTO getAdoptImg(DTO dto) {
		return reviewDao.getAdoptImg(dto);
	}
	
	public List<?> doRetrieve(DTO dto) {
		return reviewDao.doRetrieve(dto);
	}
	
	public List<?> doRetrieveP(DTO dto) {
		return reviewDao.doRetrieveP(dto);
	}
	
	public List<?> doRetrieveS(DTO dto) {
		return reviewDao.doRetrieveS(dto);
	}
	
	public List<?> doRetrieveR(DTO dto) {
		return reviewDao.doRetrieveR(dto);
	}
	
	public List<?> doRetrieveCom(DTO dto){
		return reviewDao.doRetrieveCom(dto);
	}
	
	public List<?> doRetrieveRev (DTO dto){
		return reviewDao.doRetrieveRev(dto);
	}
	
	public List<?> doRetrieveQnA(DTO dto){
		return reviewDao.doRetrieveQnA(dto);
	}
	

	public int readCount(DTO dto) {
		return reviewDao.readCount(dto);
	}
	
}
