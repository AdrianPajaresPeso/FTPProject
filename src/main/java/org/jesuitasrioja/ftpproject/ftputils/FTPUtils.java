package org.jesuitasrioja.ftpproject.ftputils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;

public class FTPUtils {

	public static FTPClient getFTPConection() throws SocketException, IOException {

		Properties propiedades = new Properties();
		propiedades.load(new FileInputStream("src/main/java/credentials.properties"));
		FTPClient cl = new FTPClient();
		cl.connect(propiedades.getProperty("direction"), 21);
		cl.login(propiedades.getProperty("user"), propiedades.getProperty("pass"));
		cl.enterLocalActiveMode();

		return cl;
	}
	
}
