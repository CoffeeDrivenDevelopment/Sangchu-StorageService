package com.cdd.storageservice.module.storage.presentation;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cdd.storageservice.module.storage.application.StorageService;
import com.cdd.storageservice.module.storage.dto.response.FileResponse;
import com.cdd.storageservice.module.storage.dto.response.FilesResponse;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequestMapping("/storage-service")
@RequiredArgsConstructor
@RestController
public class StorageController {
	private final StorageService storageService;

	@PostMapping("/v1/file")
	public ResponseEntity<FileResponse> saveFile(@RequestPart(name = "file_source") MultipartFile file) throws
		IOException {
		return ResponseEntity.ok(storageService.saveFile(file));
	}

	@PostMapping("/v1/files")
	public ResponseEntity<FilesResponse> saveFiles(@RequestPart(name = "file_sources") List<MultipartFile> files) throws
		IOException {
		return ResponseEntity.ok(storageService.saveFiles(files));
	}
}
