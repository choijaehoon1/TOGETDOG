<%@page import="com.togetdog.member.MemberVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../cmn/common.jsp"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="ko">

<head>
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="<c:url  value='/js/bootstrap.min.js' />"> </script>
    <script src="https://code.jquery.com/jquery-2.2.4.js"></script>

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
    <style>
    	#logout-basic{
    		color:white;
    	}
    	#do_login{
    	
    		background-color: #212529;
    	}
    
    </style>



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

		#loginTitle {
		   font-family: 'Do Hyeon', sans-serif;
		}

		#loginTitle {
		   font-family: 'Do Hyeon', sans-serif;
		}
		.cmnContent {
		   font-family: 'Nanum Gothic';
		   font-size: 20px;
		}

	</style>
</head>

<body>
    <!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->

    <!-- header-start -->
   		   <header>
            <div class="header-area ">
                <div id="sticky-header" class="main-header-area">
                    <div class="container-fluid ">
                        <div class="header_bottom_border">
                            <div class="row align-items-center">
                                <div class="col-xl-3 col-lg-2">
	                                <div class="logo">
	                                    <a href="desertion_info.jsp">
	                                        <img src="img/logo2.png" alt="">
	                                    </a>
	                                </div>
                                </div>
                                <div class="col-xl-6 col-lg-7">
                                    <div class="main-menu  d-none d-lg-block">
                                        <nav>
                                            <ul id="navigation">
                                                    <ul class="submenu">
                                                    </ul>
                                                </li>
                                                <li><a href="about.html"></a></li>
                                                    <ul class="submenu">
                                                    </ul>
                                                </li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                                <div class="col-xl-3 col-lg-3 d-none d-lg-block">
                                    <div class="Appointment">
                                        <div class="book_btn d-none d-lg-block">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="mobile_menu d-block d-lg-none"></div>
                                </div>
                            </div>
                        </div>
    
                    </div>
                </div>
            </div>
        </header>
    <!-- header-end -->
    
    
     <!-- bradcam_area  -->
     <div class="bradcam_area bradcam_bg_2">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="bradcam_text text-center">
                            <h3 id="loginTitle">TOGETDOG</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--/ bradcam_area  -->
  
  
  <!-- ================ 로그인 페이지 ================= -->
  <section class="contact-section section_padding">
    <div class="container">
      <div class="d-none d-sm-block mb-0 pb-0">
      </div>
      <div class="row">
        <div class="col-12">
          <h2 class="contact-title" id="loginTitle">로그인</h2>
        </div>
        <div class="col-lg-8">
          <form   name="login_frm" action="/SOMAC/view/member.do" id="login_frm"  method="post"  >
             <input type="hidden" name="work_div" value="do_login" />
             <input type="hidden" name="id_check" value="0" />
            <div class="row">
              <div class="col-12">
              </div>
              <div class="col-sm-9">
                <div class="form-group">
                  <input class="form-control" name="member_id" id="member_id" type="text" onfocus="this.placeholder = ''" onblur="this.placeholder = '아이디를 입력하세요'" placeholder = '아이디를 입력하세요'>
                </div>
              </div>
              
              <div class="col-sm-9">
                <div class="form-group">
                  <input class="form-control" name="password" id="password" type="password" onfocus="this.placeholder = ''" onblur="this.placeholder = '비밀번호를 입력하세요'" placeholder = '비밀번호를 입력하세요'>
                </div>
              </div>
       		 </div>
         </form>
            <div class="form-group mt-2">
            	 <title>API Demo - Kakao JavaScript SDK</title>
				<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
            	 <a id="kakao-login-btn" ></a>
            	<input class="btn btn-primary" id="do_login" value="로그인" >
				<button class="btn btn-danger"><a id="dologout" href="/SOMAC/view/logout.jsp"style="color: white">로그아웃</a></button>
				 <form action="/SOMAC/view/member.do" name="form2" method="post" id="form2">
                     <input type="hidden" name="work_div" value="do_logout" />
                </form>
            </div>
          <form class="form-inline">
            <div class="form-group mt-1">
             <button class="btn btn-xs"><a href="/SOMAC/view/searchid.jsp">아이디찾기</a></button>&nbsp;&nbsp;&nbsp;
             <button class="btn btn-xs"><a href="/SOMAC/view/searchpw.jsp">비밀번호 찾기</a></button>
            </div>
          </form>  
          
          
            <div class="form-group mt-2" >
             <button type="button" onclick="location.href='signup.jsp'" class="btn btn-default btn-block" >회원가입</button>  
            </div>
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
              <p id="cmnTitle"> (02) 336 8546 <br/>Mon to Fri 9am to 6pm</p>
              
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
  </section>
  <!-- ================로그인페이지================= -->

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
    
    //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('e665807e352e02746b9b2948a9f15340');
    // 카카오 로그인 버튼을 생성합니다.
    Kakao.Auth.createLoginButton({
      container: '#kakao-login-btn',
      success: function(authObj) {
        // 로그인 성공시, API를 호출합니다.
        Kakao.API.request({
          url: '/v2/user/me',
          success: function(res) {
            var member_id = res.id;      //유저의 카카오톡 고유 id
            var name = res.properties.nickname; //유저가 등록한 별명
            alert(JSON.stringify(res));
            alert(JSON.stringify(authObj));
            
            console.log("member_id "+member_id);
            console.log("name "+name);
            var password = "api";
            var email = "api";
            var phone = "api";
            
        		
        		//ajax
			    $.ajax({
			           type:"POST",
			           url:"/SOMAC/view/member.do",
			           dataType:"html",
			           data:{
			           "work_div":"do_kdlogin",
			           "member_id":member_id
			    }, 
			    success:function(res){   //성공
	              //json String(웹에서는 json String임)을 json object로 바꿔야함
	              console.log(res);
	              
	              var jsonObj = JSON.parse(res);
	              //console.log("msgId="+ jsonObj.msgId);
	              //console.log("msgContents="+ jsonObj.msgContents);
	              //alert("data" + data);
	              
	              if(jsonObj !=null && jsonObj.msgId == "1" ){
	                  alert(jsonObj.msgContents);
	                  window.location.href='<%=HR_PATH %>/view/desertion_info.jsp';
	               } else{
	                  alert(jsonObj.msgId+"|"+ jsonObj.msgContents);
	                  window.location.href='<%=HR_PATH %>/login.jsp';
	               }
	           },           
	           complete:function(data){
	               
	           },
	           error:function(xhr,status,error){
	               alert("error:"+error);
	           }
	          }); 
	          //--ajax
            
            //$("#member_id").val(member_id) ;
            //$("#name").val(name);
            //var id_api = $("#member_id").val(member_id);
            //console.log("id_api "+id_api);
            //var name_api = $("#name").val(name);
            //console.log("name_api "+name_api);
         
         		//ajax
			    $.ajax({
			           type:"POST",
			           url:"/SOMAC/view/member.do",
			           dataType:"html",
			           data:{
			           "work_div":"do_kaInsert",
			           "member_id":member_id,
			           "password": password,
			           "name":name,
			           "phone": phone,
			           "email": email
			    }, 
			    success:function(res){   //성공
	              //json String(웹에서는 json String임)을 json object로 바꿔야함
	              console.log(res);
	              
	              var jsonObj = JSON.parse(res);
	              //console.log("msgId="+ jsonObj.msgId);
	              //console.log("msgContents="+ jsonObj.msgContents);
	              //alert("data" + data);
	              
	              if(jsonObj !=null && jsonObj.msgId == "1" ){
	                  alert(jsonObj.msgContents);
	                  window.location.href='<%=HR_PATH %>/view/desertion_info.jsp';
	               } else{
	                  alert(jsonObj.msgId+"|"+ jsonObj.msgContents);
	                  window.location.href='<%=HR_PATH %>/desertion_info.jsp';
	               }
	           },           
	           complete:function(data){
	               
	           },
	           error:function(xhr,status,error){
	               alert("error:"+error);
	           }
	          }); 
	          //--ajax
         	
         	
          },
          
          
          fail: function(error) {
            alert(JSON.stringify(error));
          }
        });
      },
      fail: function(err) {
        alert(JSON.stringify(err));
      }
    });
  //]]>
    
    
    
    
    
		$("#do_login").on("click",function() {
			
			console.log("do_login");
			
			//id입력 체크
			var member_id = $("#member_id").val();
            if(null ==member_id || member_id.trim().length ==0 ){
               $("#member_id").focus();
               alert("ID를 입력 하세요.");
               return;
            }
            //비번 체크
            var password = $("#password").val();
            if(null ==password || password.trim().length ==0 ){
               $("#password").focus();
               alert("비번를 입력 하세요.");
               return;
               
         
            }          
             
            if(false == confirm("로그인을 하시겠습니까?"))return;
			console.log("member_id:"+member_id);
			console.log("password:"+password);
			
			//ajax
		    $.ajax({
           type:"POST",
           url:"/SOMAC/view/member.do",
           dataType:"html",
           data:{
           "work_div":"do_login",
           "member_id":member_id,
           "password":password
          }, 
          success:function(data){   //성공
              //json String(웹에서는 json String임)을 json object로 바꿔야함
              console.log(data);
              
              var jsonObj = JSON.parse(data);
              //console.log("msgId="+ jsonObj.msgId);
              //console.log("msgContents="+ jsonObj.msgContents);
              //alert("data" + data);
              
              if(jsonObj !=null && jsonObj.msgId == "1" ){
                 alert(jsonObj.msgContents);
                 if(member_id=="ADMIN" || member_id=="admin"){
                     window.location.href='<%=HR_PATH %>/view/admin.jsp';
                  }else 
                     {
                	  window.location.href='<%=HR_PATH %>/view/desertion_info.jsp';
                     }
              } else{
                 alert("아이디와 패스워드를 확인하세요.");
                 
              }
              
           },      complete:function(data){
               
           },
           error:function(xhr,status,error){
               alert("error:"+error);
           }
          }); 
          //--ajax

			
		});
		
		function goLogout(){
		      if(false == confirm('로그아웃 하시겠습니까?'))return;
		      var frm = document.form2;
		      frm.submit();
		      
		   }
		
		
	
	
	</script>
	
</body>

</html>