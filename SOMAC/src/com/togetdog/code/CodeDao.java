/**
 *<pre>
 * com.hr.code
 * Class Name : CodeDao.java
 * Description : 
 * Modification Information
 * 
 *   수정일      수정자              수정내용
 *  ---------   ---------   -------------------------------
 *  2020-02-27           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2020-02-27 
 * @version 1.0
 * 
 *
 *  Copyright (C) by H.R. KIM All right reserved.
 * </pre>
 */

package com.togetdog.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.togetdog.cmn.ConnectionMaker;
import com.togetdog.cmn.DTO;
import com.togetdog.cmn.JDBCResClose;
import com.togetdog.cmn.WorkDiv;

/**
 * @author sist
 *
 */
public class CodeDao extends WorkDiv {
	private ConnectionMaker connectionMaker; 
	
	public CodeDao() {
		connectionMaker = new ConnectionMaker();
	}
	
	@Override
	public DTO doSelectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doUpdate(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doInsert(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<?> doRetrieve(DTO dto) {
		
		return null;

	}

}
