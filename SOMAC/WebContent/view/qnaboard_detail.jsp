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
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page import="com.togetdog.qna.QnaVO2"%>
<%@page import="com.togetdog.member.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/cmn/common.jsp" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
List<QnaVO2> replyList = (List<QnaVO2>)request.getAttribute("replyList");
  

MemberVO vo123=(MemberVO)session.getAttribute("user");

String id =vo123.getMemberId();


%>

 
<!doctype html>
<html class="no-js" lang="zxx">

<head>
   <meta charset="utf-8">
   <meta http-equiv="x-ua-compatible" content="ie=edge">
   <title>TOGETEDOG</title>
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
           font-size: 20px;
        }
        #noticeEd {
           font-family: 'Do Hyeon', sans-serif;
           font-size: 15px;
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
 <!-- header-start -->
  <%@ include file="/cmn/togetdog_header.jsp" %>
 <!-- header-end -->

      <!-- bradcam_area  -->
      <div class="bradcam_area bradcam_bg_2">
             <div class="container">
                 <div class="row">
                     <div class="col-xl-12">
                         <div class="bradcam_text text-center">
                      
                             <h3 id="desertionTitle">상세 게시판</h3>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
         <!--/ bradcam_area  -->

   <!--================Blog Area =================-->
   <form action='<%=HR_PATH%>/view/qna.do' method="post" name="insert_frm" >
   <section class="blog_area single-post-area section-padding">
     <input type="hidden" name="work_div" id="work_div"/>
     <input type="hidden" name="qna_no" id="qna_no" value="${vo.qna_no}"/>
   
      <div class="container">
         <div class="row">
            <div class="col-lg-8 posts-list">
               <div class="single-post">
                  <div class="feature-img">
                      <!--  <img <="imgsize" class="img-fluid" src=""alt="이미지 들어갈곳">-->
                       <img id="imgsize" class="img-fluid" src="${vo.imgPath}${vo.ext}"alt="">
                      
                  </div>
                  <div class="blog_details">
                 
             <input type="text" placeholder="제목" class="form-control" value="${vo.title}" name="title" id="title" size="20"  />
                    
                     <ul class="blog-info-link mt-3 mb-4">
                       <li><i class="fa fa-user"></i>${vo.qna_no}</li>
                        <li><i class="fa fa-user"></i>${vo.memberId}</li>
                         <c:set var="TextValue" value="${vo.modDt}"/>
                         <li id="cmnContent"><i class="fa fa-user"></i>작성일&nbsp;<c:out value="${fn:substring(TextValue,0,16)}"></c:out></li><!-- 작성일 표시 -->   
                           <input type="button"  class="genric-btn primary small" onclick="javascript:centerPopup(this.form);" value="이미지 수정">
                           <input type="hidden" name="fname" id="fname"  >
                      </ul>
                         
                        
                     <div class="quote-wrapper">
                        <div class="quotes" id="testTitle">
                         <p id="cmnContent"> <textarea rows="5"  class="form-control"  name="firstcontents" id="firstcontents" cols="40"> 
                          ${vo.contents}</textarea></p>
                        </div>
                           
                     </div>
                  </div>
                  <input  type="button"  class="genric-btn success circle" value="수정" id="update_btn" />
                  <input  type="button"  class="genric-btn danger circle"    value="삭제" id="delete_btn" />
               </div>
             
               <div class="comments-area">
               <p class="like-info" ><span class="align-middle"></span> COMMENTS ♡ </p><hr/>
                  <c:choose>   
                    <c:when test="${replyList.size()>0}">  
                        <c:forEach var="revo" items="${replyList}">
                          <div class="comment-list">
                          
                          <c:set var="msg" value="Y"/> 
                            <c:if test="${msg == revo.replyYn}">
                            
                             <div class="single-comment justify-content-between d-flex">
                                <div class="user justify-content-between d-flex">
                                   <div class="thumb">
                                      <img class="card-img rounded-0" src="img/blog/logo-admin-png-7.png" alt="">
                                   </div>
                                   <div class="desc">
                                      <table id="listTable" style="display:inline;">
                                            <tr>
	                                           <td><c:out value="<font color='white'>${revo.rseqNo}</font>" escapeXml="false" /></td>
	                                           <td id="cmnContent"><input type="text"  value="${revo.rcontents}" id="rcontents" name="rcontents"  style="width:400px; border: 0px"/></td>
	                                           <td id="noticeEd" ><input  type="button"   value="수정" /></td>
	                                         </tr>
                                       </table>
                                      
                                       
                                         <table id="listTable2" style="display:inline;">
                                            <tr>
                                               <td><c:out value="<font color='white'>${revo.rseqNo}</font>" escapeXml="false" /></td>
                                                <td id="noticeEd"><input  type="button"   value="삭제" id="re_del_btn"/></td>
                                             </tr>
                                       </table>
                                       
                                      <div class="d-flex justify-content-between">
                                         <div class="d-flex align-items-center">
                                            <p id="noticeEd"><c:out value=" 　　　${revo.rregId}　${revo.rregDt}"></c:out>
                                         </div>
                                      </div>
                                   </div>
                                </div>
                             </div>
                                </c:if>
                          </div>
                        </c:forEach> 
                    </c:when>      
                  </c:choose>        
               </div>
               
               <div class="comment-form">
                  <p id="adopTitle">댓글 남기기</p>
                  <form class="form-contact comment_form" action='<%=HR_PATH%>/view/community.do' method="post" name="reply_insert_frm" readonly>
                     <div class="row">
                        <div class="col-sm-6">
                           <div class="form-group">
                              <input class="form-control" name="reg_id" id="reg_id" type="text" value="<%= id %>" style="border :1;font-size:1.4em;" readonly>
                           </div>
                        </div>
                        <div class="col-12">
                           <div class="form-group">
                              <textarea class="form-control w-100" name="mainreply" id="mainreply" cols="30" rows="9"></textarea>
                           </div>
                        </div>
                       
                     </div>
                     <div class="form-group">
                        <button type="button"  id="reple_insert_btn" class="button button-contactForm btn_1 boxed-btn">댓글 등록하기</button>
                         <button type="button"  class="button button-contactForm btn_1 boxed-btn" >　　　　　　　　　　　</button>
                         <button type="button" id="back_btn" class="button button-contactForm btn_1 boxed-btn">목록으로 이동</button>
                     </div>
                     
                  </form>
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
<%
SimpleDateFormat format1 = new SimpleDateFormat ("yy/MM/dd");
Date time = new Date();
String time1 = format1.format(time);
%>
    <script type="text/javascript">
    function goRetrieve(){
        window.location.href='/SOMAC/view/qna.do?work_div=do_retrieve';
    }
    
  //게시물 수정
    $("#update_btn").on("click",function(){
        console.log("#update_btn");
          //qna_no
        var qna_no = $("#qna_no").val();
        if(null ==qna_no || qna_no.trim().length ==0){
            alert("순번을 확인 하세요.");
            return;
        }
        //title
        var title = $("#title").val();
        if(null ==title || title.trim().length ==0){
            $("#title").focus();
            alert("제목을 확인 하세요.");
            return;
        }
        //contents
        var firstcontents = $("#firstcontents").val();
        if(null ==firstcontents || firstcontents.trim().length ==0){
            $("#firstcontents").focus();
            alert("내용을 확인 하세요.");
            return;
        }
        
        if(false==confirm(title+'을(를)\n수정 하시겠습니까?'))return;
        
        
        //ajax
        $.ajax({
            type:"POST",
            url:"/SOMAC/view/qna.do",
            dataType:"html", 
            data:{"work_div":"do_update",
                  "qna_no":$("#qna_no").val(),
                  "reg_id":$("#reg_id").val(),
                  "title":$("#title").val(),
                  "firstcontents":$("#firstcontents").val(),
                   "fname":$("#fname").val(),
                   "modDt":<%=time1%>
            },
          
            success:function(data){ //성공
                console.log("data="+data);
               
               var jData = JSON.parse(data);
               //성공
               if(null !=jData && jData.msgId=="1"){
                   alert(jData.msgContents);
                   goRetrieve();
               }else{
                   alert(jData.msgId+"||"+jData.msgContents);
               }

            },
            error:function(xhr,status,error){
                alert("error:"+error);
            },
            complete:function(data){

            }   
        });//--ajax     
       
    });
    
    //댓글 삭제
    $("#listTable2>tbody").on("click","tr",function(event){
    	var reg_id = $("#reg_id").val();
        if(reg_id!= "ADMIN"){
            
              alert("관리자만 댓글을 삭제 할 수 있습니다. ");
              return;
        }
            var pTR = $(this); //tr
            var pTR_Children = pTR.children();
            var rseq_no = pTR_Children.eq(0).text();
                
            if(false==confirm('삭제 하시겠습니까?'))return;
        //ajax
          $.ajax({
                type:"POST",
                url:"/SOMAC/view/qna.do",
                dataType:"html",
                data:{"work_div":"do_re_del",
                      "qna_no":$("#qna_no").val(),                  
                      "rseq_no": rseq_no
                    
                },
            success:function(data){ //성공
               console.log("data="+data);
              
               var jData = JSON.parse(data);
               //성공
               if(null !=jData && jData.msgId=="1"){
                   alert(jData.msgContents);
                   goRetrieve();
               }else{
                   alert(jData.msgId+"||"+jData.msgContents);
               }

            },
            error:function(xhr,status,error){
             alert("error:"+error);
            },
            complete:function(data){

            }   
        });//--ajax     
        
        
    });
    

    //삭제
    $("#delete_btn").on("click",function(){
      
        var qna_no = $("#qna_no").val();
    
        var reg_id = $("#reg_id").val();
        
        if(reg_id== "ADMIN"||reg_id=="<%=id%>"){
            
           
     
        console.log("qna_no="+qna_no);
        if(qna_no == null){
            alert("순번을 확인 하세요.");
            return;
        }
        
        if(false==confirm('삭제 하시겠습니까?'))return;
        //ajax
        $.ajax({
            type:"POST",
            url:"/SOMAC/view/qna.do",
            dataType:"html", 
            data:{"work_div":"do_delete",
                  "qna_no":$("#qna_no").val()
            },
            success:function(data){ //성공
               console.log("data="+data);
               
               var jData = JSON.parse(data);
               //성공
               if(null !=jData && jData.msgId=="1"){
                   alert(jData.msgContents);
                   goRetrieve();
                  
               }else{
                   alert(jData.msgId+"||"+jData.msgContents);
               }

            },
            error:function(xhr,status,error){
             alert("error:"+error);
            },
            complete:function(data){

            }   
        });//--ajax     
        
        }else{
	        	alert("작성자만 글을 삭제 할 수 있습니다. ");
	            return;
	         }
    });

        
    
        $("#back_btn").on('click',function(){
        	
        	goRetrieve();
        });
    
        $("#reple_insert_btn").on('click',function(){
        	var reg_id = $("#reg_id").val();
        	if(reg_id!= "ADMIN"){
        		
        		  alert("관리자만 댓글을 달 수 있습니다. ");
                  return;
        	}
        	
             
             if(null == reg_id || reg_id.trim().length==0){
                 $("#reg_id").focus();//focus
                 alert("작성자를 확인 하세요. ");
                 return;
             }
             var mainreply = $("#mainreply").val();
             if(null == mainreply || mainreply.trim().length==0){
                 $("#mainreply").focus();//focus
                 alert("내용을 확인 하세요. ");
                 return; 
             }
            
           
             
             $.ajax({
                type:"POST",
                url:"/SOMAC/view/qna.do",
                dataType:"html",
                data:{"work_div":"do_reply_insert",
                      "qna_no":$("#qna_no").val(),
                      "mainreply":$("#mainreply").val(),
                      "reg_id":$("#reg_id").val()
                },
                success:function(data){ //성공
                    var jsonObj = JSON.parse(data);
                    if(jsonObj !=null && jsonObj.msgId == "1"){
                        alert(jsonObj.msgContents);        
                      
                        window.location.href='/SOMAC/view/qna.do?qna_no='+${vo.qna_no};
                    } else{
                        alert(jsonObj.msgId+"|"+ jsonObj.msgContents);
                        
                    }
                    
                },error:function(xhr,status,error){
                    alert("error:" + error);
                },complete:function(data){
                
                }
                
               });//--ajax
             
        });
     
        
        
   //댓글수정
        $("#listTable>tbody").on("click","tr",function(event){
        	var reg_id = $("#reg_id").val();
            if(reg_id!= "ADMIN"){
                
                  alert("관리자만 댓글을 수정할 수 있습니다. ");
                  return;
            }
            var pTR = $(this); //tr
            var pTR_Children = pTR.children();
            var rseq_no = pTR_Children.eq(0).text();
          
        
            if(false==confirm('수정 하시겠습니까?'))return;
            
            $.ajax({
                type:"POST",
                url:"/SOMAC/view/qna.do",
                dataType:"html",
                data:{"work_div":"do_re_update",
                      "qna_no":$("#qna_no").val(),
                      "rcontents":$("#rcontents").val(),
                      "reg_id":$("#reg_id").val(),
                      "rseq_no": rseq_no
                    
                },
                success:function(data){ //성공
                    var jsonObj = JSON.parse(data);
                    if(jsonObj !=null && jsonObj.msgId == "1"){
                        alert(jsonObj.msgContents);  
                        window.location.href='/SOMAC/view/qna.do?qna_no='+${vo.qna_no};
                    } else{
                        alert(jsonObj.msgId+"|"+ jsonObj.msgContents);
                        
                    }
                    
                },error:function(xhr,status,error){
                    alert("error:" + error);
                },complete:function(data){
                
                }
                
               });//--ajax
            
        });
        function centerPopup(frm){
            console.log('centerPopup');
         
            var title = "팝업(제목)";
            var xWidth = window.screen.width/2;
            var xHeight = window.screen.height/2;
            console.log('xWidth:'+xWidth);
            console.log('xHeight:'+xHeight);
            
            var pX = xWidth - 400/2;
            var pY = xHeight - 300/2;
            
            console.log('pX:'+pX);
            console.log('pY:'+pY);
            
            var status ="toolbar=0,scrollbars=no,resizable=no,status=yes,width=400,height=300,left="+pX+',top='+pY;
            window.open("",title,status);
            
            frm.target = title;
            frm.method = "post";
            frm.action = "<c:url value='/view/community_popup.jsp' /> ";
            frm.submit();
            
            
        }
        
        function setValue(nm){
            document.getElementById("fname").value = nm;
        }

        
        
    </script>   
    
</body>

</html>