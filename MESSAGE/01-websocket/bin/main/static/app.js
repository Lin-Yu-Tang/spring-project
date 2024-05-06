const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/websocket-portfolio'
});

var clients = ['tom', 'mary'];

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
    
    stompClient.subscribe('/topic/messages', (greeting) => {
        showMessage(JSON.parse(greeting.body).content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function sendMessage() {
    stompClient.publish({
        destination: "/app/message",
        body: JSON.stringify({'msg': $("#msg").val()})
    });
}

function showGreeting(message) {
	if (message) {
		clients.push(message);
	}
	clients.forEach(e => {
		$("#client").append("<span class='col p-3'><span class='client'>" + e + "</span></span>");
	});
//    $("#client").append("<span>" + message + "</span>");
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
//	showGreeting('');
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#enter" ).click(() => sendName());
    $( "#send" ).click(() => sendMessage());
});




