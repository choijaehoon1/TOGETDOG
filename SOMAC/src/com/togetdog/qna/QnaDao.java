/**
 *<pre>
 * com.hr.board
 * Class Name : QnaDao.java
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
 

package com.togetdog.qna;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import com.togetdog.cmn.ConnectionMaker;
import com.togetdog.cmn.DTO;
import com.togetdog.cmn.JDBCResClose;
import com.togetdog.cmn.SearchVO;
import com.togetdog.cmn.WorkDiv;
import com.togetdog.community.CommunityVO;
import com.togetdog.community.CommunityVO2;
import com.togetdog.filemng.FileVO;


/**
 * @author sist
 *
 */
public class QnaDao extends WorkDiv{
	private ConnectionMaker  connectionMaker;
	
	
	public QnaDao() {
		connectionMaker =new ConnectionMaker();
	}
	/**
    * 
    *@Method Name:doSelectOne
    *@작성일: 2020. 2. 4.
    *@작성자: sist
    *@설명: 단건조회
    *@param dto
    *@return QnaVO
    */
	
	   @Override
		public DTO doSelectOne(DTO dto) {
			  QnaVO inVO  = (QnaVO) dto;
			  QnaVO outVO = null;
			  
			  Connection connection    = null;
			  PreparedStatement  pstmt = null;
			  ResultSet   rs           = null;
			  
			  StringBuilder sb=new StringBuilder();//검색query
			  try {
				  //1.Connection
				  connection = connectionMaker.getConnection();
				  LOG.debug("1.Connection="+connection);
				  
				  //2.Query
				  sb.append(" SELECT *   					   \n");
				  sb.append(" FROM qna_board t1, file_mng t2   \n");
				  sb.append(" WHERE t1.qna_no=t2.file_no       \n");
				  sb.append("AND t1.qna_no = ?                 \n");
				  LOG.debug("2.query=\n"+sb.toString());
				  //2.1. pstmt
				  pstmt = connection.prepareStatement(sb.toString());
				  
				  //3.param
				  LOG.debug("3.param=\n"+inVO);
				  pstmt.setString(1, inVO.getQna_no());
				  		
				  
				  //4.query수행
				  rs = pstmt.executeQuery();
				  if(rs.next()) {
					  outVO = new QnaVO();
					  outVO.setQna_no(rs.getString("qna_no"));
					  outVO.setMemberId(rs.getString("member_id"));
					  outVO.setTitle(rs.getString("title"));
					  outVO.setRegDt(rs.getString("reg_dt"));
					  outVO.setModDt(rs.getString("mod_dt"));
					  outVO.setCount(rs.getInt("count"));
					  outVO.setContents(rs.getString("contents"));
					  outVO.setAbleYn(rs.getString("able_yn"));
					  outVO.setImgPath(rs.getString("img_path"));
					  outVO.setExt(rs.getString("ext"));
				      outVO.setSaveNm(rs.getString("save_nm"));
					  LOG.debug("5.단건outVO=\n"+outVO);
					 
				  }
				 upCount(outVO); //카운트 올리기	
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
	   
	   
	   public int upCount(DTO dto) {
			 int flag = 0;
			 QnaVO inVO = (QnaVO) dto;//param
			
			
			 Connection connection   = null;//DB Connection
			 PreparedStatement pstmt = null;//VS Statement
			 try {
			     //1. Connection
				 connection = connectionMaker.getConnection();
				 LOG.debug("1. Connection="+connection);
				 
				 StringBuilder sb=new StringBuilder();
				 sb.append(" UPDATE QNA_BOARD    	 \n");
				 sb.append(" SET    count=?        	 \n");  //제목
				 sb.append(" WHERE qna_no=?          \n");
				 //2.query
				 LOG.debug("2. query=\n"+sb.toString());
				 
				 //2.1. pstmt
				 pstmt = connection.prepareStatement(sb.toString());
		    	 //3.param
				 LOG.debug("3. param="+inVO);
				 LOG.debug("새로운 조회수 =>"+Integer.valueOf(inVO.getCount()+1));
				 
		    	 pstmt.setInt(1,Integer.valueOf(inVO.getCount())+1);	  //조회수+1
		    	 pstmt.setString(2, inVO.getQna_no());	  //글번호
		    	 
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
	   
	   
	   public int doDeleteYn(DTO dto) {  
		   
		   int flag = 0;
			 QnaVO inVO = (QnaVO) dto;//param
			
			 Connection connection   = null;//DB Connection
			 PreparedStatement pstmt = null;//VS Statement
			 try {
			     //1. Connection
				 connection = connectionMaker.getConnection();
				 LOG.debug("1. Connection="+connection);
				 
				 StringBuilder sb=new StringBuilder();
				 sb.append(" UPDATE QNA_BOARD    	 	 	\n");
				 sb.append(" SET    able_yn='N'        		 \n");  //제목
				 sb.append(" WHERE qna_no=?         		 \n");
				 //2.query
				 LOG.debug("2. query=\n"+sb.toString());
				 
				 //2.1. pstmt
				 pstmt = connection.prepareStatement(sb.toString());
		    	 //3.param
				 LOG.debug("3. param="+inVO);
				 

		    	 pstmt.setString(1, inVO.getQna_no()); 	  //글번호

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
   
	   public int doReplyDel(DTO dto) {
			 int flag = 0;
			 QnaVO2 inVO = (QnaVO2) dto;//param
			
			 Connection connection   = null;//DB Connection
			 PreparedStatement pstmt = null;//VS Statement
			 try {
			     //1. Connection
				 connection = connectionMaker.getConnection();
				 LOG.debug("1. Connection="+connection);
				 
				 StringBuilder sb=new StringBuilder();
			
		    	 sb.append(" UPDATE common_reply    	 	 \n");
				 sb.append(" SET    reply_yn='N'        		 \n");  //제목
				 sb.append(" WHERE reply_no=?         	 \n");
				 sb.append(" AND rseq_no=?         	 \n");
				 //2.query
				 LOG.debug("2. query=\n"+sb.toString());
				 
				 //2.1. pstmt
				 pstmt = connection.prepareStatement(sb.toString());
		    	 //3.param
				 LOG.debug("3. param="+inVO);
				 
		    	
		    	 pstmt.setString(1, inVO.getReplyNo());	  //글번호
		    	 pstmt.setInt(2, inVO.getRseqNo());  //댓글번호
		

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
	    
	   
	   
	   
	   
		public int doReplyUpdate(DTO dto) {
			 int flag = 0;
			 QnaVO2 inVO = (QnaVO2) dto;//param
			
			 Connection connection   = null;//DB Connection
			 PreparedStatement pstmt = null;//VS Statement
			 try {
			     //1. Connection
				 connection = connectionMaker.getConnection();
				 LOG.debug("1. Connection="+connection);
				 
				 StringBuilder sb=new StringBuilder();
				 sb.append(" UPDATE common_reply    	 	 \n");
				 sb.append(" SET    rcontents=?        		 \n");  //내용
				 //sb.append("    rreg_dt=sysdate,    	 \n"); //수정일
				 sb.append(" WHERE reply_no=?         	 \n");
				 sb.append(" AND rseq_no=?         	 \n");
				 //2.query
				 LOG.debug("2. query=\n"+sb.toString());
				 
				 //2.1. pstmt
				 pstmt = connection.prepareStatement(sb.toString());
		    	 //3.param
				 LOG.debug("3. param="+inVO);
				 
		    	
		    	 pstmt.setString(1, inVO.getRcontents());	  //내용
		    	 pstmt.setString(2, inVO.getReplyNo());  //글번호
		    	 pstmt.setInt(3, inVO.getRseqNo()); 	  //댓글시퀀스번호

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
	public int doUpdate(DTO dto) {
		 int flag = 0;
		 QnaVO inVO = (QnaVO) dto;//param
		
		 Connection connection   = null;//DB Connection
		 PreparedStatement pstmt = null;//VS Statement
		 try {
		     //1. Connection
			 connection = connectionMaker.getConnection();
			 LOG.debug("1. Connection="+connection);
			 
			 StringBuilder sb=new StringBuilder();
			 sb.append(" UPDATE QNA_BOARD    	 	 \n");
			 sb.append(" SET    title=?,        		 \n");  //제목
			 sb.append("     mod_dt=sysdate,    	 \n"); //수정일
			 sb.append("     contents=?    		 \n"); //내용
			 sb.append(" WHERE qna_no=?         	 \n");
			 //2.query
			 LOG.debug("2. query=\n"+sb.toString());
			 
			 //2.1. pstmt
			 pstmt = connection.prepareStatement(sb.toString());
	    	 //3.param
			 LOG.debug("3. param="+inVO);
			 
	    	
	    	 pstmt.setString(1, inVO.getTitle());	  //제목
	    	 pstmt.setString(2, inVO.getContents());  //내용

	    	 pstmt.setString(3, inVO.getQna_no()); 	  //글번호

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
   
   public int doFileDel(DTO dto) {
	   
	   int flag = 0;
		 FileVO inVO = (FileVO) dto;//param
		
		 Connection connection   = null;//DB Connection
		 PreparedStatement pstmt = null;//VS Statement
		 try {
		     //1. Connection
			 connection = connectionMaker.getConnection();
			 LOG.debug("1. Connection="+connection);
			 
			 StringBuilder sb=new StringBuilder();
			 sb.append(" DELETE FROM file_mng \n");
	    	 sb.append(" WHERE file_no = ?   \n");
	    	 
			 //2.query
			 LOG.debug("2. query=\n"+sb.toString());
			 
			 //2.1. pstmt
			 pstmt = connection.prepareStatement(sb.toString());
	    	 //3.param
			 LOG.debug("3. param="+inVO);
			 
	    	
	    	 pstmt.setString(1, inVO.getFileNo());	//파일이름  
	    	

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
   public int doPicUpdate(DTO dto) {
		 int flag = 0;
		 FileVO inVO = (FileVO) dto;//param
		
		 Connection connection   = null;//DB Connection
		 PreparedStatement pstmt = null;//VS Statement
		 try {
		     //1. Connection
			 connection = connectionMaker.getConnection();
			 LOG.debug("1. Connection="+connection);
			 
			 StringBuilder sb=new StringBuilder();
			 sb.append(" UPDATE file_mng    	 	 \n");
			 sb.append(" SET    org_nm=?,        	 \n");  //원본이름
			 sb.append("     save_nm  =?,  	 		 \n"); //저장이름
			 sb.append("     img_path =?,  			 \n"); //이미지경로
			 sb.append("     file_size=?,   		 \n"); //파일 사이즈
			 sb.append("     ext =?,  				 \n"); //확장자
			 sb.append("     reg_dt=sysdate   		 \n"); //날짜
			 sb.append(" WHERE file_no=?         	 \n"); //글번호
			 //2.query
			 LOG.debug("2. query=\n"+sb.toString());
			 
			 //2.1. pstmt
			 pstmt = connection.prepareStatement(sb.toString());
	    	 //3.param
			 LOG.debug("3. param="+inVO);
			 
	    	
	    	 pstmt.setString(1, inVO.getOrgNm());	//원본이름  
	    	 pstmt.setString(2, inVO.getSaveNm());  //저장이름
	    	 pstmt.setString(3, inVO.getImgPath()); 	//이미지경로  
	    	 pstmt.setInt(4, inVO.getFileSize());	  //파일 사이즈
	    	 pstmt.setString(5, inVO.getExt());  	//확장자
	    	 pstmt.setString(6, inVO.getFileNo()); 	  //글번호

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

   public int doReplyInsert(DTO dto) {
		int flag =0;
		QnaVO2 inVO = (QnaVO2) dto;
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
	
   public List<?> doReplyRetrieve(DTO dto) {
		QnaVO2 inVO = (QnaVO2) dto;
		List<QnaVO2> outList = new ArrayList<QnaVO2>();
		
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
		sb.append("from qna_board t1,COMMON_REPLY t2  \n");  
		sb.append("where t1.qna_no = t2.reply_no        \n");
		sb.append("AND t2.reply_no = ?                  \n");

		
		try {
			connection = connectionMaker.getConnection();
			
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("query \n" + sb.toString());
			LOG.debug(pstmt);

			pstmt.setString(1, inVO.getReplyNo());
		
			
			rs = pstmt.executeQuery();
			//이전이 오류
			
			while(rs.next()) {
				QnaVO2 outVO = new QnaVO2();
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
			sb.append("    ?,                					     ");
			sb.append("    ?,                                             ");
			sb.append("    'image_' || ? ,       				  ");
			sb.append("    '../img/image_/'|| ?,   		");
			sb.append("    ?,                                             ");
			sb.append("    ?,                                             ");
			sb.append("    ?,                                             ");
			sb.append("    sysdate                                          ");
			sb.append(")                                                   ");
						
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
   
   @Override
	public List<?> doRetrieve(DTO dto) {

	   SearchVO inVO=(SearchVO) dto;
			QnaVO outVO = null; //return
			List<QnaVO> outList =new ArrayList<QnaVO>();
			
			Connection connection = null;
			PreparedStatement  pstmt = null;
			ResultSet   rs = null;
			
			StringBuilder sbWhere=new StringBuilder();//검색조건
			StringBuilder sb=new StringBuilder();//검색query
			
			
			//검색구분:10(제목),20(내용),30(회원ID)
			if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
				if(inVO.getSearchDiv().equals("10")) {
					sbWhere.append("AND t1.title like ? ||'%' \n ");
				} else if(inVO.getSearchDiv().equals("20")) {
					sbWhere.append("AND t1.contents like ? ||'%' \n ");
				} else if(inVO.getSearchDiv().equals("30")) {
					sbWhere.append("AND t1.member_id like ? ||'%' \n ");
				}
			}		
			
			//검색구분-------------------------------------------------------------
			sb.append("SELECT *                          \n");                   
			sb.append("FROM(                             \n");                   
			sb.append("    SELECT  *                     \n");       
			sb.append("    FROM (                        \n");                   
			sb.append("        SELECT ROWNUM as rnum,A.* \n");                   
			sb.append("        FROM(                     \n");                   
			sb.append("            SELECT t1.*,t2.img_path,t2.ext,t2.save_nm          \n");                   
			sb.append("            FROM qna_board t1 , file_mng t2       \n");      
			sb.append("            WHERE t1.qna_no = t2.FILE_NO                        \n");
			sb.append("            AND t1.qna_no like 'Q_%'  		                       \n");
			sb.append("            AND t1.able_yn ='Y'  		                       \n");
			//sb.append("            --검색조건               \n");
			//Where-----------------------------------------------------------------------
			if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
				if(inVO.getSearchWord() != null) {
					sb.append(sbWhere.toString());
				}
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
			sb.append("    FROM qna_board t1, file_mng t2                           \n");
			sb.append("    Where t1.qna_no = t2.FILE_NO                          \n");
			sb.append("    AND t1.qna_no like 'Q_%'  		                       \n");
			sb.append("            AND t1.able_yn ='Y'  		                       \n");
			//-where----------------------------------------------------------------------
			if(inVO.getSearchDiv() != null || !inVO.getSearchDiv().equals("")) {
				if(inVO.getSearchWord() != null) {
					sb.append(sbWhere.toString());
				}
			}
			sb.append(")                                                             \n"); 
			
			try {
				//1.connection
				connection = this.connectionMaker.getConnection();
				LOG.debug("1.connection="+connection);
				
				//2.pstmt
				//2.1.
				LOG.debug("2.query=\n"+sb.toString());
				pstmt = connection.prepareStatement(sb.toString());
				
				//2.2.pstmt
				LOG.debug("2.1. pstmt="+pstmt);
				
				//3.Param setting
				LOG.debug("3.param : "+inVO);	
				
				//3.1.Param binding
				//검색어 있는 경우
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
				
				
				//4.query수행
				rs = pstmt.executeQuery();
				
				
				while(rs.next()) {
						outVO=new QnaVO();
					
					  outVO.setQna_no(rs.getString("qna_no"));
					  outVO.setMemberId(rs.getString("member_Id"));
					  outVO.setTitle(rs.getString("title"));
					  outVO.setRegDt(rs.getString("reg_dt"));
					  outVO.setModDt(rs.getString("mod_dt"));
					  outVO.setCount(rs.getInt("count"));
					  outVO.setContents(rs.getString("contents"));
					  outVO.setAbleYn(rs.getString("able_yn"));
					  outVO.setTotal(rs.getInt("total"));
					  outVO.setImgPath(rs.getString("IMG_PATH"));
				      outVO.setExt(rs.getString("EXT"));
					  outVO.setSaveNm(rs.getString("SAVE_NM"));
					  
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
	
   /**
	* 
	*@Method Name:doInsert
	*@작성일: 2020. 2. 3.
	*@작성자: sist
	*@설명:게시판 글 등록. 등록성공(1),실패(0)
	*@param dto
	*@return int
	*/
   @Override
	public int doInsert(DTO dto) {
		 int flag = 0;
	     QnaVO inVO = (QnaVO) dto;//param
	    
	     Connection connection   = null;//DB Connection
	     PreparedStatement pstmt = null;//VS Statement
				
	     try {
	    	 //1.connection
	    connection = this.connectionMaker.getConnection();
	    LOG.debug("1.Connection="+connection);
	    
		StringBuilder sb=new StringBuilder();
		
		sb.append(" INSERT INTO QNA_BOARD( 	 	 \n");
		sb.append("     qna_no         		 \n");
		sb.append("     ,member_Id     			 \n");
		sb.append("     ,title        			 \n");
		sb.append("     ,reg_dt       			 \n");
		sb.append("     ,mod_dt       			 \n");
		sb.append("     ,count     				 \n");
		sb.append("     ,contents    			 \n");
		sb.append("     ,able_yn        		 \n");
		sb.append(" ) VALUES (         			 \n");
		sb.append("'Q_'||QNA_BOARD_SEQ.NEXTVAL  \n"); //글번호
		sb.append("     ,?                  	 \n");//멤버아이디?
		sb.append("     ,?                  	 \n");//글제목?
		sb.append("     ,SYSDATE            	 \n"); //날짜
		sb.append("     ,SYSDATE            	 \n"); //수정날짜
		sb.append("     ,'0'        	        \n"); //조회수
		sb.append("     ,?        	        	 \n"); //내용?
		sb.append("     ,'Y'                   	 \n"); //게시여부
	    sb.append(" )                       	 \n");	
		
	   	 //2.pstmt
	   	 LOG.debug("2.query=\n"+sb.toString());
	   	 pstmt = connection.prepareStatement(sb.toString());
	   	 LOG.debug("2.1 pstmt="+pstmt);

	   	 //3.param
    	 LOG.debug("3.  param="+inVO);
    	 
			pstmt.setString(1, inVO.getMemberId());	 //아이디
			pstmt.setString(2, inVO.getTitle()); //제목
			pstmt.setString(3, inVO.getContents()); //내용
			//pstmt.setInt   (3, inVO.getCount());	 //조회수
			
			//pstmt.setString(5, inVO.getAbleYn());	//게시여부
			
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
   public int doInsert2(DTO dto) {
		 int flag = 0;
	     QnaVO inVO = (QnaVO) dto;//param
	    
	     Connection connection   = null;//DB Connection
	     PreparedStatement pstmt = null;//VS Statement
				
	     try {
	    	 //1.connection
	    connection = this.connectionMaker.getConnection();
	    LOG.debug("1.Connection="+connection);
	    
		StringBuilder sb=new StringBuilder();
		
		sb.append(" INSERT INTO QNA_BOARD( 	 	 \n");
		sb.append("     qna_no         		 \n");
		sb.append("     ,member_Id     			 \n");
		sb.append("     ,title        			 \n");
		sb.append("     ,reg_dt       			 \n");
		sb.append("     ,mod_dt       			 \n");
		sb.append("     ,count     				 \n");
		sb.append("     ,contents    			 \n");
		sb.append("     ,able_yn        		 \n");
		sb.append(" ) VALUES (         			 \n");
		sb.append("'Q_'||QNA_BOARD_SEQ.NEXTVAL  \n"); //글번호
		sb.append("     ,?                  	 \n");//멤버아이디?
		sb.append("     ,?                  	 \n");//글제목?
		sb.append("     ,SYSDATE            	 \n"); //날짜
		sb.append("     ,SYSDATE            	 \n"); //수정날짜
		sb.append("     ,'0'        	        \n"); //조회수
		sb.append("     ,?        	        	 \n"); //내용?
		sb.append("     ,'Y'                   	 \n"); //게시여부
	    sb.append(" )                       	 \n");	
		
	   	 //2.pstmt
	   	 LOG.debug("2.query=\n"+sb.toString());
	   	 pstmt = connection.prepareStatement(sb.toString());
	   	 LOG.debug("2.1 pstmt="+pstmt);

	   	 //3.param
  	 LOG.debug("3.  param="+inVO);
  	 
			pstmt.setString(1, inVO.getMemberId());	 //아이디
			pstmt.setString(2, inVO.getTitle()); //제목
			pstmt.setString(3, inVO.getContents()); //내용
			//pstmt.setInt   (3, inVO.getCount());	 //조회수
			
			//pstmt.setString(5, inVO.getAbleYn());	//게시여부
			
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
	     QnaVO inVO = (QnaVO) dto;//param
	    
	     Connection connection   = null;//DB Connection
	     PreparedStatement pstmt = null;//VS Statement
	     try {
	    	 //1.Connection
	    	 connection = connectionMaker.getConnection();
	    	 LOG.debug("1.Connection="+connection);
	    	 
	    	 //2.query
	    	 StringBuilder sb=new StringBuilder();
	    	 sb.append(" DELETE FROM qna_board \n");
	    	 sb.append(" WHERE qna_no = ?   \n");
	    	 
	    	 LOG.debug("2.query=\n"+sb.toString());
	    	 //2.1.pstmt
	    	 pstmt = connection.prepareStatement(sb.toString());
	    	 //3.param
	    	 LOG.debug("3.param=\n"+inVO);
	    	 pstmt.setString(1, inVO.getQna_no());
	    	 
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

	
	public DTO doGetno(DTO dto) {
		QnaVO outVO = null;
		QnaVO inVO = (QnaVO) dto;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = connectionMaker.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("select t1.qna_no			\n");
			sb.append("      ,t1.member_id      \n");
			sb.append("      ,t1.title          \n");
			sb.append("      ,t1.contents       \n");
			sb.append("      ,t1.reg_dt         \n");
			sb.append("      ,t1.mod_dt         \n");
			sb.append("      ,t1.count          \n");
			sb.append("      ,t1.able_yn        \n");
			sb.append("from(                    \n");
			sb.append("select *                 \n");
			sb.append("from qna_BOARD         \n");
			sb.append("order by mod_dt desc)t1  \n");
			sb.append("where rownum = 1         \n");

			    
			pstmt = connection.prepareStatement(sb.toString());
			LOG.debug("pstmt" + pstmt);
			LOG.debug("inVO" + inVO);
			
			rs = pstmt.executeQuery();
			LOG.debug(rs);
			if(rs.next()) {
				outVO = new QnaVO();
				 outVO.setQna_no(rs.getString("qna_no"));
				  outVO.setMemberId(rs.getString("member_id"));
				  outVO.setTitle(rs.getString("title"));
				  outVO.setRegDt(rs.getString("reg_dt"));
				  outVO.setModDt(rs.getString("mod_dt"));
				  outVO.setCount(rs.getInt("count"));
				  outVO.setContents(rs.getString("contents"));
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
	
	
	
}










