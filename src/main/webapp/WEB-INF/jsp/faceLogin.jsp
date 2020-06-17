<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>人脸识别</title>
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
		<input type="button" onclick="login()" value="点击识别"/>
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
  		
		
		//获取人脸照片的base64，用于发送给后台进行识别
		function getBase64() {
			var imgSrc = document.getElementById("canvas").toDataURL("image/png");
			return imgSrc.split("base64,")[1];
		};

		//发送人脸识别的请求
		function login(){
			context.drawImage(video,0,0,500,300);//把流媒体数据画到convas画布上
			var imgData = getBase64();
			$.ajax({
				url:"${pageContext.request.contextPath}/face/login",
				type:"post",
				dataType:"JSON", 
				data:{imgData:imgData},
				success:function(result){
				    if (result == "success") {
				        alert("识别成功！");

				    //    查询用户数据

					} else {
				        alert("识别失败！");
					}
				},
				error:function () {
				    alert("error");
			    }
			});

		}
			
	</script>
</body>
</html>
