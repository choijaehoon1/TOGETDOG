<%--
       파일명:uri
	작성자:
	작성일:2020. 2. 21.	
	cache 컨트롤
--%>
<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		Logger   LOG   = Logger.getLogger(this.getClass());
		String HR_PATH = request.getContextPath();
		LOG.debug("HR_PATH:"+HR_PATH);

		//include
		//HTTP1.1에서 지원하는 헤더로 : 웹브라우저가 응답결과를 캐시하지 않음
		response.setHeader("Cashe-Control", "no-cache");
	    
	    //웹브라우저가 응답결과를 캐시하지 않음(앞/뒤로가기 캐시하지 않음)
	    response.addHeader("Cashe-Control","no-store");
	    
	    //HTTP1.0에서 지원하는 헤더로 : 웹브라우저가 응답결과를 캐시하지 않음
		response.setHeader("Pragma", "No-cache");
	    
	    //HTTP1.0에서 응답결과 만료일: 현재시간 이전으로 설정 캐지하지 않음( 기준시간 1970.1.1,)
		response.setDateHeader("Expires", 1L);
	
%>   






