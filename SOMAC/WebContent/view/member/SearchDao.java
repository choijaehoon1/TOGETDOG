/**
 *<pre>
 * com.JW.board
 * Class Name : SearchDao.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-12           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-12 
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
/**
 * @author SIST
 *
 */
public class SearchDao {


	private Connection connection;

	   

	   public SearchDao(Connection connection){

	       this.connection = connection;

	    }

	   

	   public String searchId(String Name, String Phone) throws SQLException {

	    PreparedStatement pstmt = null;

	       ResultSet rs = null;

	try {

	pstmt = connection.prepareStatement( 

	"select userId from LOGIN " +

	"where userName = ? and userPhone = ? ");

	pstmt.setString(1, Name);

	   pstmt.setString(2, Phone);

	rs = pstmt.executeQuery();

	           if( rs.next() )

	             return (rs.getString("MemberId"));

	            else

	               return null;

	} finally {

	if(rs!=null)try { rs.close(); } catch(SQLException ex) {}

	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}

	}




	}

	   public String searchPwd(String Id, String Phone) throws SQLException {

	    PreparedStatement pstmt = null;

	       ResultSet rs = null;

	try {

	pstmt = connection.prepareStatement( 

	"select userPwd from LOGIN " +

	"where userId = ? and userPhone = ? ");

	pstmt.setString(1, Id);

	   pstmt.setString(2, Phone);

	rs = pstmt.executeQuery();

	           if( rs.next() )

	             return (rs.getString("userPwd"));

	            else

	               return null;

	} finally {

	if(rs!=null)try { rs.close(); } catch(SQLException ex) {}

	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}

	}



	}



	
}
