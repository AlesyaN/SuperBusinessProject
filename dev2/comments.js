const addComment = (inputId) => {
    let query = $(`#${inputId}`).val();
    $.ajax({
        url: '/search/autocomplete.json',
        type: 'GET',
        data: {
            query: query
        },
        success: (msg) => {
            console.log(msg);
            let list = $('#comments-list');
            msg.forEach((user) => list.append(
                ` <div class="comment-item">
                    <a href="">${comment['author']}</a>
                    <p>${comment['text']}</p>
                    <small>${comment['date']}</small>
                    <br>
                    <button class="btn btn-danger">Delete comment</button>
                    <button class="btn btn-primary">Edit comment</button>
                    <hr>
                  </div>`
            ));
        }
    });
};
