<#import "/spring.ftl" as spring/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/mainStyle.css">
</head>
<body>
<header style="min-height: 59px">
    <div class="container">
        <h1> Authentication </h1>
    </div>
</header>
<section >
    <div id="login-container" class="container first">
        <#if message??><h4 style="color: red;">${message}</h4></#if>
        <form id="loginForm" action="/restApi/loginRest"  name="loginForm" method="POST">
            <label style="margin-left: 37px"> Your login </label>
            <@spring.formInput "user.login" "placeholder='login..'" "text"/><br><br>
            <label> Your password </label>
            <@spring.formInput "user.password" "placeholder='password..'" "password"/><br>
            <input value="Login" type="submit"><br>
            <hr>
            <p>If you do not have login yet, please</p>
            <p><a href="/restApi/register/0">Register HERE</a></p>
        </form>
    </div>
</section>
<footer class="navbar navbar-fixed-bottom">
    <div class="container">
        <p>&copy Chat</p>
    </div>
</footer>
</body>
</html>