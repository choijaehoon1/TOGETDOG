/**
 *<pre>
 * com.togetdog
 * Class Name : communityDao.java
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

package com.togetdog.adoption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.togetdog.cmn.ConnectionMaker;
import com.togetdog.cmn.DTO;
import com.togetdog.cmn.JDBCResClose;
import com.togetdog.cmn.SearchVO;
import com.togetdog.cmn.WorkDiv;

/**
 * @author sist128
 *
 */
public class AdoptionDao extends WorkDiv {

	private ConnectionMaker connectionMaker;
	
	public AdoptionDao() {
		connectionMaker = new ConnectionMaker();
	}
	
	@Override
	public int doInsert(DTO dto) {
		int flag = 0;
		AdoptionVO inVO = (AdoptionVO) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = connectionMaker.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append(" INSERT INTO adoption_apply 				\n");
			sb.append(" (                                       \n");
			sb.append("     apply_no,                           \n");
			sb.append("     desertion_no,                       \n");
			sb.append("     member_id,                          \n");
			sb.append("     family_cnt,                         \n");
			sb.append("     experience_yn,                      \n");
			sb.append("     apply_state,                        \n");
			sb.append("     apply_reason,                       \n");
			sb.append("     reg_dt,                             \n");
			sb.append("     areg_dt                             \n");
			sb.append(" ) VALUES (                              \n");
			sb.append("     'A_'||ADOPTION_APPLY_SEQ.nextVal,   \n");
			sb.append("     ?,                                  \n");
			sb.append("     ?,                                  \n");
			sb.append("     ?,                                  \n");
			sb.append("     ?,                                  \n");
			sb.append("     ?,                                  \n");
			sb.append("     ?,                                  \n");
			sb.append("     sysdate,                            \n");
			sb.append("     ?                                   \n");
			sb.append(" )                                 	    \n");

			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getDesertionNo());
			pstmt.setString(2, inVO.getMemberId());
			pstmt.setString(3, inVO.getFamilyCnt());
			pstmt.setString(4, inVO.getExperienceYn());
			pstmt.setString(5, inVO.getApplyState());
			pstmt.setString(6, inVO.getApplyReason());
			pstmt.setString(7, inVO.getAregDt());

