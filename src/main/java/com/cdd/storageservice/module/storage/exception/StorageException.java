package com.cdd.storageservice.module.storage.exception;

import com.cdd.common.exception.ErrorCode;
import com.cdd.common.exception.SangChuException;

public class StorageException extends SangChuException {
	public StorageException(ErrorCode errorCode) {
		super(errorCode);
	}
}
