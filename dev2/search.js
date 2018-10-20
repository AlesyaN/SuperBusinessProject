const autoComplete = (inputId) => {
    let query = $(`#${inputId}`).val();
    if (query.length > 2) {
        $.ajax({
            url: '/search/autocomplete.json',
            type: 'GET',
            data: {
                query: query
            },
            success: (msg) => {
                console.log(msg);
                let list = $('#search-list');
                $('.article-item').remove();
                msg.forEach((user) => list.append(
                    `<div class="article-item">
                        <div class="row">
                            <div class="col-4">
                                <img src="pic.jpeg" width="100px" height="100px" alt="Responsive image">
                            </div>
                            <div class="col-8">
                                <h3>${post['title']}</h3>
                                <p>${post['text']}</p>
                                <a href="">More</a>
                            </div>
                        </div>
                        <hr>
                    </div>`
                ));
            }
        });
    }
};

