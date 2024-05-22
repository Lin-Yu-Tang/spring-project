package com.tom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

@RestController
@Slf4j
public class HelloController {

	
	@GetMapping("hello")
	public String hello() {
		
		test("D://test//zip//", "test.zip", "12342");
		
		
		return "";
	}
	
	
	private void test(String rootPath, String filName, String password) {
		try {
            File zipFile = new File(rootPath + filName);
            File[] files = unzip(zipFile, password);
            if (files != null && files.length > 0) {
            	for (File f : files) {
            		System.out.println(f.getName());
            	}
            }
        } catch (ZipException e) {
            log.error("getBCode exception", "exception", e);
        }
	}
	
	
	
	
	private static File[] unzip(File zipFile, String passwd) throws ZipException {
        File parentDir = zipFile.getParentFile();
        return unzip(zipFile, parentDir.getAbsolutePath(), passwd);
    }
	
	
	private static File[] unzip(File zipFile, String dest, String passwd) throws ZipException {
        try (ZipFile zFile = new ZipFile(zipFile)) {
            zFile.setCharset(StandardCharsets.UTF_8);
            if (!zFile.isValidZipFile()) {
                throw new ZipException("壓縮檔案不合法,可能被損壞");
            }
            File destDir = new File(dest);
            if (destDir.isDirectory() && !destDir.exists()) {
                destDir.mkdir();
            }
            if (zFile.isEncrypted()) {
                zFile.setPassword(passwd.toCharArray());
            }
            zFile.extractAll(dest);

            List<FileHeader> headerList = zFile.getFileHeaders();
            List<File> extractedFileList = new ArrayList<File>();
            for (FileHeader fileHeader : headerList) {
                if (!fileHeader.isDirectory()) {
                    extractedFileList.add(new File(destDir, fileHeader.getFileName()));
                }
            }
            File[] extractedFiles = new File[extractedFileList.size()];
            extractedFileList.toArray(extractedFiles);

            return extractedFiles;
        } catch (ZipException e) {
            throw e;
        } catch (IllegalArgumentException | IOException e) {
            throw new ZipException("IllegalArgumentException or IOException");
        }
    }
	
	
	
}
