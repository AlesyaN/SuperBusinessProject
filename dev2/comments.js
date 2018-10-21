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
            addComment(text);
        }
    });
};

// const addComment = (text) => {
//     let comment = document.createElement('div');
//     comment.setAttribute('class', 'comment-item');
//     let comment_username = document.createElement('a');
//     comment.appendChild

//     let container = document.getElementById('comments-list');
//     container.appendChild(comment);
// }

//  <div class="comments-list">
//           <div class="comment-item">
          
//             <a href="" id="comment-username">Username</a>
//             <p id="comment-text">comment</p>
//             <small id="comment-date">22.10.2018</small>
            
//             <br>
//             <button class="btn btn-danger">Delete comment</button>
//             <button class="btn btn-primary">Edit comment</button>
//             <hr>

//           </div>
//         </div>