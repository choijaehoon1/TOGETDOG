<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/cms/error.jsp"%>
<%@ include file="../../cmn/common.jsp"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- http://localhost:8080/SOMAC_0213/member/member.do?work_div=do_insert&member_id=qwe&password=qwe&apassword=qwe&name=qwe&phone=qwe&email=qwe -->
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
  
  
  <!-- ================ contact section start ================= -->
  <section class="contact-section section_padding">
    <div class="container">
      <div class="d-none d-sm-block mb-0 pb-0">
      </div>
      <div class="row">
        <div class="col-12">
          <h2 class="contact-title" id="loginTitle">회원가입</h2>
        </div>
        <div class="col-lg-8">
          <form name="insert_frm" action="<%=HR_PATH %>/view/member.do" method="post"  >
                <input type="hidden" name="work_div" value="do_insert" id="work_div"/>
            <div class="row">
              <div class="col-12">
              </div>
              <div class="col-sm-9">
                <div class="form-group">
                  <a>아이디</a><input class="form-control" name="member_id" id="member_id" type="text" size="12" maxlength="12" onfocus="this.placeholder = '아이디를 입력하세요. (4~12자의 영문 대소문자와 숫자로만 입력)'" >
                </div>
                <div class="form-group">
                  <input class="btn btn-primary" type="button" id="do_idcheck" value="id 중복확인">
                </div>
              </div>
              <div class="col-sm-9">
                <div class="form-group">
                  <a>비밀번호</a><input class="form-control" name="password" id="password" type="password" maxlength="12" onfocus="this.placeholder = '비밀번호를 입력하세요. (4~12자의 영문 대소문자와 숫자로만 입력)'" >
                </div>
              </div>
              
              <div class="col-sm-9">
                <div class="form-group">
                  <a>비밀번호 확인</a><input class="form-control" name="apassword" id="apassword" type="password" maxlength="12" onfocus="this.placeholder = '비밀번호를 입력하세요. (4~12자의 영문 대소문자와 숫자로만 입력)'" >
                </div>
              </div>
              
              <div class="col-sm-9">
                <div class="form-group">
                  <a>이름</a><input class="form-control" name="name" id="name" type="text" onfocus="this.placeholder = '이름를 입력하세요'" >
                </div>
              </div>
              
              <div class="col-sm-9">
                <div class="form-group">
                  <a>핸드폰 번호</a><input class="form-control" name="phone" id="phone" type="text" onfocus="this.placeholder = '핸드폰 번호를 입력하세요. (ex) 010-0000-0000)'" >
                </div>
              </div>
              
              <div class="col-sm-9">
                <div class="form-group">
                  <a>이메일</a><input class="form-control" name="email" id="email" type="text" onfocus="this.placeholder = '이메일을 입력하세요. (ex) ____@naver.com)'" >
                </div>
              </div>
            </div>
            <table>
             <div class="form-group mt-3">
                <input type="button" value="회원가입" id="insert_btn" class="btn btn-danger" />
                <button class="btn btn-primary"><a href="/SOMAC/view/login.jsp" style="color: white">취소</a></button>
            </div>
            </table> 
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
 		 $("#do_idcheck").on('click',function(){
			    //alert("idCheckFunc");
 			 var work_div = $("#work_div").val() ;
 	         if(null == work_div || work_div.trim().length==0){
 	              alert("작업구분을 확인 하세요.");
 	              return;
 	         }
 	        var member_id = $("#member_id").val() ;
 	        var id = document.getElementById("member_id");
   		     if((id.value) == ""){
               alert("아이디를 입력하지 않았습니다.");
               id.focus();
               return false;
   		     }
   		     
   		     
			    $.ajax({
			        type:"POST",
			        url:"/SOMAC/view/member.do",
			        dataType:"html",
			        data:{"work_div":"do_idcheck",
			        	"member_id":member_id
			        		//$("#member_id").val()
			        	},
			        	success: function(data){
			                var jData = JSON.parse(data);
			                if(null != jData && jData.msgId=="10"){
			                   alert("사용가능한 아이디 입니다."+jData.msgContents);
			                }else if(null != jData && jData.msgId=="1"){
			                   alert("사용중인 아이디 입니다."+jData.msgContents);
			                }
			             },
			             complete:function(data){
			              
			             },
			             error:function(xhr,status,error){
			                 alert("error:"+error);
			             }
			             
			             
			   });//--ajax
			    
          }); //--#insert_btn
 		 
                
                
                
      $("#insert_btn").on('click',function(){
         //console.log("#insert_btn");
         var work_div = $("#work_div").val() ;
         if(null == work_div || work_div.trim().length==0){
              alert("작업구분을 확인 하세요.");
              return;
         }
    
         var id = document.getElementById("member_id");
         var re = /^[a-zA-Z0-9]{4,12}$/;
        	 if ((id.value) == ""){
                 alert("아이디를 입력하지 않았습니다.");
                 id.focus();
                 return false;}
        	 else if (!check(re,id,"아이디는 4~12자내로 입력하세요  대소문자와 숫자로만 입력 가능합니다.")) {
        		  alert("아이디 양식에 맞게 입력하세요");
        		  re.focus();
                  return false;
                  }//반환 할 곳 없이 if문 탈출
 
         var pw = document.getElementById("password");
        	 if ((pw.value) == ""){
                 alert("비밀번호를 입력하지 않았습니다.");
                 pw.focus();
                 return false;
             }
        	 else if(!check(re,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
        		 alert("비밀번호 양식에 맞게 입력하세요");
        		 re.focus();
        		 return false;
        		 
        	 }	 
          var apw = document.getElementById("apassword");
            if ((apw.value) == ""){
                   alert("비밀번호를 입력하지 않았습니다.");
                   apw.focus();
                   return false;
             }		 
        	 
        	 else if(!check(re,apw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
        		 alert("비밀번호 양식에 맞게 입력하세요");
        		 re.focus(); 
        		 return false;
        		 
        	 } 
        	 else if(pw.value != apw.value) {
                 alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
                 apw.value = "";
                 apw.focus();
                 return false;
             }
        	
         
         var name = $("#name").val();
         if(null == name || name.trim().length==0){
             $("#name").focus();//focus
             alert("이름을 입력하세요. ");
             return;
             
         }
         
         var phone = $("#phone").val();
         if(null == phone || phone.trim().length==0){
             $("#phone").focus();//focus
             alert("핸드폰 번호를 입력하세요. ");
             return;
             
         }
         
         var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
         var email = document.getElementById("email");
         if(email.value=="") {
             alert("이메일을 입력해 주세요");
             email.focus();
             return false;
         }
         else if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
             return false;
         }
      
           
         if(false == confirm("회원가입을 하시겠습니까?"))return;
         
        // var frm = document.insert_frm;
        // frm.work_div.value = "do_insert";
       //  frm.action = "/ZSOMAC_0228/view/member.do";                       
       //  frm.submit();
         
         function check(re,what,message){//정규화데이터,아이템 id,메세지
             if (re.test(what.value)) {//what의 문자열에 re의 패턴이 있는지 나타내는 함수 test
             //만약 내가 입력한 곳의 값이 정규화 데이터를 썼다면 true를 반환해서 호출 된 곳으로 리턴됨
                 return true;
             }
             alert(message);
             what.value = "";
             what.focus();
         }
      
       
         //ajax
         $.ajax({
          type:"POST",
         url:"/SOMAC/view/member.do",
         dataType:"html",
         data:{"work_div":work_div,
              "member_id":$("#member_id").val(),
              "password":$("#password").val(),
              "name":$("#name").val(),
              "phone":$("#phone").val(),
              "email":$("#email").val()
              
         },
         success:function(data){   //성공
            //json String(웹에서는 json String임)을 json object로 바꿔야함
            console.log(data);
            
            var jsonObj = JSON.parse(data);
            //console.log("msgId="+ jsonObj.msgId);
            //console.log("msgContents="+ jsonObj.msgContents);
            //alert("data" + data);
            
            if(jsonObj !=null && jsonObj.msgId == "1"){
               alert(jsonObj.msgContents);
               window.location.href='<%=HR_PATH %>/view/login.jsp';
            } else{
               alert(jsonObj.msgId+"|"+ jsonObj.msgContents);
            }
            
         },error:function(xhr,status,error){
            alert("error:" + error);
         },complete:function(data){
         
         }
         
         });//--ajax
         
         
         //console.log("#work_div="+work_div);
         //console.log("#title="+title);
         //console.log("#reg_id="+reg_id);
         //console.log("#contents="+contents);
         
      });//--#insert_btn
      
      
      $(document).ready(function(){
          //console.log("document ready");
          
   });
   </script>
   
</body>

</html>