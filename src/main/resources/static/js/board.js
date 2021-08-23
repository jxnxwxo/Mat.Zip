/**
 *  공지사항 관련 js 파일
 */

$(document).ready(function(){
	
	const user = $.cookie('logined');
	
	// 작성자 입력부분 자동완성
	 $.ajax({
		  url:"/getMember",
		  type:"post",
		  data:{ id:user },
		  success:function(data){
			
			$("#writerView").text(data.nickname);
			$("#writer").val(data.custNum);
		  },
		  error:function(){
			alert('error');  
		  }					  
	  });
});
