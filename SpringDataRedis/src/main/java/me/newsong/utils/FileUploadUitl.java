package me.newsong.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUitl {
	public static void upload(MultipartFile file, ServletContext servletContext) throws IllegalStateException, IOException {
		String root = servletContext.getRealPath("/WEB-INF/uploads");
		// 文件名的哈希码的16进制字符串
		String hex = Integer.toHexString(file.getOriginalFilename().hashCode());
		File dirFile = new File(root, "/" + hex.charAt(0) + "/" + hex.charAt(1));
		dirFile.mkdirs();// 创建文件族，两层目录，最多创建256个文件夹
		file.transferTo(new File(dirFile, CommonUtils.uuid() + "_" + file.getOriginalFilename()));
	}
}
