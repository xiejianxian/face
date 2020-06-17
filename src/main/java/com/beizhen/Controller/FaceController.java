package com.beizhen.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.beizhen.util.GetToken;
import com.beizhen.util.GsonUtils;
import com.beizhen.util.HttpUtil;
import org.json.JSONObject;
import com.baidu.aip.face.AipFace;
import com.beizhen.entity.User;
import com.beizhen.util.FactoryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("face")
public class FaceController {

    //人脸模块对象
    AipFace aipFace = FactoryUtil.getAipFace();

    /**
     * 访问人脸注册页面
     * @return
     */
    @RequestMapping("signInPage")
    public String signInPage() {
        return "jsp/faceSignIn";
    }

    /**
     * 访问人脸识别页面
     * @return
     */
    @RequestMapping("loginPage")
    public String loginPage() {
        return "jsp/faceLogin";
    }

    /**
     * 人脸注册
     * @param user
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("signIn")
    @ResponseBody
    public String signIn(User user, HttpServletRequest request, HttpServletResponse response) {
        //结果
        String result = "";

//        //请求的url,可以查看官方文档查看不同请求的url
//        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
//        Map<String, Object> map = new HashMap<>();
//        map.put("image", request.getParameter("imgData"));//获取前台的人脸识别后发送的base64
//        map.put("group_id_list", "1");//之前创建的人脸库，可以在百度云的管理控制台查看用户组，1是之前命名好的
//        map.put("image_type", "BASE64");//照片类型为base64
//        String param = GsonUtils.toJson(map);
//
//        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
//        String accessToken = GetToken.getAuth();
//
//        String result = null;
//        try {
//            result = HttpUtil.post(url, accessToken, "application/json", param);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("识别结果为：\n"+result);
//        com.alibaba.fastjson.JSONObject jsonObject = (com.alibaba.fastjson.JSONObject) JSON.parse(result);
//        com.alibaba.fastjson.JSONObject object = (com.alibaba.fastjson.JSONObject) jsonObject.get("result");
//        JSONArray string = (JSONArray) object.get("user_list");
//        com.alibaba.fastjson.JSONObject ob = (com.alibaba.fastjson.JSONObject) string.get(0);
//        BigDecimal valueOf = (BigDecimal) ob.get("score");
//        String results = null;
//        if(valueOf.doubleValue() > 90) {
//            results = "success";
//            System.out.println("识别相似度大于90分");
//        }else {
//            results = "false";
//            System.out.println("识别相似度小于90分");
//        }









        if (user.getUser_info().equals("") || null == user.getUser_info()) {
            result = "用户名为空";
            return JSON.toJSONString(result);
        } else {
            String groupId = "1";   //用户组id
            String userId = UUID.randomUUID().toString().replace("-", "").toUpperCase();//用户id(生成唯一标识)
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("user_info","斜键仙");
            JSONObject resultObject = aipFace.addUser(user.getImgData(),"BASE64",groupId,userId,map);
            System.out.println(resultObject.toString(2));
            return resultObject.toString();
        }
    }

    /**
     * 人脸识别
     * @param request
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        //请求的url,可以查看官方文档查看不同请求的url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        Map<String, Object> map = new HashMap<>();
        map.put("image", request.getParameter("imgData"));//获取前台的人脸识别后发送的base64
        map.put("group_id_list", "1");//之前创建的人脸库，可以在百度云的管理控制台查看用户组，1是之前命名好的
        map.put("image_type", "BASE64");//照片类型为base64
        String param = GsonUtils.toJson(map);

        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
        String accessToken = GetToken.getAuth();

        String result = null;
        try {
            result = HttpUtil.post(url, accessToken, "application/json", param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("识别结果为：\n"+result);
        com.alibaba.fastjson.JSONObject jsonObject = (com.alibaba.fastjson.JSONObject) JSON.parse(result);
        com.alibaba.fastjson.JSONObject object = (com.alibaba.fastjson.JSONObject) jsonObject.get("result");
        JSONArray string = (JSONArray) object.get("user_list");
        com.alibaba.fastjson.JSONObject ob = (com.alibaba.fastjson.JSONObject) string.get(0);
        BigDecimal valueOf = (BigDecimal) ob.get("score");
        String results = null;
        if(valueOf.doubleValue() > 90) {
            results = "success";
            System.out.println("识别相似度大于90分");
        }else {
            results = "false";
            System.out.println("识别相似度小于90分");
        }
        return JSON.toJSONString(results);
    }

}
