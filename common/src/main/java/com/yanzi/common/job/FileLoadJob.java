package com.yanzi.common.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;

import com.yanzi.common.trace.MLogger;


public abstract class FileLoadJob extends JobBase {
	private static final Logger LOGGER = new MLogger(FileLoadJob.class);

	protected String filePath;
	protected long lastLoadTime;

	public FileLoadJob() {
		lastLoadTime = 0;
	}

	@Override
	protected void run() {
		File file = new File(filePath);
		long lastModifiedTime = file.lastModified();
		if (!isNeedLoad(file)) {
			LOGGER.debug("file has not changed, skip load: " + filePath);
			return;
		}
		LOGGER.info("loading file: " + filePath);
		beforeRun();
		boolean success = false;
		try {
			success = loadFile(file);
		} finally {
			lastLoadTime = lastModifiedTime;
		}

		if (success) {
			afterRun();
			setNeedReloadOtherJob(true);
		}
	}

	@Override
	protected synchronized void setJobReload() {
		lastLoadTime = 0;
	}

	protected boolean loadFile(File file) {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			long start = System.currentTimeMillis();
			String eachLine;
			while ((eachLine = input.readLine()) != null && isContinue()) {
				readLine(eachLine);
			}
			waitLoadFinish();
			LOGGER.info(String.format("load file %s cost %d ms", file.getPath(), System.currentTimeMillis() - start));
			return true;
		} catch (IOException e) {
			LOGGER.error("File Read Error", e);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException exception) {
				LOGGER.error("Fail to close file reader", exception);
			}
		}
		return false;
	}

	protected void readLine(String line) {
	}

	protected void waitLoadFinish() {
	}

	protected boolean isContinue() {
		return true;
	}

	protected boolean isNeedLoad(File file) {
		if (file.exists() && file.lastModified() > lastLoadTime) {
			return true;
		}
		return false;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
