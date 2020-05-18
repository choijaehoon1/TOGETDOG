/**
 *<pre>
 * com.hr.cmn
 * Class Name : ConnectionMaker.java
 * Description : Connection생성 클래스
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
 
package com.togetdog.cmn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 1. Connection 
   2. 코드 중복: Connection getConnetion() 메소드로 추출.
  
   3. BoardDao.java MemeberDao.java 다른 XXXDAO가 Connection을 사용할수 있도록 클래스로 추출
 *
 */
public class ConnectionMaker {
	   private final Logger LOG=Logger.getLogger(this.getClass());
	   /**
	    * 
	    *@Method Name:getConnection
	    *@작성일: 2020. 2. 3.
	    *@작성자: sist
	    *@설명: DB Connection생성 
	    *@return Connection
	    */
	   public Connection getConnection() {
		        //1. JDBC Driver 로딩
		 		//1.1. Driver명 : 
		 		//1.1.1 Oracle -> oracle.jdbc.driver.OracleDriver
		 		//1.1.2 MSSQL ->  com.microsoft.sqlserver.jdbc.SQLServerDriver
		 		//1.1.3 MYSQL ->  com.mysql.jdbc.Driver
		 		
		 		Connection connection = null;
		 		try {
		 			Class.forName("oracle.jdbc.driver.OracleDriver");
		 		} catch (ClassNotFoundException e) {
		 			LOG.debug("===================");
		 			LOG.debug("ClassNotFoundException="+e.getMessage());
		 			LOG.debug("===================");
		 			e.printStackTrace();
		 		}
		 		
		 		//2. 데이터베이스 커넥션 구함: Login DB
		 		//jdbc:oracle:thin
		 		//서버IP:211.238.142.124
		 		//PORT: 1521
		 		//전역DB: orcl
		 		
		 		String url = "jdbc:oracle:thin:@211.238.142.128:1521:xe";
		 		String user = "bbom";
		 		String password = "bbom";
		 		try {
		 			connection = DriverManager.getConnection(url, user, password);
		 			LOG.debug("1.connection="+connection);
		 		} catch (SQLException e) {
		 			LOG.debug("===================");
		 			LOG.debug("SQLException="+e.getMessage());
		 			LOG.debug("===================");
		 			e.printStackTrace();
		 		}
		 		
		 		return connection;
	   }
}
