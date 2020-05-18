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

package com.togetdog.community;

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
import com.togetdog.filemng.FileVO;
import com.togetdog.reply.CommonReplyVO;
import com.togetdog.review.JoinVO;

/**
 * @author sist128
 *
 */
public class CommunityDao extends WorkDiv {
	
	

	private ConnectionMaker connectionMaker;
	
	public CommunityDao() {
		connectionMaker = new ConnectionMaker();
	}
	
	public DTO doGetno(DTO dto) {
		CommunityVO outVO = null;
		CommunityVO inVO = (CommunityVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("select t1.tot_no			\n");
			sb.append("      ,t1.member_id      \n");
			sb.append("      ,t1.title          \n");
			sb.append("      ,t1.contents       \n");
			sb.append("      ,t1.reg_dt         \n");
			sb.append("      ,t1.mod_dt         \n");
			sb.append("      ,t1.count          \n");
			sb.append("      ,t1.able_yn        \n");
			sb.append("from(                    \n");
			sb.append("select *                 \n");
			sb.append("from TOTAL_BOARD         \n");
			sb.append("order by mod_dt desc)t1  \n");
			sb.append("where rownum = 1         \n");

			    
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			
			rs = pstmt.executeQuery();
			LOG.debug(rs);
			if(rs.next()) {
				outVO = new CommunityVO();
				outVO.setTotNo(rs.getString("tot_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setTitle(rs.getString("title"));
				outVO.setContents(rs.getString("contents"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setCount(rs.getInt("count"));
				outVO.setAbleYn(rs.getString("able_yn"));

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
	
	
	public int doReplyupdate(DTO dto) {
		int flag =0;
		CommunityVO2 inVO = (CommunityVO2) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE common_reply		\n");
			sb.append("SET rcontents = ?        \n");
			sb.append("    ,rreg_id = ?         \n");
			sb.append("    ,rreg_dt = sysdate   \n");
			sb.append("    ,reply_yn = 'Y'      \n");
			sb.append("WHERE                    \n");
			sb.append("    reply_no = ?         \n");
			sb.append("    AND rseq_no = ?      \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getRcontents());
			pstmt.setString(2, inVO.getRregId());
			pstmt.setString(3, inVO.getReplyNo());
			pstmt.setInt(4, inVO.getRseqNo());
		
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
	
	
	public int doReplyDelete(DTO dto) {
		int flag =0;
		CommunityVO2 inVO = (CommunityVO2) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE common_reply	\n");
			sb.append("SET reply_yn = 'N'   \n");
			sb.append("WHERE reply_no = ?   \n");
			sb.append("AND rseq_no = ?      \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getReplyNo());
			pstmt.setInt(2, inVO.getRseqNo());
		
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
	
	
	
	public int doReplyInsert(DTO dto) {
		int flag =0;
		CommunityVO2 inVO = (CommunityVO2) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO common_reply (	\n");
			sb.append("    reply_no,                \n");
			sb.append("    rseq_no,                 \n");
			sb.append("    rcontents,               \n");
			sb.append("    rreg_id,                 \n");
			sb.append("    rreg_dt,                 \n");
			sb.append("    reply_yn                 \n");
			sb.append(") VALUES (                   \n");
			sb.append("    ?,                       \n");
			sb.append("    COMMON_REPLY_SEQ.nextval,\n");
			sb.append("    ?,                       \n");
			sb.append("    ?,                       \n");
			sb.append("    sysdate,                 \n");
			sb.append("    'Y'                      \n");
			sb.append(")                            \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getReplyNo());
			pstmt.setString(2, inVO.getRcontents());
			pstmt.setString(3, inVO.getRregId());
			
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
	
	public int doFileUpdate(DTO dto) {
		int flag =0;
		FileVO inVO = (FileVO) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE file_mng							\n");
			sb.append("SET  org_nm = ?                          \n");
			sb.append("    ,save_nm = 'image_' || ?             \n");
			sb.append("    ,img_path = '../img/image_/' || ?    \n");
			sb.append("    ,file_size = ?                       \n");
			sb.append("    ,ext = ?                             \n");
			sb.append("    ,reg_id = ?                          \n");
			sb.append("    ,reg_dt = sysdate                    \n");
			sb.append("WHERE file_no = ?                        \n");
	
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getOrgNm());
			pstmt.setString(2, inVO.getSaveNm());
			pstmt.setString(3, inVO.getImgPath());
			pstmt.setInt(4, inVO.getFileSize());
			pstmt.setString(5, inVO.getExt());
			pstmt.setString(6, inVO.getRegId());
			pstmt.setString(7, inVO.getFileNo());
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
	
	
	
	public int doFileInsert(DTO dto) {
		int flag =0;
		FileVO inVO = (FileVO) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();

			sb.append("INSERT INTO file_mng (								");
			sb.append("    file_no,                                         ");
			sb.append("    org_nm,                                          ");
			sb.append("    save_nm,                                         ");
			sb.append("    img_path,                                        ");
			sb.append("    file_size,                                       ");
			sb.append("    ext,                                             ");
			sb.append("    reg_id,                                          ");
			sb.append("    reg_dt                                           ");
			sb.append(") VALUES (                                           ");
			sb.append("    ?,                					   			");
			sb.append("    ?,                                               ");
			sb.append("    'image_' || ? ,       						    ");
			sb.append("    '../img/image_/'|| ?,   							");
			sb.append("    ?,                                               ");
			sb.append("    ?,                                               ");
			sb.append("    ?,                                               ");
			sb.append("    sysdate                                          ");
			sb.append(")                                                    ");
						
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getFileNo());
			pstmt.setString(2, inVO.getOrgNm());
			pstmt.setString(3, inVO.getSaveNm());
			pstmt.setString(4, inVO.getImgPath());
			pstmt.setInt(5, inVO.getFileSize());
			pstmt.setString(6, inVO.getExt());
			pstmt.setString(7, inVO.getRegId());
			
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
	
	
	
	public int doJInsert(DTO dto) {
		int flag =0;
		CommunityVO inVO = (CommunityVO) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO total_board (		   \n");
			sb.append("    tot_no,                         \n");
			sb.append("    member_id,                      \n");
			sb.append("    title,                          \n");
			sb.append("    contents,                       \n");
			sb.append("    reg_dt,                         \n");
			sb.append("    mod_dt,                         \n");
			sb.append("    count,                          \n");
			sb.append("    able_yn                         \n");
			sb.append(") VALUES (                          \n");
			sb.append("    'j_'|| COMMUNITY_j_SEQ.NEXTVAL, \n");
			sb.append("    ?,                              \n");
			sb.append("    ?,                              \n");
			sb.append("    ?,                              \n");
			sb.append("    sysdate,                        \n");
			sb.append("    sysdate,                        \n");
			sb.append("    0,                              \n");
			sb.append("    'Y'                             \n");
			sb.append(")                                   \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getMemberId());
			pstmt.setString(2, inVO.getTitle());
			pstmt.setString(3, inVO.getContents());
			
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
	public int doInsert(DTO dto) {
		int flag =0;
		CommunityVO inVO = (CommunityVO) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO total_board (		\n");
			sb.append("    tot_no,                      \n");
			sb.append("    member_id,                   \n");
			sb.append("    title,                       \n");
			sb.append("    contents,                    \n");
			sb.append("    reg_dt,                      \n");
			sb.append("    mod_dt,                      \n");
			sb.append("    count,                       \n");
			sb.append("    able_yn                      \n");
			sb.append(") VALUES (                       \n");
			sb.append("    ? || COMMUNITYSEQ.NEXTVAL,   \n");
			sb.append("    ?,                           \n");
			sb.append("    ?,                           \n");
			sb.append("    ?,                           \n");
			sb.append("    sysdate,                     \n");
			sb.append("    sysdate,                     \n");
			sb.append("    0,                           \n");
			sb.append("    'Y'                          \n");
			sb.append(")                                \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getTotNo());
			pstmt.setString(2, inVO.getMemberId());
			pstmt.setString(3, inVO.getTitle());
			pstmt.setString(4, inVO.getContents());
			
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
		CommunityVO inVO =(CommunityVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE total_board      \n");
			sb.append("SET able_yn = 'N'       \n");
			sb.append("WHERE tot_no = ?        \n");
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug("pstmt" + pstmt);
			pstmt.setString(1, inVO.getTotNo());
			
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
		CommunityVO inVO = (CommunityVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE total_board       \n");
			sb.append("SET  member_id = ?       \n");
			sb.append("    ,title = ?           \n");
			sb.append("    ,contents = ?        \n");
			sb.append("    ,reg_dt = sysdate    \n");
			sb.append("    ,mod_dt = sysdate    \n");
			sb.append("WHERE tot_no = ?			\n");
			   
			
			pstmt = connection.prepareStatement(sb.toString());
			pstmt.setString(1, inVO.getMemberId());
			pstmt.setString(2, inVO.getTitle());
			pstmt.setString(3, inVO.getContents());
			pstmt.setString(4, inVO.getTotNo());
			
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

	public DTO doRSelectOne(DTO dto) {
		JoinVO outVO = null;
		JoinVO inVO = (JoinVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT t1.rev_no,											\n");					
			sb.append("	   t1.member_id,                                            \n");
			sb.append("	   t1.title,                                                \n");
			sb.append("	   t1.contents,                                             \n");
			sb.append("	   DECODE(to_char(t1.reg_dt,'YYYY/MM/DD')                   \n");
			sb.append("				,to_char(SYSDATE,'YYYY/MM/DD')                  \n");
			sb.append("				,to_char(t1.reg_dt,'HH24:MI')                   \n");
			sb.append("				,to_char(t1.reg_dt,'YYYY/MM/DD HH24:MI:SS')     \n");
			sb.append("       ) reg_dt,                                             \n");
			sb.append("	   DECODE(to_char(t1.mod_dt,'YYYY/MM/DD')                   \n");
			sb.append("				,to_char(SYSDATE,'YYYY/MM/DD')                  \n");
			sb.append("				,to_char(t1.mod_dt,'HH24:MI')                   \n");
			sb.append("				,to_char(t1.mod_dt,'YYYY/MM/DD HH24:MI:SS')     \n");
			sb.append("       ) mod_dt,                                             \n");
			sb.append("	     t1.count,                                              \n");
			sb.append("	     t1.able_yn,                                            \n");
			sb.append("         t2.img_path,                                        \n");
			sb.append("         t2.ext,                                             \n");
			sb.append("         t2.save_nm                                          \n");
			sb.append("FROM review t1 , file_mng t2                                 \n");
			sb.append("WHERE t1.rev_no = t2.FILE_NO                                 \n");
			sb.append("AND t1.rev_no = ?                                            \n");
			    
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getRevNo());
			
			rs = pstmt.executeQuery();
			LOG.debug(rs);
			if(rs.next()) {
				outVO = new JoinVO();
				outVO.setRevNo(rs.getString("rev_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setTitle(rs.getString("title"));
				outVO.setContents(rs.getString("contents"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setCount(rs.getInt("count"));
				outVO.setAbleYn(rs.getString("able_yn"));
				outVO.setImgPath(rs.getString("img_path"));
				outVO.setExt(rs.getString("ext"));
				
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
	
	public DTO doreplySelectOne(DTO dto) {
		CommonReplyVO outVO = null;
		CommonReplyVO inVO = (CommonReplyVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT reply_no,		\n");
			sb.append("        rseq_no,     \n");
			sb.append("        rcontents,   \n");
			sb.append("        rreg_id,     \n");
			sb.append("        rreg_dt,     \n");
			sb.append("        reply_yn     \n");
			sb.append("FROM common_reply    \n");
			sb.append("Where rreg_id = ?    \n");
			sb.append("AND reply_no =?      \n");
			    
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getRegId());
			pstmt.setString(2, inVO.getReplyNo());
			
			rs = pstmt.executeQuery();
			LOG.debug(rs);
			if(rs.next()) {
				outVO = new CommonReplyVO();
				outVO.setReplyNo(rs.getString("reply_no"));
				outVO.setRseqNo(rs.getInt("rseq_no"));
				outVO.setContents(rs.getString("rcontents"));
				outVO.setRegId(rs.getString("rreg_id"));
				outVO.setRegDt(rs.getString("rreg_dt"));
				outVO.setReplyYn(rs.getString("reply_yn"));	
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
	public DTO doSelectOne(DTO dto) {
		CommunityVO1 outVO = null;
		CommunityVO1 inVO = (CommunityVO1) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT t1.tot_no,											\n");		
			sb.append("	   t1.member_id,                                            \n");
			sb.append("	   t1.title,                                                \n");
			sb.append("	   t1.contents,                                             \n");
			sb.append("	   DECODE(to_char(t1.reg_dt,'YYYY/MM/DD')                   \n");
			sb.append("				,to_char(SYSDATE,'YYYY/MM/DD')                  \n");
			sb.append("				,to_char(t1.reg_dt,'HH24:MI')                   \n");
			sb.append("				,to_char(t1.reg_dt,'YYYY/MM/DD HH24:MI:SS')     \n");
			sb.append("       ) reg_dt,                                             \n");
			sb.append("	   DECODE(to_char(t1.mod_dt,'YYYY/MM/DD')                   \n");
			sb.append("				,to_char(SYSDATE,'YYYY/MM/DD')                  \n");
			sb.append("				,to_char(t1.mod_dt,'HH24:MI')                   \n");
			sb.append("				,to_char(t1.mod_dt,'YYYY/MM/DD HH24:MI:SS')     \n");
			sb.append("       ) mod_dt,                                             \n");
			sb.append("	     t1.count,                                              \n");
			sb.append("	     t1.able_yn,                                            \n");
			sb.append("         t2.img_path,                                        \n");
			sb.append("         t2.ext,                                             \n");
			sb.append("         t2.save_nm                                          \n");
			sb.append("FROM TOTAL_BOARD t1 , file_mng t2                            \n");
			sb.append("WHERE t1.tot_no = t2.FILE_NO                                 \n");
			sb.append("AND t1.tot_no = ?                                            \n");
			    
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getTotNo());
			
			rs = pstmt.executeQuery();
			LOG.debug(rs);
			if(rs.next()) {
				outVO = new CommunityVO1();
				outVO.setTotNo(rs.getString("tot_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setTitle(rs.getString("title"));
				outVO.setContents(rs.getString("contents"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setCount(rs.getInt("count"));
				outVO.setAbleYn(rs.getString("able_yn"));
				outVO.setImgPath(rs.getString("img_path"));
				outVO.setExt(rs.getString("ext"));
				outVO.setSaveNm(rs.getString("save_nm"));
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
	
	public List<?> doRReplyRetrieve(DTO dto) {
		CommunityVO2 inVO = (CommunityVO2) dto;
		List<CommunityVO2> outList = new ArrayList<CommunityVO2>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("select t2.reply_no					\n");	
		sb.append("      ,t2.rseq_no	                \n");  
		sb.append("      ,t2.rcontents                  \n");  
		sb.append("      ,t2.rreg_id                    \n");  
		sb.append("      ,t2.rreg_dt                    \n");  
		sb.append("      ,t2.reply_yn                   \n");  
		sb.append("from review t1,COMMON_REPLY t2  		\n");  
		sb.append("where t1.rev_no = t2.reply_no        \n");
		sb.append("AND t2.reply_no = ?                  \n");
		sb.append("ORDER BY t2.rreg_dt desc             \n");

		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug(pstmt);

			pstmt.setString(1, inVO.getReplyNo());
		
			
			rs = pstmt.executeQuery();
			//이전이 오류
			
			while(rs.next()) {
				CommunityVO2 outVO = new CommunityVO2();
				outVO.setReplyNo(rs.getString("reply_no"));
				outVO.setRseqNo(rs.getInt("rseq_no"));
				outVO.setRcontents(rs.getString("rcontents"));
				outVO.setRregId(rs.getString("rreg_id"));
				outVO.setRregDt(rs.getString("rreg_dt"));
				outVO.setReplyYn(rs.getString("reply_yn"));
				
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
	
	
	
	public List<?> doReplyRetrieve(DTO dto) {
		CommunityVO2 inVO = (CommunityVO2) dto;
		List<CommunityVO2> outList = new ArrayList<CommunityVO2>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("select t2.reply_no					\n");	
		sb.append("      ,t2.rseq_no	                \n");  
		sb.append("      ,t2.rcontents                  \n");  
		sb.append("      ,t2.rreg_id                    \n");  
		sb.append("      ,t2.rreg_dt                    \n");  
		sb.append("      ,t2.reply_yn                   \n");  
		sb.append("from total_board t1,COMMON_REPLY t2  \n");  
		sb.append("where t1.tot_no = t2.reply_no        \n");
		sb.append("AND t2.reply_no = ?                  \n");
		sb.append("ORDER BY t2.rreg_dt desc             \n");

		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug(pstmt);

			pstmt.setString(1, inVO.getReplyNo());
		
			
			rs = pstmt.executeQuery();
			//이전이 오류
			
			while(rs.next()) {
				CommunityVO2 outVO = new CommunityVO2();
				outVO.setReplyNo(rs.getString("reply_no"));
				outVO.setRseqNo(rs.getInt("rseq_no"));
				outVO.setRcontents(rs.getString("rcontents"));
				outVO.setRregId(rs.getString("rreg_id"));
				outVO.setRregDt(rs.getString("rreg_dt"));
				outVO.setReplyYn(rs.getString("reply_yn"));
				
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

	
	public List<?> doRecentRRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<JoinVO> outList = new ArrayList<JoinVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select t1.rev_no									\n");
		sb.append("      ,t1.title                                  \n");
		sb.append("      ,DECODE(to_char(t1.mod_dt,'YYYY/MM/DD')    \n");
		sb.append("		,to_char(SYSDATE,'YYYY/MM/DD')              \n");
		sb.append("		,to_char(t1.mod_dt,'HH24:MI')               \n");
		sb.append("		,to_char(t1.mod_dt,'YYYY/MM/DD HH24:MI:SS') \n");
		sb.append("        ) mod_dt                                 \n");
		sb.append("      ,t2.img_path                               \n");
		sb.append("      ,t2.ext                                    \n");
		sb.append("      ,t1.able_yn                                \n");
		sb.append("from(                                            \n");
		sb.append("select *                                         \n");
		sb.append("from REVIEW                                      \n");
		sb.append("order by mod_dt desc)t1, file_mng t2             \n");
		sb.append("Where rownum <=4                                 \n");
		sb.append("AND t1.rev_no = t2.file_no                       \n");
		sb.append("AND t1.able_yn ='Y'		                        \n");
		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug(pstmt);
			LOG.debug(inVO);
			
			rs = pstmt.executeQuery();
			//이전이 오류
			
			while(rs.next()) {
				JoinVO outVO = new JoinVO();
				outVO.setRevNo(rs.getString("rev_no"));
				outVO.setTitle(rs.getString("title"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setImgPath(rs.getString("IMG_PATH"));
				outVO.setExt(rs.getString("EXT"));
				outVO.setAbleYn(rs.getString("able_yn"));
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
	
	
	public List<?> doRecentJRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<CommunityVO1> outList = new ArrayList<CommunityVO1>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select t1.tot_no									\n");
		sb.append("      ,t1.title                                  \n");
		sb.append("      ,DECODE(to_char(t1.mod_dt,'YYYY/MM/DD')    \n");     
		sb.append("		,to_char(SYSDATE,'YYYY/MM/DD')              \n");
		sb.append("		,to_char(t1.mod_dt,'HH24:MI')               \n");
		sb.append("		,to_char(t1.mod_dt,'YYYY/MM/DD HH24:MI:SS') \n");
		sb.append("        ) mod_dt                                 \n");  
		sb.append("      ,t2.img_path                               \n");
		sb.append("      ,t2.ext                                    \n");
		sb.append("      ,t1.able_yn                                \n");
		sb.append("from(                                            \n");
		sb.append("select *                                         \n");
		sb.append("from total_board                                 \n");
		sb.append("order by mod_dt desc)t1, file_mng t2             \n");
		sb.append("Where rownum <=4                                 \n");
		sb.append("AND t1.tot_no like 'j_%'                         \n");
		sb.append("AND t1.tot_no = t2.file_no                       \n");
		sb.append("AND t1.able_yn ='Y'		                        \n");
		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug(pstmt);
			LOG.debug(inVO);
			
			rs = pstmt.executeQuery();
			//이전이 오류
			
			while(rs.next()) {
				CommunityVO1 outVO = new CommunityVO1();
				outVO.setTotNo(rs.getString("tot_no"));
				outVO.setTitle(rs.getString("title"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setImgPath(rs.getString("IMG_PATH"));
				outVO.setExt(rs.getString("EXT"));
				outVO.setAbleYn(rs.getString("able_yn"));
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
	
	
	public List<?> doRecentMRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<CommunityVO1> outList = new ArrayList<CommunityVO1>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select t1.tot_no									\n");
		sb.append("      ,t1.title                                  \n");
		sb.append("      ,DECODE(to_char(t1.mod_dt,'YYYY/MM/DD')    \n");     
		sb.append("		,to_char(SYSDATE,'YYYY/MM/DD')              \n");
		sb.append("		,to_char(t1.mod_dt,'HH24:MI')               \n");
		sb.append("		,to_char(t1.mod_dt,'YYYY/MM/DD HH24:MI:SS') \n");
		sb.append("        ) mod_dt                                 \n");  
		sb.append("      ,t2.img_path                               \n");
		sb.append("      ,t2.ext                                    \n");
		sb.append("      ,t1.able_yn                                \n");
		sb.append("from(                                            \n");
		sb.append("select *                                         \n");
		sb.append("from total_board                                 \n");
		sb.append("order by mod_dt desc)t1, file_mng t2             \n");
		sb.append("Where rownum <=4                                 \n");
		sb.append("AND t1.tot_no like 'm_%'                         \n");
		sb.append("AND t1.tot_no = t2.file_no                       \n");
		sb.append("AND t1.able_yn ='Y'		                        \n");

		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug(pstmt);
			LOG.debug(inVO);
			
			rs = pstmt.executeQuery();
			//이전이 오류
			
			while(rs.next()) {
				CommunityVO1 outVO = new CommunityVO1();
				outVO.setTotNo(rs.getString("tot_no"));
				outVO.setTitle(rs.getString("title"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setImgPath(rs.getString("IMG_PATH"));
				outVO.setExt(rs.getString("EXT"));
				outVO.setAbleYn(rs.getString("able_yn"));
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
	
	
	public List<?> doRecentRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<CommunityVO1> outList = new ArrayList<CommunityVO1>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select t1.tot_no									\n");
		sb.append("      ,t1.title                                  \n");
		sb.append("      ,DECODE(to_char(t1.mod_dt,'YYYY/MM/DD')    \n");
		sb.append("		,to_char(SYSDATE,'YYYY/MM/DD')              \n");
		sb.append("		,to_char(t1.mod_dt,'HH24:MI')               \n");
		sb.append("		,to_char(t1.mod_dt,'YYYY/MM/DD HH24:MI:SS') \n");
		sb.append("        ) mod_dt                                 \n");
		sb.append("      ,t2.img_path                               \n");
		sb.append("      ,t2.ext                                    \n");
		sb.append("      ,t1.able_yn                                \n");
		sb.append("from(                                            \n");
		sb.append("select *                                         \n");
		sb.append("from total_board                                 \n");
		sb.append("order by mod_dt desc)t1, file_mng t2             \n");
		sb.append("Where rownum <=4                                 \n");
		sb.append("AND t1.tot_no = t2.file_no                       \n");
		sb.append("AND t1.able_yn ='Y'		                        \n");

		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug(pstmt);
			LOG.debug(inVO);
			
			rs = pstmt.executeQuery();
			//이전이 오류
			
			while(rs.next()) {
				CommunityVO1 outVO = new CommunityVO1();
				outVO.setTotNo(rs.getString("tot_no"));
				outVO.setTitle(rs.getString("title"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setImgPath(rs.getString("IMG_PATH"));
				outVO.setExt(rs.getString("EXT"));
				outVO.setAbleYn(rs.getString("able_yn"));
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
	
	public List<?> doJRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<CommunityVO1> outList = new ArrayList<CommunityVO1>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sbWhere = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		
		//10: 제목, 20:내용, 30: 작성자	40: 목격자  50: 주인
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
			if(inVO.getSearchDiv().equals("10")) {
				sbWhere.append("AND t1.title like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("20")) {
				sbWhere.append("AND t1.contents like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("30")) {
				sbWhere.append("AND t1.member_id like ? ||'%' \n ");
			}
		}					      
				      
		
		sb.append("SELECT *															\n");
		sb.append("FROM(                                                            \n");
		sb.append("    SELECT B.tot_no,                                             \n");
		sb.append(" 		  rnum num,												\n");
		sb.append("           B.title,                                              \n");
		sb.append("           B.count,                                          	\n");
		sb.append("		      B.member_id,                                          \n");
		sb.append("		      DECODE(to_char(B.mod_dt,'YYYY/MM/DD')                 \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                    \n");
		sb.append("				  ,to_char(B.mod_dt,'HH24:MI')                      \n");
		sb.append("				  ,to_char(B.mod_dt,'YYYY/MM/DD HH24:MI:SS')        \n");
		sb.append("                  ) mod_dt,                                      \n");
		sb.append("           B.contents,                               	        \n");
		sb.append("		      DECODE(to_char(B.reg_dt,'YYYY/MM/DD')                 \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                    \n");
		sb.append("				  ,to_char(B.reg_dt,'HH24:MI')                      \n");
		sb.append("				  ,to_char(B.reg_dt,'YYYY/MM/DD HH24:MI:SS')        \n");
		sb.append("                  ) reg_dt,                                      \n");
		sb.append("		      B.able_yn,                                      	    \n");
		sb.append("		      B.IMG_PATH,                                 	  	    \n");
		sb.append("		      B.EXT,                                 	  	 	 	\n");
		sb.append("		      B.SAVE_NM                                 	   	 	\n");
		sb.append("    FROM (                                                       \n");
		sb.append("        SELECT ROWNUM as rnum, A.*                               \n");
		sb.append("        FROM(                                                    \n");
		sb.append("            SELECT t1.* , t2.img_path, t2.ext, t2.save_nm        \n");
		sb.append("            FROM TOTAL_BOARD t1 , file_mng t2                    \n");
		sb.append("            WHERE t1.tot_no = t2.FILE_NO                         \n");
		sb.append("            AND t1.tot_no like 'j_%' 		                    \n");
		sb.append("            AND t1.able_yn = 'Y'                            	    \n");
		//sb.append("			   --검색어                                              					\n");
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
			if(inVO.getSearchWord() != null) {
				sb.append(sbWhere.toString());
			}
		}
		sb.append("            ORDER BY t1.mod_dt desc                              \n");
		sb.append("        )A                                                       \n");
		//sb.append("        WHERE rownum <=(&PAGE_SIZE*(&PAGE_NUM-1)) + &PAGE_SIZE \n");
		sb.append("        WHERE rownum <=(?*(?-1)) + ? 						    \n");
		sb.append("    )B                                                           \n");
		//sb.append("    WHERE rnum >=(&PAGE_SIZE*(&PAGE_NUM-1)+1)                  \n");
		sb.append("    WHERE rnum >=(?*(?-1)+1)        					            \n");
		sb.append(")                                                                \n");
		sb.append("CROSS JOIN                                                       \n");
		sb.append("(                                                                \n");
		sb.append("    SELECT COUNT(*) total                                        \n");
		sb.append("    FROM TOTAL_BOARD t1, file_mng t2                             \n");
		sb.append("    Where t1.tot_no = t2.FILE_NO                         	    \n");
		sb.append("    AND t1.tot_no like 'j_%' 		                     	    \n");
		sb.append("    AND t1.able_yn = 'Y'                          			    \n");
		//sb.append("    --검색어                                                      							\n");
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
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
			if((inVO.getSearchDiv() !=null && !inVO.getSearchDiv().equals("")) && inVO.getSearchWord() != null) {
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
				CommunityVO1 outVO = new CommunityVO1();
				outVO.setTotNo(rs.getString("tot_no"));
				outVO.setNum(rs.getInt("num"));
				outVO.setTitle(rs.getString("title"));
				outVO.setCount(rs.getInt("count"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setContents(rs.getString("contents"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAbleYn(rs.getString("able_yn"));
				outVO.setTotal(rs.getInt("total"));
				outVO.setImgPath(rs.getString("IMG_PATH"));
				outVO.setExt(rs.getString("EXT"));
				outVO.setSaveNm(rs.getString("SAVE_NM"));
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
	
	
	public List<?> doMRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<CommunityVO1> outList = new ArrayList<CommunityVO1>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sbWhere = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		
		//10: 제목, 20:내용, 30: 작성자	40: 목격자  50: 주인
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
			if(inVO.getSearchDiv().equals("10")) {
				sbWhere.append("AND t1.title like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("20")) {
				sbWhere.append("AND t1.contents like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("30")) {
				sbWhere.append("AND t1.member_id like ? ||'%' \n ");
			}
		}					      
				      
		
		sb.append("SELECT *															\n");
		sb.append("FROM(                                                            \n");
		sb.append("    SELECT B.tot_no,                                             \n");
		sb.append(" 		  rnum num,												\n");
		sb.append("           B.title,                                              \n");
		sb.append("           B.count,                                          	\n");
		sb.append("		      B.member_id,                                          \n");
		sb.append("		      DECODE(to_char(B.mod_dt,'YYYY/MM/DD')                 \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                    \n");
		sb.append("				  ,to_char(B.mod_dt,'HH24:MI')                      \n");
		sb.append("				  ,to_char(B.mod_dt,'YYYY/MM/DD HH24:MI:SS')        \n");
		sb.append("                  ) mod_dt,                                      \n");
		sb.append("           B.contents,                               	        \n");
		sb.append("		      DECODE(to_char(B.reg_dt,'YYYY/MM/DD')                 \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                    \n");
		sb.append("				  ,to_char(B.reg_dt,'HH24:MI')                      \n");
		sb.append("				  ,to_char(B.reg_dt,'YYYY/MM/DD HH24:MI:SS')        \n");
		sb.append("                  ) reg_dt,                                      \n");
		sb.append("		      B.able_yn,                                      	    \n");
		sb.append("		      B.IMG_PATH,                                 	  	    \n");
		sb.append("		      B.EXT,                                 	  	 	 	\n");
		sb.append("		      B.SAVE_NM                                 	   	 	\n");
		sb.append("    FROM (                                                       \n");
		sb.append("        SELECT ROWNUM as rnum, A.*                               \n");
		sb.append("        FROM(                                                    \n");
		sb.append("            SELECT t1.* , t2.img_path, t2.ext, t2.save_nm        \n");
		sb.append("            FROM TOTAL_BOARD t1 , file_mng t2                    \n");
		sb.append("            WHERE t1.tot_no = t2.FILE_NO                         \n");
		sb.append("            AND t1.tot_no like 'm_%' 		                    \n");
		sb.append("            AND t1.able_yn = 'Y'                             	\n");
		//sb.append("			   --검색어                                              					\n");
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
			if(inVO.getSearchWord() != null) {
				sb.append(sbWhere.toString());
			}
		}
		sb.append("            ORDER BY t1.mod_dt desc                              \n");
		sb.append("        )A                                                       \n");
		//sb.append("        WHERE rownum <=(&PAGE_SIZE*(&PAGE_NUM-1)) + &PAGE_SIZE \n");
		sb.append("        WHERE rownum <=(?*(?-1)) + ? 						    \n");
		sb.append("    )B                                                           \n");
		//sb.append("    WHERE rnum >=(&PAGE_SIZE*(&PAGE_NUM-1)+1)                  \n");
		sb.append("    WHERE rnum >=(?*(?-1)+1)        					            \n");
		sb.append(")                                                                \n");
		sb.append("CROSS JOIN                                                       \n");
		sb.append("(                                                                \n");
		sb.append("    SELECT COUNT(*) total                                        \n");
		sb.append("    FROM TOTAL_BOARD t1, file_mng t2                             \n");
		sb.append("    Where t1.tot_no = t2.FILE_NO                   		        \n");
		sb.append("    AND t1.tot_no like 'm_%' 		                     	    \n");
		sb.append("    AND t1.able_yn = 'Y'                            			    \n");
		//sb.append("    --검색어                                                      							\n");
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
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
			if((inVO.getSearchDiv() !=null && !inVO.getSearchDiv().equals("")) && inVO.getSearchWord() != null) {
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
				CommunityVO1 outVO = new CommunityVO1();
				outVO.setTotNo(rs.getString("tot_no"));
				outVO.setNum(rs.getInt("num"));
				outVO.setTitle(rs.getString("title"));
				outVO.setCount(rs.getInt("count"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setContents(rs.getString("contents"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAbleYn(rs.getString("able_yn"));
				outVO.setTotal(rs.getInt("total"));
				outVO.setImgPath(rs.getString("IMG_PATH"));
				outVO.setExt(rs.getString("EXT"));
				outVO.setSaveNm(rs.getString("SAVE_NM"));
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
	
	public List<?> doRRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<JoinVO> outList = new ArrayList<JoinVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sbWhere = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		
		//10: 제목, 20:내용, 30: 작성자	40: 목격자  50: 주인
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
			if(inVO.getSearchDiv().equals("10")) {
				sbWhere.append("AND t1.title like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("20")) {
				sbWhere.append("AND t1.contents like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("30")) {
				sbWhere.append("AND t1.member_id like ? ||'%' \n ");
			}
		}					      
				      
		
		sb.append("SELECT *															\n");
		sb.append("FROM(                                         					\n");           
		sb.append("    SELECT B.REV_NO,                                             \n");
		sb.append(" 		  rnum num,		                                        \n");
		sb.append("          B.apply_no,                                            \n");
		sb.append("           B.title,                                              \n");
		sb.append("           B.count,                                              \n");
		sb.append("           B.family_dt,                                          \n");
		sb.append("		      B.member_id,                                          \n");
		sb.append("		      DECODE(to_char(B.mod_dt,'YYYY/MM/DD')                 \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                    \n");
		sb.append("				  ,to_char(B.mod_dt,'HH24:MI')                      \n");
		sb.append("				  ,to_char(B.mod_dt,'YYYY/MM/DD HH24:MI:SS')        \n");
		sb.append("                  ) mod_dt,                                      \n");
		sb.append("           B.contents,                               	        \n");
		sb.append("		      DECODE(to_char(B.reg_dt,'YYYY/MM/DD')                 \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                    \n");
		sb.append("				  ,to_char(B.reg_dt,'HH24:MI')                      \n");
		sb.append("				  ,to_char(B.reg_dt,'YYYY/MM/DD HH24:MI:SS')        \n");
		sb.append("                  ) reg_dt,                                      \n");
		sb.append("		      B.able_yn,                                      	    \n");
		sb.append("		      B.IMG_PATH,                                 	  	    \n");
		sb.append("		      B.EXT                                 	  	 	 	\n");                        	   	  	
		sb.append("    FROM (                                                       \n");
		sb.append("        SELECT ROWNUM as rnum, A.*                               \n");
		sb.append("        FROM(                                                    \n");
		sb.append("            SELECT t1.* , t2.img_path, t2.ext, t2.save_nm        \n");
		sb.append("            FROM REVIEW t1 , file_mng t2                         \n");
		sb.append("            WHERE t1.REV_NO = t2.FILE_NO                         \n");
		sb.append("            AND t1.able_yn = 'Y'                           	    \n");
		//sb.append("			   --검색어                                              					\n");
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
			if(inVO.getSearchWord() != null) {
				sb.append(sbWhere.toString());
			}
		}
		sb.append("            ORDER BY t1.mod_dt desc                              \n");
		sb.append("        )A                                                       \n");
		sb.append("        WHERE rownum <=(?*(?-1)) + ?   							\n");
		sb.append("        					                                        \n");
		sb.append("    )B                                                           \n");
		sb.append("    WHERE rnum >=(?*(?-1)+1)                   					\n");			            
		sb.append(")                                                                \n");
		sb.append("CROSS JOIN                                                       \n");
		sb.append("(                                                                \n");
		sb.append("    SELECT COUNT(*) total                                        \n");
		sb.append("    FROM REVIEW t1, file_mng t2                           	    \n");
		sb.append("    Where t1.REV_NO = t2.FILE_NO                          		\n");
		sb.append("    AND t1.able_yn = 'Y'                            	   			\n");
		//sb.append("    --검색어                                                      							\n");
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
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
			if((inVO.getSearchDiv() !=null && !inVO.getSearchDiv().equals("")) && inVO.getSearchWord() != null) {
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
				JoinVO outVO = new JoinVO();
				outVO.setRevNo(rs.getString("REV_NO"));
				outVO.setNum(rs.getInt("num"));
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setTitle(rs.getString("title"));
				outVO.setCount(rs.getInt("count"));
				outVO.setFamilyDt(rs.getString("family_dt"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setContents(rs.getString("contents"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAbleYn(rs.getString("able_yn"));
				outVO.setTotal(rs.getInt("total"));
				outVO.setImgPath(rs.getString("IMG_PATH"));
				outVO.setExt(rs.getString("EXT"));
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
	
	
	@Override
	public List<?> doRetrieve(DTO dto) {
		SearchVO inVO = (SearchVO) dto;
		List<CommunityVO1> outList = new ArrayList<CommunityVO1>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sbWhere = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		
		//10: 제목, 20:내용, 30: 작성자	40: 목격자  50: 주인
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
			if(inVO.getSearchDiv().equals("10")) {
				sbWhere.append("AND t1.title like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("20")) {
				sbWhere.append("AND t1.contents like ? ||'%' \n ");
			} else if(inVO.getSearchDiv().equals("30")) {
				sbWhere.append("AND t1.member_id like ? ||'%' \n ");
			}
		}					      
				      
		
		sb.append("SELECT *															\n");
		sb.append("FROM(                                                            \n");
		sb.append("    SELECT B.tot_no,                                             \n");
		sb.append(" 		  rnum num,												\n");
		sb.append("           B.title,                                              \n");
		sb.append("           B.count,                                          	\n");
		sb.append("		      B.member_id,                                          \n");
		sb.append("		      DECODE(to_char(B.mod_dt,'YYYY/MM/DD')                 \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                    \n");
		sb.append("				  ,to_char(B.mod_dt,'HH24:MI')                      \n");
		sb.append("				  ,to_char(B.mod_dt,'YYYY/MM/DD HH24:MI:SS')        \n");
		sb.append("                  ) mod_dt,                                      \n");
		sb.append("           B.contents,                               	        \n");
		sb.append("		      DECODE(to_char(B.reg_dt,'YYYY/MM/DD')                 \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                    \n");
		sb.append("				  ,to_char(B.reg_dt,'HH24:MI')                      \n");
		sb.append("				  ,to_char(B.reg_dt,'YYYY/MM/DD HH24:MI:SS')        \n");
		sb.append("                  ) reg_dt,                                      \n");
		sb.append("		      B.able_yn,                                      	    \n");
		sb.append("		      B.IMG_PATH,                                 	  	    \n");
		sb.append("		      B.EXT,                                 	  	 	 	\n");
		sb.append("		      B.SAVE_NM                                 	   	 	\n");
		sb.append("    FROM (                                                       \n");
		sb.append("        SELECT ROWNUM as rnum, A.*                               \n");
		sb.append("        FROM(                                                    \n");
		sb.append("            SELECT t1.* , t2.img_path, t2.ext, t2.save_nm        \n");
		sb.append("            FROM TOTAL_BOARD t1 , file_mng t2                    \n");
		sb.append("            WHERE t1.tot_no = t2.FILE_NO                         \n");
		sb.append("            AND t1.able_yn = 'Y'                                 \n");
		//sb.append("			   --검색어                                              					\n");
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
			if(inVO.getSearchWord() != null) {
				sb.append(sbWhere.toString());
			}
		}
		sb.append("            ORDER BY t1.mod_dt desc                              \n");
		sb.append("        )A                                                       \n");
		//sb.append("        WHERE rownum <=(&PAGE_SIZE*(&PAGE_NUM-1)) + &PAGE_SIZE \n");
		sb.append("        WHERE rownum <=(?*(?-1)) + ? 						    \n");
		sb.append("    )B                                                           \n");
		//sb.append("    WHERE rnum >=(&PAGE_SIZE*(&PAGE_NUM-1)+1)                  \n");
		sb.append("    WHERE rnum >=(?*(?-1)+1)        					            \n");
		sb.append(")                                                                \n");
		sb.append("CROSS JOIN                                                       \n");
		sb.append("(                                                                \n");
		sb.append("    SELECT COUNT(*) total                                        \n");
		sb.append("    FROM TOTAL_BOARD t1, file_mng t2                             \n");
		sb.append("    Where t1.tot_no = t2.FILE_NO                        		    \n");
		sb.append("    AND t1.able_yn = 'Y'                             			\n");
		//sb.append("    --검색어                                                      							\n");
		if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
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
			if((inVO.getSearchDiv() !=null && !inVO.getSearchDiv().equals("")) && inVO.getSearchWord() != null) {
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
				CommunityVO1 outVO = new CommunityVO1();
				outVO.setTotNo(rs.getString("tot_no"));
				outVO.setNum(rs.getInt("num"));
				outVO.setTitle(rs.getString("title"));
				outVO.setCount(rs.getInt("count"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setContents(rs.getString("contents"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAbleYn(rs.getString("able_yn"));
				outVO.setTotal(rs.getInt("total"));
				outVO.setImgPath(rs.getString("IMG_PATH"));
				outVO.setExt(rs.getString("EXT"));
				outVO.setSaveNm(rs.getString("SAVE_NM"));
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

	public int readRCount(DTO dto) {
		int flag = 0;
		JoinVO inVO =(JoinVO) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE review					\n");
		sb.append("set count = NVL(count,0) + 1     \n");
		sb.append("where rev_no = ?                 \n");
		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setString(1, inVO.getRevNo());
			LOG.debug(pstmt);
			flag = pstmt.executeUpdate();
			LOG.debug(flag);
			
			
		} catch(SQLException e) {
			LOG.debug("SQLException" + e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		return flag;
		
	}
	
	public int readCount(DTO dto) {
		int flag = 0;
		CommunityVO1 inVO =(CommunityVO1) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE total_board					\n");	
		sb.append("set count = NVL(count,0) + 1         \n");
		sb.append("where tot_no = ?                    	\n");
		
		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setString(1, inVO.getTotNo());
			LOG.debug(pstmt);
			flag = pstmt.executeUpdate();
			LOG.debug(flag);
			
			
		} catch(SQLException e) {
			LOG.debug("SQLException" + e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		return flag;
		
	}
	

}
