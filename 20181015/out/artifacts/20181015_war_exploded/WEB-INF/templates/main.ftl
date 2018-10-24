<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
</head>
<body>
    <button id="button">GET</button>
    <p id="number"></p>
    <script>
        $(document).ready( function () {
            $('#button').click( function () {
                ('#number').text('42');
                    }
            )
                }
        );
    </script>
</body>
</html>