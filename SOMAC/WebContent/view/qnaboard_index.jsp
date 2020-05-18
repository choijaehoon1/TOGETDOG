<%--
  /**
  * Class Name : 
  * Description : 
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 3. 9.            최초 생성
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
<c:url value="/view/qna.do" var="hrUrl">
 <c:param name="work_div" value="do_retrieve"></c:param>
</c:url>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>hr_html</title>
</head>
<body onload="window.location.href='${hrUrl}'">
</body>
</html>