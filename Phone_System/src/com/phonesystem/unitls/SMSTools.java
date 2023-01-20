package com.phonesystem.unitls;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SMSTools {

	 
	public static Boolean getCode(String phone_number,long random ) {
		
	 
		//long random = (long) (Math.random()*(9999-1000)+1000);  
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		 	final String product = "Dysmsapi"; 
		final String domain = "dysmsapi.aliyuncs.com"; 
	 	final String accessKeyId = "LTAI4GDS4rr17HNqVYPTvCES"; 
		final String accessKeySecret = "xSvN9hCFCAjUppp3daa4P13yyPUmcR"; 
	  	IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
		  SendSmsRequest request = new SendSmsRequest();
		  request.setMethod(MethodType.POST);
		  request.setPhoneNumbers(phone_number);
		  request.setSignName("è´Ã÷ÉÌ³Ç");
		  request.setTemplateCode("SMS_205590638");
		 	 request.setTemplateParam("{\"name\":\"Tom\", \"code\":\""+random+"\"}");
		  request.setOutId("yourOutId");
	 	SendSmsResponse sendSmsResponse = null;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
		 	return true;
		}else {
			return false;
		}
	}
	
	 
}
