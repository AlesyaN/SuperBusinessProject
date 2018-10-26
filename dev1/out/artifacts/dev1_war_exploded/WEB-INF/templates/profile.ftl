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
<div class="profile">
    <div class="row">
        <div class="col-4"></div>
        <div class="col-4">
        <#if user??>
            <#if user.picPath??>
            <img src="${user.picPath}" width="100px" height="100px" alt="Responsive image">
            </#if>
            <h3>My Profile</h3>
            <#if user.getFullName()??>
            <h3>${user.getFullName()}</h3>
            </#if>
            <#if user.getDateOfBirthToString()??>
            <h4>Date of birth:</h4> ${user.getDateOfBirthToString()}
            </#if>
            <#if user.placeOfBirth??>
            <h4>Place of birth:</h4> ${user.placeOfBirth}
            </#if>
            <#if user.education??>
            <h4>Education:</h4> ${user.education}
            </#if>
            <#if user.experience??>
            <h4>Work experience:</h4> ${user.experience}
            </#if>
            <#if user.scope??>
            <h4>Scope of work:</h4> ${user.scope}
            </#if>
            <#if user.position??>
            <h4>Current position:</h4> ${user.position}
            </#if>
            <a href="/edit-profile" class="btn btn-primary">Settings</a>
        </#if>
        </div>
        <div class="col-4"></div>

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