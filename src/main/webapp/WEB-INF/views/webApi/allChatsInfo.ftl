<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/mainStyle.css">
</head>
<body>
<header style="min-height: 59px">
    <div class="container">
        <h1> All Chats </h1>
    </div>
</header>
<section >
<div class="container first">
    <a href="/chatsInfo/freeUsers/AGENT">Show all free agents</a><br>
    <a href="/chatsInfo/freeUsers/CLIENT">Show all free clients</a>
    <div style="border solid 1px black">
        <table class="table table-striped">
            <#if chats?has_content>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">AGENT</th>
                    <th scope="col">CLIENT</th>
                </tr>
                <#list chats as chat>
                    <tr>
                        <td><a href="/chatsInfo/chatMessages/${chat.id}">Chat #${chat.id}</a></td>
                        <td><#if chat.agent?has_content>${chat.agent.name}</#if></td>
                        <td><#if chat.client?has_content>${chat.client.name}</#if></td>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>
</div>
</section>
<footer class="navbar navbar-fixed-bottom">
    <div class="container">
        <p>&copyChat</p>
    </div>
</footer>
</body>
</html>