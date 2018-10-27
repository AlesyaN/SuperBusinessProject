function insertAll() {
    $.ajax({
        url: '/ajax',
        type: 'post',
        data: {
            "ajax": "allposts",
        },
        success: (msg) => {
            console.log(msg.posts);
            let list = $('#search-list');
            $('.article-item').remove();
            if (msg.posts != null) {
                for (var i = 0; i < msg.posts.length; i++) {
                    list.append("<li class='article-item'>" +
                        "<div class='row'> " +
                        "<div class='col-4'>" +
                        "<img src=" + msg.posts[i].picPath + " width='100px' height='100px' alt='Responsive image'>" +
                        "</div>" +
                        "<div class='col-8'>" +
                        "<h3>" + msg.posts[i].title + "</h3>" +
                        "<p>" + msg.posts[i].text + "</p>" +
                        "<a href='/" + msg.posts[i].theme + "/" + msg.posts[i].id + "'>More</a>" +
                        "</div>" +
                        "</div>" +
                        "<hr>" +
                        "</li>");
                }
            } else {
            }
        }
    });
}

const autoComplete = (inputId) => {
    let query = $("#search-box").val();
    if (query.length > 0) {
        $.ajax({
            url: '/ajax',
            type: 'post',
            data: {
                "ajax": "search",
                "query": query
            },
            success: (msg) => {
                console.log(msg.posts);
                let list = $('#search-list');
                $('.article-item').remove();
                if (msg.posts != null) {
                    for (var i = 0; i < msg.posts.length; i++) {
                        list.append("<li class='article-item'>" +
                            "<div class='row'> " +
                            "<div class='col-4'>" +
                            "<img src=" + msg.posts[i].picPath + " width='100px' height='100px' alt='Responsive image'>" +
                            "</div>" +
                            "<div class='col-8'>" +
                            "<h3>" + msg.posts[i].title + "</h3>" +
                            "<p>" + msg.posts[i].text + "</p>" +
                            "<a href='/" + msg.posts[i].theme + "/" + msg.posts[i].id + "'>More</a>" +
                            "</div>" +
                            "</div>" +
                            "<hr>" +
                            "</li>");
                    }
                } else {
                }
            }

            });
        } else {
            insertAll();
        }

    };

