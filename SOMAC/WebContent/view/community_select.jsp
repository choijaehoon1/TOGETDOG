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
   		<%@ include file="/cmn/togetdog_header.jsp" %>        
    <!-- header-end -->
      <!-- bradcam_area  -->
      <div class="bradcam_area bradcam_bg_2">
             <div class="container">
                 <div class="row">
                     <div class="col-xl-12">
                         <div class="bradcam_text text-center">
                             <h3 id="communityTitle">상세 게시판</h3>
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
   	 <input type="hidden" name="seq" id="seq" value="${vo.totNo}"/>
   
      <div class="container">
         <div class="row">
            <div class="col-lg-8 posts-list">
               <div class="single-post">
                  <div class="feature-img">
                     <img id="imgsize" class="img-fluid" src="${vo.imgPath}${vo.ext}"alt="">
                  </div>
                  <div class="blog_details">
                   <h3 class="contentsTitle" ><input type="text" placeholder="제목을 입력하세요" class="form-control" value="${vo.title}" name="detail_title" id="detail_title" size="20"  /></h3>
                     <ul class="blog-info-link mt-3 mb-4">
                        <li><i class="fa fa-user"></i>${vo.memberId}</li>
                        <li><i class="fa fa-comments"></i>${vo.modDt}</li>
                        <c:if test="${check eq '1'}">
                        	<div align="right">
                        		<input type="button" class="genric-btn danger circle" value ="초기화" id="reset_btn" name="reset_btn">
	                        	<input type="button" class="genric-btn success circle" value ="수정" id="update_btn" name="update_btn">
	                        	<input type="button" class="genric-btn danger circle" value ="삭제" id="delete_btn" name="delete_btn">
	                        	<input type="button" class="genric-btn success circle" onclick="javascript:centerPopup(this.form);" value="파일 등록하기">
	                        	<input type="hidden" name="fname" id="fname"  >
                        	</div>
                        </c:if>
                     </ul>
                     <div class="quote-wrapper">
                        <div class="quotes">
                          <textarea rows="5"  class="form-control"  placeholder="내용을 입력하세요" name="detail_contents" id="detail_contents" cols="40">${vo.contents }</textarea>
                        </div>
                     </div>
                  </div>
               </div>
            
              
	<div class="comments-area">
		<c:choose>   
			<c:when test="${replyList.size()>0}">  
				<c:forEach var="revo" items="${replyList}">
					<c:if test="${revo.replyYn =='Y'}">
						<div class="comment-list">
							<div class="single-comment justify-content-between d-flex">
								<div class="user justify-content-between d-flex">
		                           <c:if test="${existcheck eq '1'}">
			                           <div class="thumb">
			                              <img src="${vo.imgPath}${vo.ext}" alt="">
			                           </div>
		                           <div class="desc">
			                           	<div class="d-flex align-items-center">
				                                    <h5 class = "contentsTitle">
				                                       <c:out value="${revo.rregId}"></c:out>
				                                    </h5>
			                                    	<p class="date contentsTitle"><c:out value="${revo.rregDt}"></c:out></p>
			                            </div>
			                            
		                              		<c:if test="${recheck eq '1'}">
					                        <div align="right">
					                        	<table id="listTable" style="display:inline-block;">
							                     	<tr>
							                     		<td><input type="text"  class="col-12" placeholder="댓글을 입력하세요"  value="${revo.rcontents}" name="reply_contents"  id="reply_contents" size="85"  /></td>
							                     		
							                     		<c:if test="${sessionScope.user.memberId == revo.rregId}" >
							                        	<td><input type="button" class="genric-btn success circle" value ="수정" id="comment_update_btn" name="comment_update_btn" onclick="javascript:test($(this));"></td>
							                     		<td><input type="hidden" value="${revo.rseqNo}" /></td>
							                     		<td><input type="hidden" value="${revo.rcontents}" /></td>
							                        	<td><input type="button" class="genric-btn danger circle" value ="삭제" onclick="javascript:removeTest($(this));" ></td>
							                        	
							                        	</c:if>
							                        	
						                        	</tr>
						                        </table>
					                        	</div>
											</c:if>
											<c:if test="${recheck eq '0'}">
												<div align="right">
					                        	<table >
							                     	<tr>
							                     		<td><input type="text"  class="col-12" placeholder="댓글을 입력하세요"  value="${revo.rcontents}"  size="85"  /></td>
							                        	
						                        	</tr>
						                        </table>
					                        	</div>
											</c:if>
		                           	  </div>
		                           </c:if>
								</div>
							</div>
		                    
                        </div>
					</c:if>
				</c:forEach> 
			</c:when>
			<c:otherwise>
					<div>
					</div>	
			</c:otherwise>      
		</c:choose> 
	</div>
		                   
	<div class="comment-form">
		<h4 class= "communityTitleFont">댓글 남기기</h4>
                  <form class="form-contact comment_form" action='<%=HR_PATH%>/view/community.do' method="post" name="reply_insert_frm" id="reply_insert_frm">
                     <div class="row">
                     	<div class="col-sm-6">
                           <div class="form-group">
                              <input class="form-control contentsTitle" name="reg_id" id="reg_id" type="text" value="<%= id %>" disabled >
                           </div>
                        </div>
                        <div class="col-12">
                           <div class="form-group">
                              <textarea class="form-control w-100" name="contents" id="contents" cols="30" rows="9"></textarea>
                           </div>
                        </div> 
                     </div>
                     <div class="form-group">
                        <button type="button"   id="reple_insert_btn" class="button button-contactForm btn_1 boxed-btn">댓글 등록하기</button>
                     </div>
                  </form>
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
	
		function resetTest(param){
			//alert(param.parent().parent().children().children().eq(1).val());
			var reset = param.parent().parent().children().children().eq(1).val();
			alert(reset);
			
		

			
		}
		
		function removeTest(param){
			var rseq = param.parent().parent().children().children().eq(2).val();
			var seq = $("#seq").val();
			
			
			if(false==confirm('삭제 하시겠습니까?'))return;
			
			$.ajax({
				type:"POST",
				url:"/SOMAC/view/community.do",
				dataType:"html", 
				data:{"work_div":"do_reply_delete",
					  "seq":seq.trim(),
					  "rseq":rseq.trim()
				},
				success:function(data){ //성공
				   console.log("data="+data);
				   //alert("data:"+data);
				   var jData = JSON.parse(data);
				   //성공
				   if(null !=jData && jData.msgId=="1"){
					   alert(jData.msgContents);
					   goReplyRetrieve();
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
			
		}
	
		
		
		
		function test(param){
			//alert(param.parent().parent().children().children().eq(0).val());
			//var rseq = param.parent().parent().children().children().eq(2).val();
			//alert(param.parent().parent().children().children().eq(2).val());
			var rcontents = param.parent().parent().children().children().eq(0).val();
			var seq = $("#seq").val();
			
			var rseq = param.parent().parent().children().children().eq(2).val();
			//alert(rseq + rcontents);
			
			if(false==confirm('수정 하시겠습니까?'))return;
			
			$.ajax({
				type:"POST",
				url:"/SOMAC/view/community.do",
				dataType:"html", 
				data:{"work_div":"do_reply_update",
					  "seq":seq.trim(),
					  "rcontents":rcontents.trim(),
					  "rseq":rseq
				},
				success:function(data){ //성공
				   console.log("data="+data);
				   //alert("data:"+data);
				   var jData = JSON.parse(data);
				   //성공
				   if(null !=jData && jData.msgId=="1"){
					   alert(jData.msgContents);
					   goReplyRetrieve();
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
			
			
		}
	
		function goReplyRetrieve(){
			window.location.href='/SOMAC/view/community.do?work_div=do_selectOne&seq='+'${vo.totNo}';
		}
		
		function centerPopup(frm){
			console.log('centerPopup');
			/*
			window.open 옵션
			no:0
			yes:1
			
			width : 팝업창 가로길이
			height : 팝업창 세로길이
			toolbar=no : 단축도구창(툴바) 표시안함
			menubar=no : 메뉴창(메뉴바) 표시안함
			location=no : 주소창 표시안함
			scrollbars=no : 스크롤바 표시안함
			status=no : 아래 상태바창 표시안함
			resizable=no : 창변형 하지않음
			fullscreen=no : 전체화면 하지않음
			channelmode=yes : F11 키 기능이랑 같음
			left=0 : 왼쪽에 창을 고정(ex. left=30 이런식으로 조절)
			top=0 : 위쪽에 창을 고정(ex. top=100 이런식으로 조절)
			*/
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
		
		
		$("#reset_btn").on('click',function(){
			$("#detail_title").val("");
			$("#detail_contents").val("");			
		});
		
		/*
		$("#listTable1>tbody").on("click","tr",function(event){
			var pTR = $(this); //tr
			var pTR_Children = pTR.children();
			var rseq = pTR_Children.eq(0).text();
			alert("rseq:" + rseq);
			
			var seq = $("#seq").val();
			
			if(false==confirm('삭제 하시겠습니까?'))return;
			
			$.ajax({
				type:"POST",
				url:"/SOMAC/view/community.do",
				dataType:"html", 
				data:{"work_div":"do_reply_delete",
					  "seq":seq.trim(),
					  "rseq":rseq.trim()
				},
				success:function(data){ //성공
				   console.log("data="+data);
				   //alert("data:"+data);
				   var jData = JSON.parse(data);
				   //성공
				   if(null !=jData && jData.msgId=="1"){
					   alert(jData.msgContents);
					   goReplyRetrieve();
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
		
		*/
		
		
		
		
		/*
		$("#listTable>tbody").on("click","tr",function(event){
			var pTR = $(this); //tr
			var pTR_Children = pTR.children();
			var rseq = pTR_Children.eq(0).text();
			var rcontents = pTR_Children.eq(1).text();
			
			var seq = $("#seq").val();
			
			
			alert(rseq + rcontents);
			
			
			
			var reply_contents = $("#reply_contents").val();
			if(null ==reply_contents || reply_contents.trim().length ==0){
				$("#reply_contents").focus();
				alert("내용을 확인 하세요.");
				return;
			}
		});
		*/
		
		
		
		$("#update_btn").on('click',function(){
			console.log("update_btn");
			
			var seq = $("#seq").val();
			
			
			
			var detail_title = $("#detail_title").val();
			if(null ==detail_title || detail_title.trim().length ==0){
				$("#detail_title").focus();
				alert("제목을 확인 하세요.");
				return;
			}
			
			var detail_contents = $("#detail_contents").val();
			if(null ==detail_contents || detail_contents.trim().length ==0){
				$("#detail_contents").focus();
				alert("내용을 확인 하세요.");
				return;
			}
			
			
			if(false==confirm('수정 하시겠습니까?'))return;
			
			
			$.ajax({
	          	type:"POST",
	  			url:"/SOMAC/view/community.do",
	  			dataType:"html",
	  			data:{"work_div":"do_update",
	  				  "seq":$("#seq").val(),
	  				  "detail_title":$("#detail_title").val(),
	  				  "detail_contents":$("#detail_contents").val(),
	  				  "fname":$("#fname").val()
	  			},
	  			success:function(data){	//성공
	  				var jsonObj = JSON.parse(data);
	  				if(jsonObj !=null && jsonObj.msgId == "1"){
	  					alert(jsonObj.msgContents);			//메시지 먼저 뜨고 아래에서 이동시킴
	  					//window.location.href='/SOMAC_0213/view/community.jsp';
	  					//goRetrieve();
	  					goReplyRetrieve();
	  				} else{
	  					alert(jsonObj.msgContents);
	  					
	  				}
	  				
	  			},error:function(xhr,status,error){
	  				alert("error:" + error);
	  			},complete:function(data){
	  			
	  			}
	  			
	           });//--ajax
			
			
			
		});
		
		$("#delete_btn").on('click',function(){
			//console.log("delete_btn");
			
			var seq = $("#seq").val();
			if(false==confirm('삭제 하시겠습니까?'))return;
			
			$.ajax({
				type:"POST",
				url:"/SOMAC/view/community.do",
				dataType:"html", 
				data:{"work_div":"do_delete",
					  "seq":seq.trim()
				},
				success:function(data){ //성공
				   console.log("data="+data);
				   //alert("data:"+data);
				   var jData = JSON.parse(data);
				   //성공
				   if(null !=jData && jData.msgId=="1"){
					   alert(jData.msgContents);
					   window.location.href='/SOMAC/view/community.do?work_div=do_retrieve';
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