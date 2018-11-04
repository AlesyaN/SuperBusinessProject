function insertAll() {
    $.ajax({
        url: '/ajax',
        type: 'post',
        data: {
            "ajax": "allposts",
        },
        success: (msg) => {
            let list = $('#search-list');
            $('.article-item').remove();
            if (msg.posts != null) {
                for (var i = 0; i < msg.posts.length; i++) {
                    list.append("<div class='article-item'>" +
                        "<div class='row'> " +
                        "<div class='col-4'>" +
                        "<img src=" + msg.posts[i].picPath + " class='image img-fluid' alt='Responsive image'>" +
                        "</div>" +
                        "<div class='col-8'>" +
                        "<div class='art-text'>" +
                        "<h3>" + msg.posts[i].title + "</h3>" +
                        "<p>" + msg.posts[i].text + "</p>" +
                        "</div>" +
                        "<a href='/" + msg.posts[i].theme + "/" + msg.posts[i].id + "'>More</a>" +
                        "</div>" +
                        "</div>" +
                        "<hr>" +
                        "</div>");
                }
            } else {
            }
        }
    });
}

const autoComplete = (inputId) => {

    var query = $("#search-box").val();
    var news = document.getElementById("news").checked;
    var analysis = document.getElementById("analysis").checked;
    if (query.length > 0) {
        $.ajax({
            url: '/ajax',
            type: 'post',
            data: {
                "ajax": "search",
                "query": query,
                "news": news,
                "analysis": analysis
            },
            success: (msg) => {
                let list = $('#search-list');
                $('.article-item').remove();
                if (msg.posts != null) {
                    for (var i = 0; i < msg.posts.length; i++) {
                        list.append("<div class='article-item'>" +
                            "<div class='row'> " +
                            "<div class='col-4'>" +
                            "<img src=" + msg.posts[i].picPath + " class='image img-fluid' alt='Responsive image'>" +
                            "</div>" +
                            "<div class='col-8'>" +
                            "<div class='art-text'>" +
                            "<h3>" + msg.posts[i].title + "</h3>" +
                            "<p>" + msg.posts[i].text + "</p>" +
                            "</div>" +
                            "<a href='/" + msg.posts[i].theme + "/" + msg.posts[i].id + "'>More</a>" +
                            "</div>" +
                            "</div>" +
                            "<hr>" +
                            "</div>");
                    }
                } else {
                }
            }

            });
        } else {
            insertAll();
        }

    };

