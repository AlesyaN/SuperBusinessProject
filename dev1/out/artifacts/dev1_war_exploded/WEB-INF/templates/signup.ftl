<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<#include "navbar.ftl">

<div class="row align-items-center signup">
    <div class="col">

        <p align="center">To have more options you should fill all the fields!</p>
        <form method="post" enctype="multipart/form-data">
            <div class="form-group row">
                <div class="col-3"></div>
                <div class="col-6">
                    <label for="exampleFormControlFile1">You can change your profile pic:</label>
                    <input type="file" name="file" class="form-control-file" id="exampleFormControlFile1">
                </div>

                <div class="col-3"></div>
            </div>

            <div class="form-group row">
                <div class="col-3"></div>
                <label for="email" class="col-2 col-form-label">Email*</label>
                <div class="col-4">
                    <input name="email" type="text" class="form-control" id="email" placeholder="Email" required required
                           onblur="validateForm('email', /[a-zA-Z][0-9a-zA-Z.]*@([a-zA-Z][0-9a-zA-Z]+\.)+[a-zA-Z]+/, 'email_msg', 'Email should be like example123@mail.com')">
                    <div id="email_msg"></div>
                </div>
                <div class="col-3"></div>
            </div>

            <div class="form-group row">
                <div class="col-3"></div>
                <label for="password" class="col-2 col-form-label">Password*</label>
                <div class="col-4">
                    <input name="password" type="password" class="form-control" id="password" placeholder="Password" required
                           onblur="validateForm('password', /[0-9a-zA-Z!@#$%^&*()+-_=]{8,16}/, 'password_msg', 'Password should contain only characters A-Z, a-z, digits, special symbols: !@#$%^&*()+-_= and length 8-16')">
                    <div id="password_msg"></div>
                </div>
                <div class="col-3"></div>
            </div>

            <div class="form-group row">
                <div class="col-3"></div>
                <label for="repeat_password" class="col-2 col-form-label">Repeat password*</label>
                <div class="col-4">
                    <input type="password" class="form-control" id="repeat_password" placeholder="Repeat password"
                           required onblur="repeatPassword()">
                    <div id="repeat_password_msg"></div>
                </div>
                <div class="col-3"></div>
            </div>

            <div class="form-group row">
                <div class="col-3"></div>
                <label for="firstname" class="col-2 col-form-label">First name</label>
                <div class="col-4">
                    <input name='name' type="text" class="form-control" id="firstname" placeholder="First name">
                </div>
                <div class="col-3"></div>
            </div>

            <div class="form-group row">
                <div class="col-3"></div>
                <label for="lastname" class="col-2 col-form-label">Last name</label>
                <div class="col-4">
                    <input name="surname" type="text" class="form-control" id="lastname" placeholder="Last name">
                </div>
                <div class="col-3"></div>
            </div>

            <div class="form-group row">
                <div class="col-3"></div>
                <label for="lastname" class="col-2 col-form-label">Patronymic</label>
                <div class="col-4">
                    <input name="patronymic" type="text" class="form-control" id="patronymic" placeholder="Last name">
                </div>
                <div class="col-3"></div>
            </div>

            <div class="form-group row">
                <div class="col-3"></div>
                <label for="birthdate" class="col-2 col-form-label">Birth date</label>
                <div class="col-4">
                    <input name="dateOfBirth" type="text" class="form-control" id="birthdate" placeholder="Birth date">
                </div>
                <div class="col-3"></div>
            </div>

            <div class="form-group row">
                <div class="col-3"></div>
                <label for="birthplace" class="col-2 col-form-label">Birth place</label>
                <div class="col-4">
                    <input name='placeOfBirth' type="text" class="form-control" id="birthplace" placeholder="Birth place">
                </div>
                <div class="col-3"></div>
            </div>
            <div class="form-group row">
                <div class="col-3"></div>
                <label for="edu" class="col-2 col-form-label">Education</label>
                <div class="col-4">
                    <input name="education" type="text" class="form-control" id="edu" placeholder="Education">
                </div>
                <div class="col-3"></div>
            </div>
            <div class="form-group row">
                <div class="col-3"></div>
                <label for="scope" class="col-2 col-form-label">Scope of work</label>
                <div class="col-4">
                    <input  name="scope" type="text" class="form-control" id="scope" placeholder="Scope">
                </div>
                <div class="col-3"></div>
            </div>

            <div class="form-group row">
                <div class="col-3"></div>
                <label for="position" class="col-2 col-form-label">Position</label>
                <div class="col-4">
                    <input name="position" type="text" class="form-control" id="position" placeholder="Position">
                </div>
                <div class="col-3"></div>
            </div>
            <div class="form-group row">
                <div class="col-3"></div>
                <label for="experience" class="col-2 col-form-label">Work experience</label>
                <div class="col-4">
                    <input name='experience' type="text" class="form-control" id="experience" placeholder="Work experience">
                </div>
                <div class="col-3"></div>
            </div>
            <div class="form-group row">
                <div class="col-3"></div>
                <div class="col-sm-6">
                    <button type="submit" class="btn btn-primary" id="submit" onclick="validateForm()">Submit</button>
                </div>
                <div class="col-3"></div>
            </div>
        </form>
    </div>
</div>
</div>
</div>

<!-- Optional JavaScript -->
<script src="js/script.js"></script>
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