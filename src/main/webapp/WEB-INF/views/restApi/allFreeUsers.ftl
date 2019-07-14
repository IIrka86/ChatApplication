<#import "/spring.ftl" as spring/>

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
<body id="${type}">
<header style="min-height: 59px">
    <div class="container">
        <h1> Free <#if type=="AGENT">agents<#else >clients</#if></h1>
    </div>
</header>
<section >
    <div class="container first">
        <div id="freeUsers">
            {{users}}
        </div>
<#--        <div style="border solid 1px black">-->
<#--            <#if users?has_content>-->
<#--                <#list users as user>-->
<#--                    <p>${user.name}</p>-->
<#--                </#list>-->
<#--            <#else >-->
<#--                <p>Do not have free <#if type=="AGENT">agents<#else >clients</#if></p>-->
<#--            </#if>-->
<#--        </div>-->
    </div>
</section>
<footer class="navbar navbar-fixed-bottom">
    <div class="container">
        <p>&copyChat</p>
    </div>
</footer>
<script src="/js/freeUsers.js"></script>
</body>
</html>