<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
    <head>
        <title>即时聊天系统</title>
        <base href="<%=basePath%>"/>
        <script src="static/js/jquery-3.4.1.js"></script>
        <script>
            var userId = getCookie("userId");

            $(function () {
                $("#chat_content").css("height", window.innerHeight - 150);

                // 给#chat_edit_content绑定ctrl+enter事件即为#chat_edit_submit的点击事件，该事件为sendChat，
                $("#chat_edit_content").ctrlEnter($("#chat_edit_submit"), sendChat)

                refresh();
            })
            
            function refresh() {
                // 定时获取最新聊天记录
                setTimeout(refresh, 1000);
                $.ajax({
                    url: "refresh",
                    dataType: "json",
                    success: function (resp) {
                        $.each(resp, function (i, n) {
                            var $newChat;
                            if (n.userId == userId) {
                                $newChat = $("<div style='margin: 2px 5px 2px 52px; display: flex; justify-content: flex-end; align-items: flex-start;'>" +
                                        "<div style='text-align: right'>" +
                                            n.userName +
                                            "<div style='background-color: Lime; text-align: justify; padding: 3px; margin: 3px'>" +
                                                n.content +
                                            "</div>" +
                                        "</div>" +
                                        "<img style='height: 50px; width: 50px; margin: 3px; background-color: snow' src='static/images/" + n.userImage + "'>" +
                                        "</img>" +
                                    "</div>")
                            } else {
                                $newChat = $("<div style='margin: 2px 55px 2px 2px; display: flex; justify-content: flex-start; align-items: flex-start;'>" +
                                        "<img style='height: 50px; width: 50px; margin: 3px; background-color: snow' src='static/images/" + n.userImage + "'>" +
                                        "</img>" +
                                        "<div style='text-align: left'>" +
                                            n.userName +
                                            "<div style='background-color: darkturquoise; text-align: justify; padding: 3px; margin: 3px'>" +
                                                n.content +
                                            "</div>" +
                                        "</div>" +
                                    "</div>")
                            }

                            var $chat_content = $("#chat_content");

                            // 添加聊天记录
                            $chat_content.append($newChat)

                            $chat_content.animate({
                                scrollTop: $newChat.offset().top - $chat_content.offset().top + $chat_content.scrollTop()
                            }, 1000)
                        })
                    }
                })
            }

            function getCookie(cname)
            {
                var name = cname + "=";
                var ca = document.cookie.split(';');
                for(var i=0; i<ca.length; i++)
                {
                    var c = ca[i].trim();
                    if (c.indexOf(name)==0) return c.substring(name.length,c.length);
                }
                return "";
            }

            function sendChat() {
                var content = $("#chat_edit_content").val().trim();
                if (content != "") {
                    content = content.replace(/\r\n/g, '<br/>'); //IE9、FF、chrome
                    content = content.replace(/\n/g, '<br/>'); //IE7-8
                    content = content.replace(/\s/g, ' '); //空格处理
                    $.ajax({
                        url: "sendChat",
                        data: "content=" + content,
                        success: function (resp) {
                            if (resp == "true") {
                                $("#chat_edit_content").val("");
                            }
                        }
                    })
                }
            }

            $.fn.ctrlEnter = function (btns, fn) {
                var thiz = $(this);

                function performAction (e) {
                    fn.call(thiz, e);
                };
                thiz.bind("keydown", function (e) {
                    if (e.keyCode === 13 && e.ctrlKey) {
                        performAction(e);
                        e.preventDefault(); //阻止默认回车换行
                    }
                });
                btns.bind("click", performAction);
            }
        </script>
    </head>
    <body style="background: url(static/bg/bg.jpg) no-repeat center top">
        <div align="center">
            <div style="margin: 5px">
                <p align="center">即时聊天系统</p>
                <a href="exit" style="float: right">
                    <button>退出账号</button>
                </a>
            </div>
            <div id="chat" style="background: url(static/bg/chatbg.jpg) no-repeat center top; width: 500px">
                <div style="overflow: hidden; width: 100%">
                    <div id="chat_content" style="width: 520px; overflow-y: scroll; overflow-x: hidden;">
                    </div>
                </div>
                <div id="chat_edit" style="width: 100%; height: 100px" align="left">
                    <table style="height: 100%; width: 100%">
                        <tr>
                            <td style="width: 100%">
                                <textarea id="chat_edit_content" maxlength="200" style="resize: none; overflow-y:hidden; height: 100%; width: 100%"></textarea>
                            </td>
                            <td style="width: 50px">
                                <input id="chat_edit_submit" type="button" value="发送" style="height: 100%; width: 100%;">
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
