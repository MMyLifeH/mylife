package com.phonesystem.unitls;

public class YanTool {
	public static   String getRandom()
	  {
		  char cs[]="asdfghjklasdawertyuioxcvbadsdasdnWERTYUIODFGHJKXCVBNM12456789".toCharArray();
		  
		  StringBuffer sb=new StringBuffer();
		  for(int i=0;i<4;i++)
		  {
			  int pos=(int)(Math.random()*cs.length);
			  sb.append(cs[pos]);
		  }
		  return sb.toString();
		  
	  }
}
