package com.cdd.storageservice.global.utils;

import java.util.UUID;

import com.cdd.common.exception.CallConstructorException;

public class UUIDFactory {

	private UUIDFactory() {
		throw new CallConstructorException();
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}
}