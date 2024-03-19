package cn.xstrive.miniprogram.controller;


import cn.xstrive.entity.Customer;
import cn.xstrive.miniprogram.common.HttpClientUtil;
import cn.xstrive.miniprogram.common.JsonUtils;
import cn.xstrive.miniprogram.common.RedisOperator;
import cn.xstrive.miniprogram.common.XstriveJSONResult;
import cn.xstrive.miniprogram.model.WXSessionModel;
import cn.xstrive.miniprogram.pojo.MiniUser;
import cn.xstrive.miniprogram.service.openfeign.UserInfoProviderService;
import cn.xstrive.miniprogram.util.WXCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WXLoginController {
	
	@Autowired
	private RedisOperator redis;
	@Autowired
	private UserInfoProviderService parttwo;

	@PostMapping("/wxLogin")
	public XstriveJSONResult wxLogin(@RequestParam(value = "code", required = false) String code,
									 @RequestParam(value = "rawData", required = false) String rawData,
									 @RequestParam(value = "signature", required = false) String signature,  //由前端传回的签名
									 @RequestParam(value = "encrypteData", required = false) String encrypteData,
									 @RequestParam(value = "iv", required = false) String iv) {
		MiniUser miniUser = JsonUtils.jsonToPojo(rawData, MiniUser.class);
		String url="https://api.weixin.qq.com/sns/jscode2session";
		Map<String,String>param = new HashMap<>();
		param.put("appid","wxe267a1e702331ada");
		param.put("secret","0be132098121913cdd080c08c32c9b80");
		param.put("js_code",code);
		param.put("grant_type","authorization_code");
		String s = HttpClientUtil.doGet(url, param);
		WXSessionModel wxSessionModel = JsonUtils.jsonToPojo(s, WXSessionModel.class);
		//但是我先不对数据的完整性进校验
		//String aftersec = WXCore.decrypt("wxe267a1e702331ada", encrypteData, wxSessionModel.getSession_key(), iv);
		//MiniUser miniUser = JsonUtils.jsonToPojo(aftersec, MiniUser.class);
		//System.out.println(miniUser);

		//String infourl="https://api.weixin.qq.com/sns/userinfo";
		//Map<String,String>infoparam = new HashMap<>();

		miniUser.setOpenId(wxSessionModel.getOpenid());
		miniUser.setSessionvalue(wxSessionModel.getSession_key());
		System.out.println(miniUser);
		long l = System.currentTimeMillis();
		String touser="user-session:" + wxSessionModel.getSession_key()+l%10000;  //返回的自定义id
		miniUser.setSkey(touser);

		//如果数据库中openid存在，则跳过，否则则插入数据库
		Customer wxisexist = parttwo.wxisexist(wxSessionModel.getOpenid());
		if(wxisexist==null){    //说明不存在，则需要插入数据,否则就不执行相关代码
			Customer customer=new Customer();
			customer.setAvatar(miniUser.getAvatarUrl());
			customer.setNickName(miniUser.getNickName());
			if(miniUser.getGender()==0)
				customer.setSex("男");
			else
				customer.setSex("女");
			customer.setOpenid(miniUser.getOpenId());
			parttwo.saveinfo(customer);
		}
		redis.set(touser,wxSessionModel.getOpenid(),24*3600);

		return XstriveJSONResult.ok(touser);    //把自定义登录态返回给小程序

	}
	
}
