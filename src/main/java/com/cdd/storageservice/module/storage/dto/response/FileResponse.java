package com.cdd.storageservice.module.storage.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record FileResponse(
	@JsonProperty("file_source")
	String fileSource
) {
}
