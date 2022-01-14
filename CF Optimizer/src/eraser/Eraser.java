package eraser;

import java.awt.Toolkit;
import java.io.File;

import javax.swing.JDialog;

import gui.Result;

public class Eraser implements Runnable {
	
	public Eraser() {
	}
	
	public void run() {
		File dir = new File(System.getProperty("user.dir"));
		String fileSep = System.getProperty("file.separator");
		String str = dir.getAbsolutePath() + fileSep;
		File content = new File(str + "patcher_cf.exe");
		if (content.exists()) {
			// CF directory
			content = new File(str + "backup");
			deleteDir(content);
			content = new File(str + "hgwc");
			deleteDir(content);
			content = new File(str + "Log");
			deleteDir(content);
			content = new File(str + "Report");
			deleteDir(content);
			content = new File(str + "x64" + fileSep + "Report");
			deleteDir(content);
			// Current working drive
			String driveDir = System.getProperty("user.home").substring(0, 2) + fileSep + "CFLog";
			content = new File(driveDir);
			deleteDir(content);
			showResult("Operation finished successfully!");
		}
		else
			showResult("Error: this is not the CF folder!");
	}
	
	private void deleteDir(File content) {
		if (content.exists() && content.isDirectory()) {
			File[] list = content.listFiles();
			for (File f: list) {
				if (f.isFile())
					f.delete();
				else
					deleteDir(f);
			}
			content.delete();
		}
	}
	
	private void showResult(String s) {
		try {
			Result dialog = new Result(s);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Runnable snd = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
		if (snd != null)
			snd.run();
	}
	
	public static void main(String [] args) {
		new Thread(new Eraser()).start();
	}
}