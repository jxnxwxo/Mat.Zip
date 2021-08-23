/**
 * 메인 페이지 검색 및 이동
 */

let chunks = [];
let mediaRecorder = null;
let blob = null;

// 마이크 녹음
if (navigator.mediaDevices) {

	const constraints = {
		audio: true
	}

	navigator.mediaDevices.getUserMedia(constraints)
		.then(function(stream) {

			mediaRecorder = new MediaRecorder(stream);

			$("#btn").click(function() {
				alert('3초안에 검색어를 말해주세요');
				setTimeout(function() {
					mediaRecorder.stop();
				}, 3000);
				mediaRecorder.start();
			});

			mediaRecorder.onstop = function(e) {
				blob = new Blob(chunks, { 'type': 'audio/ogg; codecs=opus' });
				chunks = [];
				sttService(blob);
			}

			mediaRecorder.ondataavailable = function(e) {
				chunks.push(e.data);
			}
		})
		.catch(function(err) {
			console.log(err);
		})
}

// STT service 전송
function sttService(audio) {

	let file = new File([audio], "file");
	const form = new FormData();
	form.append("file", file);

	$.ajax({
		type: "POST",
		url: "/sttService",
		contentType: false,
		processData: false,
		enctype: 'multipart/form-data',
		//dataType : 'text',
		data: form,
		success: function(data) {
			const temp = JSON.parse(data);
			console.log(temp.text);
			location.href = decodeURI("html/searchPage.html?rtName=" + temp.text);
		},
		error: function(e) {
			console.log('fail');
		}
	});
}

//검색페이지 이동 함수
function searchMove() {

	//const rtAddress = $("#rtAddress").val();
	//const rtGrade = $("#rtGrade").val();
	const rtName = $("#rtName").val();

	//console.log(rtAddress + rtGrade + rtName);
	//location.href = decodeURI("html/searchPage.html?rtAddress=" + rtAddress + "&rtGrade=" + rtGrade + "&rtName=" + rtName);
	
	location.href = decodeURI("html/searchPage.html?&rtName=" + rtName);
}