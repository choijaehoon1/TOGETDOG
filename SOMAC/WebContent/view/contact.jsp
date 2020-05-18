<%@page import="com.togetdog.cmn.StringUtil"%>
<%@page import="com.togetdog.cmn.SearchVO"%>
<%@page import="com.togetdog.qna.QnaVO2"%>
<%@page import="com.togetdog.qna.QnaVO"%>
<%@page import="com.togetdog.member.MemberDao"%>
<%@page import="com.togetdog.member.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    errorPage="/cmn/error.jsp"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>      
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
   MemberVO vo123=(MemberVO)session.getAttribute("user");
   
   String id =vo123.getMemberId();
   String message="";
   String mempop="";
   SearchVO sv=new SearchVO();
   sv.setPageSize(1000);
   sv.setPageNum(1);
   
   MemberDao memdao=new MemberDao();
   
   List<MemberVO> list=  (List<MemberVO>)memdao.doRetrieve(sv);
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

    <link rel="stylesheet" href="css/slick.css">
    <link rel="stylesheet" href="css/slicknav.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- <link rel="stylesheet" href="css/responsive.css"> -->
 <!-- 폰트 다운로드 -->
<link
   href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua&display=swap"
   rel="stylesheet">
<link
   href="https://fonts.googleapis.com/css?family=Cute+Font|Gothic+A1|Noto+Sans+KR&display=swap"
   rel="stylesheet">
<link
   href="https://fonts.googleapis.com/css?family=Cute+Font|Gothic+A1|Nanum+Gothic|Noto+Sans+KR&display=swap"
   rel="stylesheet">
    <style>
        #desertionTitle {
           font-family: 'Do Hyeon', sans-serif;
        }
        #desertionSubTitle {
           font-family: 'Do Hyeon', sans-serif;
           font-size: 30px;
        }
        #adopTitle{
           font-family: 'Do Hyeon', sans-serif;
           font-size: 30px;
        }
        #noticeEd {
           font-family: 'Do Hyeon', sans-serif;
           font-size: 25px;
        }
        #imgDiv {
           height: 300px;
        }
        .cmnTitle {
        font-size: 20px;
           font-family: 'Jua', sans-serif;
           font-family: 'Do Hyeon', sans-serif;
        }
        .cmnContent {
           font-family: 'Nanum Gothic';
           font-size: 20px;
        }
        .imgDiv{
             height: 300px;
        }
    </style>
</head>

<body>
    <!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

    <!-- header-start -->
  <%@ include file="/cmn/togetdog_header.jsp" %>
 <!-- header-end -->
 
     <!-- bradcam_area  -->
     <div class="bradcam_area bradcam_bg_2">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="bradcam_text text-center">
                           <h3 id="desertionTitle">Contact</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--/ bradcam_area  -->
  <!-- ================ contact section start ================= -->
  <section class="contact-section section_padding">
    <div class="container">
      <div class="d-none d-sm-block mb-5 pb-4">
   

      <div class="row">
        <div class="col-12">
          <h2 class="contact-title" id="adopTitle">문의메일 보내기</h2>
        </div>
        <hr/>
        <div class="col-lg-8">
          <form class="form-contact contact_form" action="contact_process.php" method="post" id="contactForm" novalidate="novalidate">
            <div class="row">
              <div class="col-12">
                <div class="form-group">
                  <div class="col-12">
                <div class="form-group">
                  <input class="form-control" name="subject" id="subject" type="text" onfocus="this.placeholder = ''" onblur="this.placeholder = '제목'" placeholder = '제목'>
                </div>
              </div>
                    <textarea class="form-control w-100" name="message" id="message" cols="30" rows="9" onfocus="this.placeholder = ''" onblur="this.placeholder = 내용'" placeholder = '내용'></textarea>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group">
                  <input class="form-control" name="name" id="name" type="text" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter your name'" value="<%=id %>" disabled>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group">
                  <input class="form-control" name="email" id="email" type="email" onfocus="this.placeholder = ''" onblur="this.placeholder = '연락받을 e-mail'" placeholder = '연락받을 e-mail'>
                </div>
              </div>
              
            </div>
            <div class="form-group mt-3">
              <button  type="button" onclick="javascript:gomail();" class="button button-contactForm btn_4 boxed-btn">메일 보내기</button>
            </div>
          </form>
          
        </div>
        
        
          <div class="col-lg-4" >
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-home"></i></span>
            <div class="media-body">
          
              <p id="cmnContent">Mapo,  Republic of Korea. 21<br/> World Cup buk-ro</p>
            </div>
          </div>
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-tablet"></i></span>
            <div class="media-body">
              <p id=cmnTitle> (02) 336 8546 <br/>Mon to Fri 9am to 6pm</p>
              
            </div>
          </div>
          <div class="media contact-info">
            <span class="contact-info__icon"><i class="ti-email"></i></span>
            <div class="media-body">
       
              <p id="cmnContent">helpdog@togetdog.com</p>
             
            </div>
          </div>
        </div>
            </div>
      </div>
    </div>
  </section>
  <!-- ================ contact section end ================= -->

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
                function gomail(){
                    
                var email=('helpdog@togetdog.com');
                var subject = ('<%=id%>님의 문의사항입니다.');
                var body = $('#message').val();
                subject+=  $('#subject').val();
                body+=  $('#email').val();
                body+=('로 연락주세요');
                document.write('<a href="mailto:' + email + '?subject=' +subject+ '&body=' +body+'" id="chk">' + '전송완료' + '<'+'/a>');
                 chk.click(); 
                 history.go(0) ; 
            }
            </script>
</body>

</html>