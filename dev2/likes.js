const like = () => {
    $.ajax({
        url: '/posts/*/like',
        type: 'GET',
        success: (msg) => {
            console.log(msg);
            let likes = $('#likes_number').val();
            $('#likes_number').val(likes + 1);
        }
    });
};
