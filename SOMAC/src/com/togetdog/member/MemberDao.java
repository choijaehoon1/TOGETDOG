/**
 *<pre>
 * com.JW.board
 * Class Name : MemberDao.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-11           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-11 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */

package com.togetdog.member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.togetdog.cmn.MessageVO;
import com.togetdog.cmn.ConnectionMaker;
import com.togetdog.cmn.DTO;
import com.togetdog.cmn.JDBCResClose;
import com.togetdog.cmn.SearchVO;
import com.togetdog.cmn.WorkDiv;
import com.togetdog.member.MemberVO;


/**
 * @author SIST
 * 로그인 기능
 * 1. ID_CHECK
 * 2. PASS_CHECK
 * 3. LOGIN 생성
 */
public class MemberDao extends WorkDiv {
	private ConnectionMaker connectionMaker;
	
	public MemberDao() {
		connectionMaker = new ConnectionMaker();
	}
	
	public DTO dokalogin(DTO dto) {
		  MemberVO inVO  = (MemberVO) dto;//param
		  MessageVO outVO = null; //return
		  
		  Connection connection    = null; //DB Connection
		  PreparedStatement  pstmt = null; //VS Statement 해킹에 취약
		  ResultSet   rs           = null; //결과 값 처리 class
		  
		  StringBuilder sb=new StringBuilder();//검색query
		  try {
			  //1.Connection
			  connection = connectionMaker.getConnection();
			  LOG.debug("1.Connection="+connection);
			  
			  //2.Query
			  sb.append(" SELECT count(*) cnt   \n");
			  sb.append(" FROM member       \n");
			  sb.append(" WHERE member_id = ?  \n");
			  
			  LOG.debug("2.query=\n"+sb.toString());
			  //2.1. pstmt
			  pstmt = connection.prepareStatement(sb.toString());
			  
			  //3.param
			  LOG.debug("3.param=\n"+inVO);
			
			  pstmt.setString(1, inVO.getMemberId());
			  
			  //4.query수행
			  rs = pstmt.executeQuery();
			  if(rs.next()) {
				  outVO = new MessageVO();
				  int result = rs.getInt("cnt");//아이디
				  //ID가 있으면 1 없으면 0
				  if(0 == result) {
					  outVO.setMsgId("10"); // ID가 없는경우
					  outVO.setMsgContents("");// ID가 없는경우
				  }else {
					  outVO.setMsgId("1"); // ID가 있는 경우
					  outVO.setMsgContents("로그인 되었습니다.");// ID가 있는 경우 메세지를 뿌릴 필요 x
				  }
				  
				  LOG.debug("4.return :\n"+outVO);
				}
			} catch(SQLException e) {
				LOG.debug("===========================");
				LOG.debug("=======SQLException======="+e.getMessage());
				LOG.debug("===========================");
				e.printStackTrace();
			} finally {
				//5.preparedStatement, ResultSet 자원 반납 - 사용하는 역순으로 close
				//6.Connection 종료
				JDBCResClose.close(rs);
				JDBCResClose.close(pstmt);
				JDBCResClose.close(connection);
			}
			return outVO;
		}
	
	
	public int dokaInsert(DTO dto) {
		int flag = 0;
		MemberVO inVO = (MemberVO) dto;//param
		
		Connection  connection = null; // DB Connection
		PreparedStatement pstmt = null; // VS Statement
		
		try {
			//1. Connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1.Connection="+connection);
			StringBuilder sb=new StringBuilder();
	    	 sb.append(" INSERT INTO member(      \n");
	    	 sb.append("     member_id,           \n");
	    	 sb.append("     password,            \n");
	    	 sb.append("     name,                \n");
	    	 sb.append("     phone,               \n");
	    	 sb.append("     email,               \n");
	    	 sb.append("     author,              \n");
	    	 sb.append("     reg_id,              \n");
	    	 sb.append("     reg_dt,              \n");
	    	 sb.append("     mod_id,              \n");
	    	 sb.append("     mod_dt               \n");
	    	 sb.append(" ) VALUES (               \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     1,                   \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     sysdate,             \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     sysdate              \n");    	 
	    	 sb.append("     )                    \n");	    
	    	 
	    	 //2.pstmt
	    	 LOG.debug("2.query=\n"+sb.toString());
	    	 pstmt = connection.prepareStatement(sb.toString());
	    	 LOG.debug("2.1 pstmt="+pstmt);
			
	    	 //3.param
	    	 LOG.debug("3. param+"+inVO);
	    	 pstmt.setString(1, inVO.getMemberId());
	    	 pstmt.setString(2, inVO.getPassword());
	    	 pstmt.setString(3, inVO.getName());
	    	 pstmt.setString(4, inVO.getPhone());
	    	 pstmt.setString(5, inVO.getEmail());
	    	 pstmt.setString(6, inVO.getMemberId());
	    	 pstmt.setString(7, inVO.getMemberId());
		
	    	 //4.query수행
	    	 flag = pstmt.executeUpdate();
	    	 LOG.debug("4. flag="+flag);
		
	    	 
		}catch(SQLException e) {
			 LOG.debug("===========================");
		      LOG.debug("=SQLException="+e.getMessage());
		      LOG.debug("===========================");
		      e.printStackTrace();
	      
	     }finally { //자원반납
		      //사용 역순으로 close
		      JDBCResClose.close(pstmt);//prepareStatement
		      JDBCResClose.close(connection);//connection
	     }
	     return flag;
	}
	
	
	public DTO doidcheck(DTO dto) {
		  MemberVO inVO  = (MemberVO) dto;//param
		  MessageVO outVO = null; //return
		  
		  Connection connection    = null; //DB Connection
		  PreparedStatement  pstmt = null; //VS Statement 해킹에 취약
		  ResultSet   rs           = null; //결과 값 처리 class
		  
		  StringBuilder sb=new StringBuilder();//검색query
		  try {
			  //1.Connection
			  connection = connectionMaker.getConnection();
			  LOG.debug("1.Connection="+connection);
			  
			  //2.Query
			  sb.append(" SELECT count(*) cnt   \n");
			  sb.append(" FROM member       \n");
			  sb.append(" WHERE member_id = ?  \n");
			  
			  LOG.debug("2.query=\n"+sb.toString());
			  //2.1. pstmt
			  pstmt = connection.prepareStatement(sb.toString());
			  
			  //3.param
			  LOG.debug("3.param=\n"+inVO);
			
			  pstmt.setString(1, inVO.getMemberId());
			  
			  //4.query수행
			  rs = pstmt.executeQuery();
			  if(rs.next()) {
				  outVO = new MessageVO();
				  int result = rs.getInt("cnt");//아이디
				  //ID가 있으면 1 없으면 0
				  if(0 == result) {
					  outVO.setMsgId("10"); // ID가 없는경우
					  outVO.setMsgContents("");// ID가 없는경우
				  }else {
					  outVO.setMsgId("1"); // ID가 있는 경우
					  outVO.setMsgContents("");// ID가 있는 경우 메세지를 뿌릴 필요 x
				  }
				  
				  LOG.debug("4.return :\n"+outVO);
				}
			} catch(SQLException e) {
				LOG.debug("===========================");
				LOG.debug("=======SQLException======="+e.getMessage());
				LOG.debug("===========================");
				e.printStackTrace();
			} finally {
				//5.preparedStatement, ResultSet 자원 반납 - 사용하는 역순으로 close
				//6.Connection 종료
				JDBCResClose.close(rs);
				JDBCResClose.close(pstmt);
				JDBCResClose.close(connection);
			}
			return outVO;
		}
		
	

