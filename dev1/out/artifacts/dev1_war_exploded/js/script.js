const charsLeft = () => document.getElementById('count').innerHTML = "Characters left: " + (160 - document.getElementById('comment').value.length);

const validateForm = (id, re, msg_id, msg) => {
    let btn = document.getElementById('submit');
    let form = document.getElementById(id);
    let message = document.getElementById(msg_id);
    let check = form.value.match(re);
    if (check == null) {
        message.innerHTML = msg;
        btn.disabled = true;
        return false;
    } else {
        message.innerHTML = '';
        btn.disabled = false;
        return true;
    }
}

const repeatPassword = () => {
    let btn = document.getElementById('submit');
    let message = document.getElementById('repeat_password_msg');
    if (document.getElementById('password').value != document.getElementById('repeat_password').value) {
        message.innerHTML = 'Passwords don\'t match!';
        btn.disabled = true;
        return false;
    } else {
        message.innerHTML = '';
        btn.disabled = false;
        return true;
    }
}















