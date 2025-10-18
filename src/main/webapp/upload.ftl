<html lang="en">
<#include "base.ftl">
<#macro title>
    Upload file
</#macro>
<#macro content>
    <P>Upload File</P>
    <form action="upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <br>
        <input type="submit" value="upload">
    </form>
</#macro>
</html>