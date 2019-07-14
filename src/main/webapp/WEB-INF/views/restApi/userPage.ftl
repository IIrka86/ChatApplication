
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/mainStyle.css">
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.1"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
<#--    <script src="/js/ws.js"></script>-->
</head>
<body id="${user.id}">
<header style="min-height: 59px">
    <div class="container">
        <h1> ${user.type} ${user.name} </h1>
        <h2>${mainMessage}</h2>
    </div>
</header>
<section >
    <div class="container first">
        <div id="chatMessages">
            {{messages}}
        </div>
    </div>
</section>
<footer class="navbar navbar-fixed-bottom">
    <div class="container">
        <p>&copyChat</p>
    </div>
</footer>
<script src="/js/userPage.js"></script>
<#--<script>-->
<#--    (function poll(){-->
<#--        $.ajax({-->
<#--            url: "/chat?id=${user.id}&name=${user.name}&type=${user.type}",-->
<#--            success: function(data, status, jqXHR) {-->
<#--                // do something with the data-->
<#--                setTimeout( poll, 10 );-->
<#--            },-->
<#--            error: function(jqXHR, status, errorThrown) {-->
<#--                if (status=='timeout') {-->
<#--                    console.log( 'request timed out.' );-->
<#--                    setTimeout( poll, 10 );-->
<#--                }-->
<#--                else {-->
<#--                    console.log(status);-->
<#--                    setTimeout( poll, 60000 );-->
<#--                }-->
<#--            },-->
<#--            dataType: "json",-->
<#--            timeout: 60000-->
<#--        });-->
<#--    })();-->
<#--</script>-->
</body>
</html>