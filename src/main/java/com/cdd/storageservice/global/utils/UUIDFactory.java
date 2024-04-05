package com.cdd.storageservice.global.utils;

import java.util.UUID;

public class UUIDFactory {
	public static String generateUUID(){
		return UUID.randomUUID().toString();
	}
}