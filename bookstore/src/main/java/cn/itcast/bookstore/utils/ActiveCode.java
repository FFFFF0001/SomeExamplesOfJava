package cn.itcast.bookstore.utils;

import java.util.UUID;

public class ActiveCode {
	public static String generateActicecode(){
		String uuid=UUID.randomUUID().toString().replace("-", "");
		System.out.println(uuid);
		return uuid;
	}
}
