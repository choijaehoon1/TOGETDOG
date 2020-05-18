<%--
  /**
  * Class Name : 
  * Description : 
  * Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2020. 3. 5.            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.01.06
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>


<%@page import="com.togetdog.member.MemberVO"%>
<%@page import="com.togetdog.community.CommunityVO2"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/cmn/common.jsp" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	MemberVO mvo= (MemberVO)session.getAttribute("user");
	String id = mvo.getMemberId();
	List<CommunityVO2> replyList = (List<CommunityVO2>)request.getAttribute("replyList");
%>

 
<!doctype html>
<html class="no-js" lang="zxx">

<head>
   <meta charset="utf-8">
   <meta http-equiv="x-ua-compatible" content="ie=edge">
   <title>ToGetDog</title>
   <meta name="description" content="">
   <meta name="viewport" content="width=device-width, initial-scale=1">

   <!-- <link rel="manifest" href="site.webmanifest"> -->
   <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png">
   <!-- Place favicon.ico in the root directory -->

   <!-- CSS here -->
   <link rel="stylesheet" href="css/bootstrap.min.css">
   <link rel="stylesheet" href="css/owl.carousel.min.css">
   <link rel="stylesheet" href="css/magnific-popup.css">
   <link rel="stylesheet" href="css/font-awesome.min.css">
   <link rel="stylesheet" href="css/themify-icons.css">
   <link rel="stylesheet" href="css/nice-select.css">
   <link rel="stylesheet" href="css/flaticon.css">
   <link rel="stylesheet" href="css/gijgo.css">
   <link rel="stylesheet" href="css/animate.css">
   <link rel="stylesheet" href="css/slicknav.css">
   <link rel="stylesheet" href="css/style.css">
   <!-- <link rel="stylesheet" href="css/responsive.css"> -->
   <!-- 폰트 다운로드 -->
   	<link href="https://fonts.googleapis.com/css?family=Stylish&display=swap" rel="stylesheet">
   	<link href="https://fonts.googleapis.com/css?family=Black+Han+Sans|Stylish&display=swap" rel="stylesheet">
   	<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Cute+Font|Gothic+A1|Noto+Sans+KR&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Cute+Font|Gothic+A1|Nanum+Gothic|Noto+Sans+KR&display=swap" rel="stylesheet">
    <style>	
        #communityTitle {
           font-family: 'Do Hyeon', sans-serif;
           font-size: 50px; 
        }
        
        .contentsTitle{
         font-family: 'Nanum Gothic';
         font-size: 25px;
       }
       
       .subTitle{
        font-family: 'Nanum Gothic';
         font-size: 20px;
       }
       
       .communityTitleFont{
         
         font-family: 'Do Hyeon', sans-serif; 
         font-size: 30px;
       }	
    	
       #recentImgsize{
       	width: 100px;
       	height: 100px;
       }
       
       #imgsize{
       	width: 600px;
       	height: 400px;
       }
       
       	#imgsize{
       	width: 650px;
       	height: 400px;
       }
       .pageSize{
       	font-family: 'Stylish' ; 
       	font-size: 25px;
       	 
      
       }
       
    </style>
</head>

<body>
   <!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->
    <!-- header-start -->
    <header>
         <%@ include file="/cmn/togetdog_header.jsp" %>        
     </header>
     <!-- header-end -->
      <!-- bradcam_area  -->
      <div class="bradcam_area bradcam_bg_2">
             <div class="container">
                 <div class="row">
                     <div class="col-xl-12">
                         <div class="bradcam_text text-center">
                             <h3 id="communityTitle">상세 입양후기</h3>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
         <!--/ bradcam_area  -->

   <!--================Blog Area =================-->
   <form action='<%=HR_PATH%>/view/community.do' method="post" name="insert_frm" >
   <section class="blog_area single-post-area section-padding">
  	 <input type="hidden" name="work_div" id="work_div"/>
   	 <input type="hidden" name="seq" id="seq" value="${vo.revNo}"/>
   
      <div class="container">
         <div class="row">
            <div class="col-lg-8 posts-list">
               <div class="single-post">
                  <div class="feature-img">
                     <img id="imgsize" class="img-fluid" src="${vo.imgPath}${vo.ext}"alt="">
                  </div>
                  <div class="blog_details">
                     <h2 class="contentsTitle">${vo.title}</h2>
                     <ul class="blog-info-link mt-3 mb-4">
                        <li><i class="fa fa-user"></i>${vo.memberId}</li>
                        <li><i class="fa fa-comments "></i>${vo.modDt}</li>
                     </ul>
                     <div class="quote-wrapper">
                        <div class="quotes">
                           ${vo.contents}
                        </div>
                     </div>
                  </div>
               </div>
              
              
               <div class="comments-area">
				  <c:choose>   
				  	<c:when test="${replyList.size()>0}">  
				  		<c:forEach var="revo" items="${replyList}">
		                  <div class="comment-list">
		                     <div class="single-comment justify-content-between d-flex">
		                        <div class="user justify-content-between d-flex">
		                           <div class="thumb">
		                              <img src="${vo.imgPath}${vo.ext}" alt="">
		                           </div>
		                           <div class="desc">
		                              <p class="contentsTitle">
		                              	<c:out value="${revo.rcontents}"></c:out>
		                                
		                              </p>
		                              <div class="d-flex justify-content-between">
		                                 <div class="d-flex align-items-center">
		                                    <h5 class = "contentsTitle">
		                                       <a href="#"><c:out value="${revo.rregId}"></c:out></a>
		                                    </h5>
		                                    <p class="date"><c:out value="${revo.rregDt}"></c:out></p>
		                                 </div>
		                                 <div class="reply-btn">
		                                    <a href="#" class="btn-reply text-uppercase">reply</a>
		                                 </div>
		                              </div>
		                           </div>
		                        </div>
		                     </div>
		                  </div>
                  		</c:forEach> 
                  	</c:when>      
                  </c:choose>        
               </div>
               
               
            </div>
            <div class="col-lg-4">
               <div class="blog_right_sidebar">
                  <aside class="single_sidebar_widget post_category_widget">
                     <h4 class="communityTitleFont">Category</h4>
                     <ul class="list cat-list subTitle">
                         <li>
                             <a href="/SOMAC/view/community.do?work_div=do_retrieve_m">목격자를 찾습니다</a>
                         </li>
                         <li>
                             <a href="/SOMAC/view/community.do?work_div=do_retrieve_j">주인을 찾습니다</a>
                         </li>
                         <li>
                             <a href="/SOMAC/view/community.do?work_div=do_retrieve_r">입양후기</a>
                         </li>
                     </ul>
                  </aside>    
               </div>
            </div>
         </div>
      </div>
   </section>
   </form>
   <!--================ Blog Area end =================-->

    <!-- footer start -->
    	<%@ include file="/cmn/togetdog_footer.jsp" %>   
    <!--/ footer end  -->


   <!-- JS here -->
   <script src="js/vendor/modernizr-3.5.0.min.js"></script>
   <script src="js/vendor/jquery-1.12.4.min.js"></script>
   <script src="js/popper.min.js"></script>
   <script src="js/bootstrap.min.js"></script>
   <script src="js/owl.carousel.min.js"></script>
   <script src="js/isotope.pkgd.min.js"></script>
   <script src="js/ajax-form.js"></script>
   <script src="js/waypoints.min.js"></script>
   <script src="js/jquery.counterup.min.js"></script>
   <script src="js/imagesloaded.pkgd.min.js"></script>
   <script src="js/scrollIt.js"></script>
   <script src="js/jquery.scrollUp.min.js"></script>
   <script src="js/wow.min.js"></script>
   <script src="js/nice-select.min.js"></script>
   <script src="js/jquery.slicknav.min.js"></script>
   <script src="js/jquery.magnific-popup.min.js"></script>
   <script src="js/plugins.js"></script>
   <script src="js/gijgo.min.js"></script>

   <!--contact js-->
   <script src="js/contact.js"></script>
   <script src="js/jquery.ajaxchimp.min.js"></script>
   <script src="js/jquery.form.js"></script>
   <script src="js/jquery.validate.min.js"></script>
   <script src="js/mail-script.js"></script>

   <script src="js/main.js"></script>

	<script type="text/javascript">
		
	
		function goReplyRetrieve(){
			window.location.href='/SOMAC/view/community.do?work_div=do_selectOne&seq='+'${vo.revNo}';
		}
		
		$("#reple_insert_btn").on('click',function(){
			 var reg_id = $("#reg_id").val();
	         if(null == reg_id || reg_id.trim().length==0){
	        	 $("#reg_id").focus();//focus
	             alert("작성자를 확인 하세요. ");
	             return;
	         }
	         var contents = $("#contents").val();
	         if(null == contents || contents.trim().length==0){
	        	 $("#contents").focus();//focus
	             alert("내용을 확인 하세요. ");
	             return; 
	         }
	        
	         //if(confirm(contents+"을(를)\n등록하시겠습니까?") == false) return;
	         
	         $.ajax({
	          	type:"POST",
	  			url:"/SOMAC/view/community.do",
	  			dataType:"html",
	  			data:{"work_div":"do_reply_insert",
	  				  "seq":$("#seq").val(),
	  				  "contents":$("#contents").val(),
	  				  "reg_id":$("#reg_id").val()
	  			},
	  			success:function(data){	//성공
	  				var jsonObj = JSON.parse(data);
	  				if(jsonObj !=null && jsonObj.msgId == "1"){
	  					alert(jsonObj.msgContents);			//메시지 먼저 뜨고 아래에서 이동시킴
	  					//window.location.href='/SOMAC_0213/view/community.jsp';
	  					//goRetrieve();
	  					goReplyRetrieve();
	  				} else{
	  					alert(jsonObj.msgId+"|"+ jsonObj.msgContents);
	  					
	  				}
	  				
	  			},error:function(xhr,status,error){
	  				alert("error:" + error);
	  			},complete:function(data){
	  			
	  			}
	  			
	           });//--ajax
	         
		});
		
	</script>	
	
</body>

</html>