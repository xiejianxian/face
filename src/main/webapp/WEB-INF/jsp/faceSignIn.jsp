<%--
  Created by IntelliJ IDEA.
  User: 72957
  Date: 2020/6/16
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人脸注册</title>
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        #media {
            width: 400px;
            height: 400px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div id="media">
        <video style="transform: rotateY(180deg);" id="video" width="500" height="300" autoplay></video>
        <canvas style="display: none;" id="canvas" width="500" height="300" ></canvas>
        <span class='user_info'>用户名称:</span> <input type="text" name="user_info" id="user_info" placeholder="请输入名称">
        <input type="button" onclick="signIn()" value="点击注册"/>
    </div>


    <script type="text/javascript" src="../statics/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        var video = document.getElementById("video");
        var context = canvas.getContext("2d");
        var con  ={
            audio:false,
            video:{
                width:1980,
                height:1024,
            }
        };

        //导航 获取用户媒体对象
        navigator.mediaDevices.getUserMedia(con).then(function(stream){
            video.srcObject = stream;
            video.onloadmetadate = function(e){
                video.play();
            }
        });

        //获取人脸照片的base64，
        function getBase64() {
            var imgSrc = document.getElementById("canvas").toDataURL("image/png");
            return imgSrc.split("base64,")[1];
        };

        //发送人脸注册的请求
        function signIn(){
            context.drawImage(video,0,0,400,300);//把流媒体数据画到convas画布上
            var base = getBase64();
            var userInfo = $('#user_info').val();
            var name = new Date().getTime() + ".png";
            $.ajax({
                url:"${pageContext.request.contextPath}/face/signIn",
                type:"post",
                data:{
                    imgData:base,
                    imgName:name,
                    user_info:userInfo
                },
                dataType:"JSON",
                success:function(result){
                    alert(result);
                    if (result.error_msg == "SUCCESS") {
                        var faceId = result.faceId;
                        $.ajax({
                            url:"${pageContext.request.contextPath}/face/addFaceIdToDatabase",
                            type:"post",
                            data:{},
                            dataType:"JSON",
                            success:function(result){
                                if (result == "success") {
                                    alert("人脸注册成功！");
                                    window.location="${pageContext.request.contextPath}/face/loginPage";
                                } else {
                                    alert("人脸注册失败！");
                                }
                            }
                        });
                    } else if (result == "exist") {
                        alert("人脸已存在，无需再次录入！");
                    }
                },
                error:function (data) {
                    alert("error");
                }
            });
        }



    </script>
</body>
</html>