	/**
	 * 
	*Method passCheck
	*작성일: 2020. 3. 12.
	*작성자: sist
	*설명: 로그인에 비번 check
	*@param dto
	*@return
	 */
	public DTO passCheck(DTO dto) {
		  MemberVO inVO  = (MemberVO) dto;
		  MessageVO outVO = null;
		  
		  Connection connection    = null;
		  PreparedStatement  pstmt = null;
		  ResultSet   rs           = null;
		  
		  StringBuilder sb=new StringBuilder();//검색query
		  try {
			  //1.Connection
			  connection = connectionMaker.getConnection();
			  LOG.debug("1.Connection="+connection);
			  
			  //2.Query
			  sb.append(" SELECT count(*) cnt  \n");
			  sb.append(" FROM member      \n");
			  sb.append(" WHERE member_id = ? \n");
			  sb.append(" AND password = ?    \n");
			  
			  LOG.debug("2.query=\n"+sb.toString());
			  //2.1. pstmt
			  pstmt = connection.prepareStatement(sb.toString());
			  
			  //3.param
			  LOG.debug("3.param=\n"+inVO);
			
			  pstmt.setString(1, inVO.getMemberId());
			  pstmt.setString(2, inVO.getPassword());
			  
			  //4.query수행
			  rs = pstmt.executeQuery();
			  if(rs.next()) {
				  outVO = new MessageVO();
				  int result = rs.getInt("cnt");//아이디
				  //ID/비번이  있으면 1 없으면 0
				  if(0 == result) {
					  outVO.setMsgId("20"); // ID/비번이 없는경우
					  outVO.setMsgContents("비번를 확인하세요");// ID/비번이 없는경우
				  
				  }else {
					  outVO.setMsgId("1"); // ID/비번이있는 경우
					  outVO.setMsgContents("로그인에 성공하였습니다.");// ID/비번이 경우 메세지를 뿌릴 필요 x
				  }
				  
				  LOG.debug("5.outVO=\n"+outVO);
			  }

		  }catch(SQLException e) {
			  LOG.debug("===================");
			  LOG.debug("=SQLException="+e.getMessage());
			  LOG.debug("===================");
			  e.printStackTrace();
		  }finally {
			  JDBCResClose.close(rs);
			  JDBCResClose.close(pstmt);
			  JDBCResClose.close(connection);
		  }
		  
		  
		  return outVO;
		}
	
	
	
