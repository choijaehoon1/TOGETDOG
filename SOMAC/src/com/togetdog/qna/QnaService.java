/**
 *<pre>
 * com.hr.member
 * Class Name : MemberService.java
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

import com.togetdog.cmn.DTO;

/**
 * @author sist
 *
 */
public class QnaService {

	private QnaDao QnaDao;
	
	public QnaService() {
		QnaDao = new QnaDao();
	}
	
	/**
	 * 
	 *Method Name:doSelectOne
	 *작성일: 2020. 2. 7.
	 *작성자: sist
	 *설명:
	 *@param dto
	 *@return
	 */
	public List<?> doReplyRetrieve(DTO dto){
		return QnaDao.doReplyRetrieve(dto);
	}
	
	public int doReplyInsert(DTO dto) {
		return QnaDao.doReplyInsert(dto);
	}
	
	public int doReplyUpdate(DTO dto) {
		return QnaDao.doReplyUpdate(dto);
	}
	
	
	public int doDeleteYn(DTO dto) {
		return QnaDao.doDeleteYn(dto);
	}
	
	public int doReplyDel(DTO dto) {
		return QnaDao.doReplyDel(dto);
	}
	public int doFileInsert(DTO dto) {
		return QnaDao.doFileInsert(dto);
	}
	public int doFileDel(DTO dto) {
		return QnaDao.doFileDel(dto);
	}
	public DTO doGetno(DTO dto) {
		return QnaDao.doGetno(dto);
	}
	
	public DTO doSelectOne(DTO dto) {
		return QnaDao.doSelectOne(dto);
	}
	/**
	 * 
	 *Method Name:doUpdate
	 *작성일: 2020. 2. 7.
	 *작성자: sist
	 *설명:
	 *@param dto
	 *@return
	 */
	public int doUpdate(DTO dto) {
		return QnaDao.doUpdate(dto);
	}
	
	public int doPicUpdate(DTO dto) {
		return QnaDao.doPicUpdate(dto);
	}
	/**
	 * 
	 *Method Name:doDelete
	 *작성일: 2020. 2. 7.
	 *작성자: sist
	 *설명:
	 *@param dto
	 *@return
	 */
	public int doDelete(DTO dto) {
		return QnaDao.doDeleteYn(dto);
	}
	/**
	 * 
	 *Method Name:doInsert
	 *작성일: 2020. 2. 7.
	 *작성자: sist
	 *설명:
	 *@param dto
	 *@return
	 */
	public int doInsert(DTO dto) {
		return QnaDao.doInsert(dto);
	}
	/**
	 * 
	 *Method Name:doRetrieve
	 *작성일: 2020. 2. 7.
	 *작성자: sist
	 *설명:
	 *@param dto
	 *@return
	 */
	
	public List<?> doRetrieve(DTO dto) {
		return QnaDao.doRetrieve(dto);
	}
	
	
	
	public int doInsert2(DTO dto) {
		return QnaDao.doInsert2(dto);
	}
	
	
	
	
}








