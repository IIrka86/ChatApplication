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
        <h1> Registration </h1>
    </div>
</header>
<section >
    <div id="login-container" class="container first">
        <form id="loginForm" action="registerUser"  name="loginForm" method="POST">
            <#assign options = {"AGENT":"Agent","CLIENT":"Client"}>
            <@spring.formRadioButtons "user.type" options "" ""/><br><br>
            <label style="margin-left: 37px"> Your name </label>
            <@spring.formInput "user.name" "placeholder='Your name'" "text"/><br><br>
            <label style="margin-left: 37px"> Your login </label>
            <@spring.formInput "user.login" "placeholder='Your login' pattern='[a-zA-Z0-9]{4,15}'" "text"/><br><br>
            <label> Your password </label>
            <@spring.formInput "user.password" "placeholder='Your password'" "password"/><br><br>
            <input value="Register" type="submit">
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
