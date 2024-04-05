package com.cdd.storageservice.module.storage.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.cdd.storageservice.global.utils.UUIDFactory;
import com.cdd.storageservice.module.storage.dto.request.FileRequest;
import com.cdd.storageservice.module.storage.dto.request.FilesRequest;
import com.cdd.storageservice.module.storage.dto.response.FileResponse;
import com.cdd.storageservice.module.storage.dto.response.FilesResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StorageService {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
	@Value(("${cloud.aws.path}"))
	private String awsPath;

	public FileResponse saveFile(MultipartFile file) throws IOException {
		String filename = UUIDFactory.generateUUID();
		String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		String fileSource = filename + "." + extension;
		amazonS3Client.putObject(bucket, fileSource, file.getInputStream(), getObjectMetadata(file));
		return FileResponse.builder()
			.fileSource(awsPath + fileSource)
			.build();
	}

	public FilesResponse saveFiles(List<MultipartFile> files) throws IOException {
		List<String> fileSourceList = new ArrayList<>();
		for(MultipartFile file : files){
			String filename = UUIDFactory.generateUUID();
			String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
			String fileSource = filename + "." + extension;
			amazonS3Client.putObject(bucket, fileSource, file.getInputStream(), getObjectMetadata(file));
			fileSourceList.add(awsPath + fileSource);
		}
		return FilesResponse.builder()
			.fileSourceList(fileSourceList)
			.build();
	}
    public void deleteFile(FileRequest file) {
        amazonS3Client.deleteObject(bucket, file.filesource());
    }

    public void deleteFiles(FilesRequest files) {
        for (String filesource : files.fileSourceList()) {
            amazonS3Client.deleteObject(bucket, filesource);
        }
    }

	private ObjectMetadata getObjectMetadata(MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        return metadata;
    }
}
