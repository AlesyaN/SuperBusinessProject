<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">

</head>
<body>
     <#include "navbar.ftl">

<div class="news-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-1"></div>
            <div class="col-10">
                <div class="row article-wrapper-main">
                    <div class="col-4"><img src="${mainPost.picPath}" class="image img-fluid" alt="Responsive image">
                    </div>
                    <div class="col-8">
                        <div class="main-text">
                            <h1><a href="/${mainPost.theme}/${mainPost.id}">${mainPost.title}</a></h1>
                            ${mainPost.text}
                        </div>
                        <a href="/${mainPost.theme}/${mainPost.id}">More</a>
                    </div>
                </div>
                <div class="row article-wrapper-main">
                        <#list posts as p>
                            <div class="col"><img src="${p.picPath}" class="image img-fluid" alt="Responsive image">
                                <br>
                                <div class="main-text">
                                <h3><a href="/${p.theme}/${p.id}">${p.title}</a></h3>
                                ${p.text}
                                </div>
                                <a href="/${p.theme}/${p.id}">More</a>
                            </div>
                        </#list>
                </div>
            </div>
        </div>

    </div>

</div>
</div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>