	/**
	 * 
	*Method Name:idCheck
	*작성일: 2020. 3. 12.
	*작성자: sist
	*설명: 로그인에 id check
	*@param dto
	*@return
	 */
	public DTO idCheck(DTO dto) {
		  MemberVO inVO  = (MemberVO) dto;
		  MessageVO outVO = null;
		  
		  Connection connection    = null;
		  PreparedStatement  pstmt = null;
		  ResultSet   rs           = null;
		  
		  StringBuilder sb=new StringBuilder();//검색query
		  try {
			  //1.Connection
			  connection = connectionMaker.getConnection();
			  LOG.debug("1.Connection="+connection);
			  
			  //2.Query
			  sb.append(" SELECT count(*) cnt   \n");
			  sb.append(" FROM member       \n");
			  sb.append(" WHERE member_id = ?  \n");
			  
			  LOG.debug("2.query=\n"+sb.toString());
			  //2.1. pstmt
			  pstmt = connection.prepareStatement(sb.toString());
			  
			  //3.param
			  LOG.debug("3.param=\n"+inVO);
			
			  pstmt.setString(1, inVO.getMemberId());
			  
			  //4.query수행
			  rs = pstmt.executeQuery();
			  if(rs.next()) {
				  outVO = new MessageVO();
				  int result = rs.getInt("cnt");//아이디
				  //ID가 있으면 1 없으면 0
				  if(0 == result) {
					  outVO.setMsgId("10"); // ID가 없는경우
					  outVO.setMsgContents("");// ID가 없는경우
				  }else {
					  outVO.setMsgId("1"); // ID가 있는 경우
					  outVO.setMsgContents("");// ID가 있는 경우 메세지를 뿌릴 필요 x
				  }
				  
				  LOG.debug("5.outVO=\n"+outVO);
			  }

		  }catch(SQLException e) {
			  LOG.debug("===================");
			  LOG.debug("=SQLException="+e.getMessage());
			  LOG.debug("===================");
			  e.printStackTrace();
		  }finally {
			  JDBCResClose.close(rs);
			  JDBCResClose.close(pstmt);
			  JDBCResClose.close(connection);
		  }
		  
		  
		  return outVO;
		}
	
	
	
