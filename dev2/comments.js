const deleteComment = (id) => {
    $.ajax({
        url: `${window.location.pathname}/comments`,
        type: 'DELETE',
        success: (data) => {
            $('comments-item-${id}').remove();
        }
    });
}

const createComment = (textId) => {
    let text = $(`#${textId}`).val();
    $.ajax({
        url: `${window.location.pathname}/comments/${id}`,
        type: 'POST',
        data: {
            text: text
        },
        success: (data) => {
            // addComment(text);

            let list = $('#comments-list');
            list.append('<div class="comment-item">
                            <a href="/profile/${user.id}" id="comment-username">${user.username}</a>
                            <p id="comment-text">${text}</p>
                            <small id="comment-date">${comment.date}</small>
                            
                            <br>
                            <button class="btn btn-danger">Delete comment</button>
                            <button class="btn btn-primary">Edit comment</button>
                            <hr>
                        </div>'
            );
        }
    });
};

