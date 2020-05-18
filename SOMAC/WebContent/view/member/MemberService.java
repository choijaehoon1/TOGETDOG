/**
 *<pre>
 * com.togetdog.member
 * Class Name : MemberService.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-13           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-13 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */

package com.togetdog.member;

import org.apache.log4j.Logger;

import com.togetdog.cmn.MessageVO;
import com.togetdog.cmn.DTO;



/**
 * @author SIST
 *
 */
public class MemberService {
		private final Logger LOG = Logger.getLogger(this.getClass());

		private MemberDao memberDao;
		
		/**
		*Method kalogin
		*작성일: 2020. 2. 7.
		*작성자: SIST
		*설명: ID/ 비번 CHECK
		*@param dto
		*@return MessageVO
		 */
		public DTO dokalogin(DTO dto) {
			MessageVO outVO = new MessageVO();
			//ID CHECK
	       outVO = (MessageVO) memberDao.dokalogin(dto);
			
			if(outVO.getMsgId().equals("10")) {
				return outVO;
			}
			return outVO;
		}
		
		/**
		*Method loginCheck
		*작성일: 2020. 2. 7.
		*작성자: SIST
		*설명: ID/ 비번 CHECK
		*@param dto
		*@return MessageVO
		 */
		public DTO doidcheck(DTO dto) {
			MessageVO outVO = new MessageVO();
			//ID CHECK
			outVO = (MessageVO) memberDao.idCheck(dto);
			
			if(outVO.getMsgId().equals("10")) {
				return outVO;
			}else if(outVO.getMsgId().equals("1")) {
				return outVO;
			}
			return outVO;
		}
		
		public DTO loginCheck(DTO dto) {
			MessageVO outVO = new MessageVO();
			//ID CHECK
			outVO = (MessageVO) memberDao.idCheck(dto);
			
			if(outVO.getMsgId().equals("10")) {
				return outVO;
			}
			
			//ID / 비번 CHECK
			outVO = (MessageVO) memberDao.passCheck(dto);
			if(outVO.getMsgId().equals("20")) {
			return outVO;
			
			}
			return outVO;
		}
		
		public MemberService() {
				memberDao = new MemberDao();
		}
		
		public int doInsert(DTO dto) {
			return memberDao.doInsert(dto);
		}
		public int dokaInsert(DTO dto) {
			return memberDao.dokaInsert(dto);
		}
		
		public int doDelete(DTO dto) {
			return memberDao.doDelete(dto);
		}
		
		public DTO doSelectOne(DTO dto) {
			return memberDao.doSelectOne(dto);
		}
		
		public int doUpadte(DTO dto) {
			return memberDao.doUpdate(dto);
		}
		
		public DTO doSelectOneId(DTO dto) {
			return memberDao.doSelectOneId(dto);
		}
		
		public DTO doSelectOnePw(DTO dto) {
			return memberDao.doSelectOnePw(dto);
		}
		
}
