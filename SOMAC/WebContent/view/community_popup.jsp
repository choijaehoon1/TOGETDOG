<%--
  /**
  * Class Name : 
  * Description : 
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 3. 11.            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.01.06
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ include file="/cmn/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h2>File Upload</h2>
	<hr/>
	<form action='<%=HR_PATH %>/view/fload.do' id="uploadForm" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td><label for="fileName01">파일</label></td>
				<td><input type="file" name="fileName01" id="fileName01"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="btn-upload" value="등록하기" >
				</td>
			</tr>
        </table>
	
	</form>
	
	<script type="text/javascript">
	  		$('#btn-upload').on('click', function () { 
		     	//console.log('btn-upload'); 
	  			 var form = new FormData(document.getElementById('uploadForm')); 
	  		     $.ajax(
	  		       {url: "/SOMAC/view/fload.do", 
	  		        data: form, 
	  		        dataType: 'text', 
	  		        type: 'POST', 
	  		        processData: false, 
	  		        contentType: false, 
	  		        success: function (response) { 
	  		         //console.log('success'); 
	  		         alert("등록되었습니다"); 
	  		         window.opener.setValue(response);
	  		         window.self.close(); 
	  		        }, 
	  		        error: function (jqXHR) { 
	  		         console.log('error'); 
	  		        } 
	  		       }); 
		    });
	</script>
</body>
</html>