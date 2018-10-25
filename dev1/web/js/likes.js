const like = (id) => {
    $.ajax({
        url: `/posts/${id}/like`,
        type: 'GET',
        success: (msg) => {
            console.log(msg);
            let likes = $('#likes_number').val();
            $('#likes_number').val(likes + 1);
        }
    });
};

const dislike = (id) => {
    $.ajax({
        url: `/posts/${id}/dislike`,
        type: 'GET',
        success: (msg) => {
            console.log(msg);
            let likes = $('#likes_number').val();
            $('#likes_number').val(likes - 1);
        }
    });
};

