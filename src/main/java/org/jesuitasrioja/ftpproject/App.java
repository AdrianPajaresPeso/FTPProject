package org.jesuitasrioja.ftpproject;

import org.apache.commons.net.ftp.FTPFile;
import org.jesuitasrioja.ftpproject.ftpLayer.FTPOperations;
import org.jesuitasrioja.ftpproject.ftpLayer.FTPOperationsImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		FTPOperations ftp = new FTPOperationsImpl();
		System.out.println(ftp.directorioDeTrabajo());
		System.out.println(ftp.cambiarDirectorioDeTrabajo("apuntesPSP"));
		System.out.println(ftp.subirFichero("IMAGE.jpg"));
		System.out.println(ftp.bajarFichero("IMAGE.jpg", "bajado.jpg"));
		System.out.println(ftp.crearCarpeta("AdrianPajaresPeso"));
		System.out.println(ftp.eliminarCarpeta("AdrianPajaresPeso"));
	
	}
	
}
