package com.cdd.storageservice.module.storage.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FileRequest(
	@JsonProperty("file_source")
	String fileSource
) {
}
