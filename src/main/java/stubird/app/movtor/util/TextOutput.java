package stubird.app.movtor.util;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class TextOutput implements Closeable {
	private static Logger logger = Logger.getLogger(TextOutput.class);
	
	private String outputPath;
	private String fileName;
	private File outputFile;
	private BufferedWriter writer;
	private final String pathSplitter = System.getProperty("file.separator");
	private final String lineSeparator = System.lineSeparator();
	
	private int writeCount = 0;
	private int writeSuccessCount = 0;
	private boolean isOutTime = false;
	public static int endLine = 5;
	public static String endMark = "";
	
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public TextOutput() throws IOException {
		outputPath = getDefaultOutputPath();
		fileName = getDefaultFileName();
		outputFile = new File(outputPath + pathSplitter + fileName);
		
		createWriter(outputFile, true);
	}
	
	public TextOutput(File outputFile, boolean isAppend) throws IOException {
		fileName = outputFile.getName();
		outputPath = outputFile.getCanonicalPath();
		this.outputFile = outputFile;
		
		createWriter(outputFile, isAppend);
	}
	
	public TextOutput(File outputFile, boolean isAppend, boolean isOverWrite) throws IOException {
		fileName = outputFile.getName();
		outputPath = outputFile.getCanonicalPath();
		this.outputFile = outputFile;
		
		createWriter(outputFile, isAppend, isOverWrite);
	}

	public TextOutput(String inputPath) throws IOException {
		getPathAndName(inputPath);
		outputFile = new File(outputPath + pathSplitter + fileName);
		
		createWriter(outputFile, true);
	}
	
	private void createWriter(File file, boolean isAppend) throws IOException {
		createWriter(file, isAppend, true);
	}
	
	private void createWriter(File file, boolean isAppend, boolean isOverwrite) throws IOException {
		if (file.exists() && !isAppend && !isOverwrite) {
			throw new FileAlreadyExistsException(file.getAbsolutePath() + " is already exist.");
		}
		
		if (!file.getParentFile().exists()) {
			logger.debug("dir is not exist. make the dir : " + file.getParent());
			file.getParentFile().mkdirs();
		}
		
		writer = new BufferedWriter(new FileWriter(file, isAppend));
	}
	
	private void getPathAndName(String inputPath) {
		int fileSplitterIndex = inputPath.lastIndexOf(pathSplitter);
		if (fileSplitterIndex >= 0) {
			outputPath = inputPath.substring(0, fileSplitterIndex);
			fileName = fileSplitterIndex == (inputPath.length() - 1) ? getDefaultFileName() : inputPath.substring(fileSplitterIndex + 1, inputPath.length()) ;
		} else {
			outputPath = getDefaultOutputPath();
			fileName = inputPath.length() > 0 ? inputPath : getDefaultFileName();
		}
	}

	private String getDefaultFileName() {
		return "output.txt";
	}
	
	private String getDefaultOutputPath() {
		return System.getProperty("user.dir") + pathSplitter + "log";
	}
	
	public boolean outLine(String lineContent) {
		boolean isOK = true;
		
		if (writeCount == 0 && isOutTime == true)
			outTime();
		
		try {
			writer.append(lineContent);
			writer.append(lineSeparator);
			writeSuccessCount++;
		} catch (IOException e) {
			logger.debug("write the line failed : " + lineContent + "\t" + e);
			isOK = false;
		}
		
		writeCount++;
		return isOK;
	}
	
	public boolean out(String content) {
		boolean isOK = true;
		if (writeCount == 0 && isOutTime == true)
			outTime();
				
		try {
			writer.append(content);
			writeSuccessCount++;
		} catch (IOException e) {
			logger.debug("write the line failed : " + content + "\t" + e);
			isOK = false;
		}
		
		writeCount++;
		return isOK;
	}
	
	public void outTime() {
		String timeStr = df.format(System.currentTimeMillis());

		try {
			writer.append("OutputTime : " + timeStr);
			writer.append(lineSeparator);
		} catch (IOException e) {
			logger.debug("write time failed : " + timeStr + "\t" + e);
		}
	}
	
	public void isOutTime(boolean isOut) {
		isOutTime = isOut;
	}
	
	public void writeOver() {
		for(int i = 0; i < endLine; i++) {
			outLine(endMark);
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			logger.debug("close bufferedwriter failed : " + e);
			logger.error(e.getMessage(), e);
		}
		
		logger.debug("output line : " + writeCount + "\tsuccess : " + writeSuccessCount);
	}
	
	public void close() throws IOException {
		writeOver();
	}
	
	public static void main(String[] args) {
		
		try {
			TextOutput output = new TextOutput("C:\\Users\\Administrator\\Desktop\\org.txt");
			output.outLine("line");
			
			output.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
