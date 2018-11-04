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
    <#--<script src="static/jquery-3.3.1.min.js" defer></script>-->



</head>
<body>
<#include "navbar.ftl">

<div class="container-fluid">
    <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
            <div class="row search-form">
                <div class="col-2"></div>
                <div class="col-8">
                    <form class="form-group" method="get">
                        <input name="search" class="form-control mr-sm-2" id="search-box" type="text" placeholder="Search"
                               aria-label="Search" oninput="autoComplete('search-box')">
                        <div class="form-check">
                            <label>
                                <input id="news" type="checkbox" class="form-check-input" checked="checked" name="news"> News
                            </label>
                        </div>
                        <div class="form-check">
                            <label>
                                <input id="analysis" type="checkbox" class="form-check-input" checked="checked" name="analysis"> Analysis
                            </label>
                        </div>
                        <input class="btn btn-outline-success my-2 my-sm-1" type="submit" value="Search"></input>
                    </form>
                </div>
                <div class="col-2"></div>
            </div>

            <!-- search results -->
            <div id="search-list" class="search-list">
                <#list posts as p>
                    <div class="article-item">
                        <div class="row">
                            <div class="col-4">
                                <img src="${p.picPath}" class="image img-fluid" alt="Responsive image">
                            </div>
                            <div class="col-8">
                                <div class="art-text">
                                <h3>${p.title}</h3>
                                <p>${p.text}</p>
                                </div>
                                <a href="/${p.theme}/${p.id}">More</a>
                            </div>
                        </div>
                        <hr>
                    </div>
                </#list>
            </div>
            <!--  -->


        </div>
    </div>
</div>
<div class="col-1"></div>

</div>
<script type="application/javascript"
        src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/search.js"></script>
<!-- Optional JavaScript -->
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