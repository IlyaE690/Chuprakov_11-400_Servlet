<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
</head>
<body>
<div class="profile">
    <h1>Профиль</h1>

    <#if user??>
        <div class="user-photo">
            <#if user.path?? && user.path?has_content>
                <img src="/${user.path}" alt="Фото" width="200">
            <#else>
                <p>Фото отсутствует</p>
            </#if>
        </div>

        <div class="user-details">
            <p><strong>ID:</strong> ${user.id!''}</p>
            <p><strong>Name:</strong> ${user.name!''}</p>
            <p><strong>Lastname:</strong> ${user.lastname!''}</p>
            <p><strong>Login:</strong> ${user.login!''}</p>
        </div>
    <#else>
        <p>User not found</p>
    </#if>
</div>
</body>
</html>