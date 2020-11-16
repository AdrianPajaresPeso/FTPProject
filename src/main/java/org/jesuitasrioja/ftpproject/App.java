package org.jesuitasrioja.ftpproject;

import org.jesuitasrioja.ftpproject.ftpLayer.FTPOperations;
import org.jesuitasrioja.ftpproject.ftpLayer.FTPOperationsImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		FTPOperations ftp = new FTPOperationsImpl();
//		System.out.println(ftp.directorioDeTrabajo());
		System.out.println(ftp.cambiarDirectorioDeTrabajo("apuntesPSP"));
//		System.out.println(ftp.subirFichero("IMAGE.jpg"));
	}
	
}
