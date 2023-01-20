package com.phonesystem.unitls;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static String getMD5(String str) {
		try {
			MessageDigest md =MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buffer=new StringBuffer("");
			for(int offset=0;offset<b.length;offset++) {
				i=b[offset];
				if(i<0) {
					i+=256;
				}
				if(i<16) {
					buffer.append("0");
				}
				buffer.append(Integer.toHexString(i));
			}
			str=buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
