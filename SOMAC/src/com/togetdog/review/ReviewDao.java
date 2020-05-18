/**
 *
 *
 *
 *<pre>
 * com.hr.board
 * Class Name : BoardDao.java
 * Description : Board CRUD
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.togetdog.cmn.ConnectionMaker;
import com.togetdog.cmn.DTO;
import com.togetdog.cmn.JDBCResClose;
import com.togetdog.cmn.SearchVO;
import com.togetdog.cmn.WorkDiv;
import com.togetdog.community.CommunityVO1;
import com.togetdog.filemng.FileVO;
import com.togetdog.member.MemberVO;

/**
 * @author sist130
 * 
 */
public class ReviewDao extends WorkDiv {
	private final Logger LOG = Logger.getLogger(ReviewDao.class);
	private ConnectionMaker connectionMaker;
	
	//1.CRUD
	//1-1. 등록 : int doInsert(DTO dto)
	//1-2. 수정 : int doUpdate(DTO dto)
	//1-3. 삭제 : int doDelete(DTO dto)
	//1-4. 단건조회 : DTO doSelectOne(DTO dto)
	//1-5. 목록조회 : List<?> doRetrieve(DTO dto)
	
	public ReviewDao() {
		connectionMaker = new ConnectionMaker();
	}
	
	/**
	 * 조회 Count증가
	 */
	public int readCount(DTO dto) {
		int flag = 0;
	     JoinVO inVO = (JoinVO) dto;//param
	    
	     Connection connection   = null;//DB Connection
	     PreparedStatement pstmt = null;//VS Statement
	     try {
	    	 //1. Connection
	    	 connection = connectionMaker.getConnection();
	    	 LOG.debug("1. Connection: "+connection);
	    	 
	    	 
	    	 //2.query
	    	 StringBuilder sb = new StringBuilder();
	      
	    	 sb.append("UPDATE review                     \n");
	    	 sb.append("set count = NVL(count,0) + 1      \n");
	    	 sb.append("where rev_no = ?                    \n");
	    	 
	    	 LOG.debug("2. query: "+sb.toString());
	    	 
	    	 
	    	 //2-1.
	    	 pstmt = connection.prepareStatement(sb.toString());
	    	 LOG.debug("2-1. pstmt: "+pstmt);
	    	 
	    	 //3.param
	    	 LOG.debug("3. param: "+inVO);
	    	 pstmt.setString(1, inVO.getRevNo());
	    	 
	    	 
	    	 //4.query수행
	    	 flag = pstmt.executeUpdate();
	    	 LOG.debug("4. flag: "+flag);
	    	 
	    	 
	     }catch(SQLException e) {
	      LOG.debug("===========================");
	      LOG.debug("=SQLException="+e.getMessage());
	      LOG.debug("===========================");
	      e.printStackTrace();
	      //Rollback
	      //JDBCResClose.rollBack(connection);
	      
	     }finally { //자원반납
	      //사용 역순으로 close
	      JDBCResClose.close(pstmt);//prepareStatement
	      JDBCResClose.close(connection);//connection
	      
	      
	     }
	  return flag;
	}

	
	/**
	 * 
	 *@Method Name:doInsert
	 *@작성일: 2020. 2. 3.
	 *@작성자: sist130
	 *@설명: 게시판 글 등록 -> 등록성공(1), 실패(0)
	 *@param dto
	 *@return int
	 */
	@Override
	public int doInsert(DTO dto) {
		int flag = 0;

		Connection connection = connectionMaker.getConnection(); 
	      
	      // * 3.query 수행을 위한 PreparedStatement
	      StringBuilder sb = new StringBuilder();
	      sb.append("INSERT INTO review (			  \n");
	      sb.append("	rev_no,                       \n");
	      sb.append("    apply_no,                    \n");
	      sb.append("    member_id,                   \n");
	      sb.append("    title,                       \n");
	      sb.append("    contents,                    \n");
	      sb.append("    family_dt,                   \n");
	      sb.append("    reg_dt,                      \n");
	      sb.append("    mod_dt,                      \n");
	      sb.append("    count,                       \n");
	      sb.append("    able_yn                      \n");
	      sb.append(") VALUES (                       \n");
	      sb.append("    'r_'||REVIEW_SEQ.NEXTVAL ,   \n");
	      sb.append("    ?,      	                  \n");
	      sb.append("    ?,                           \n");
	      sb.append("    ?,                           \n");
	      sb.append("    ?,                           \n");
	      sb.append("    '20200204',                  \n");
	      sb.append("    SYSDATE,                     \n");
	      sb.append("    SYSDATE,                     \n");
	      sb.append("    0,                           \n");
	      sb.append("    'Y'                          \n");
	      sb.append(")                                \n");
	      
	      PreparedStatement pstmt =null;
	      ReviewVO inVO = (ReviewVO) dto;
	      
	      try {
	         LOG.debug("2.sql = \n" + sb.toString());
	         
	         LOG.debug("2.1 param = \n" + inVO);
	         
	         pstmt = connection.prepareStatement(sb.toString());
	         
	         LOG.debug("3.pstmt=" + pstmt);
	         
	         // * 4.query실행
	         pstmt.setString(1,inVO.getApplyNo()); //입양코드
	         pstmt.setString(2,inVO.getMemberId()); //회원id
	         pstmt.setString(3,inVO.getTitle()); //제목
	         pstmt.setString(4,inVO.getContents()); //내용
	         //pstmt.setString(5,inVO.getFamilyDt()); //입양날짜
	         
	         
	         //pstmt.setString(4,inVO.getApplyNo()); //입양코드
//	         pstmt.setString(2,inVO.getMemberId()); //회원id
//	         pstmt.setString(3,inVO.getTitle()); //제목
//	         pstmt.setString(4,inVO.getContents()); //내용
//	         pstmt.setString(5,inVO.getFamilyDt()); //입양날짜
	         
	         //4-2. query수행 
	         //	-executeUpdate() : insert, update, delete를 수행하는 함수
	         flag = pstmt.executeUpdate();
	         LOG.debug("4.flag=" + flag);         //flag 1이면 성공 1이 아니면 실패
	         
	      } catch (SQLException e) {
	    	  
	         LOG.debug("===========================");
	         LOG.debug("SQLException=" + e.getMessage());
	         LOG.debug("===========================");
	         e.printStackTrace();
	         
	      } finally {
			 //**위에서 사용된 순서의 역순으로 반납 진행 -> 순서가 의미가 있음!!
			
			JDBCResClose.close(pstmt);//prepareStatement 자원 반납
			JDBCResClose.close(connection);//connection 자원 반납
			
	      }
		return flag;
	}
	
	
	public DTO doFindNo(DTO dto) {
		ReviewVO inVO = (ReviewVO) dto;//param
		FileVO outVO = null;//return값
		
		Connection connection = null;//DB Connection
		PreparedStatement pstmt = null;//VS Statement
		ResultSet rs = null;//결과값 처리 class
		
		try {
			//1. Connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. Connection: "+connection);
			
			
			//2. PreparedStatement
			//2-1. sql
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT rev_no						\n");					
			sb.append("FROM review                          \n");
			sb.append("WHERE reg_dt =(SELECT MAX(reg_dt)   \n");
			sb.append("                FROM review          \n");
			sb.append("                WHERE member_id=?)   \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			LOG.debug("2. pstmt: "+pstmt);
			LOG.debug("2-1. sql \n: "+sb.toString());
			
			
			//3. param
			LOG.debug("3. param: "+inVO);
			pstmt.setString(1, inVO.getMemberId());
			
			
			//4. query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			
			if(rs.next()) {//Moves the cursor forward one row from its current position. 
				
				outVO = new FileVO();//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)
				outVO.setFileNo(rs.getString("rev_no"));
				
				LOG.debug("4. return: "+outVO);
			}
			
		}catch(SQLException e) {
			LOG.debug("=================================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=================================");
			e.printStackTrace();
		}finally {
			//자원반납 - 순서 주의!!
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		return outVO;
	}

	public DTO doGetno(DTO dto) {
		ReviewVO outVO = null;
		ReviewVO inVO = (ReviewVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			//review 테이블을 최근 수정일 순으로 정렬후 열번호 1번(가장 최근 수정된 열)인 데이터 select
			sb.append("select t1.rev_no			\n");
			sb.append("      ,t1.member_id      \n");
			sb.append("      ,t1.title          \n");
			sb.append("      ,t1.contents       \n");
			sb.append("      ,t1.reg_dt         \n");
			sb.append("      ,t1.mod_dt         \n");
			sb.append("      ,t1.count          \n");
			sb.append("      ,t1.able_yn        \n");
			sb.append("from(                    \n");
			sb.append("select *                 \n");
			sb.append("from REVIEW         		\n");
			sb.append("order by mod_dt desc)t1  \n");
			sb.append("where rownum = 1         \n");

			    
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			
			rs = pstmt.executeQuery();
			LOG.debug(rs);
			if(rs.next()) {
				outVO = new ReviewVO();
				outVO.setRevNo(rs.getString("rev_no"));
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
	
	/**
	 * 파일인서트
	 * @param dto
	 * @return
	 */
	public int doInsertFile(DTO dto) {
		int flag = 0;

		Connection connection = connectionMaker.getConnection(); 
	      
	      // * 3.query 수행을 위한 PreparedStatement
	      StringBuilder sb = new StringBuilder();
	      sb.append("INSERT INTO file_mng (	  \n");
	      sb.append("    file_no,             \n");
	      sb.append("    org_nm,              \n");
	      sb.append("    save_nm,             \n");
	      sb.append("    img_path,            \n");
	      sb.append("    file_size,           \n");
	      sb.append("    ext,                 \n");
	      sb.append("    reg_id,              \n");
	      sb.append("    reg_dt               \n");
	      sb.append(") VALUES (               \n");
	      sb.append("    ?, \n");
	      sb.append("    ?,                   \n");
	      sb.append("    'image_'||?,                   \n");
	      sb.append("    '../img/image_/'||?,                   \n");
	      sb.append("    ?,                   \n");
	      sb.append("    ?,                   \n");
	      sb.append("    ?,                   \n");
	      sb.append("    SYSDATE              \n");
	      sb.append(")                        \n");
	      

	      PreparedStatement pstmt =null;
	      FileVO inVO = (FileVO) dto;
	      
	      try {
	         LOG.debug("2.sql = \n" + sb.toString());
	         
	         LOG.debug("2.1 param = \n" + inVO);
	         
	         pstmt = connection.prepareStatement(sb.toString());
	         
	         LOG.debug("3.pstmt=" + pstmt);
	         
	         // * 4.query실행
	         pstmt.setString(1,inVO.getFileNo()); //입양코드
	         pstmt.setString(2,inVO.getOrgNm()); //회원id
	         pstmt.setString(3,inVO.getSaveNm()); //제목
	         pstmt.setString(4,inVO.getImgPath()); //내용
	         pstmt.setInt(5,inVO.getFileSize());
	         pstmt.setString(6,inVO.getExt());
	         pstmt.setString(7,inVO.getRegId());
	         
	         //4-2. query수행 
	         //	-executeUpdate() : insert, update, delete를 수행하는 함수
	         flag = pstmt.executeUpdate();
	         LOG.debug("4.flag=" + flag);         //flag 1이면 성공 1이 아니면 실패
	         
	      } catch (SQLException e) {
	    	  
	         LOG.debug("===========================");
	         LOG.debug("SQLException=" + e.getMessage());
	         LOG.debug("===========================");
	         e.printStackTrace();
	         
	      } finally {
			 //**위에서 사용된 순서의 역순으로 반납 진행 -> 순서가 의미가 있음!!
			
			JDBCResClose.close(pstmt);//prepareStatement 자원 반납
			JDBCResClose.close(connection);//connection 자원 반납
			
	      }
		return flag;
	}
	
	
	/**
	 * 
	 *@Method Name:doUpdate
	 *@작성일: 2020. 2. 4.
	 *@작성자: sist130
	 *@설명: 수정
	 *@param dto
	 *@return
	 */
	@Override
	public int doUpdate (DTO dto) {
		int flag = 0;
		ReviewVO inVO = (ReviewVO) dto;//param
		Connection connection = null;//DB Connection
		PreparedStatement pstmt = null;//VS Statement
		
		try { 
			
			//1. Connection 생성
			//	-connectionMaker클래스의 getConnection메소드를 이용
			connection = connectionMaker.getConnection();
			
			
			LOG.debug("1. connection:"+connection);//확인용, 출력되면 성공
			
			
			//2. PreparedStatement
			//2-1. SQL
			StringBuilder sb = new StringBuilder();//StringBuilder : 수행속도가 빠름
			
			sb.append ("UPDATE review		  \n");
			sb.append ("SET                   \n");
			sb.append ("    title = ?         \n");
			sb.append ("    ,contents = ?     \n");
			sb.append ("    ,family_dt = ?    \n");
			sb.append ("    ,mod_dt = SYSDATE \n");
			sb.append ("WHERE rev_no = ?       \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2. PreparedStatement: "+pstmt);//확인용, 출력되면 성공
			LOG.debug("2-1. query:\n"+sb.toString());//확인용, 출력되면 성공
			
			
			//3. param
			LOG.debug("3. param: "+inVO);
			
			//3-1. param binding(순번매기기)
			//	--입력받은 값(string) 앞에 순서대로 인덱스를 하나씩 붙여주기
			pstmt.setString(1, inVO.getTitle());	
			pstmt.setString(2, inVO.getContents()); 
			pstmt.setString(3, inVO.getFamilyDt());	
			pstmt.setString(4, inVO.getRevNo());
			
			
			//4. query 수행
			flag = pstmt.executeUpdate();//DML발생에 사용
			LOG.debug("4. flag: "+flag);//확인용, flag=1이 출력되면 성공

		}catch(SQLException e) {
			
			LOG.debug("===================================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("===================================");
			e.printStackTrace();
						
		}finally {
			
			JDBCResClose.close(pstmt);//prepareStatement 자원 반납
			JDBCResClose.close(connection);//connection 자원 반납
			
			
		}
		
		
		return flag;
	}
	
	public int doUpdateMem(DTO dto) {
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
	    	 pstmt.setString(5, inVO.getMemberId());
	    	 pstmt.setString(6, inVO.getMemberId());
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
	public int doDelete(DTO dto) {
		int flag = 0;
	      
	      Connection connection = connectionMaker.getConnection();  
	      
	      // * 3.query 수행을 위한 PreparedStatement
	      StringBuilder sb = new StringBuilder();
	      
	      sb.append("UPDATE review	   \n");
	      sb.append("SET able_yn = 'N' \n");
	      sb.append("WHERE rev_no = ?  \n");
	      
	      PreparedStatement pstmt =null;
	      ReviewVO inVO = (ReviewVO) dto;
	      
	      try {
	    	 
	    	  //1. connection
	    	 
	    	 LOG.debug("1.connection" + connection);
	    	 
	    	 //2. sql문 확인
	         LOG.debug("2.sql = \n" + sb.toString());
	         
	         //2-1. param 화인
	         LOG.debug("2.1 param = \n" + inVO);
	         
	         pstmt = connection.prepareStatement(sb.toString());
	         LOG.debug("3.pstmt=" + pstmt);
	         
	         // * 4.query실행
	         //4-1. Bind변수에 값 설정
	         pstmt.setString(1,inVO.getRevNo()); //게시순번
	         
	         //4-2. query수행 : 하나는 select 나머지  (총 2개라 생각하면됨)
	         flag = pstmt.executeUpdate();
	         LOG.debug("4.flag=" + flag);         //flag 1이면 성공 1이 아니면 실패
	         
	      } catch (SQLException e) {
	    	  
	         LOG.debug("===========================");
	         LOG.debug("SQLException=" + e.getMessage());
	         LOG.debug("===========================");
	         e.printStackTrace();
	         
	      } finally {
				
				JDBCResClose.close(pstmt);//prepareStatement 자원 반납
				JDBCResClose.close(connection);//connection 자원 반납
	      }
		return flag;
		
	}
	
	public DTO doFindImgpath(DTO dto) {
		ReviewVO inVO = (ReviewVO) dto;//param
		ReviewVO outVO = null;//return값
		
		Connection connection = null;//DB Connection
		PreparedStatement pstmt = null;//VS Statement
		ResultSet rs = null;//결과값 처리 class
		
		try {
			//1. Connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. Connection: "+connection);
			
			
			//2. PreparedStatement
			//2-1. sql
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT									\n");
			sb.append("    rev_no,                              \n");
			sb.append("    apply_no,                            \n");
			sb.append("    member_id,                           \n");
			sb.append("    title,                               \n");
			sb.append("    contents,                            \n");
			sb.append("    family_dt,                           \n");
			sb.append("    TO_CHAR(reg_dt,'YYYY/MM/DD') reg_dt, \n");
			sb.append("    TO_CHAR(mod_dt,'YYYY/MM/DD') mod_dt, \n");
			sb.append("    count                                \n");
			sb.append("FROM review                              \n");
			sb.append("WHERE rev_no = ?                         \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			LOG.debug("2. pstmt: "+pstmt);
			LOG.debug("2-1. sql \n: "+sb.toString());
			
			
			//3. param
			LOG.debug("3. param: "+inVO);
			pstmt.setString(1, inVO.getRevNo());
			
			
			//4. query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			
			if(rs.next()) {//Moves the cursor forward one row from its current position. 
				
				outVO = new ReviewVO();//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)
				outVO.setRevNo(rs.getString("rev_no"));
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setTitle(rs.getString("title"));
				outVO.setContents(rs.getString("contents"));
				outVO.setFamilyDt(rs.getString("family_dt"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setCount(rs.getInt("count"));
				
				LOG.debug("4. return: "+outVO);
			}
			
		}catch(SQLException e) {
			LOG.debug("=================================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=================================");
			e.printStackTrace();
		}finally {
			//자원반납 - 순서 주의!!
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		return outVO;
	}
	
	public DTO doSelectOneC(DTO dto) {
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
	
	
	/**
	 * 
	 *@Method Name:doSelectOne
	 *@작성일: 2020. 2. 4.
	 *@작성자: sist130
	 *@설명: 단건조회
	 *@param dto
	 *@return BoardVO
	 */
	@Override
	public DTO doSelectOne(DTO dto) {
		JoinVO inVO = (JoinVO) dto;//
		JoinVO outVO = null;//return값
		
		Connection connection = null;//DB Connection
		PreparedStatement pstmt = null;//VS Statement
		ResultSet rs = null;//결과값 처리 class
		
		try {
			//1. Connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. Connection: "+connection);
			
			
			//2. PreparedStatement
			//2-1. sql
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT	t1.rev_no,  						\n");							
			sb.append("		t1.apply_no,                            \n");     
			sb.append("		t1.member_id,                           \n");     
			sb.append("		t1.title,                               \n");     
			sb.append("		t1.contents,                            \n");     
			sb.append("		t1.family_dt,                           \n");     
			sb.append("		TO_CHAR(t1.reg_dt,'YYYY/MM/DD') reg_dt, \n");        
			sb.append("		TO_CHAR(t1.mod_dt,'YYYY/MM/DD') mod_dt, \n");        
			sb.append("		t1.count,                               \n");     
			sb.append("		t2.img_path,                            \n");     
			sb.append("		t2.ext                                  \n");     
			sb.append("FROM review t1, file_mng t2                  \n");
			sb.append("WHERE t1.rev_no = ?                          \n");
			sb.append("AND t1.rev_no=t2.file_no	                    \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			LOG.debug("2. pstmt: "+pstmt);
			LOG.debug("2-1. sql \n: "+sb.toString());
			
			
			//3. param
			LOG.debug("3. param: "+inVO);
			pstmt.setString(1, inVO.getRevNo());
			
			
			//4. query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			
			if(rs.next()) {//Moves the cursor forward one row from its current position. 
				
				outVO = new JoinVO();//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)
				outVO.setRevNo(rs.getString("rev_no"));
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setTitle(rs.getString("title"));
				outVO.setContents(rs.getString("contents"));
				outVO.setFamilyDt(rs.getString("family_dt"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setCount(rs.getInt("count"));
				outVO.setImgPath(rs.getString("img_path"));
				outVO.setExt(rs.getString("ext"));
				
				LOG.debug("4. return: "+outVO);
			}
			
		}catch(SQLException e) {
			LOG.debug("=================================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=================================");
			e.printStackTrace();
		}finally {
			//자원반납 - 순서 주의!!
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		return outVO;
	}
	
	public DTO doSelectOneMem(DTO dto) {
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
			  sb.append("     author,    									\n");
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
				  if(rs.getString("author").equals("9")) {
					  outVO.setAuthor("관리자");
				  } else {
					  outVO.setAuthor("일반");
				  }
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
	
	
	public DTO doSelectOneInsert(DTO dto) {
		AdoptionVO inVO = (AdoptionVO) dto;
		AdoptionVO outVO = null;
		
		Connection connection = null;//DB Connection
		PreparedStatement pstmt = null;//VS Statement
		ResultSet rs = null;//결과값 처리 class
		
		try {
			//1. Connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. Connection: "+connection);
			
			
			//2. PreparedStatement
			//2-1. sql
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT apply_no		\n");
			sb.append("      ,desertion_no  \n");
			sb.append("      ,member_id     \n");
			sb.append("      ,family_cnt    \n");
			sb.append("      ,experience_yn \n");
			sb.append("      ,apply_state   \n");
			sb.append("      ,apply_reason  \n");
			sb.append("      ,reg_dt        \n");
			sb.append("      ,areg_dt       \n");
			sb.append("FROM adoption_apply  \n");
			sb.append("WHERE apply_no = ?   \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			LOG.debug("2. pstmt: "+pstmt);
			LOG.debug("2-1. sql \n: "+sb.toString());
			
			
			//3. param
			LOG.debug("3. param: "+inVO);
			pstmt.setString(1, inVO.getApplyNo());
			
			
			//4. query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			
			if(rs.next()) {//Moves the cursor forward one row from its current position. 
				
				outVO = new AdoptionVO();//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setFamilyCnt(rs.getString("family_cnt"));
				outVO.setExperienceYn(rs.getString("experience_yn"));
				outVO.setApplyState(rs.getString("apply_state"));
				outVO.setApplyReason(rs.getString("apply_reason"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAregDt(rs.getString("areg_dt"));
				
				LOG.debug("4. return: "+outVO);
			}
			
		}catch(SQLException e) {
			LOG.debug("=================================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=================================");
			e.printStackTrace();
		}finally {
			//자원반납 - 순서 주의!!
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		return outVO;
	}
	
	public DTO getAdoptImg(DTO dto) {
		AdoptImgVO inVO = (AdoptImgVO) dto;
		AdoptImgVO outVO = null;
		
		Connection connection = null;//DB Connection
		PreparedStatement pstmt = null;//VS Statement
		ResultSet rs = null;//결과값 처리 class
		
		try {
			//1. Connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. Connection: "+connection);
			
			
			//2. PreparedStatement
			//2-1. sql
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT t1.desertion_no,						\n");
			sb.append("       t1.member_id,                         \n");
			sb.append("       t2.pop_file                           \n");
			sb.append("FROM adoption_apply t1, desertion_info t2    \n");
			sb.append("WHERE t1.member_id = ?                       \n");
			sb.append("AND t1.desertion_no = t2.desertion_no        \n");
			
			pstmt = connection.prepareStatement(sb.toString());
			
			LOG.debug("2. pstmt: "+pstmt);
			LOG.debug("2-1. sql \n: "+sb.toString());
			
			
			//3. param
			LOG.debug("3. param: "+inVO);
			pstmt.setString(1, inVO.getMemberId());
			
			
			//4. query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			
			if(rs.next()) {//Moves the cursor forward one row from its current position. 
				
				outVO = new AdoptImgVO();//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)
				
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setPopFile(rs.getString("pop_file"));
				
				LOG.debug("4. return: "+outVO);
			}
			
		}catch(SQLException e) {
			LOG.debug("=================================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=================================");
			e.printStackTrace();
		}finally {
			//자원반납 - 순서 주의!!
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		
		return outVO;
	}
	
	
	//입양 신청 내역 확인
	@Override
	public List<?> doRetrieve(DTO dto) {
		
		AdoptionVO inVO = (AdoptionVO) dto;
		List<AdoptionVO> outList = new ArrayList<AdoptionVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder(); //검색query에 사용할 변수
		
		
		//main query
		sb.append("SELECT *												\n");	
		sb.append("FROM(                                                \n");
		sb.append("	SELECT B.apply_no,                                  \n");
		sb.append("        B.desertion_no,                              \n");
		sb.append("        B.rnum num,                                  \n");
		sb.append("        B.member_id,                                 \n");
		sb.append("        B.family_cnt,                                \n");
		sb.append("        B.experience_yn,                             \n");
		sb.append("        B.apply_reason,                              \n");
		sb.append("		TO_CHAR(B.reg_dt,'YYYY/MM/DD') reg_dt,          \n");
		sb.append("		TO_CHAR(B.areg_dt,'YYYY/MM/DD') areg_dt,        \n");
		sb.append("		B.apply_state,                                  \n");
		sb.append("		B.pop_file                                      \n");
		sb.append("	FROM(                                               \n");
		sb.append("		SELECT ROWNUM as rnum, A.*                      \n");
		sb.append("		FROM(                                           \n");
		sb.append("			SELECT t1.*, t2.pop_file pop_file           \n");                
		sb.append("			FROM adoption_apply t1, desertion_info t2   \n");             
		sb.append("			WHERE t1.member_id = ?                      \n");
		sb.append("			AND t1.desertion_no = t2.desertion_no       \n");
		sb.append("			ORDER BY t1.reg_dt DESC                     \n");
		sb.append("		)A                                              \n");
		sb.append("		WHERE rownum <=(?*(?-1)+?)    				    \n");
		sb.append("	)B                                                  \n");
		sb.append("	WHERE rnum >= (?*(?-1)+1)                 		    \n");
		sb.append(") CROSS JOIN(                                        \n");
		sb.append("			SELECT COUNT(*) total                       \n");
		sb.append("			FROM adoption_apply t1, desertion_info t2   \n");                  
		sb.append("			WHERE t1.member_id = ?                      \n");
		sb.append("			AND t1.desertion_no = t2.desertion_no       \n");
		sb.append(")                                                    \n");

		
		try {
			//1. connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. connection= "+connection);
			
			
			//2. query 입력된 쿼리문들 확인
			//2-1. 쿼리문이 pstmt객체에 입력됐는지 확인
			LOG.debug("2. query= \n"+sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2-1. pstmt= "+pstmt);
			
			
			//3.param확인
			LOG.debug("3. param= "+inVO);
			
			//1.검색어
			pstmt.setString(1, inVO.getMemberId());
			//2.PAGE_SIZE
			pstmt.setInt(2, inVO.getPageSize());
			//3.PAGE_NUM
			pstmt.setInt(3, inVO.getPageNum());
			//4.PAGE_SIZE
			pstmt.setInt(4, inVO.getPageSize());
			//5.PAGE_SIZE
			pstmt.setInt(5, inVO.getPageSize());
			//6.PAGE_NUM
			pstmt.setInt(6, inVO.getPageNum());
			//7.검색어
			pstmt.setString(7, inVO.getMemberId());
			
			
			//4.query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			LOG.debug("/////////////////////////");
			
			//	-다건이므로 쿼리가 수행되면 rs에 여러 건의 데이터가 들어옴, rs에 있는 데이터가 outVO에 다 담길때 까지 while문 돌리기
			while(rs.next()) {
				
				//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)
				AdoptionVO outVO = new AdoptionVO();
				
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setFamilyCnt(rs.getString("family_cnt"));
				outVO.setExperienceYn(rs.getString("experience_yn"));
				outVO.setApplyReason(rs.getString("apply_reason"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAregDt(rs.getString("areg_dt"));
				outVO.setPopFile(rs.getString("pop_file"));
				if(rs.getString("apply_state").equals("P")) {
					outVO.setApplyState("승인심사 진행중");
				} else if(rs.getString("apply_state").equals("R")) {
					outVO.setApplyState("입양 불가");
				} else {
					outVO.setApplyState("입양 가능");
				}
				
				
				outVO.setNum(rs.getInt("num"));
				outVO.setTotal(rs.getInt("total"));
				
				
				outList.add(outVO);
				
				//rs에 담긴 쿼리가 수행된 데이터들을 한줄씩 outVO(반환하는 BoardVO타입의 변수)에 set해주고, 
				//outVO를 List타입인 outList에 더해서 List형식으로 return
			}
			LOG.debug("4. return: "+outList);
		
			
			
		} catch(SQLException e) {
			LOG.debug("=========================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=========================");
			e.printStackTrace();
			
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		return outList;
	}
	
	
	/**
	 * 입양심사중 리스트
	 * @param dto
	 * @return
	 */
	public List<?> doRetrieveP(DTO dto) {
		
		AdoptionVO inVO = (AdoptionVO) dto;
		List<AdoptionVO> outList = new ArrayList<AdoptionVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder(); //검색query에 사용할 변수
		
		
		//main query
		sb.append("SELECT *												\n");	
		sb.append("FROM(                                                \n");
		sb.append("	SELECT B.apply_no,                                  \n");
		sb.append("        B.desertion_no,                              \n");
		sb.append("        B.rnum num,                                  \n");
		sb.append("        B.member_id,                                 \n");
		sb.append("        B.family_cnt,                                \n");
		sb.append("        B.experience_yn,                             \n");
		sb.append("        B.apply_reason,                              \n");
		sb.append("		TO_CHAR(B.reg_dt,'YYYY/MM/DD') reg_dt,          \n");
		sb.append("		TO_CHAR(B.areg_dt,'YYYY/MM/DD') areg_dt,        \n");
		sb.append("		B.apply_state,                                  \n");
		sb.append("		B.pop_file                                      \n");
		sb.append("	FROM(                                               \n");
		sb.append("		SELECT ROWNUM as rnum, A.*                      \n");
		sb.append("		FROM(                                           \n");
		sb.append("			SELECT t1.*, t2.pop_file pop_file           \n");                
		sb.append("			FROM adoption_apply t1, desertion_info t2   \n");             
		sb.append("			WHERE t1.member_id = ?                      \n");
		sb.append("			AND t1.desertion_no = t2.desertion_no       \n");
		sb.append("			ORDER BY t1.reg_dt DESC                     \n");
		sb.append("		)A                                              \n");
		sb.append("		WHERE rownum <=(?*(?-1)+?)    				    \n");
		sb.append("	)B                                                  \n");
		sb.append("	WHERE B.apply_state = 'P'                 		    \n");
		sb.append("	AND rnum >= (?*(?-1)+1)                 		    \n");
		sb.append(") CROSS JOIN(                                        \n");
		sb.append("			SELECT COUNT(*) total                       \n");
		sb.append("			FROM adoption_apply t1, desertion_info t2   \n");                  
		sb.append("			WHERE t1.member_id = ?                      \n");
		sb.append("			AND t1.desertion_no = t2.desertion_no       \n");
		sb.append(")                                                    \n");

		
		try {
			//1. connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. connection= "+connection);
			
			
			//2. query 입력된 쿼리문들 확인
			//2-1. 쿼리문이 pstmt객체에 입력됐는지 확인
			LOG.debug("2. query= \n"+sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2-1. pstmt= "+pstmt);
			
			
			//3.param확인
			LOG.debug("3. param= "+inVO);
			
			//1.검색어
			pstmt.setString(1, inVO.getMemberId());
			//2.PAGE_SIZE
			pstmt.setInt(2, inVO.getPageSize());
			//3.PAGE_NUM
			pstmt.setInt(3, inVO.getPageNum());
			//4.PAGE_SIZE
			pstmt.setInt(4, inVO.getPageSize());
			//5.PAGE_SIZE
			pstmt.setInt(5, inVO.getPageSize());
			//6.PAGE_NUM
			pstmt.setInt(6, inVO.getPageNum());
			//7.검색어
			pstmt.setString(7, inVO.getMemberId());
			
			
			//4.query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			LOG.debug("/////////////////////////");
			
			//	-다건이므로 쿼리가 수행되면 rs에 여러 건의 데이터가 들어옴, rs에 있는 데이터가 outVO에 다 담길때 까지 while문 돌리기
			while(rs.next()) {
				
				//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)
				AdoptionVO outVO = new AdoptionVO();
				
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setFamilyCnt(rs.getString("family_cnt"));
				outVO.setExperienceYn(rs.getString("experience_yn"));
				outVO.setApplyReason(rs.getString("apply_reason"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAregDt(rs.getString("areg_dt"));
				outVO.setApplyState("승인심사 진행중");
				outVO.setPopFile(rs.getString("pop_file"));
				
				outVO.setNum(rs.getInt("num"));
				outVO.setTotal(rs.getInt("total"));
				
				
				outList.add(outVO);
				
				//rs에 담긴 쿼리가 수행된 데이터들을 한줄씩 outVO(반환하는 BoardVO타입의 변수)에 set해주고, 
				//outVO를 List타입인 outList에 더해서 List형식으로 return
			}
			LOG.debug("4. return: "+outList);
		
			
			
		} catch(SQLException e) {
			LOG.debug("=========================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=========================");
			e.printStackTrace();
			
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		return outList;
	}

	
	/**
	 * 입양가능 리스트
	 * @param dto
	 * @return
	 */
	public List<?> doRetrieveS(DTO dto) {
		
		AdoptionVO inVO = (AdoptionVO) dto;
		List<AdoptionVO> outList = new ArrayList<AdoptionVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder(); //검색query에 사용할 변수
		
		
		//main query
		sb.append("SELECT *												\n");	
		sb.append("FROM(                                                \n");
		sb.append("	SELECT B.apply_no,                                  \n");
		sb.append("        B.desertion_no,                              \n");
		sb.append("        B.rnum num,                                  \n");
		sb.append("        B.member_id,                                 \n");
		sb.append("        B.family_cnt,                                \n");
		sb.append("        B.experience_yn,                             \n");
		sb.append("        B.apply_reason,                              \n");
		sb.append("		TO_CHAR(B.reg_dt,'YYYY/MM/DD') reg_dt,          \n");
		sb.append("		TO_CHAR(B.areg_dt,'YYYY/MM/DD') areg_dt,        \n");
		sb.append("		B.apply_state,                                  \n");
		sb.append("		B.pop_file                                      \n");
		sb.append("	FROM(                                               \n");
		sb.append("		SELECT ROWNUM as rnum, A.*                      \n");
		sb.append("		FROM(                                           \n");
		sb.append("			SELECT t1.*, t2.pop_file pop_file           \n");                
		sb.append("			FROM adoption_apply t1, desertion_info t2   \n");             
		sb.append("			WHERE t1.member_id = ?                      \n");
		sb.append("			AND t1.desertion_no = t2.desertion_no       \n");
		sb.append("			ORDER BY t1.reg_dt DESC                     \n");
		sb.append("		)A                                              \n");
		sb.append("		WHERE rownum <=(?*(?-1)+?)    				    \n");
		sb.append("	)B                                                  \n");
		sb.append("	WHERE B.apply_state = 'S'                 		    \n");
		sb.append("	AND rnum >= (?*(?-1)+1)                 		    \n");
		sb.append(") CROSS JOIN(                                        \n");
		sb.append("			SELECT COUNT(*) total                       \n");
		sb.append("			FROM adoption_apply t1, desertion_info t2   \n");                  
		sb.append("			WHERE t1.member_id = ?                      \n");
		sb.append("			AND t1.desertion_no = t2.desertion_no       \n");
		sb.append(")                                                    \n");

		
		try {
			//1. connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. connection= "+connection);
			
			
			//2. query 입력된 쿼리문들 확인
			//2-1. 쿼리문이 pstmt객체에 입력됐는지 확인
			LOG.debug("2. query= \n"+sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2-1. pstmt= "+pstmt);
			
			
			//3.param확인
			LOG.debug("3. param= "+inVO);
			
			//1.검색어
			pstmt.setString(1, inVO.getMemberId());
			//2.PAGE_SIZE
			pstmt.setInt(2, inVO.getPageSize());
			//3.PAGE_NUM
			pstmt.setInt(3, inVO.getPageNum());
			//4.PAGE_SIZE
			pstmt.setInt(4, inVO.getPageSize());
			//5.PAGE_SIZE
			pstmt.setInt(5, inVO.getPageSize());
			//6.PAGE_NUM
			pstmt.setInt(6, inVO.getPageNum());
			//7.검색어
			pstmt.setString(7, inVO.getMemberId());
			
			
			//4.query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			LOG.debug("/////////////////////////");
			
			//	-다건이므로 쿼리가 수행되면 rs에 여러 건의 데이터가 들어옴, rs에 있는 데이터가 outVO에 다 담길때 까지 while문 돌리기
			while(rs.next()) {
				
				//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)
				AdoptionVO outVO = new AdoptionVO();
				
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setFamilyCnt(rs.getString("family_cnt"));
				outVO.setExperienceYn(rs.getString("experience_yn"));
				outVO.setApplyReason(rs.getString("apply_reason"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAregDt(rs.getString("areg_dt"));
				outVO.setApplyState("입양 가능");
				outVO.setPopFile(rs.getString("pop_file"));
				
				outVO.setNum(rs.getInt("num"));
				outVO.setTotal(rs.getInt("total"));
				
				
				outList.add(outVO);
				
				//rs에 담긴 쿼리가 수행된 데이터들을 한줄씩 outVO(반환하는 BoardVO타입의 변수)에 set해주고, 
				//outVO를 List타입인 outList에 더해서 List형식으로 return
			}
			LOG.debug("4. return: "+outList);
		
			
			
		} catch(SQLException e) {
			LOG.debug("=========================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=========================");
			e.printStackTrace();
			
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		return outList;
	}
	
	
	/**
	 * 입양불가 리스트
	 * @param dto
	 * @return
	 */
	public List<?> doRetrieveR(DTO dto) {
		
		AdoptionVO inVO = (AdoptionVO) dto;
		List<AdoptionVO> outList = new ArrayList<AdoptionVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder(); //검색query에 사용할 변수
		
		
		//main query
		sb.append("SELECT *												\n");	
		sb.append("FROM(                                                \n");
		sb.append("	SELECT B.apply_no,                                  \n");
		sb.append("        B.desertion_no,                              \n");
		sb.append("        B.rnum num,                                  \n");
		sb.append("        B.member_id,                                 \n");
		sb.append("        B.family_cnt,                                \n");
		sb.append("        B.experience_yn,                             \n");
		sb.append("        B.apply_reason,                              \n");
		sb.append("		TO_CHAR(B.reg_dt,'YYYY/MM/DD') reg_dt,          \n");
		sb.append("		TO_CHAR(B.areg_dt,'YYYY/MM/DD') areg_dt,        \n");
		sb.append("		B.apply_state,                                  \n");
		sb.append("		B.pop_file                                      \n");
		sb.append("	FROM(                                               \n");
		sb.append("		SELECT ROWNUM as rnum, A.*                      \n");
		sb.append("		FROM(                                           \n");
		sb.append("			SELECT t1.*, t2.pop_file pop_file           \n");                
		sb.append("			FROM adoption_apply t1, desertion_info t2   \n");             
		sb.append("			WHERE t1.member_id = ?                      \n");
		sb.append("			AND t1.desertion_no = t2.desertion_no       \n");
		sb.append("			ORDER BY t1.reg_dt DESC                     \n");
		sb.append("		)A                                              \n");
		sb.append("		WHERE rownum <=(?*(?-1)+?)    				    \n");
		sb.append("	)B                                                  \n");
		sb.append("	WHERE B.apply_state = 'R'                 		    \n");
		sb.append("	AND rnum >= (?*(?-1)+1)                 		    \n");
		sb.append(") CROSS JOIN(                                        \n");
		sb.append("			SELECT COUNT(*) total                       \n");
		sb.append("			FROM adoption_apply t1, desertion_info t2   \n");                  
		sb.append("			WHERE t1.member_id = ?                      \n");
		sb.append("			AND t1.desertion_no = t2.desertion_no       \n");
		sb.append(")                                                    \n");
		
		try {
			//1. connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. connection= "+connection);
			
			
			//2. query 입력된 쿼리문들 확인
			//2-1. 쿼리문이 pstmt객체에 입력됐는지 확인
			LOG.debug("2. query= \n"+sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2-1. pstmt= "+pstmt);
			
			
			//3.param확인
			LOG.debug("3. param= "+inVO);
			
			//1.검색어
			pstmt.setString(1, inVO.getMemberId());
			//2.PAGE_SIZE
			pstmt.setInt(2, inVO.getPageSize());
			//3.PAGE_NUM
			pstmt.setInt(3, inVO.getPageNum());
			//4.PAGE_SIZE
			pstmt.setInt(4, inVO.getPageSize());
			//5.PAGE_SIZE
			pstmt.setInt(5, inVO.getPageSize());
			//6.PAGE_NUM
			pstmt.setInt(6, inVO.getPageNum());
			//7.검색어
			pstmt.setString(7, inVO.getMemberId());
			
			
			//4.query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			LOG.debug("/////////////////////////");
			
			//	-다건이므로 쿼리가 수행되면 rs에 여러 건의 데이터가 들어옴, rs에 있는 데이터가 outVO에 다 담길때 까지 while문 돌리기
			while(rs.next()) {
				
				//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)
				AdoptionVO outVO = new AdoptionVO();
				
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setDesertionNo(rs.getString("desertion_no"));
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setFamilyCnt(rs.getString("family_cnt"));
				outVO.setExperienceYn(rs.getString("experience_yn"));
				outVO.setApplyReason(rs.getString("apply_reason"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setAregDt(rs.getString("areg_dt"));
				outVO.setApplyState("입양 불가");
				outVO.setPopFile(rs.getString("pop_file"));
				
				outVO.setNum(rs.getInt("num"));
				outVO.setTotal(rs.getInt("total"));
				
				
				outList.add(outVO);
				
				//rs에 담긴 쿼리가 수행된 데이터들을 한줄씩 outVO(반환하는 BoardVO타입의 변수)에 set해주고, 
				//outVO를 List타입인 outList에 더해서 List형식으로 return
			}
			LOG.debug("4. return: "+outList);
		
			
			
		} catch(SQLException e) {
			LOG.debug("=========================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=========================");
			e.printStackTrace();
			
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		return outList;
	}
	
	public List<?> doRetrieveRev(DTO dto) {
		
		JoinVO inVO = (JoinVO) dto;
		List<JoinVO> outList = new ArrayList<JoinVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder(); //검색query에 사용할 변수
		
		
		//main query
		sb.append("SELECT *														\n");				
		sb.append("FROM(                                                        \n"); 
		sb.append("	SELECT B.rev_no,                                            \n"); 
		sb.append("        B.apply_no,                                          \n"); 
		sb.append("		B.rnum num,                                             \n"); 
		sb.append("		B.member_id,                                            \n"); 
		sb.append("		B.title,                                                \n"); 
		sb.append("        B.contents ,                                         \n"); 
		sb.append("		DECODE(to_char(B.reg_dt,'YYYY/MM/DD')                   \n"); 
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                \n"); 
		sb.append("				  ,to_char(B.reg_dt,'HH24:MI')                  \n"); 
		sb.append("				  ,to_char(B.reg_dt,'YYYY/MM/DD HH24:MI:SS')    \n"); 
		sb.append("                  ) reg_dt  ,                                \n"); 
		sb.append("		      DECODE(to_char(B.mod_dt,'YYYY/MM/DD')             \n"); 
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                \n"); 
		sb.append("				  ,to_char(B.mod_dt,'HH24:MI')                  \n"); 
		sb.append("				  ,to_char(B.mod_dt,'YYYY/MM/DD HH24:MI:SS')    \n"); 
		sb.append("                  ) mod_dt  ,                                \n"); 
		sb.append("		B.count,		                                        \n"); 
		sb.append("		B.able_yn,                                              \n"); 
		sb.append("		B.img_path,                                 	  	    \n"); 
		sb.append("		B.ext                                                   \n"); 
		sb.append("	FROM(                                                       \n"); 
		sb.append("		SELECT ROWNUM as rnum, A.*                              \n"); 
		sb.append("		FROM(                                                   \n"); 
		sb.append("			SELECT t1.*, t2.img_path img_path, t2.ext ext  		\n");	
		sb.append("			FROM REVIEW t1, FILE_MNG t2                         \n"); 
		sb.append("			WHERE t1.member_id = ?                              \n");
		sb.append("			AND t1.rev_no = t2.file_no                          \n");
		sb.append("            AND t1.able_yn = 'Y'                             \n");
		sb.append("			ORDER BY t1.reg_dt DESC                             \n"); 
		sb.append("		)A                                                      \n"); 
		sb.append("		WHERE rownum <=(?*(?-1)+?)            					\n");	
		sb.append("	)B                                                          \n"); 
		sb.append("	WHERE rnum >= (?*(?-1)+1)                        			\n");	
		sb.append(") CROSS JOIN(                                                \n"); 
		sb.append("			SELECT COUNT(*) total                               \n"); 
		sb.append("			FROM REVIEW t1, FILE_MNG t2                         \n"); 
		sb.append("			WHERE t1.member_id = ?                              \n"); 
		sb.append("			AND t1.rev_no = t2.file_no                          \n");
		sb.append("            AND t1.able_yn = 'Y'                             \n");
		sb.append(")                                                            \n"); 
		
		try {
			//1. connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. connection= "+connection);
			
			
			//2. query 입력된 쿼리문들 확인
			//2-1. 쿼리문이 pstmt객체에 입력됐는지 확인
			LOG.debug("2. query= \n"+sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2-1. pstmt= "+pstmt);
			
			
			//3.param확인
			LOG.debug("3. param= "+inVO);
			
			//1.검색어
			pstmt.setString(1, inVO.getMemberId());
			//2.PAGE_SIZE
			pstmt.setInt(2, inVO.getPageSize());
			//3.PAGE_NUM
			pstmt.setInt(3, inVO.getPageNum());
			//4.PAGE_SIZE
			pstmt.setInt(4, inVO.getPageSize());
			//5.PAGE_SIZE
			pstmt.setInt(5, inVO.getPageSize());
			//6.PAGE_NUM
			pstmt.setInt(6, inVO.getPageNum());
			//7.검색어
			pstmt.setString(7, inVO.getMemberId());
			
			//4.query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			LOG.debug("/////////////////////////");
			
			//	-다건이므로 쿼리가 수행되면 rs에 여러 건의 데이터가 들어옴, rs에 있는 데이터가 outVO에 다 담길때 까지 while문 돌리기
			while(rs.next()) {
				
				//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)
				JoinVO outVO = new JoinVO();
				
				outVO.setRevNo(rs.getString("rev_no"));
				outVO.setApplyNo(rs.getString("apply_no"));
				outVO.setMemberId(rs.getString("member_id"));				
				outVO.setTitle(rs.getString("title"));
				outVO.setContents(rs.getString("contents"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setCount(rs.getInt("count"));
				outVO.setAbleYn(rs.getString("able_yn"));
				outVO.setImgPath(rs.getString("img_path"));
				outVO.setExt(rs.getString("ext"));

				
				outVO.setNum(rs.getInt("num"));
				outVO.setTotal(rs.getInt("total"));
				
				
				outList.add(outVO);
				
				//rs에 담긴 쿼리가 수행된 데이터들을 한줄씩 outVO(반환하는 BoardVO타입의 변수)에 set해주고, 
				//outVO를 List타입인 outList에 더해서 List형식으로 return
			}
			LOG.debug("4. return: "+outList);
		
			
			
		} catch(SQLException e) {
			LOG.debug("=========================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=========================");
			e.printStackTrace();
			
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		return outList;
	}
	
	
	public List<?> doRetrieveCom(DTO dto) {
		
		CommuVO inVO = (CommuVO) dto;
		List<CommuVO> outList = new ArrayList<CommuVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder(); //검색query에 사용할 변수
		
		
		//main query
		sb.append("SELECT *														\n");
		sb.append("FROM(                                                        \n");
		sb.append("	SELECT B.tot_no,                                            \n");
		sb.append("     B.rnum num,                                             \n");
		sb.append("		B.member_id,                                            \n");
		sb.append("		B.title,                                                \n");
		sb.append("		DECODE(to_char(B.reg_dt,'YYYY/MM/DD')                   \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                \n");
		sb.append("				  ,to_char(B.reg_dt,'HH24:MI')                  \n");
		sb.append("				  ,to_char(B.reg_dt,'YYYY/MM/DD HH24:MI:SS')    \n");
		sb.append("                  ) reg_dt  ,                                \n");
		sb.append("		DECODE(to_char(B.mod_dt,'YYYY/MM/DD')                   \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                \n");
		sb.append("				  ,to_char(B.mod_dt,'HH24:MI')                  \n");
		sb.append("				  ,to_char(B.mod_dt,'YYYY/MM/DD HH24:MI:SS')    \n");
		sb.append("                  ) mod_dt,                                  \n");
		sb.append("		B.count,                                                \n");
		sb.append("		B.contents,                                             \n");
		sb.append("		B.able_yn,                                              \n");
		sb.append("		B.img_path,                                 	  	    \n");
		sb.append("		B.ext                                                   \n");
		sb.append("	FROM(                                                       \n");
		sb.append("		SELECT ROWNUM as rnum, A.*                              \n");
		sb.append("		FROM(                                                   \n");
		sb.append("			SELECT t1.*, t2.img_path img_path, t2.ext ext       \n");                  
		sb.append("			FROM TOTAL_BOARD t1, FILE_MNG t2                    \n");
		sb.append("			WHERE t1.member_id = ?                              \n");
		sb.append("			AND t1.tot_no = t2.file_no			                \n");
		sb.append("            AND t1.able_yn = 'Y'                             \n");
		sb.append("			ORDER BY t1.reg_dt DESC                             \n");
		sb.append("		)A                                                      \n");
		sb.append("		WHERE rownum <=(?*(?-1)+?)    			                \n");
		sb.append("	)B                                                          \n");
		sb.append("	WHERE rnum >= (?*(?-1)+1)               	                \n");
		sb.append(") CROSS JOIN(                                                \n");
		sb.append("			SELECT COUNT(*) total                               \n");
		sb.append("			FROM TOTAL_BOARD t1, FILE_MNG t2                    \n");
		sb.append("			WHERE t1.member_id = ?                              \n");
		sb.append("			AND t1.tot_no = t2.file_no                          \n");
		sb.append("            AND t1.able_yn = 'Y'                             \n");
		sb.append(")                                                            \n");
		
		try {
			//1. connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. connection= "+connection);
			
			
			//2. query 입력된 쿼리문들 확인
			//2-1. 쿼리문이 pstmt객체에 입력됐는지 확인
			LOG.debug("2. query= \n"+sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2-1. pstmt= "+pstmt);
			
			
			//3.param확인
			LOG.debug("3. param= "+inVO);
			
			//1.검색어
			pstmt.setString(1, inVO.getMemberId());
			//2.PAGE_SIZE
			pstmt.setInt(2, inVO.getPageSize());
			//3.PAGE_NUM
			pstmt.setInt(3, inVO.getPageNum());
			//4.PAGE_SIZE
			pstmt.setInt(4, inVO.getPageSize());
			//5.PAGE_SIZE
			pstmt.setInt(5, inVO.getPageSize());
			//6.PAGE_NUM
			pstmt.setInt(6, inVO.getPageNum());
			//7.검색어
			pstmt.setString(7, inVO.getMemberId());
			
			//4.query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			LOG.debug("/////////////////////////");
			
			//	-다건이므로 쿼리가 수행되면 rs에 여러 건의 데이터가 들어옴, rs에 있는 데이터가 outVO에 다 담길때 까지 while문 돌리기
			while(rs.next()) {
				
				//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)
				CommuVO outVO = new CommuVO();
				
				outVO.setTotNo(rs.getString("tot_no"));
				outVO.setMemberId(rs.getString("member_id"));				
				outVO.setTitle(rs.getString("title"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setCount(rs.getInt("count"));
				outVO.setContents(rs.getString("contents"));
				outVO.setAbleYn(rs.getString("able_yn"));
				outVO.setImgPath(rs.getString("img_path"));
				outVO.setExt(rs.getString("ext"));

				
				outVO.setNum(rs.getInt("num"));
				outVO.setTotal(rs.getInt("total"));
				
				
				outList.add(outVO);
				
				//rs에 담긴 쿼리가 수행된 데이터들을 한줄씩 outVO(반환하는 BoardVO타입의 변수)에 set해주고, 
				//outVO를 List타입인 outList에 더해서 List형식으로 return
			}
			LOG.debug("4. return: "+outList);
		
			
			
		} catch(SQLException e) {
			LOG.debug("=========================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=========================");
			e.printStackTrace();
			
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		return outList;
	}
	
	
	public List<?> doRetrieveQnA(DTO dto) {
		
		QnAVO inVO = (QnAVO) dto;
		List<QnAVO> outList = new ArrayList<QnAVO>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder(); //검색query에 사용할 변수
		
		
		//main query
		sb.append("SELECT *														\n");
		sb.append("FROM(                                                        \n");
		sb.append("	SELECT B.qna_no,                                            \n");
		sb.append("     B.rnum num,                                             \n");
		sb.append("		B.member_id,                                            \n");
		sb.append("		B.title,                                                \n");
		sb.append("		DECODE(to_char(B.reg_dt,'YYYY/MM/DD')                   \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                \n");
		sb.append("				  ,to_char(B.reg_dt,'HH24:MI')                  \n");
		sb.append("				  ,to_char(B.reg_dt,'YYYY/MM/DD HH24:MI:SS')    \n");
		sb.append("                  ) reg_dt  ,                                \n");
		sb.append("		DECODE(to_char(B.mod_dt,'YYYY/MM/DD')                   \n");
		sb.append("				  ,to_char(SYSDATE,'YYYY/MM/DD')                \n");
		sb.append("				  ,to_char(B.mod_dt,'HH24:MI')                  \n");
		sb.append("				  ,to_char(B.mod_dt,'YYYY/MM/DD HH24:MI:SS')    \n");
		sb.append("                  ) mod_dt  ,                                \n");
		sb.append("		B.count,                                                \n");
		sb.append("		B.contents,                                             \n");
		sb.append("		B.able_yn,                                              \n");
		sb.append("		B.img_path,                                 	  	    \n");
		sb.append("		B.ext		                                            \n");
		sb.append("	FROM(                                                       \n");
		sb.append("		SELECT ROWNUM as rnum, A.*                              \n");
		sb.append("		FROM(                                                   \n");
		sb.append("			SELECT t1.*, t2.img_path img_path, t2.ext ext       \n");                        
		sb.append("			FROM QNA_BOARD t1, FILE_MNG t2                      \n");   
		sb.append("			WHERE t1.member_id = ?	                            \n");
		sb.append("			AND t1.qna_no = t2.file_no 			                \n");
		sb.append("            AND t1.able_yn = 'Y'                             \n");
		sb.append("			ORDER BY t1.reg_dt DESC                             \n");
		sb.append("		)A                                                      \n");
		sb.append("		WHERE rownum <=(?*(?-1)+?)   					        \n");
		sb.append("	)B                                                          \n");
		sb.append("	WHERE rnum >= (?*(?-1)+1)                 			        \n");
		sb.append(") CROSS JOIN(                                                \n");
		sb.append("			SELECT COUNT(*) total                               \n");
		sb.append("			FROM QNA_BOARD t1, FILE_MNG t2                      \n");   
		sb.append("			WHERE t1.member_id = ?                              \n");
		sb.append("			AND t1.qna_no = t2.file_no			                \n");
		sb.append("            AND t1.able_yn = 'Y'                             \n");
		sb.append(")                                                            \n");
		
		try {
			//1. connection
			connection = this.connectionMaker.getConnection();
			LOG.debug("1. connection= "+connection);
			
			
			//2. query 입력된 쿼리문들 확인
			//2-1. 쿼리문이 pstmt객체에 입력됐는지 확인
			LOG.debug("2. query= \n"+sb.toString());
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("2-1. pstmt= "+pstmt);
			
			
			//3.param확인
			LOG.debug("3. param= "+inVO);
			
			//1.검색어
			pstmt.setString(1, inVO.getMemberId());
			//2.PAGE_SIZE
			pstmt.setInt(2, inVO.getPageSize());
			//3.PAGE_NUM
			pstmt.setInt(3, inVO.getPageNum());
			//4.PAGE_SIZE
			pstmt.setInt(4, inVO.getPageSize());
			//5.PAGE_SIZE
			pstmt.setInt(5, inVO.getPageSize());
			//6.PAGE_NUM
			pstmt.setInt(6, inVO.getPageNum());
			//7.검색어
			pstmt.setString(7, inVO.getMemberId());
			
			//4.query수행
			rs = pstmt.executeQuery();//쿼리 수행해서 나온 값을 rs에 담아줌
			LOG.debug("/////////////////////////");
			
			//	-다건이므로 쿼리가 수행되면 rs에 여러 건의 데이터가 들어옴, rs에 있는 데이터가 outVO에 다 담길때 까지 while문 돌리기
			while(rs.next()) {
				
				//Data 1건을 꺼내서 outVO에 담기(outVO는 데이터를 반환하는 변수)

				QnAVO outVO = new QnAVO();
				
				outVO.setQnaNo(rs.getString("qna_no"));
				outVO.setMemberId(rs.getString("member_Id"));
				outVO.setTitle(rs.getString("title"));
				outVO.setRegDt(rs.getString("reg_dt"));
 				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setCount(rs.getInt("count"));
				outVO.setContents(rs.getString("contents"));
				outVO.setAbleYn(rs.getString("able_yn"));
				outVO.setImgPath(rs.getString("img_path"));
				outVO.setExt(rs.getString("ext"));
				
				
				outVO.setNum(rs.getInt("num"));
				outVO.setTotal(rs.getInt("total"));
				
				
				outList.add(outVO);
				
				//rs에 담긴 쿼리가 수행된 데이터들을 한줄씩 outVO(반환하는 BoardVO타입의 변수)에 set해주고, 
				//outVO를 List타입인 outList에 더해서 List형식으로 return
			}
			LOG.debug("4. return: "+outList);
		
			
			
		} catch(SQLException e) {
			LOG.debug("=========================");
			LOG.debug("=SQLException="+e.getMessage());
			LOG.debug("=========================");
			e.printStackTrace();
			
		} finally {
			JDBCResClose.close(rs);
			JDBCResClose.close(pstmt);
			JDBCResClose.close(connection);
		}
		
		return outList;
	}
	
	
	
	
	
}
