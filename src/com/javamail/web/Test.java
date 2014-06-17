package com.javamail.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.util.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Test {
	public static String byte2hex(byte[] b) // 二进制转字符串  
	{  
	   StringBuffer sb = new StringBuffer();  
	   String stmp = "";  
	   for (int n = 0; n < b.length; n++) {  
	    stmp = Integer.toHexString(b[n] & 0XFF);  
	    if (stmp.length() == 1){  
	        sb.append("0" + stmp);  
	    }else{  
	        sb.append(stmp);  
	    }  
	      
	   }  
	   return sb.toString();  
	}  
	  
	public static byte[] hex2byte(String str) { // 字符串转二进制  
	    if (str == null)  
	     return null;  
	    str = str.trim();  
	    int len = str.length();  
	    if (len == 0 || len % 2 == 1)  
	     return null;  
	    byte[] b = new byte[len / 2];  
	    try {  
	     for (int i = 0; i < str.length(); i += 2) {  
	      b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();  
	     }  
	     return b;  
	    } catch (Exception e) {  
	     return null;  
	    }  
	 }  
	
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();   
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
	
	 static String getImageBinary(String file){   
	        File f = new File(file);          
	        BufferedImage bi;   
	        try {   
	            bi = ImageIO.read(f);   
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();   
	            ImageIO.write(bi, "jpg", baos);   
	            byte[] bytes = baos.toByteArray();   
	               
	            return StringUtils.trimAllWhitespace(encoder.encodeBuffer(bytes));   
	        } catch (IOException e) {   
	            e.printStackTrace();   
	        }   
	        return null;   
	    }   
	 
	public static void main(String [] args){
		String a=getImageBinary("f:\\logo.png");
		System.out.print(a);
	}
}