	@Override
	public int doInsert(DTO dto) {
		int flag = 0;
		MemberVO inVO = (MemberVO) dto;//param
		
		Connection  connection = null; // DB Connection
		PreparedStatement pstmt = null; // VS Statement
		
		try {
			//1. Connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1.Connection="+connection);
			StringBuilder sb=new StringBuilder();
	    	 sb.append(" INSERT INTO member(      \n");
	    	 sb.append("     member_id,           \n");
	    	 sb.append("     password,            \n");
	    	 sb.append("     name,                \n");
	    	 sb.append("     phone,               \n");
	    	 sb.append("     email,               \n");
	    	 sb.append("     author,              \n");
	    	 sb.append("     reg_id,              \n");
	    	 sb.append("     reg_dt,              \n");
	    	 sb.append("     mod_id,              \n");
	    	 sb.append("     mod_dt               \n");
	    	 sb.append(" ) VALUES (               \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     1,                   \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     sysdate,             \n");
	    	 sb.append("     ?,                   \n");
	    	 sb.append("     sysdate              \n");    	 
	    	 sb.append("     )                    \n");	    
	    	 
	    	 //2.pstmt
	    	 LOG.debug("2.query=\n"+sb.toString());
	    	 pstmt = connection.prepareStatement(sb.toString());
	    	 LOG.debug("2.1 pstmt="+pstmt);
			
	    	 //3.param
	    	 LOG.debug("3. param+"+inVO);
	    	 pstmt.setString(1, inVO.getMemberId());
	    	 pstmt.setString(2, inVO.getPassword());
	    	 pstmt.setString(3, inVO.getName());
	    	 pstmt.setString(4, inVO.getPhone());
	    	 pstmt.setString(5, inVO.getEmail());
	    	 pstmt.setString(6, inVO.getMemberId());
	    	 pstmt.setString(7, inVO.getMemberId());
		
	    	 //4.query수행
	    	 flag = pstmt.executeUpdate();
	    	 LOG.debug("4. flag="+flag);
		
	    	 
		}catch(SQLException e) {
			 LOG.debug("===========================");
		      LOG.debug("=SQLException="+e.getMessage());
		      LOG.debug("===========================");
		      e.printStackTrace();
	      
	     }finally { //자원반납
		      //사용 역순으로 close
		      JDBCResClose.close(pstmt);//prepareStatement
		      JDBCResClose.close(connection);//connection
	     }
	     return flag;
	}

	@Override
	public int doDelete(DTO dto) {
		int flag = 0;
		MemberVO inVO = (MemberVO) dto; //param
		
		Connection connection = null; // DB Connection
		PreparedStatement pstmt = null; // VS Statement
		try {
			//1.Connection
	    	 connection = connectionMaker.getConnection();
	    	 LOG.debug("1.Connection="+connection);
	    	 
	    	 //2.query
	    	 StringBuilder sb=new StringBuilder();
	    	 sb.append(" DELETE FROM member \n");
	    	 sb.append(" WHERE member_id = ?   \n");
	    	 LOG.debug("2.query=\n"+sb.toString());
	    	 //2.1.pstmt
	    	 pstmt = connection.prepareStatement(sb.toString());
	    	 //3.param
	    	 LOG.debug("3.param=\n"+inVO);
	    	 pstmt.setString(1, inVO.getMemberId());
	    	 
	    	 //4.query수행
	    	 flag = pstmt.executeUpdate();
	    	 LOG.debug("4.flag="+flag);
	      
	     }catch(SQLException e) {
		      LOG.debug("===========================");
		      LOG.debug("=SQLException="+e.getMessage());
		      LOG.debug("===========================");
		      e.printStackTrace();
	      
	     }finally { //자원반납
		      //사용 역순으로 close
		      JDBCResClose.close(pstmt);//prepareStatement
		      JDBCResClose.close(connection);//connection
	     }
	     return flag;
	}


