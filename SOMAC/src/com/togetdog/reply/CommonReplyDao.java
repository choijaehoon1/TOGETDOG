package com.togetdog.reply;

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

public class CommonReplyDao extends WorkDiv {
	
	private ConnectionMaker connectionMaker;
	
	public CommonReplyDao() {
		connectionMaker = new ConnectionMaker();
	}
	@Override
	public int doInsert(DTO dto) {
		int flag =0;
		CommonReplyVO inVO = (CommonReplyVO) dto;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO common_reply (		\n");
			sb.append("    reply_no,                    \n");
			sb.append("    rseq_no,                     \n");
			sb.append("    rcontents,                    \n");
			sb.append("    rreg_id,                      \n");
			sb.append("    rreg_dt,                      \n");
			sb.append("    reply_yn                     \n");
			sb.append(") VALUES (                       \n");
			sb.append("    ?,							\n");
			sb.append("    COMMON_REPLY_SEQ.nextval,    \n");
			sb.append("    ?,                           \n");
			sb.append("    ?,                  		    \n");
			sb.append("    sysdate,                     \n");
			sb.append("    'Y'                          \n");
			sb.append(")                                \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			pstmt.setString(1, inVO.getReplyNo());
			pstmt.setString(2, inVO.getContents());
			pstmt.setString(3, inVO.getRegId());
			
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
		CommonReplyVO inVO =(CommonReplyVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE common_reply 	     \n");
			sb.append("SET reply_yn = 'N' 	     \n");
			sb.append("WHERE reply_no = ?        \n");
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug("pstmt" + pstmt);
//			pstmt.setInt(1, inVO.getReplyNo());
			
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
		CommonReplyVO inVO = (CommonReplyVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE common_reply	   \n");
			sb.append("SET board_no = ?,       \n");
			sb.append("    contents = ?,       \n");
			sb.append("    reg_id = ?,    	   \n");
			sb.append("    reg_dt = sysdate,   \n");   
			sb.append("    reply_yn = 'Y'      \n");
			sb.append("WHERE                   \n");
			sb.append("    reply_no = ?        \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
//			pstmt.setString(1, inVO.getBoardNo());
			pstmt.setString(2, inVO.getContents());
			pstmt.setString(3, inVO.getRegId());
//			pstmt.setInt(4, inVO.getReplyNo());
			
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
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<?> doreRetrieve(DTO dto) {
		CommonReplyVO inVO = (CommonReplyVO) dto;
		List<CommonReplyVO> outList = new ArrayList<CommonReplyVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT *															\n");				
		sb.append("FROM(                                                            \n");
		sb.append("    SELECT B.REPLY_NO,                                           \n");  
		sb.append(" 		   rnum num,											\n");	
		sb.append("           B.BOARD_NO,                                           \n");   
		sb.append("           B.CONTENTS,                                          	\n");
		sb.append("           B.REG_ID,                                             \n");                         	        
		sb.append("           DECODE(to_char(B.reg_dt,'YYYY/MM/DD')                 \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                    \n");
		sb.append("				  ,to_char(B.reg_dt,'HH24:MI')                      \n");
		sb.append("				  ,to_char(B.reg_dt,'YYYY/MM/DD')                   \n");
		sb.append("                  ) reg_dt,                                      \n");
		sb.append("           B.REPLY_YN                                      	    \n");
		sb.append("    FROM (                                                       \n");
		sb.append("        SELECT ROWNUM as rnum, A.*                               \n");
		sb.append("        FROM(                                                    \n");
		sb.append("            SELECT t1.*                                          \n");
		sb.append("            FROM COMMON_REPLY t1                                 \n"); 
		sb.append("			WHERE t1.reg_id = ?                                     \n");
		sb.append("            ORDER BY t1.reg_dt desc                              \n");
		sb.append("        )A                                                       \n");
		sb.append("        WHERE rownum <=(?*(?-1)) + ? 						    \n");
		sb.append("    )B                                                           \n");
		sb.append("    WHERE rnum >=(?*(?-1)+1)                                     \n");
		sb.append(")                                                                \n");
		sb.append("CROSS JOIN                                                       \n");
		sb.append("(                                                                \n");
		sb.append("    SELECT COUNT(*) total                                        \n");
		sb.append("    FROM COMMON_REPLY t1                                         \n"); 
		sb.append("    WHERE t1.reg_id =?                                           \n");      
		sb.append(")                                                                \n");
		
		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug(pstmt);
			LOG.debug(inVO);
			
			pstmt.setString(1, inVO.getRegId());
			pstmt.setInt(2, inVO.getPageSize());
			pstmt.setInt(3, inVO.getPageNum());
			pstmt.setInt(4, inVO.getPageSize());
			pstmt.setInt(5, inVO.getPageSize());
			pstmt.setInt(6, inVO.getPageNum());
			pstmt.setString(7, inVO.getRegId());
		
			
			
			
			rs = pstmt.executeQuery();
			//이전이 오류
			
			while(rs.next()) {
				CommonReplyVO outVO = new CommonReplyVO();
//				outVO.setReplyNo(rs.getInt("REPLY_NO"));
				outVO.setNum(rs.getInt("num"));
//				outVO.setBoardNo(rs.getString("BOARD_NO"));
				outVO.setContents(rs.getString("CONTENTS"));
				outVO.setRegId(rs.getString("REG_ID"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setReplyYn(rs.getString("REPLY_YN"));
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
	
	
	
	@Override
	public List<?> doRetrieve(DTO dto) {
		CommonReplyVO inVO = (CommonReplyVO) dto;
		List<CommonReplyVO> outList = new ArrayList<CommonReplyVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT *															\n");				
		sb.append("FROM(                                                            \n");
		sb.append("    SELECT B.REPLY_NO,                                           \n");  
		sb.append(" 		   rnum num,											\n");	
		sb.append("           B.BOARD_NO,                                           \n");   
		sb.append("           B.CONTENTS,                                          	\n");
		sb.append("           B.REG_ID,                                             \n");                         	        
		sb.append("           DECODE(to_char(B.reg_dt,'YYYY/MM/DD')                 \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                    \n");
		sb.append("				  ,to_char(B.reg_dt,'HH24:MI')                      \n");
		sb.append("				  ,to_char(B.reg_dt,'YYYY/MM/DD')                   \n");
		sb.append("                  ) reg_dt,                                      \n");
		sb.append("           B.REPLY_YN                                      	    \n");
		sb.append("    FROM (                                                       \n");
		sb.append("        SELECT ROWNUM as rnum, A.*                               \n");
		sb.append("        FROM(                                                    \n");
		sb.append("            SELECT t1.*                                          \n");
		sb.append("            FROM COMMON_REPLY t1                                 \n"); 
		sb.append("			WHERE t1.reg_id = ?                                     \n");
		sb.append("            ORDER BY t1.reg_dt desc                              \n");
		sb.append("        )A                                                       \n");
		sb.append("        WHERE rownum <=(?*(?-1)) + ? 						    \n");
		sb.append("    )B                                                           \n");
		sb.append("    WHERE rnum >=(?*(?-1)+1)                                     \n");
		sb.append(")                                                                \n");
		sb.append("CROSS JOIN                                                       \n");
		sb.append("(                                                                \n");
		sb.append("    SELECT COUNT(*) total                                        \n");
		sb.append("    FROM COMMON_REPLY t1                                         \n"); 
		sb.append("    WHERE t1.reg_id =?                                           \n");      
		sb.append(")                                                                \n");
		
		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug(pstmt);
			LOG.debug(inVO);
			
			pstmt.setString(1, inVO.getRegId());
			pstmt.setInt(2, inVO.getPageSize());
			pstmt.setInt(3, inVO.getPageNum());
			pstmt.setInt(4, inVO.getPageSize());
			pstmt.setInt(5, inVO.getPageSize());
			pstmt.setInt(6, inVO.getPageNum());
			pstmt.setString(7, inVO.getRegId());
		
			
			
			
			rs = pstmt.executeQuery();
			//이전이 오류
			
			while(rs.next()) {
				CommonReplyVO outVO = new CommonReplyVO();
//				outVO.setReplyNo(rs.getInt("REPLY_NO"));
				outVO.setNum(rs.getInt("num"));
//				outVO.setBoardNo(rs.getString("BOARD_NO"));
				outVO.setContents(rs.getString("CONTENTS"));
				outVO.setRegId(rs.getString("REG_ID"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setReplyYn(rs.getString("REPLY_YN"));
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