			flag = pstmt.executeUpdate();
			LOG.debug("flag" + flag);

		} catch (SQLException e) {
			LOG.debug("SQLException" + e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}

		return flag;
	}

	@Override
	public int doDelete(DTO dto) {
		int flag = 0;
		AdoptionVO inVO =(AdoptionVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append(" DELETE FROM adoption_apply \n");
			sb.append(" WHERE apply_no = ? 		   \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug("pstmt" + pstmt);
			pstmt.setString(1, inVO.getApplyNo());
			
			flag = pstmt.executeUpdate();
			LOG.debug("flag" + flag);
		} catch(SQLException e) {
			LOG.debug("SQLException" + e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		return flag;
	}

	public int adopDoUpdate(DTO dto) {
		int flag = 0;
		AdoptionVO inVO = (AdoptionVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append(" UPDATE adoption_apply \n");
			sb.append(" SET                   \n");
			sb.append("     member_id = ?,    \n");
			sb.append("     family_cnt = ?,   \n");
			sb.append("     experience_yn = ?,\n");
			sb.append("     apply_state = ?,  \n");
			sb.append("     apply_reason = ?, \n");
			sb.append("     reg_dt = SYSDATE, \n");
			sb.append("     areg_dt = ?       \n");
			sb.append(" WHERE apply_no = ?    \n");
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setString(1, inVO.getMemberId());
			pstmt.setString(2, inVO.getFamilyCnt());
			pstmt.setString(3, inVO.getExperienceYn());
			pstmt.setString(4, inVO.getApplyState());
			pstmt.setString(5, inVO.getApplyReason());
			pstmt.setString(6, inVO.getAregDt());
			pstmt.setString(7, inVO.getApplyNo());
			
			flag = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			LOG.debug("SQLException" + e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		return flag;
	}
	
	
	public int doUpdate(DTO dto) {
		int flag = 0;
		AdoptionVO inVO = (AdoptionVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append(" UPDATE adoption_apply \n");
			sb.append(" SET apply_state = ?,   \n");
			sb.append(" 	 areg_dt = ?   \n");
			sb.append(" WHERE apply_no =?     \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setString(1, inVO.getApplyState());
			pstmt.setString(2, inVO.getAregDt());
			pstmt.setString(3, inVO.getApplyNo());
			flag = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			LOG.debug("SQLException" + e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		return flag;
	}

	@Override
	public DTO doSelectOne(DTO dto) {
		AdoptionVO outVO = null;
		AdoptionVO inVO = (AdoptionVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT					\n");
			sb.append("     apply_no,           \n");
			sb.append("     desertion_no,       \n");
			sb.append("     member_id,          \n");
			sb.append("     family_cnt,         \n");
			sb.append("     experience_yn,      \n");
			sb.append("     apply_state,        \n");
			sb.append("     apply_reason,       \n");
			sb.append("     reg_dt,             \n");
			sb.append("     areg_dt             \n");
			sb.append(" FROM                    \n");
			sb.append("     adoption_apply      \n");
			sb.append(" 	where apply_no=?    \n");
			    
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getApplyNo());
			
			rs = pstmt.executeQuery();
			LOG.debug(rs);
			
			if(rs.next()) {
				outVO = new AdoptionVO();
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setFamilyCnt(rs.getString("family_cnt"));
				outVO.setExperienceYn(rs.getString("experience_yn"));
				outVO.setApplyState(rs.getString("apply_state"));
				outVO.setApplyReason(rs.getString("apply_reason"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAregDt(rs.getString("areg_dt"));
				LOG.debug(outVO);
			}
			
			
		} catch(SQLException e) {
			LOG.debug("SQLException" + e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		return outVO;
	}
	
	public DTO dnoSelectOne(DTO dto) {
		AdoptionVO outVO = null;
		AdoptionVO inVO = (AdoptionVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT					\n");
			sb.append("     apply_no,           \n");
			sb.append("     desertion_no,       \n");
			sb.append("     member_id,          \n");
			sb.append("     family_cnt,         \n");
			sb.append("     experience_yn,      \n");
			sb.append("     apply_state,        \n");
			sb.append("     apply_reason,       \n");
			sb.append("     reg_dt,             \n");
			sb.append("     areg_dt             \n");
			sb.append(" FROM                    \n");
			sb.append("     adoption_apply      \n");
			sb.append(" 	where desertion_no=?    \n");
			    
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getDesertionNo());
			
			rs = pstmt.executeQuery();
			LOG.debug(rs);
			
			if(rs.next()) {
				outVO = new AdoptionVO();
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setFamilyCnt(rs.getString("family_cnt"));
				outVO.setExperienceYn(rs.getString("experience_yn"));
				outVO.setApplyState(rs.getString("apply_state"));
				outVO.setApplyReason(rs.getString("apply_reason"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAregDt(rs.getString("areg_dt"));
				LOG.debug(outVO);
			}
			
			
		} catch(SQLException e) {
			LOG.debug("SQLException" + e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		return outVO;
	}
	
	
	
	public DTO sCheckSelectOne(DTO dto) {
		AdoptionVO outVO = null;
		AdoptionVO inVO = (AdoptionVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT														\n");
			sb.append("     apply_no,                                               \n");
			sb.append("     desertion_no,                                           \n");
			sb.append("     member_id,                                              \n");
			sb.append("     family_cnt,                                             \n");
			sb.append("     experience_yn,                                          \n");
			sb.append("     apply_state,                                            \n");
			sb.append("     apply_reason,                                           \n");
			sb.append("     reg_dt,                                                 \n");
			sb.append("     areg_dt                                                 \n");
			sb.append(" FROM                                                        \n");
			sb.append("     adoption_apply where desertion_no=? AND apply_state=?   \n");
			    
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getDesertionNo());
			pstmt.setString(2, inVO.getApplyState());
			
			rs = pstmt.executeQuery();
			LOG.debug(rs);
			
			if(rs.next()) {
				outVO = new AdoptionVO();
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setFamilyCnt(rs.getString("family_cnt"));
				outVO.setExperienceYn(rs.getString("experience_yn"));
				outVO.setApplyState(rs.getString("apply_state"));
				outVO.setApplyReason(rs.getString("apply_reason"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAregDt(rs.getString("areg_dt"));
				LOG.debug(outVO);
			}
			
			
		} catch(SQLException e) {
			LOG.debug("SQLException" + e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		return outVO;
	}

	@Override
	public List<?> doRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<AdoptionVO> outList = new ArrayList<AdoptionVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sbWhere = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		
		//10: 제목, 20:내용, 30: 작성자
		if(inVO.getSearchDiv() != null) {
			if(inVO.getSearchDiv().equals("10")) {
				sbWhere.append("WHERE t1.apply_state like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("20")) {
				sbWhere.append("WHERE t1.member_id like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("30")) {
				sbWhere.append("WHERE t1.desertion_no like ? ||'%' \n ");
			}
		}					      
		
		sb.append(" SELECT *							  \n");					
		sb.append(" FROM(                                 \n");                     
		sb.append("     SELECT   B.apply_no,          	  \n");                             
		sb.append("     rnum num,			  			  \n");
		sb.append("     B.desertion_no,       			  \n");
		sb.append("     B.member_id,          			  \n");
		sb.append("     B.family_cnt,         			  \n");
		sb.append("     B.experience_yn,      			  \n");
		sb.append("     B.apply_state,        			  \n");
		sb.append("     B.apply_reason,       			  \n");
		sb.append("     B.reg_dt,             			  \n");
		sb.append("     B.areg_dt             			  \n");
		sb.append("     FROM (                            \n");                     
		sb.append("          SELECT ROWNUM as rnum, A.*   \n");                      
		sb.append("          FROM(                         \n");                     
		sb.append("             SELECT t1.*               \n");                     
		sb.append("             FROM adoption_apply t1    \n");
					
		//sb.append("			   --검색어                 \n");

		if(inVO.getSearchDiv() != null) {
			if(inVO.getSearchWord() != null) {
				sb.append(sbWhere.toString());
			}
		}
				
		sb.append("             ORDER BY t1.reg_dt desc                      		\n");      
		sb.append("     )A                                                       	\n");
		//sb.append("       WHERE rownum <=(&PAGE_SIZE*(&PAGE_NUM-1)) + &PAGE_SIZE 	\n");
		sb.append("         WHERE rownum <=(?*(?-1)+?)                              \n");
		sb.append("                                                              	\n");
		sb.append("  )B                                                          	\n");
		//sb.append("  WHERE rnum >=(&PAGE_SIZE*(&PAGE_NUM-1)+1)                   	\n");
		sb.append("    WHERE rnum >=(?*(?-1)+1)        					            \n");
		sb.append(" )                                                            	\n");
		sb.append(" CROSS JOIN                                                   	\n");  
		sb.append(" (                                                            	\n");
		sb.append(" 	SELECT COUNT(*) total                                    	\n");  
		sb.append(" 	FROM adoption_apply t1                                   	\n");   
		//sb.append("    --검색어                              						\n");                       							
		if(inVO.getSearchDiv() != null) {                    	
			if(inVO.getSearchWord() != null) {               	
				sb.append(sbWhere.toString());               	
			}                                                	
		}                                                    	
		sb.append(")                                                                \n");
		
		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug(pstmt);
			LOG.debug(inVO);
			if(inVO.getSearchDiv() !=null && inVO.getSearchWord() != null) {
				pstmt.setString(1, inVO.getSearchWord());
				pstmt.setInt(2, inVO.getPageSize());
				pstmt.setInt(3, inVO.getPageNum());
				pstmt.setInt(4, inVO.getPageSize());
				pstmt.setInt(5, inVO.getPageSize());
				pstmt.setInt(6, inVO.getPageNum());
				pstmt.setString(7, inVO.getSearchWord());
				
			} else {
				pstmt.setInt(1, inVO.getPageSize());
				pstmt.setInt(2, inVO.getPageNum());
				pstmt.setInt(3, inVO.getPageSize());
				pstmt.setInt(4, inVO.getPageSize());
				pstmt.setInt(5, inVO.getPageNum());
			}
			
			rs = pstmt.executeQuery();
			//이전이 오류
			
			while(rs.next()) {
				AdoptionVO outVO = new AdoptionVO();
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setFamilyCnt(rs.getString("family_cnt"));
				outVO.setExperienceYn(rs.getString("experience_yn"));
				outVO.setApplyState(rs.getString("apply_state"));
				outVO.setApplyReason(rs.getString("apply_reason"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAregDt(rs.getString("areg_dt"));
				
				outList.add(outVO);
			}
			
		} catch(SQLException e) {
			LOG.debug("SQLException" + e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		
		return outList;
	}

	

}
