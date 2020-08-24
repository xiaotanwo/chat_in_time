<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 路径 getContextPath时有'/'的，即：/myWeb
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <title>即时聊天系统</title>
    <base href="<%=basePath%>"/>
    <script src="static/js/jquery-3.4.1.js"></script>
    <script>
        $(function () {
            var flag = false;
            $("#name").blur(function () {
                flag = false;
                var name = $("#name").val().trim();
                $("#btn_register").attr('disabled', true);
                if (name != "") {
                    $.ajax({
                        url: "check_name",
                        data: "name=" + name,
                        success: function (resp) {
                            if (resp == "true") {
                                flag = true;
                                $("#name_tips").html("");
                                if ($("#password").val().trim() != "") {
                                    $("#btn_register").attr('disabled', false);
                                }
                            } else {
                                $("#name_tips").html("用户名已被使用！");
                            }
                        }
                    })
                } else {
                    $("#name_tips").html("用户名不能为空！");
                }
            })

            $("#password").blur(function () {
                $("#password_tips").html("");
                $("#btn_register").attr('disabled', true);
                var password = $("#password").val().trim();
                if (password == "") {
                    $("#password_tips").html("密码不能为空/空格！");
                } else if (flag) {
                    $("#btn_register").attr('disabled', false);
                }
            })

            $("#image").change(function () {
                var file = this.files[0];
                // 确认选择的文件是图片
                if(file.type.indexOf("image") == 0) {
                    var reader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = function(e) {
                        // 图片base64化
                        var newUrl = this.result;
                        $("#imageshow").get(0).src = newUrl;
                    };
                }
            })
        })
    </script>
</head>
    <body style="background: url(static/bg/bg.jpg) no-repeat center top">
        <div align="center">
            <br>
            <h1>即时聊天系统，注册页面</h1>
            <br>
            <form action="register" method="post" enctype="multipart/form-data">
                头像：<br/>
                <img id="imageshow" height="100" width="100" src="static/images/default.jpg" />
                <br/>
                <input id="image" name="imageFile" type="file" accept="image/*"  value="" />
                <br><br>
                <span style="color: red">*</span>用户：<input id="name" name="name" type="text"/>
                <span id="name_tips" style="color: red"></span><br><br>
                <span style="color: red">*</span>密码：<input id="password" name="password" type="password"/>
                <span id="password_tips" style="color: red"></span><br><br>
                <%--头像：<input name="imageFile" type="file"><br><br>--%>
                <input id="btn_register" type="submit" value="注册" disabled>
            </form>
        </div>
    </body>
</html>