	@Override
	public int doUpdate(DTO dto) {
		 int flag = 0;
		 MemberVO inVO = (MemberVO) dto;//param
		
		 Connection connection   = null;//DB Connection
		 PreparedStatement pstmt = null;//VS Statement
		 try {
		     //1. Connection
			 connection = connectionMaker.getConnection();
			 LOG.debug("1. Connection="+connection);
			 
			 StringBuilder sb=new StringBuilder();
			 sb.append(" UPDATE member                     \n");
			 sb.append(" SET  password 		 = ?           \n");
			 sb.append("     ,name           = ?           \n");
	    	 sb.append("     ,phone          = ?           \n");
	    	 sb.append("     ,email          = ?           \n");
	    	 sb.append("     ,author         = 1           \n");
	    	 sb.append("     ,mod_id         = ?           \n");
	    	 sb.append("     ,mod_dt         = sysdate     \n");
	    	 sb.append(" WHERE member_id = ?               \n");
	    	//2.query
			 LOG.debug("2. query=\n"+sb.toString());
	    	 
			 //2.1. pstmt
			 pstmt = connection.prepareStatement(sb.toString());
			 //3.param 
			 LOG.debug("3. param="+inVO);
	    	 
			 pstmt.setString(1, inVO.getPassword());
	    	 pstmt.setString(2, inVO.getName());
	    	 pstmt.setString(3, inVO.getPhone());
	    	 pstmt.setString(4, inVO.getEmail());
	    	 pstmt.setString(5, inVO.getAuthor());
	    	 pstmt.setString(6, inVO.getMemberId());
	    	 pstmt.setString(7, inVO.getMemberId());
	    	//4.query수행
		    	
	    	 flag = pstmt.executeUpdate();
	    	 LOG.debug("4.query수행="+flag);
			 
			 
		 }catch(SQLException e) {
			  LOG.debug("===========================");
			  LOG.debug("=SQLException="+e.getMessage());
			  LOG.debug("===========================");
			  e.printStackTrace();
		 }finally { //자원반납
			  //사용 역순으로 close
			  JDBCResClose.close(pstmt);//prepareStatement
			  JDBCResClose.close(connection);//connection
		}
		return flag;
	}
	@Override
	public DTO doSelectOne(DTO dto) {
		MemberVO inVO = (MemberVO) dto;
		MemberVO outVO = null;
		
		Connection connection	= null;
		PreparedStatement pstmt = null;
		ResultSet  rs 			= null;
		
		StringBuilder sb=new StringBuilder(); //검새query
		try {
			//1. Connection
			connection = connectionMaker.getConnection();
			LOG.debug("1.Connection="+connection);
			
			//2.Query
			sb.append(" SELECT member_id,                                   \n");
			  sb.append("     password,                                     \n");
			  sb.append("     name,                                         \n");
			  sb.append("     phone,                                        \n");
			  sb.append("     email,                                        \n");
			  sb.append("     DECODE(author,1,'일반',9,'관리자') author,    \n");
			  sb.append("     reg_id,                                       \n");
			  sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD') reg_dt,          \n");
			  sb.append("     mod_id,                                       \n");
			  sb.append("     TO_CHAR(mod_dt,'YYYY/MM/DD') mod_dt           \n");
			  sb.append(" FROM member                                       \n");
			  sb.append(" WHERE member_id = ?                               \n");
		
			  LOG.debug("2.query=\n"+sb.toString());
			  //2.1. pstmt
			  pstmt = connection.prepareStatement(sb.toString());
			  
			  //3.param
			  LOG.debug("3.param=\n"+inVO);
			  
			  pstmt.setString(1, inVO.getMemberId());
			  
			  //4.query수행
			  rs= pstmt.executeQuery();
			  if(rs.next()) {
				  outVO = new MemberVO();
				  outVO.setMemberId(rs.getString("member_id"));
				  outVO.setPassword(rs.getString("password"));
				  outVO.setName(rs.getString("name"));
				  outVO.setPhone(rs.getString("phone"));
				  outVO.setEmail(rs.getString("email"));
				  outVO.setAuthor(rs.getString("author"));
				  outVO.setRegId(rs.getString("reg_id"));
				  outVO.setRegDt(rs.getString("reg_dt"));
				  outVO.setModId(rs.getString("mod_id"));
				  outVO.setModDt(rs.getString("mod_dt"));
			 
				  LOG.debug("5.outVO=\n"+outVO);
			  }
			  
		}catch(SQLException e) {
			  LOG.debug("===================");
			  LOG.debug("=SQLException="+e.getMessage());
			  LOG.debug("===================");
			  e.printStackTrace();
		  }finally {
			  JDBCResClose.close(rs);
			  JDBCResClose.close(pstmt);
			  JDBCResClose.close(connection);
		  }
		  
		  
		  return outVO;
		
		}


