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

package com.togetdog.desertion;

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
public class DesertionDao extends WorkDiv {

	private ConnectionMaker connectionMaker;
	
	public DesertionDao() {
		connectionMaker = new ConnectionMaker();
	}
	
	@Override
	public int doInsert(DTO dto) {
		int flag =0;
		DesertionVO inVO = (DesertionVO) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO desertion_info (  \n");
			sb.append("    desertion_no,             \n");
			sb.append("    process_state,            \n");
			sb.append("    file_name,                \n");
			sb.append("    happen_dt,                \n");
			sb.append("    happen_place,             \n");
			sb.append("    kind_cd,                  \n");
			sb.append("    color_cd,                 \n");
			sb.append("    age,                      \n");
			sb.append("    weight,                   \n");
			sb.append("    notice_no,                \n");
			sb.append("    notice_sdt,               \n");
			sb.append("    notice_edt,               \n");
			sb.append("    pop_file,                 \n");
			sb.append("    sex_cd,                   \n");
			sb.append("    neuter_yn,                \n");
			sb.append("    special_mark,             \n");
			sb.append("    care_nm,                  \n");
			sb.append("    care_tel,                 \n");
			sb.append("    care_addr,                \n");
			sb.append("    org_nm,                   \n");
			sb.append("    charge_nm,                \n");
			sb.append("    office_tel                \n");
			sb.append(") VALUES (                    \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?,                        \n");
			sb.append("    ?                         \n");
			sb.append(")                             \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getDesertionNo());
			pstmt.setString(2, inVO.getProcessState());
			pstmt.setString(3, inVO.getFileName());
			pstmt.setString(4, inVO.getHappenDt());
			pstmt.setString(5, inVO.getHappenPlace());
			pstmt.setString(6, inVO.getKindCd());
			pstmt.setString(7, inVO.getColorCd());
			pstmt.setString(8, inVO.getAge());
			pstmt.setString(9, inVO.getWeight());
			pstmt.setString(10,inVO.getNoticeNo());
			pstmt.setString(11,inVO.getNoticeSdt());
			pstmt.setString(12,inVO.getNoticeEdt());
			pstmt.setString(13,inVO.getPopFile());
			pstmt.setString(14,inVO.getSexCd());
			pstmt.setString(15,inVO.getNeuterYn());
			pstmt.setString(16,inVO.getSpecialMark());
			pstmt.setString(17,inVO.getCareNm());
			pstmt.setString(18,inVO.getCareTel());
			pstmt.setString(19,inVO.getCareAddr());
			pstmt.setString(20,inVO.getOrgNm());
			pstmt.setString(21,inVO.getChargeNm());
			pstmt.setString(22,inVO.getOfficeTel());
			
			
			flag = pstmt.executeUpdate();
			LOG.debug("flag" + flag);
			
			
		}catch(SQLException e) {
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
		DesertionVO inVO =(DesertionVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM desertion_info \n");
			sb.append("WHERE desertion_no = ? \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug("pstmt" + pstmt);
			pstmt.setString(1, inVO.getDesertionNo());
			
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

	@Override
	public int doUpdate(DTO dto) {
		int flag = 0;
		DesertionVO inVO = (DesertionVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE desertion_info  \n");
			sb.append("SET process_state = ? \n");
			sb.append("WHERE desertion_no = ? \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setString(1, inVO.getProcessState());
			pstmt.setString(2, inVO.getDesertionNo());
			
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
		DesertionVO outVO = null;
		DesertionVO inVO = (DesertionVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT                    \n"); 
			sb.append("    desertion_no,         \n");
			sb.append("    process_state,        \n");
			sb.append("    file_name,            \n");
			sb.append("    happen_dt,            \n");
			sb.append("    happen_place,         \n");
			sb.append("    kind_cd,              \n");
			sb.append("    color_cd,             \n");
			sb.append("    age,                  \n");
			sb.append("    weight,               \n");
			sb.append("    notice_no,            \n");
			sb.append("    notice_sdt,           \n");
			sb.append("    notice_edt,           \n");
			sb.append("    pop_file,             \n");
			sb.append("    sex_cd,               \n");
			sb.append("    neuter_yn,            \n");
			sb.append("    special_mark,         \n");
			sb.append("    care_nm,              \n");
			sb.append("    care_tel,             \n");
			sb.append("    care_addr,            \n");
			sb.append("    org_nm,               \n");
			sb.append("    charge_nm,            \n");
			sb.append("    office_tel            \n");
			sb.append("FROM                      \n");
			sb.append("    desertion_info        \n");
			sb.append("where  desertion_no =  ?  \n");
			    
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getDesertionNo());
			
			rs = pstmt.executeQuery();
			LOG.debug(rs);
			if(rs.next()) {
				outVO = new DesertionVO();
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setProcessState(rs.getString("process_state"));
				outVO.setFileName(rs.getString("file_name"));
				outVO.setHappenDt(rs.getString("happen_dt"));
				outVO.setHappenPlace(rs.getString("happen_place"));
				outVO.setKindCd(rs.getString("kind_cd"));
				outVO.setColorCd(rs.getString("color_cd"));
				outVO.setAge(rs.getString("age"));
				outVO.setWeight(rs.getString("weight"));
				outVO.setNoticeNo(rs.getString("notice_no"));
				outVO.setNoticeSdt(rs.getString("notice_sdt"));
				outVO.setNoticeEdt(rs.getString("notice_edt"));
				outVO.setPopFile(rs.getString("pop_file"));
				outVO.setSexCd(rs.getString("sex_cd"));
				outVO.setNeuterYn(rs.getString("neuter_yn"));
				outVO.setSpecialMark(rs.getString("special_mark"));
				outVO.setCareNm(rs.getString("care_nm"));
				outVO.setCareTel(rs.getString("care_tel"));
				outVO.setCareAddr(rs.getString("care_addr"));
				outVO.setOrgNm(rs.getString("org_nm"));
				outVO.setChargeNm(rs.getString("charge_nm"));
				outVO.setOfficeTel(rs.getString("office_tel"));
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
		List<DesertionVO> outList = new ArrayList<DesertionVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sbWhere = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		
		//10: 제목, 20:내용, 30: 작성자
		if(inVO.getSearchDiv() != null) {
			if(inVO.getSearchDiv().equals("10")) {
				sbWhere.append("WHERE t1.PROCESS_STATE like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("20")) {
				sbWhere.append("WHERE t1.ORG_NM like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("30")) {
				sbWhere.append("WHERE t1.DESERTION_NO like ? ||'%' \n ");
			}
		}					      
		
		sb.append(" SELECT *							  \n");					
		sb.append(" FROM(                                 \n");                     
		sb.append("     SELECT   B.DESERTION_NO,          \n");                             
		sb.append("                 rnum num,			  \n");							
		sb.append("                 B.PROCESS_STATE ,     \n");
		sb.append("                 B.FILE_NAME     ,     \n");
		sb.append("                 B.HAPPEN_DT     ,     \n");
		sb.append("                 B.HAPPEN_PLACE  ,     \n");
		sb.append("                 B.KIND_CD       ,     \n");
		sb.append("                 B.COLOR_CD      ,     \n");
		sb.append("                 B.AGE           ,     \n");
		sb.append("                 B.WEIGHT        ,     \n");
		sb.append("                 B.NOTICE_NO     ,     \n");
		sb.append("                 B.NOTICE_SDT    ,     \n");
		sb.append("                 B.NOTICE_EDT    ,     \n");
		sb.append("                 B.POP_FILE      ,     \n");
		sb.append("                 B.SEX_CD        ,     \n");
		sb.append("                 B.NEUTER_YN     ,     \n");
		sb.append("                 B.SPECIAL_MARK  ,     \n");
		sb.append("                 B.CARE_NM       ,     \n");
		sb.append("                 B.CARE_TEL      ,     \n");
		sb.append("                 B.CARE_ADDR     ,     \n");
		sb.append("                 B.ORG_NM        ,     \n");
		sb.append("                 B.CHARGE_NM     ,     \n");
		sb.append("                 B.OFFICE_TEL          \n");
		sb.append("     FROM (                            \n");                     
		sb.append("          SELECT ROWNUM as rnum, A.*   \n");                      
		sb.append("         FROM(                         \n");                     
		sb.append("             SELECT t1.*               \n");                     
		sb.append("             FROM DESERTION_INFO t1    \n");
					
		//sb.append("			   --검색어                 \n");

		if(inVO.getSearchDiv() != null) {
			if(inVO.getSearchWord() != null) {
				sb.append(sbWhere.toString());
			}
		}
				
		sb.append("             ORDER BY t1.notice_sdt desc                      	\n");      
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
		sb.append(" 	FROM DESERTION_INFO t1                                   	\n");   
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
				DesertionVO outVO = new DesertionVO();

				outVO = new DesertionVO();
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setNum(rs.getInt("num"));
				outVO.setProcessState(rs.getString("process_state"));
				outVO.setFileName(rs.getString("file_name"));
				outVO.setHappenDt(rs.getString("happen_dt"));
				outVO.setHappenPlace(rs.getString("happen_place"));
				outVO.setKindCd(rs.getString("kind_cd"));
				outVO.setColorCd(rs.getString("color_cd"));
				outVO.setAge(rs.getString("age"));
				outVO.setWeight(rs.getString("weight"));
				outVO.setNoticeNo(rs.getString("notice_no"));
				outVO.setNoticeSdt(rs.getString("notice_sdt"));
				outVO.setNoticeEdt(rs.getString("notice_edt"));
				outVO.setPopFile(rs.getString("pop_file"));
				outVO.setSexCd(rs.getString("sex_cd"));
				outVO.setNeuterYn(rs.getString("neuter_yn"));
				outVO.setSpecialMark(rs.getString("special_mark"));
				outVO.setCareNm(rs.getString("care_nm"));
				outVO.setCareTel(rs.getString("care_tel"));
				outVO.setCareAddr(rs.getString("care_addr"));
				outVO.setOrgNm(rs.getString("org_nm"));
				outVO.setChargeNm(rs.getString("charge_nm"));
				outVO.setOfficeTel(rs.getString("office_tel"));
				outVO.setTotal(rs.getInt("total"));
				
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
