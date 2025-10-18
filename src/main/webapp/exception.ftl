<html lang="en">
<#include "base.ftl">
<#macro title>
    Exception details
</#macro>
<#macro content>
    <h1>Exception details:</h1>
    <strong> Request uri: ${uri}</strong>
    <strong>Status code: ${statusCode}</strong>
    <#if message??><strong>${message}</strong></#if>
</#macro>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

</body>
</html>