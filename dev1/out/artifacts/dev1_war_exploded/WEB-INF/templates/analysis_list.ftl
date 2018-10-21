<!DOCTYPE html>
<html>
<head>
    <title>Article</title>
    <meta charset="utf-8">
    <script type="application/javascript"
            src="/js/jquery-1.9.1.js"></script>
</head>
<body>
    <#if user??>
        <a href='/profile'>Profile</a>
    <#else>
        <a href='/login'>Log In </a>
        <a href='/sign-up'> Sign Up</a>
    </#if>
<form method='get' action='/search'>
    <input type='text' name='search'>
    <input type='submit' name='submit'>
</form>
<a href='/main'>Main</a>
<a href='/currencies'>Currencies</a>
<a href='/stocks'>Stocks</a>
<a href='/news'>News</a>
<a href='/analysis'>Analysis</a>
<a href='/crypto'>Crypto</a><br>
<#if posts?has_content>
    <#list posts as p>
                <a href="/${p.theme}/${p.id}">POST # ${p.id}</a><br>
                <b>${p.title} by ${p.author.name}</b><br>
                <i>${p.getDateToString()}</i><br>
                <p>${p.text}</p><br>
    </#list>
</#if>
</body>
</html>