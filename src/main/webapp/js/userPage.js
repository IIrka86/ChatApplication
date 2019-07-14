
var userId = document.body.getAttribute('id');

var restApi = Vue.resource('/rest/chatMessages/' + userId);

Vue.component('message-form',{
    props:['messages'],
    data: function(){
        return {
            text: ''
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="message..." v-model="text" />'+
            '<input type="button" value="Send" @click="sendMessage" />'+
        '</div>',
    methods: {
        // sendMessage: function (){
        //     sendMessage({id: userId,
        //                 sender: {},
        //                 content: this.text,
        //                 date: {},
        //                 type: ''});
        //     this.text = '';
        // }
        sendMessage: function () {
            var mess = this.text;
            restApi.save({}, mess).then(result =>
                result.json().then(data =>
                    this.messages.push(data)));
            this.text = '';
        }
    }

})

Vue.component('message-row',{
    props:['message'],
    template: '<tr>' +
            '<td>{{message.sender.name}}</td>' +
            '<td>{{message.content}}</td>' +
            '<td>{{message.date}}</td>' +
        '</tr>'
});

Vue.component('messages-list',{
    props: ['messages'],
    template:
        '<div>'+
        '<table class="table table-striped">' +
            '<message-row v-for="message in messages" :key="message.id" :message="message" /></table>'+
        '<message-form :messages="messages" />'+
        '</div>',
    created: function () {
        addHendler(data => {
            this.messages.push(data)
        })
    }
});

var chatMessages = new Vue({
    el: '#chatMessages',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    },
    created: function () {
        restApi.get().then(result =>
            result.json().then(data =>
                data.forEach(message => this.messages.push(message)))

        )
    }
});


//
// var stompClient = null;
// const hendlers = [];
//
// function connect() {
//     var socket = new SockJS('/gs-guide-websocket');
//     stompClient = Stomp.over(socket);
//     //sendMessage("/register agent alice")
//     stompClient.connect({}, function (frame) {
//         console.log('Connected: ' + frame);
//         stompClient.subscribe('/topic/activity', function (message) {
//             hendlers.forEach(hendler => hendler(JSON.parse(message.body)));
//         });
//     });
// }
//
// connect();
//
// function addHendler(hendler) {
//     hendlers.push(hendler);
// }
//
// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect();
//     }
//     console.log("Disconnected");
// }
//
// function send(message) {
//     stompClient.send("/rest/wsMessage", {}, JSON.stringify(message));
// }