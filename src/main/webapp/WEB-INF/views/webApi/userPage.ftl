<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/mainStyle.css">
    <script src="/js/jquery-3.3.1.js"></script>
</head>
<body>
<header style="min-height: 59px">
    <div class="container">
        <h1> ${user.type} ${user.name} </h1>
        <h2>${mainMessage}</h2>
    </div>
</header>
<section >
    <div class="container first">
    <div style="min-height: 200px; border solid 2px black">
        <table class="table table-striped">
            <#if messages?has_content>
                <#list messages as mess>
                    <tr>
                        <td style="width: 20%; text-align: right">${mess.sender.name} :</td>
                        <td>${mess.content}</td>
                        <td style="width: 10%">${mess.date?time}</td>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>
    <div>
        <form action="sendMessage" method="post">
            <input  name="newMessage" type="text">
            <@spring.formInput "user.id" "value='${user.id}'" "hidden"/>
            <input value="Send" type="submit">
        </form>
    </div>
    <div id="content"></div>
</div>
</section>
<footer class="navbar navbar-fixed-bottom">
    <div class="container">
        <p>&copyChat</p>
    </div>
</footer>
<script>

    (function ($) {
        $(document).ready(function () {

            (function poll(){
                var request = $.ajax({
                    method: "GET",
                    url: "/rest/getAjax/${user.id}",
                    dataType: "json",
                    timeout: 10000});

                request.done( function(data, status, jqXHR) {
                    console.log(data);
                    data.forEach(function (item, i, arr) {
                        $('.table').append('<tr><td style="width: 20%; text-align: right">'+item.sender.name+' :</td>' +
                            '<td>'+item.content+'</td>' +
                            '<td style="width: 10%">'+item.date+'</td></tr>');
                    });
                    setTimeout( poll, 100 );});

                request.fail( function(jqXHR, status, errorThrown) {
                    if (status=='timeout') {
                        console.log( 'request timed out.' );
                        setTimeout( poll, 100 );
                    } else {
                        //console.log(status);
                        setTimeout( poll, 100 );
                    }});
            })();

        })
    })(jQuery);

</script>
</body>
</html>