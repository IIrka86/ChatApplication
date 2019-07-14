
var usertype = document.body.getAttribute('id');

var restApi = Vue.resource('/rest/freeUsers/' + usertype);

Vue.component('user-row',{
    props:['user'],
    template: '<p>{{user.name}}</p>',
});

Vue.component('users-list',{
    props: ['users'],
    template:
        '<div>' +
        '<user-row v-for="user in users" :key="user.id" :user="user" />' +
        '</div>',
    created: function () {
        restApi.get().then(result =>
            result.json().then(data =>
                data.forEach((user => this.users.push(user)))))
    }
});

var freeUsers = new Vue({
    el: '#freeUsers',
    template: '<users-list :users="users" />',
    data: {
        users: []
    }
})