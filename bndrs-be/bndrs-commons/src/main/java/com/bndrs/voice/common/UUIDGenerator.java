package com.bndrs.voice.common;

import java.text.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDGenerator {

	private final static String[] CHARS = new String[] { "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };

	/**
	 * 创建主键ID
	 * @return	返回格式:"yyyyMMddHHmmss"+8位UUID字符
	 */
	public static String generateId() {

		Date now = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		String id = format.format(now) + generateShortUuid();
		return id;
	}

	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(CHARS[x % 0x3E]);
		}
		return shortBuffer.toString();
	}

	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-","").toUpperCase();
	}

	public static void main(String[] args) {
		System.out.print(getUUID());
	}
}