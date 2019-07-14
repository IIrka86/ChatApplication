
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/mainStyle.css">
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.1"></script>
</head>
<body id="${chatId}">
<header style="min-height: 59px">
    <div class="container">
        <h1> Chat #${chatId} </h1>
    </div>
</header>
<section >
    <div class="container first info">
        <div id="chat">
            {{chat}}
        </div>
</section>
<footer class="navbar navbar-fixed-bottom">
    <div class="container">
        <p>&copyChat</p>
    </div>
</footer>
<script src="/js/chatInfo.js"></script>
</body>
</html>