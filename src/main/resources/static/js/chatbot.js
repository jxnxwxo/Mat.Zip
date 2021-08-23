$(document).ready(function(){
	var stompClient = null;

	var BotMsgContainer = {
		BotMsg: function (replymsg) {
			$('.Messages_list').append('<div class="msg"><span class="avtr"><figure><img src = "/img/chatbot.jpg" width="30" height="30"></figure></span><span class="responsText">' + replymsg + '</span></div>');
			}
		}
		
	function connect() {
	
	    var socket = new SockJS('/ws');
	    stompClient = Stomp.over(socket);
		stompClient.connect({}, function (frame) {
	        stompClient.subscribe('/topic/public', function (message) {
	        showMessage(message.body);
	        });
	    });
	}
	
	connect();
	
	function sendMessage() {
		let message = $("#chat-input").val()
	    stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(message));
	}
	
	function showMessage(message) {
    	$('.Messages_list').append('<div class="msg"><span class="avtr"><figure><img src = "/img/chatbot.jpg" width="30" height="30"></figure></span><span class="responsText">' + message + '</span></div>');
	}
	
	$(function () {
	    $("form").on('submit', function (e) {
	        e.preventDefault();
	    });
	    $( "#chat-submit" ).click(function() { sendMessage(); });
	});
	
	jQuery(document).ready(function($) {
		jQuery(document).on('click', '.iconInner', function(e) {
			jQuery(this).parents('.botIcon').addClass('showBotSubject');
			$("[name='msg']").focus();
		});
	
		jQuery(document).on('click', '.closeBtn, .chat_close_icon', function(e) {
			jQuery(this).parents('.botIcon').removeClass('showBotSubject');
			jQuery(this).parents('.botIcon').removeClass('showMessenger');
		});
	
		jQuery(document).on('submit', '#botSubject', function(e) {
			e.preventDefault();
	
			jQuery(this).parents('.botIcon').removeClass('showBotSubject');
			jQuery(this).parents('.botIcon').addClass('showMessenger');
		});
		
		BotMsgContainer.BotMsg('안녕하세요, 무엇을 도와드릴까요?');
		/* Chatboat Code */
		$(document).on("submit", "#messenger", function(e) {
			e.preventDefault();
	
			var val = $("[name=msg]").val().toLowerCase();
			var mainval = $("[name=msg]").val();
			name = '';
	
			function userMsg(msg) {
				$('.Messages_list').append('<div class="msg user"><span class="avtr"><figure><img src = "/img/user.jpg" width="30" height="30"></figure></span><span class="responsText">' + mainval + '</span></div>');
				$("[name='msg']").val("");
			};
			function appendMsg(msg) {
				$('.Messages_list').append('<div class="msg"><span class="avtr"><img src = "/img/chatbot.jpg" width="30" height="30"></figure></span><span class="responsText">' + msg + '</span></div>');
			};
			userMsg(mainval);
			
			var lastMsg = $('.Messages_list').find('.msg').last().offset().top;
			$('.Messages').animate({scrollTop: lastMsg}, 'slow');
		});
		/* Chatboat Code */
	});
});