<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/mainStyle.css">
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.1"></script>
    <title>Document</title>
</head>
<body>
<header style="min-height: 59px">
    <div class="container">
        <h1> All Chats </h1>
    </div>
</header>
<section >
    <div class="container first">
        <a href="/restApi/freeUsers/AGENT">Show all free agents</a><br>
        <a href="/restApi/freeUsers/CLIENT">Show all free clients</a>
        <div id="app">
            {{chats}}
        </div>
    </div>
</section>
<footer class="navbar navbar-fixed-bottom">
    <div class="container">
        <p>&copyChat</p>
    </div>
</footer>
 <script src="/js/chats.js"></script>
</body>
</html>