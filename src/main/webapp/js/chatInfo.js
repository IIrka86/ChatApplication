
var chatId = document.body.getAttribute('id');

var restApi = Vue.resource('/rest/chats/' + chatId);

Vue.component('message-row',{
    props:['message'],
    template: '<tr>' +
        '<td>{{message.sender.name}}</td>' +
        '<td>{{message.content}}</td>' +
        '<td>{{message.date}}</td>' +
        '</tr>'
});

Vue.component('chat',{
    props: ['chat', 'messages'],
    // data: function(){
    //     return{ chat: this.chat}
    // },
    template:
        '<div>' +
            '<h2>Agent :{{chat.agent.name}}</h2><h2>Client :{{chat.client.name}}</h2>'+
            '<table><message-row v-for="message in messages" :key="message.id" :message="message" /> </table>' +
        '</div>',
    created: function () {
        //this.loading = true;
        restApi.get().then(result =>
            result.json().then(data =>{
                    this.chat = data;
                    this.messages = data.messages;
                    //data.messages.forEach(message => this.messages.push(message));
                    console.log(this.messages);
                    //this.loading = false;
            }))
    }
});

var chat = new Vue({
    el: '#chat',
    //template: '<div>Chat #{{chat.id}}</div>',
    template: '<chat :chat="chat" />',
    data: {
        chat: [],
        messages: [],
        //loading: false
    }
});