	public DTO doSelectOneId(DTO dto) {
		MemberVO inVO = (MemberVO) dto;
		MemberVO outVO = null;
		
		Connection connection	= null;
		PreparedStatement pstmt = null;
		ResultSet  rs 			= null;
		
		StringBuilder sb=new StringBuilder(); //검새query
		try {
			//1. Connection
			connection = connectionMaker.getConnection();
			LOG.debug("1.Connection="+connection);
			
			//2.Query
			sb.append(" SELECT member_id                                     \n");
			sb.append(" FROM member                                          \n");
			sb.append(" WHERE name = ?                                       \n");
			sb.append("   and phone = ?   						             \n");
			  
			  LOG.debug("2.query=\n"+sb.toString());
			  //2.1. pstmt
			  pstmt = connection.prepareStatement(sb.toString());
			  
			  //3.param
			  LOG.debug("3.param=\n"+inVO);
			  
			  pstmt.setString(1, inVO.getName());
			  pstmt.setString(2, inVO.getPhone());
			  //4.query수행
			  rs= pstmt.executeQuery();
			  if(rs.next()) {
				  outVO = new MemberVO();
				  outVO.setMemberId(rs.getString("member_id"));
				  LOG.debug("5.outVO=\n"+outVO);
			  }
			  
		}catch(SQLException e) {
			  LOG.debug("===================");
			  LOG.debug("=SQLException="+e.getMessage());
			  LOG.debug("===================");
			  e.printStackTrace();
		  }finally {
			  JDBCResClose.close(rs);
			  JDBCResClose.close(pstmt);
			  JDBCResClose.close(connection);
		  }
		  
		  
		  return outVO;
		
		}


	public DTO doSelectOnePw(DTO dto) {
		MemberVO inVO = (MemberVO) dto;
		MemberVO outVO = null;
		
		Connection connection	= null;
		PreparedStatement pstmt = null;
		ResultSet  rs 			= null;
		
		StringBuilder sb=new StringBuilder(); //검새query
		try {
			//1. Connection
			connection = connectionMaker.getConnection();
			LOG.debug("1.Connection="+connection);
			
			//2.Query
			sb.append(" SELECT password                                     \n");
			sb.append(" FROM member                                          \n");
			sb.append(" WHERE member_id = ?                                       \n");
			sb.append("   and name = ?   						             \n");
			  
			  LOG.debug("2.query=\n"+sb.toString());
			  //2.1. pstmt
			  pstmt = connection.prepareStatement(sb.toString());
			  
			  //3.param
			  LOG.debug("3.param=\n"+inVO);
			  
			  pstmt.setString(1, inVO.getMemberId());
			  pstmt.setString(2, inVO.getName());
			  //4.query수행
			  rs= pstmt.executeQuery();
			  if(rs.next()) {
				  outVO = new MemberVO();
				  outVO.setPassword(rs.getString("password"));
				  LOG.debug("5.outVO=\n"+outVO);
			  }
			  
		}catch(SQLException e) {
			  LOG.debug("===================");
			  LOG.debug("=SQLException="+e.getMessage());
			  LOG.debug("===================");
			  e.printStackTrace();
		  }finally {
			  JDBCResClose.close(rs);
			  JDBCResClose.close(pstmt);
			  JDBCResClose.close(connection);
		  }
		  
		  
		  return outVO;
		
		}



