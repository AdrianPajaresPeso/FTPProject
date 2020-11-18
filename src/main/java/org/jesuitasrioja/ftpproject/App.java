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
		
		/**
		 * Se está probando que salga por pantalla todos los logs puestos en el codigo
		 * */
		
		FTPOperationsImpl ftp = new FTPOperationsImpl();
		ftp.connectToFTPServer();
		System.out.println(ftp.directorioDeTrabajo());
		ftp.cambiarDirectorioDeTrabajo("apuntesPSP");
		ftp.cambiarDirectorioDeTrabajo("apuntesPSP"); // suelta error
		ftp.subirFichero("IMAGE.jpg");
		ftp.subirFichero("IMAGEs.jpg"); // suelta error
		ftp.bajarFichero("IMAGE.jpg", "bajado.jpg");
		ftp.bajarFichero("IMAGEs.jpg", "bajado.jpg");//suelta error
		ftp.eliminarFichero("IMAGEs.jpg"); // suelta error
		ftp.cambiarDirectorioDeTrabajo("/");
		ftp.crearCarpeta("AdrianPajaresPeso");
		ftp.eliminarCarpeta("AdrianPajaresPeso");
		ftp.eliminarCarpeta("AdrianPajaresPeso");// suelta error
		ftp.disconectFromFTPServer();

	}

}
