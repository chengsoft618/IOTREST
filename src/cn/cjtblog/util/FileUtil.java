package cn.cjtblog.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cjtblog.domain.Image;

public class FileUtil {
	private static Logger logger=LoggerFactory.getLogger(FileUtil.class);
	public static void saveFile(String filePath, String fileName, byte[] bytes) {
		
		String fileFullName = filePath + fileName;
		saveFile(fileFullName, bytes);
	}

	public static void makeDir(String nodeDir) {
		File dir = new File(Image.BASICIMAGEDIR + nodeDir);
		if (dir.exists()) {
			logger.info(dir.getAbsolutePath() + " is exist");
			return;
		}
		if (!dir.mkdirs()) {
			logger.info("mkdir " + nodeDir + "failed");
		} else {
			try {
				logger.info("mkdir " + dir.getCanonicalPath() + "success");
			} catch (IOException e) {
				logger.info("",e);
			}
		}
	}

	public static void saveFile(String fileFullName, byte[] bytes) {
		File file = new File(fileFullName);
		if (file.exists()) {
			logger.info("file has exitst");
			return;
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("",e);
		}
		try (FileOutputStream fileOutput = new FileOutputStream(file)) {
			logger.info("writing:"+bytes.length);
			fileOutput.write(bytes);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.info("",e1);
		}
	}

	public static void deleteFileByFullName(String fileFullName) {
		File file = new File(fileFullName);
		if (file.exists()) {
			file.delete();
		}
	}
}
