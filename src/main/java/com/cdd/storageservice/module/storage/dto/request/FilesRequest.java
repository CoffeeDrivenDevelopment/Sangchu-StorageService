package com.cdd.storageservice.module.storage.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilesRequest(
	@JsonProperty("file_sources")
	List<String> fileSourceList
) {
}
