<!DOCTYPE html>
<html>
<head>
    <title>Analysis</title>
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
<#if post??>
    <b>${post.title} by ${post.author.name}</b><br>
    <i>${post.getDateToString()}</i><br>
    <p>${post.text}</p><br>
</#if>
<#if likes??>
    Likes: ${likes} <br>
</#if>
<#if comments??>
    Comments: <br>
    <ul id="list">
    <#list comments as c>
        <li id="${c.id}">${c.author.name} on ${c.getDateToString()} <br>
            ${c.text}  <br>
            <#if user??>
                <#if c.author.name=user.name>
                <button onclick="deleteComment(${c.id})">delete</button>
                </#if>

            </#if>
        </li>
    </#list>
    </ul>
</#if>
<#if user??>
    Your comment: <br>
        <textarea id="text" rows='4' name='text'></textarea><br>
        <button onclick="newComment()">submit</button>

</#if>

<script type="application/javascript">
    function deleteComment(id) {
        $.ajax({
            url: "/ajax",
            type: "post",
            data: {
                "id": id,
                "ajax": "deleteComment"
            },
            success: function(msg) {
                document.getElementById(id).remove();
            },
            error: function (msg) {
                alert("error");
            }
        });
    }

    function newComment(request, response) {
        var textarea = $("#text");
        if (textarea.val().length > 0) {
            $.ajax({
                url: "/ajax",
                type: "post",
                data: {
                    "ajax": "saveComment",
                    "text": textarea.val(),
                    "id": ${post.id}
                },
                success: function (msg) {
                    var lst = $("#list");
                    lst.append("<li id='"+ msg.id+"'>" + msg.authorName + " on " + msg.date + "<br>" +
                            textarea.val() + "<br>" +
                            "<button onclick=\"deleteComment(" + msg.id + ")\">delete</button>");
                    textarea.val("");
                },
                error: function (msg) {
                    alert("error");
                }
            });
        } else  {
            $("#list").html("");
        }
    }

</script>
</body>
</html>