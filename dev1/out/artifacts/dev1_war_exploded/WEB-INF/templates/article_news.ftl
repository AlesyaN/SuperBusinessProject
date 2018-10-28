<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="/css/style.css">
<#--<script src="js/jquery-3.3.1.min.js" defer></script>-->
    <script type="application/javascript"
            src="/js/jquery-1.9.1.js"></script>
</head>
<body>
<#include "navbar.ftl">

<div class="container-fluid">
    <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
            <h1>${post.title}</h1>
        <#--<p>Author: <a href="/profile/${post.author.id}">${post.author.getFullName()}</a></p>-->
            <img src="<#if post.picPath??>${post.picPath}</#if>" width="1000vh" height="auto">
            <p>${post.text}</p>
            <p>${post.getDateToString()}</p>
            <div class="row">
                <div class="col">
            <#if user??>
                <button type="button" class="btn btn-success" onclick="like();">Like</button>
                <button type="button" class="btn btn-warning" onclick="dislike()">Dislike</button>
            </#if>
                </div>
                <div class="card">
                    <div class="card-body bg-success text-white">
                        <div id="likes">${likes}</div> likes
                    </div>
                </div>
                <div class="card">
                    <div class="card-body bg-warning text-white">
                        <div id="dislikes">${dislikes}</div> dislikes
                    </div>
                </div>
        </div>

        <hr>
            <#if comments??>
            <ul id="list" class="comments-list">
                <#list comments as c>
                    <li id="${c.id}" class="comment-item-1">
                        <a href="/profile/${c.author.id}">${c.author.name}</a>
                        <p>${c.text}</p>
                        <small>${c.getDateToString()}</small>
                    <#if user??>
                        <#if c.author.name=user.name>
                    <br>
                    <button class="btn btn-danger" onclick="deleteComment(${c.id})">Delete comment</button>
                    <hr>
                        </#if>
                    </#if>

                    </li>
                </#list>
            </ul>
            </#if>

            <#if user??>
            <div class="form-group comment">
                <label for="comment">Comment:</label>
                <textarea class="form-control" rows="5" id="text" maxlength="160" onkeyup="charsLeft()"></textarea>
                <div id="count"></div>
                <br>
                <button class="btn btn-outline-success my-2 my-sm-0" id="comment" type="submit"
                        onclick="newComment()">Send comment
                </button>
            </div>
            </#if>
    </div>
    <div class="col-1"></div>
</div>


</div>

</div>
<script type="application/javascript">
    function like() {
        $.ajax({
            url: "/ajax",
            type: "post",
            data: {
                "ajax": "like",
                "post-id": ${post.id}
            },
            success: function (msg) {
                var likes = document.getElementById("likes");
                var val = likes.innerHTML;
                if (msg.like) {
                    val = +val + 1;
                    likes.innerHTML = val;
                } else {
                    val = +val - 1;
                    likes.innerHTML = val;
                }
            }
        });
    }

    function dislike() {
        $.ajax ({
            url: "/ajax",
            type: "post",
            data: {
                "ajax": "dislike",
                "post-id": ${post.id}
            },
            success: function(msg) {
                var likes = document.getElementById("likes");
                var vallikes = likes.innerHTML;
                var dislikes = document.getElementById("dislikes");
                var valdislikes = dislikes.innerHTML;
                if (msg.like) {
                    vallikes = +vallikes - 1;
                    likes.innerHTML = vallikes;
                }
                if (msg.dislike) {
                    valdislikes = +valdislikes + 1;
                    dislikes.innerHTML = valdislikes;
                } else {
                    valdislikes = +valdislikes - 1;
                    dislikes.innerHTML = valdislikes;
                }
            }
        });
    }

    function deleteComment(id) {
        $.ajax({
            url: "/ajax",
            type: "post",
            data: {
                "id": id,
                "ajax": "deleteComment"
            },
            success: function (msg) {
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
                    lst.append("<li id='" + msg.id + "' class='comment-item-1'>" +
                            "<a href='/profile/" + msg.authorId + "'>" + msg.authorName + "</a>" +
                            "<p>" + textarea.val() + "</p>" +
                            "<small>" + msg.date + "</small><br>" +
                            "<button class='btn btn-danger' onclick='deleteComment(" + msg.id + ")'>Delete comment</button>" +
                            "<hr>" + "</li>");
                    textarea.val("");
                },
                error: function (msg) {
                    alert("error");
                }
            });
        } else {
            $("#list").html("");
        }
    }

</script>

<!-- Optional JavaScript -->
<script src="/js/script.js"></script>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<#--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"-->
<#--integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"-->
<#--crossorigin="anonymous"></script>-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>