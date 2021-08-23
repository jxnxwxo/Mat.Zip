/**
 * 검색페이지 js 코드들 모음
 */
 	
$(document).ready(function(){
	
	// 검색결과 호출
	searchProcess();
	
	// 검색버튼 클릭 이벤트 
	$("#searchBtn").click(function(){

		const rtAddress = $("#locationSelect").val();
		const rtGrade = $("#rtGrade").val();
		const rtName = $("#rtName").val();
		location.href = decodeURI("../html/searchPage.html?rtAddress=" + rtAddress + "&rtGrade=" + rtGrade + "&rtName=" + rtName);
	});
});

// 검색과 맨 지도 그리기
function searchProcess(){

	var urlParams = location.search.split(/[?&]/).slice(1).map(function(paramPair) {
	    return paramPair.split(/=(.+)?/).slice(0, 2);
	}).reduce(function(obj, pairArray) {
	    obj[pairArray[0]] = decodeURI(pairArray[1]);
	    return obj;
	}, {});
	
	if ( urlParams != null ) {
		if ( urlParams.rtAddress == "undefined") {
			urlParams.rtAddress = '';
		}
		$("#locationSelect").val(urlParams.rtAddress).prop("selected", true);
		if ( urlParams.rtGrade == "undefined" ) {
			urlParams.rtGrade = '';
		}
  		$("#rtGrade").val(urlParams.rtGrade).prop("selected", true);
  		if ( urlParams.rtName == "undefined" || urlParams.rtName == "" || urlParams.rtName == null ) {
  			urlParams.rtName = "";
  		}
  		$("#rtName").val(urlParams.rtName);
	}
	
	// 마커 위치
	let positions = [];
	
	// 검색 ajax
	$.post('/searchList', urlParams, function(data){
	
		console.log(data);
		let html = '';
		
		// 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
		var bounds = new kakao.maps.LatLngBounds(); 
		
		html += "<table class='table table-striped'><thead><tr><th> 이름</th><th> 미슐랭 등급</th><th> 주소</th></thead><tbody>";
		for(let i=0; i < data.rtList.length; i++){
			html += "<tr><td>" + data.rtList[i].rtName + "</td><td>" + data.rtList[i].rtGrade + "</td><td>" + data.rtList[i].rtAddress + "<td></tr>";
			let param = {};
			param.title=data.rtList[i].rtName;
			param.latlng=new kakao.maps.LatLng(data.rtList[i].rtX, data.rtList[i].rtY);
			// LatLngBounds 객체에 좌표를 추가합니다
    		bounds.extend(param.latlng);
			positions.push(param);
		}
		html += "</tbody></table>";
	
		// 마커 이미지의 이미지 주소입니다
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		
		for (let i = 0; i < positions.length; i ++) {
	 
		     // 마커 이미지의 이미지 크기 입니다
		     var imageSize = new kakao.maps.Size(24, 35); 
		     
		     // 마커 이미지를 생성합니다    
		     var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		     
		     // 마커를 생성합니다
		     var marker = new kakao.maps.Marker({
		         map: map, // 마커를 표시할 지도
		         position: positions[i].latlng, // 마커를 표시할 위치
		         title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		         image : markerImage, // 마커 이미지 
		         clickable : true
		 	});
		 	
		 	map.setBounds(bounds);
		}
		
		if(data.rtList.length == 0) {
			html = "조회하신 정보가 없습니다.";
		}
		$("#searchResultList").html(html);

		if(data.rtList.length > 0) {
			html = "";
			html += "<nav aria-label='Page navigation example'><ul class='pagination justify-content-center'><li class='page-item'><a class='page-link' onclick='movePage(" + data.paging.prev + ")\'>prev</a></li>";
			// 페이징 그리기
			for(let i=data.paging.startPage; i<=data.paging.endPage; i++) {
				html += "<li class='page-item'><a class='page-link' onclick='movePage(" + i + ")\'>"+ (i) + "</a></li>";
			}
			html += "<li class='page-item'><a class='page-link' onclick='movePage(" + data.paging.next + ")\'>next" + "</a></li></ul></nav>";
			$("#paging").html(html);
		}
		
	});
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new kakao.maps.LatLng(37.555078, 126.970702), // 지도의 중심좌표
		    level: 5 // 지도의 확대 레벨 
		}; 
		
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
}

function movePage(page) {

	let params = {};
	params.rtName = $("#rtName").val();
	params.rtAddress = $("#locationSelect").val();
	params.rtGrade = $("#rtGrade").val();
	params.currentPage = page;

	// 마커 위치
	let positions = [];

	// 검색 ajax
	$.post('/searchList', params, function(data){
		//console.log(data);
		let html = '';
		
		// 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
		var bounds = new kakao.maps.LatLngBounds(); 
		
		if(data.rtList.length > 0) {
			html += "<table class='table table-striped'><thead><tr><th> 이름</th><th> 미슐랭 등급</th><th> 주소</th></thead><tbody>";
			for(let i=0; i < data.rtList.length; i++){
				html += "<tr><td>" + data.rtList[i].rtName + "</td><td>" + data.rtList[i].rtGrade + "</td><td>" + data.rtList[i].rtAddress + "<td></tr>";
				let param = {};
				param.title=data.rtList[i].rtName;
				param.latlng=new kakao.maps.LatLng(data.rtList[i].rtX, data.rtList[i].rtY);
				// LatLngBounds 객체에 좌표를 추가합니다
	    		bounds.extend(param.latlng);
				positions.push(param);
			}
			html += "</tbody></table>";
		} else {
			html += "조회하시는 정보가 없습니다.";
		}
		

	
		// 마커 이미지의 이미지 주소입니다
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		
		for (let i = 0; i < positions.length; i ++) {
	 
		     // 마커 이미지의 이미지 크기 입니다
		     var imageSize = new kakao.maps.Size(24, 35); 
		     
		     // 마커 이미지를 생성합니다    
		     var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		     
		     // 마커를 생성합니다
		     var marker = new kakao.maps.Marker({
		         map: map, // 마커를 표시할 지도
		         position: positions[i].latlng, // 마커를 표시할 위치
		         title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		         image : markerImage, // 마커 이미지 
		         clickable : true
		 	});
		 	
		 	map.setBounds(bounds);
		}
		
		$("#searchResultList").html(html);
		
		html = "";
		html += "<nav aria-label='Page navigation example'><ul class='pagination justify-content-center'><li class='page-item'><a class='page-link' onclick='movePage(" + data.paging.prev + ")\'>prev</a></li>";
		// 페이징 그리기
		for(let i=data.paging.startPage; i<=data.paging.endPage; i++) {
			html += "<li class='page-item'><a class='page-link' onclick='movePage(" + i + ")\'>"+ (i) + "</a></li>";
		}
		html += "<li class='page-item'><a class='page-link' onclick='movePage(" + data.paging.next + ")\'>next" + "</a></li></ul></nav>";
		$("#paging").html(html);

	});
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new kakao.maps.LatLng(37.555078, 126.970702), // 지도의 중심좌표
	    level: 5 // 지도의 확대 레벨 
	}; 
	console.log("test");
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
}
