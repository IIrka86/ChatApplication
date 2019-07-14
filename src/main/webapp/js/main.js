Vue.component('message-row',{
    props:['message'],
    template: '<div>{{message.string}}</div>'
});

Vue.component('message-list',{
    props: ['messages'],
    template: '<div><message-row v-for="message in messages" :message="message" /></div>'
});

var app = new Vue({
    el: '#app',
    template: '<message-list :messages="messages" />',
    data: {
        messages: [
            {string:'Hello'},
            {string:'bye'}
        ]
    }
})