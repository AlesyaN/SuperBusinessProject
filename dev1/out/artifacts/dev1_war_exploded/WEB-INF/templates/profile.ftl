<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<#include "navbar.ftl">
<div class="profile">
    <div class="row">
        <div class="col-4"></div>
        <div class="col-5">
        <#if someuser??>
            <#if someuser.picPath??>
            <div class="row">
                <div class="col">
                    <img src="${user.picPath}" class="image img-fluid" alt="Responsive image">
                </div>
            <div class="col">
            </div>
            </#if>
            <#if user?? && user=someuser>
            </div>
            <h3>My Profile</h3>
            <#else >
             <h3>Profile of</h3>
            </#if>
            <#if someuser.getFullName()??>
            <h3>${someuser.getFullName()}</h3>
            </#if>
            <#if someuser.getDateOfBirthToString()??>
            <h4>Date of birth:</h4> ${someuser.getDateOfBirthToString()}
            </#if>
            <#if someuser.placeOfBirth??>
            <h4>Place of birth:</h4> ${someuser.placeOfBirth}
            </#if>
            <#if someuser.education??>
            <h4>Education:</h4> ${someuser.education}
            </#if>
            <#if someuser.experience??>
            <h4>Work experience:</h4> ${someuser.experience}
            </#if>
            <#if someuser.scope??>
            <h4>Scope of work:</h4> ${someuser.scope}
            </#if>
            <#if someuser.position??>
            <h4>Current position:</h4> ${someuser.position}
            </#if>
            <br>
            <#if user?? && user=someuser>
            <a href="/edit-profile" class="btn btn-primary">Settings</a>
            <form method="post">
                <input type="submit" name="logout" class="btn btn-danger" value="Log out">
            </form>
            </#if>
        </#if>
        </div>
        <div class="col-2"></div>

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