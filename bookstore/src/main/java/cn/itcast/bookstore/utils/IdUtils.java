package cn.itcast.bookstore.utils;

import java.util.UUID;

public class IdUtils {
	public static String getID(){
		String uuid=UUID.randomUUID().toString().replace("-","");
		System.out.println(uuid);
		return uuid;
	}
	public static void main(String[]args){
		getID();
	}
}
