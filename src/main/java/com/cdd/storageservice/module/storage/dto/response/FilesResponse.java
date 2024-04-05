package com.cdd.storageservice.module.storage.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record FilesResponse(
	@JsonProperty("file_sources")
	List<String> fileSourceList
) {
}
