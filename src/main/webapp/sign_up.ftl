<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign up page</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<form method="post" action="/sign_up" enctype="multipart/form-data">
    Name:
    <input type="text" name="name" placeholder="Type your name here" required>
    <br><br>

    LastName:
    <input type="text" name="lastname" placeholder="Type your lastname here" required>
    <br><br>

    Login:
    <input type="text" name="login" id="login-input" placeholder="Type your login here" required>
    <div id="login-status"></div>
    <br><br>

    Password:
    <input type="password" name="password" placeholder="Type your password here" required>
    <br><br>

    <P>Upload Photo</P>
    <input type="file" name="image" accept="image/*" required>
    <br><br>

    <input type="submit" id="signup-btn" value="Sign up">
</form>

<script>
    var timeout;

    $(document).on("input", "#login-input", function (){
        var login = $(this).val();

        clearTimeout(timeout);
        timeout = setTimeout(() => {
            $.get("/ajax/sign_up?login=" + login, function (response) {
                $("#login-status").text(response);

                if (response === "Login already exists!") {
                    $("#signup-btn").prop('disabled', true);
                } else {
                    $("#signup-btn").prop('disabled', false);
                }
            });
        }, 500);
    });
</script>

</body>
</html>