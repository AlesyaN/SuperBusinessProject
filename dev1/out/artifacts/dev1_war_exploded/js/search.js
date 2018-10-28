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

    var query = $("#search-box").val();
    console.log(document.getElementById("news"));
    var news = document.getElementById("news").checked;
    var analysis = document.getElementById("analysis").checked;
    console.log(news);
    console.log(analysis);
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

