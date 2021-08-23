
 $(document).ready(function(){
	
	let idSave ;
	let idchkcount = 0;
	let codechkcount = 0;
	let dicechkcount = 0;
		
	var login = $.cookie('logined');// 유저정보 
	
	if(login){
		const tempdata = $.cookie('tempdata');
		$("#msgDiv").html(tempdata); // 버튼그리기 
	} 

	
		
				/* ========== 이메일 인증번호 값 확인 ========== */
				$("#EmailVerify").click(function(){
					
					const dice = $("#emailcode").val();
					const verifyCode = $("#verifyCode").val();
					
					if(dice == verifyCode) {
						dicechkcount++;
						alert("이메일이 인증되었습니다.");
						const target = 
						document.getElementById('email');
						target.disabled = true;
						const btnElement = 
						document.getElementById('EmailCodeSend')
						btnElement.innerText = '인증완료';
						const target2 = 
						document.getElementById('EmailCodeSend');
						target2.disabled = true;
						const target3 = 
						document.getElementById('EmailVerify');
						target3.disabled = true;
						return;
					} else {
						alert("이메일 인증 번호를 다시 입력해주세요");
						return;
					}
				});//function끝
				
				
				/* ========== 이메일 인증번호 보내기 ========== */
				$("#EmailCodeSend").click(function(){
					alert("인증번호 발송까지 약 10초정도 소요됩니다.\n확인버튼을 누르고 기다려주세요.");
					var email=$("#email").val();
				
				$.post("/codesend",
					   {email : email},
					
					function(data, status){
						if ( data == 0 ) {
							alert("이메일 인증에 실패하였습니다.\n이메일을 다시 입력해주세요.");
							return;
						} else {
							const target = 
							document.getElementById('email');
							target.disabled = true;
							codechkcount++ ;
							alert("인증번호를 전송했습니다.\n이메일 확인 후 입력해주세요.");
							$("#verifyCode").val(data);
							$("#verifyDiv").show();
						}
					});
				});
					
		
				/* ========== 로그인 ========== */
				$("#loginBtn").click(function(){
						//$('#signupDiv').hide();
						
						var id=$("#id").val();
						var pw=$("#pw").val();
						
						if(id == ""){
							alert("아이디를 입력하세요.");
							$("#id").focus();
							return;
						}
						if(pw == ""){
							alert("패스워드를 입력하세요.");
							$("#pw").focus();
							return;
						}
						
						$.post("/login",
							  {	id, pw },
							  function(data, status){			
							  	if(data.nickname){
								  	$.cookie('logined', data.id, { path: '/' }); //쿠키이름 logined data를 담음
							  		data = "<div>" + data.nickname + "님 환영합니다.   <a id='mypageBtn' class='btn scrollto text-light'>마이페이지</a><a type='button' value='logout' id='logoutBtn' class='btn scrollto text-light'>로그아웃</a>";
								  	$.cookie('tempdata', data, { path: '/' }); //쿠키이름 logined data를 담음
								  	
									location.href = "/";
									
									console.log("hide확인");
							  	}else{
							  		alert("아이디 혹은 비밀번호를 확인해주세요.");
									location.reload();	
							  	}
							 }
						);//end post()
					});//end 로그인 처리
					
					/* ========== 마이페이지 ========== */
					$('#mypageBtn').click(function(){
						$(location).attr('href','/html/mypage.html')
					});
					
					
					
					/* ========== 로그아웃 ========== */
					$(document).on("click", "#logoutBtn", function(event) {
					
					$.post("logout",
						  {	},
					  	  function(data, status){		  	
					 		$.removeCookie("logined", { path: '/'});
					 		$.removeCookie("tempdata", { path: '/'});
							location.reload();
										   
					 	 }
					   );//end post() 
					});//end 로그아웃 처리
					
					
					
					

					/* ========== 아이디 중복확인 ========== */
					$("#IdChkBtn").click(function(){
					var id = $('#id').val();			// id에 입력되는 값
					var data = {id : id}				// '컨트롤에 넘길 데이터 이름' : '데이터(.id에 입력되는 값)'
				
					var idcheck= /^[a-zA-Z0-9]{4,12}$/;
					if (!idcheck.test(id)) {
					    alert("아이디는 영문, 숫자 4~12자리만 사용 가능합니다.");
					 	$('#id').val('');
					    id.focus();
						return;
					}	
				
					$.ajax({
					type : "post",
					url : "/customerIdChk",
					data : data,
					success : function (result){
							if(result != 'fail'){
								idSave = data;
								idchkcount++;
								alert("사용가능한 아이디입니다.");
								$('.id_input_re_2').css("display","none");
								$('#pw').focus();						
							} else {
								$('.id_input_re_2').css("display","inline-block");
								$('.id_input_re_1').css("display", "none");
								$('#id').val('');
							}
						},
						error:function(){
						alert('error');
						}// success 종료
						 
					}); // ajax 종료
					
				});// function 종료	

			
				 /* ========== 회원 가입 ========== */
			 	$("#memberInsertBtn").click(function(){	
				
				
					var id = document.getElementById("id");
					var pw = document.getElementById("pw");
					var cpass = document.getElementById("cpass");
					var nickname = document.getElementById("nickname");
					var birth = document.getElementById("birth");
					var email = document.getElementById("email");
					
					if(id.value == ""){
						alert("아이디를 입력하세요.");
						id.focus();
						return false;
					};
					
					//아이디체크 영문, 숫자 4~12자리만 되게
					var idcheck= /^[a-zA-Z0-9]{4,12}$/;
					 if (!idcheck.test(id.value)) {
				    alert("아이디는 영문, 숫자 4~12자리만 사용 가능합니다.");
				    id.value="";
				    id.focus();
				    return false;
					}	    
				    
				    if(idchkcount == 0){
					  alert('아이디 중복확인을 꼭 해주세요.');
					  return;
				    }			
					
					
					if(pw.value == ""){
						alert("비밀번호를 입력하세요.");
						pw.focus();
						return false;
					};
			
					//비밀번호 영문+숫자+특수문자로 8~16자리
					 var pwCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
					if(!pwCheck.test(pw.value)) {
					    alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~16자리 사용해야 합니다.");
					    pwd.value="";
					    pwd.focus();
					    return false;
			 		 };
					
					 if(cpass.value !== pw.value) {
					    alert("비밀번호가 일치하지 않습니다.");
					    repwd.focus();
					    return false;
					  };
					
					
					if(nickname.value == ""){
						alert("닉네임을 입력해주세요.");
						nickname.focus();
						return false;
					}
			
					//닉네임 한글 2~6자리만 되게
					var nameCheck = /^[가-힣]{2,6}$/;
					if(!nameCheck.test(nickname.value)) {
						alert("닉네임은 한글 2~6자리만 가능합니다.");
						nickname.value="";
						nickname.focus();
						return false;
					};
			
					
					if(yy.value == ""){
						alert("생년월일을 입력해주세요.");
						yy.focus();
						return false;
					}
					
					if(mm.value == ""){
						alert("생년월일을 입력해주세요.");
						mm.focus();
						return false;
					}
					
					if(dd.value == ""){
						alert("생년월일을 입력해주세요.");
						dd.focus();
						return false;
					}
					
					/* //나이 숫자만 입력할수있게
					var birthCheck = /^[0-9]+/g;
					if (!birthCheck.test(birth.value)) {
						alert("생년월일은 숫자만 입력할 수 있습니다.");
						birth.focus();
						return false;
					}*/
					
					 if (email.value == "") {
						 alert("이메일 주소를 입력해주세요.");
						 email="";
						 email.focus();
					     return false;
					 }
					  
					 // 이메일 exmample@gmail.com 형식에 맞게 com 자리는 2~3자리만
					 var emailCheck = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
					 if (!emailCheck.test(email.value)) {
						 alert("이메일 형식에 맞게 입력해주세요 example@gmail.com")
						 email.value="";
						 email.focus();
						 return false;
					 }
					
					 if(codechkcount == 0){
					   alert('이메일 인증을 꼭 해주세요.');
					   return;
				     }			
					
					 if(dicechkcount == 0){
					   alert('이메일 인증이 완료되지 않았습니다.');
					   return;
				     }	
			
			 		let data = {};
			 		id = id.value;
			 		pw = pw.value;
			 		nickname = nickname.value;
			 		birth = yy.value+mm.value+dd.value;
			 		email = email.value;
			 		
			 		data = JSON.stringify(data);
			 		//ajax(data);
			 		
			 		if(idSave.id == id){
				 		$.ajax({
						    url: "/customerInsert",
						    data: {id, pw, nickname, birth, email},
						    type: "POST",
						    success : function(data){
						      alert("회원가입이 완료되었습니다!")
						      window.close();
						    },
						    error : function(){
						      alert("회원가입에 실패했습니다.")      
						    }
						});
					  }else{
						alert('기존에 입력하신 ID와 다릅니다.');
					  }
				});
			});