	@Override
	public List<?> doRetrieve(DTO dto) {
//		10: 제목
//		20: 내용
//		30: 회원ID

		SearchVO inVO=(SearchVO) dto;
		List<MemberVO> outList =new ArrayList<MemberVO>();
		
		Connection connection = null;
		PreparedStatement  pstmt = null;
		ResultSet   rs = null;
		
		StringBuilder sbWhere=new StringBuilder();//검색조건
		StringBuilder sb=new StringBuilder();//검색query
		//검색구분:10(제목),20(내용),30(회원ID)
		if(null !=inVO.getSearchDiv()) {
			if("10".equals(inVO.getSearchDiv())) {//제목
				sbWhere.append(" WHERE t1.title like ?||'%' \n");
			}else if("20".equals(inVO.getSearchDiv())) {//내용
				sbWhere.append(" WHERE t1.contents like ?||'%' \n");
			}else if("30".equals(inVO.getSearchDiv())) {//내용
				sbWhere.append(" WHERE t1.member_Id like ?||'%' \n");
			}
		}
		//검색구분-------------------------------------------------------------
		sb.append("SELECT *                          \n");                   
		sb.append("FROM(                             \n");                   
		sb.append("    SELECT  *                     \n");       
		sb.append("    FROM (                        \n");                   
		sb.append("        SELECT ROWNUM as rnum,A.* \n");                   
		sb.append("        FROM(                     \n");                   
		sb.append("            SELECT t1.*           \n");                   
		sb.append("            FROM Member t1     \n");                   
		sb.append("            --검색조건               \n");
		//Where-----------------------------------------------------------------------
		if(null !=inVO.getSearchDiv() && null !=inVO.getSearchWord()) {
			sb.append(sbWhere.toString());
		}		
		//----------------------------------------------------------------------------		
		sb.append("             ORDER BY t1.mod_dt DESC                           \n");
		sb.append("         )A                                                    \n");
		//sb.append("         WHERE rownum <=(&PAGE_SIZE*(&PAGE_NUM-1)+&PAGE_SIZE)  \n");  
		sb.append("         WHERE rownum <=(?*(?-1)+?)                            \n");
		sb.append("     )B                                                        \n");
		//sb.append("     WHERE rnum >=(&PAGE_SIZE*(&PAGE_NUM-1)+1) )               \n");
		sb.append("     WHERE rnum >=(?*(?-1)+1) )                                \n");
		sb.append(" CROSS JOIN                                                    \n");
		sb.append(" (                                                             \n");
		sb.append("     SELECT COUNT(*) TOTAL                                     \n");
		sb.append("     FROM Member t1                                        \n");
		sb.append("     --검색조건                                                   \n");

		//sb.append(sbWhere.toString());
		//Where-----------------------------------------------------------------------왜 이런지 모르겠지만 일단 막아둠...
		if(null !=inVO.getSearchDiv() && null !=inVO.getSearchWord()) {
			sb.append(sbWhere.toString());
		}
		//----------------------------------------------------------------------------			
		sb.append(" )                                                            \n"); 
		
		try {
			//1.connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1.connection="+connection);
			
			//2.pstmt
			//2.1.
			LOG.debug("2.query=\n"+sb.toString());
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2.1. pstmt="+pstmt);
			LOG.debug("3. param="+inVO);			
			//검색어 있는 경우
			if(null !=inVO.getSearchDiv() && null !=inVO.getSearchWord() ) {
				//검색어
				//&PAGE_SIZE*(&PAGE_NUM-1)+&PAGE_SIZE
			    //&PAGE_SIZE*(&PAGE_NUM-1)+1)
				
				//1.검색어
				//2.PAGE_SIZE				
				//3.PAGE_NUM
				//4.PAGE_SIZE				
				//5.PAGE_SIZE				
				//6.PAGE_NUM
				//7.검색어
				pstmt.setString(1, inVO.getSearchWord());
				pstmt.setInt(2, inVO.getPageSize());
				pstmt.setInt(3, inVO.getPageNum());
				pstmt.setInt(4, inVO.getPageSize());
				pstmt.setInt(5, inVO.getPageSize());
				pstmt.setInt(6, inVO.getPageNum());
				pstmt.setString(7, inVO.getSearchWord());
				
			//검색어 없을때
			}else {
				//1.PAGE_SIZE				
				//2.PAGE_NUM
				//3.PAGE_SIZE				
				//4.PAGE_SIZE				
				//5.PAGE_NUM				
				pstmt.setInt(1, inVO.getPageSize());
				pstmt.setInt(2, inVO.getPageNum());
				pstmt.setInt(3, inVO.getPageSize());
				pstmt.setInt(4, inVO.getPageSize());
				pstmt.setInt(5, inVO.getPageNum());				
				
			}			
			
			//4.query수행
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO  outVO=new MemberVO();
				
				  outVO.setMemberId(rs.getString("Member_Id"));
				  outVO.setPassword(rs.getString("password"));
				  outVO.setName(rs.getString("name"));
				  outVO.setPhone(rs.getString("phone"));
				  outVO.setEmail(rs.getString("email"));
				  outVO.setAuthor(rs.getString("author"));
				  outVO.setRegId(rs.getString("reg_id"));
				  outVO.setRegDt(rs.getString("reg_dt"));
				  outVO.setModId(rs.getString("mod_id"));
				  outVO.setModDt(rs.getString("mod_dt"));
				  
				outList.add(outVO);
			}
			
		}catch(SQLException e) {
			LOG.debug("==================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("==================");
			e.printStackTrace();
		}finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		return outList;
	}

	
	
	
}

