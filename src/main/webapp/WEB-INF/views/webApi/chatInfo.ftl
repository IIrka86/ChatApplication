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
        <h1> Chat #${chat.id} </h1>
    </div>
</header>
<section >
    <div class="container first info">
        <h2> Chat agent: ${chat.agent.name} </h2>
        <h2> Chat client: ${chat.client.name} </h2>
        <div style="border solid 1px black">
            <#if chat.messages?has_content>
                <table class="table table-striped">
                    <#list chat.messages as mess>
                        <tr>
                            <td style="width: 10%; text-align: right">${mess.sender.name} :</td>
                            <td>${mess.content}</td>
                            <td style="width: 10%">${mess.date?time}</td>
                        </tr>
                    </#list>
                </table>
            <#else>
                <p>Chat do not have message</p>
            </#if>
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