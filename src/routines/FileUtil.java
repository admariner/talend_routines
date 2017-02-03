package routines;

import java.io.File;
/**
 * Copyright 2017 Jan Lolling jan.lolling@cimt-ag.de
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class FileUtil {
	
	/**
	 * Returns the file extension of file
	 * 
	 * {Category} FileUtil
	 * 
	 * {param} string(filePath) fullPath: String.
	 * 
	 * {example} getFileExtension(filePath) # ""
	 */
	public static String getFileExtension(String filePath) {
		File f = new File(filePath);
		String name = f.getName();
		int pos = name.lastIndexOf('.');
		if (pos >= 0 && pos < name.length() - 1) {
			return name.substring(pos + 1);
		} else {
			return "";
		}
	}

	/**
	 * Returns the directory of the file
	 * 
	 * {Category} StringUtil
	 * 
	 * {param} string(fullPath) fullPath: String.
	 * 
	 * {example} getFileDir(fullPath) # ""
	 */
	public static String getFileDir(String filePath) {
		File f = new File(filePath);
		String parent = f.getParent();
		if (parent != null) {
			return parent;
		} else {
			return "";
		}
	}

	/**
	 * Returns the name of file without path
	 * 
	 * {Category} StringUtil
	 * 
	 * {param} string(fullPath) fullPath: String.
	 * 
	 * {example} getFileName(fullPath) # ""
	 */
	public static String getFileName(String filePath) {
		File f = new File(filePath);
		return f.getName();
	}

	/**
	 * Returns the name of file without path and extension
	 * 
	 * {Category} StringUtil
	 * 
	 * {param} string(fullPath) fullPath: String.
	 * 
	 * {example} getFileNameWithoutExt(fullPath) # ""
	 */
	public static String getFileNameWithoutExt(String filePath) {
		File f = new File(filePath);
		String name = f.getName();
		int pos = name.lastIndexOf('.');
		if (pos > 0) {
			return name.substring(0, pos);
		} else {
			return name;
		}
	}

	/**
	 * Returns the relative path from a full path based on a base path
	 * 
	 * {Category} StringUtil
	 * 
	 * {param} string(fullPath) fullPath: String.
	 * 
	 * {param} string(basePath) basePath: String.
	 * 
	 * {example} getRelativePath(basePath, fullPath) # ""
	 */
	public static String getRelativePath(String fullPath, String basePath) {
		if (fullPath == null || fullPath.trim().isEmpty()) {
			return null;
		}
		if (basePath == null || basePath.trim().isEmpty()) {
			return fullPath;
		}
		// normalize path
		fullPath = fullPath.replaceAll("\\\\", "/").trim();
		basePath = basePath.replaceAll("\\\\", "/").trim();
		fullPath = fullPath.replaceAll("[/]{2,}", "/").trim();
		fullPath = fullPath.replaceAll("/./", "/").trim();
		basePath = basePath.replaceAll("[/]{2,}", "/").trim();
		basePath = basePath.replaceAll("/./", "/").trim();
		if (basePath.endsWith("/")) {
			basePath = basePath.substring(0, basePath.length() - 1);
		}
		int pos = fullPath.indexOf(basePath);
		if (pos == -1) {
			return null;
		}
		return fullPath.substring(pos + basePath.length() + 1);
	}

	/**
	 * Setup a path to a directory, all missing parts will be created
	 * 
	 * {Category} FileUtil
	 * 
	 * {param} string(directoryPath) basePath: String.
	 * 
	 * {example} ensureDirectoryExists(directoryPath) # ""
	 */
	public static void ensureDirectoryExists(String directoryPath) throws Exception {
		File dir = new File(directoryPath);
		if (dir.exists() == false) {
			dir.mkdirs();
		} else {
			if (dir.isFile()) {
				throw new Exception("The directory path: " + directoryPath + " points to an existing file!");
			}
		}
		if (dir.exists() == false) {
			throw new Exception("The directory path: " + directoryPath + " cannot be created. Check rights or path syntax.");
		}
	}

	/**
	 * Checks if a file exists
	 * 
	 * {Category} FileUtil
	 * 
	 * {param} string(filePath) basePath: String.
	 * 
	 * {example} doesFileExist(filePath) # ""
	 */
	public static boolean doesFileExist(String filePath) throws Exception {
		if (filePath == null) {
			return false;
		}
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * Checks if a file exists
	 * 
	 * {Category} FileUtil
	 * 
	 * {param} string(dirPath) basePath: String.
	 * {param} string(fileName) basePath: String.
	 * 
	 * {example} doesFileExist(dirPath, fileName) # ""
	 */
	public static boolean doesFileExist(String dirPath, String fileName) throws Exception {
		if (dirPath == null || dirPath.trim().isEmpty()) {
			return false;
		}
		if (fileName == null || fileName.trim().isEmpty()) {
			return false;
		}
		File file = new File(dirPath, fileName);
		return file.exists();
	}
	
}
