

var stompClient = null;
const hendlers = [];

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    //sendMessage("/register agent alice")
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/activity', function (message) {
            hendlers.forEach(hendler => hendler(JSON.parse(message.body)));
        });
    });
}

connect();

export function addHendler(hendler) {
    hendlers.push(hendler);
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

export function sendMessage(message) {
    stompClient.send("/rest/wsMessage", {}, JSON.stringify(message));
}