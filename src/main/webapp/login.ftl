<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Login page</title>
</head>

<body>
<p>Уже есть аккаунт</p>
<br>
<form method="post" action="/login">
    Login:
    <input type="text" name="login" placeholder="type your login here">
    Password:
    <input type="password" name="password">
    <br>
    <input type="submit" value="Войти">
</form>

<br>
<p><a href="/sign_up">Зарегистрироваться</a></p>
</body>

</html>