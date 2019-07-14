
var restApi = Vue.resource('/rest/chats');

Vue.component('chat-row',{
    props:['chat'],
    data: function () {
        return{
            href: '/restApi/chatInfo/' + this.chat.id
        }
    },
    template: '<tr>' +
            '<td><a :href="href">Chat #{{chat.id}}</a></td>' +
            '<td>{{chat.agent.name}}</td>' +
            '<td>{{chat.client.name}}</td>' +
        '</tr>',
});

Vue.component('chats-list',{
    props: ['chats'],
    template:
            '<table class="table table-striped">' +
        '       <tr><th></th><th>Agent</th><th>Client</th></tr>' +
        '       <chat-row v-for="chat in chats" :key="chat.id" :chat="chat" /></table>'
});

var app = new Vue({
    el: '#app',
    template: '<chats-list :chats="chats" />',
    data: {
        chats: [
        ]
    },
    created: function () {
        restApi.get().then(result =>
            result.json().then(data =>
                data.forEach((chat => this.chats.push(chat)))))
    }
})