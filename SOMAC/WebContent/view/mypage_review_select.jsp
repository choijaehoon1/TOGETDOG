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


<%@page import="com.togetdog.community.CommunityVO2"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/cmn/common.jsp" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%


%>

 
<!doctype html>
<html class="no-js" lang="zxx">

<head>
   <meta charset="utf-8">
   <meta http-equiv="x-ua-compatible" content="ie=edge">
   <title>TOGETDOG</title>
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
   
   <link href="https://fonts.googleapis.com/css?family=Stylish&display=swap" rel="stylesheet">
   <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans|Stylish&display=swap" rel="stylesheet">
    <style>
      #myPageTitle{
         font-family: 'Do Hyeon', sans-serif;
         font-size: 50px; 
       }
      
       .myPageTitleFont{
         font-family: 'Do Hyeon', sans-serif;
         font-size: 40px; 
       }
       
       .myPageSubTitleFont{
         font-family: 'Do Hyeon', sans-serif; 
         font-size: 35px;
       }

       .contentsTitle{
         font-family: 'Nanum Gothic';
         font-size: 25px;
       }
       
       .cmnContent{
         font-family: 'Nanum Gothic';
         font-size: 15px;
       }
 
   	 #imgsize{
       	width: 650px;
       	height: 400px;
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
                             <h3 id="myPageTitle">상세 입양후기</h3>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
         <!--/ bradcam_area  -->

   <!--================Blog Area =================-->
   <form action='<%=HR_PATH%>/view/mypage.do' method="post" name="insert_frm" >
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
                        <li><i class="fa fa-comments"></i>${vo.modDt}</li>
                     </ul>
                     <div class="quote-wrapper">
                        <div class="quotes">
                           ${vo.contents}
                        </div>
                     </div>
                  </div>
               </div>
               <div class="navigation-top">
                  <div class="d-sm-flex justify-content-between text-center">
                     <p class="like-info"><span class="align-middle"><i class="fa fa-heart"></i></span> Lily and 4
                        people like this</p>
                     <div class="col-sm-4 text-center my-2 my-sm-0">
                        <!-- <p class="comment-count"><span class="align-middle"><i class="fa fa-comment"></i></span> 06 Comments</p> -->
                     </div>
                     <ul class="social-icons">
                        <li><a href="#"><i class="fa fa-facebook-f"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                        <li><a href="#"><i class="fa fa-behance"></i></a></li>
                     </ul>
                  </div>
               </div>

            </div>
            <div class="col-lg-4">
               <div class="blog_right_sidebar">
                  <aside class="single_sidebar_widget post_category_widget">
                     <h4 class="widget_title">Category</h4>
                     <ul class="list cat-list">
                        <li>
                            <a>
                                <p onclick="javascript:goMRetrieve();">목격자를 찾습니다</p>
                            </a>
                        </li>
                        <li>
                            <a>
                                <p onclick="javascript:goJRetrieve();">주인을 찾습니다</p>
                            </a>
                        </li>
                        <li>
                            <a>
                                <p onclick="javascript:goRRetrieve();">입양후기</p>
                            </a>
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

    <!-- ===============footer start======================= -->
    <footer class="footer">
        <%@ include file="/cmn/togetdog_footer.jsp" %>
    </footer>
    <!-- ======================footer end==========================  -->


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
		function goMRetrieve(){
	  		window.location.href='/SOMAC/view/community.do?work_div=do_retrieve_m';
	  	}
		
		function goJRetrieve(){
	  		window.location.href='/SOMAC/view/community.do?work_div=do_retrieve_j';
	  	}
		
		function goRRetrieve(){
	  		window.location.href='/SOMAC/view/community.do?work_div=do_retrieve_r';
	  	}
	

		
	</script>	
	
</body>

</html>