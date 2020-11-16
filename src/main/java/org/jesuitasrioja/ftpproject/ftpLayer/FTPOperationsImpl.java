package org.jesuitasrioja.ftpproject.ftpLayer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;
import org.jesuitasrioja.ftpproject.ftputils.FTPUtils;

public class FTPOperationsImpl implements FTPOperations {

	Logger logger = Logger.getLogger(FTPOperationsImpl.class);
	private static Properties p = new Properties();

	public FTPOperationsImpl() {
		try {
			p.load(new FileInputStream("src/main/java/credentials.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	@Override
	public String directorioDeTrabajo() {
		String cadenaRetorno = "";
		try {
			FTPClient fclient = FTPUtils.getFTPConection();

			cadenaRetorno += fclient.printWorkingDirectory();
			fclient.disconnect();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cadenaRetorno;
	}

	@Override
	public Boolean cambiarDirectorioDeTrabajo(String path) {
		boolean flag = false;
		try {
			FTPClient fclient = FTPUtils.getFTPConection();
			fclient.changeWorkingDirectory(path);
			flag = true;
			fclient.disconnect();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public Boolean subirFichero(String path) {
		boolean flag = false;
		FTPClient cl;
		try {
			cl = FTPUtils.getFTPConection();

			cl.setFileType(FTP.BINARY_FILE_TYPE);
			File fileToUpload = new File(path);
			
			cl.storeFile(fileToUpload.getName(), new FileInputStream(fileToUpload));
			System.out.println(cl.getReplyCode());

			cl.disconnect();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public Boolean bajarFichero(String file, String path) {
		Boolean flag = false;
		
		try {
			FTPClient cl = FTPUtils.getFTPConection();
			
			
			cl.retrieveFile(file, new FileOutputStream(new File(path)));
			
			cl.disconnect();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public Boolean eliminarFichero(String file) {
		try {
			FTPClient cl = FTPUtils.getFTPConection();
			cl.deleteFile(file);
			cl.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean crearCarpeta(String nombreCarpeta) {
		try {
			FTPClient cl = FTPUtils.getFTPConection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Boolean eliminarCarpeta(String nombreCarpeta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FTPFile> listaFicherosCarpeta(String path) {
		List<FTPFile> listaFicheros = null;
		FTPClient cl;
		try {
			cl = FTPUtils.getFTPConection();

			FTPFile[] files = cl.listFiles(path);
			
			for (FTPFile ftpFile : files) {
				listaFicheros.add(ftpFile);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaFicheros;
	}

	@Override
	public List<FTPFile> listaDirectoriosCarpeta(String path) {
		List<FTPFile> listaDirectorios = null;
		FTPClient cl;
		try {
			cl = FTPUtils.getFTPConection();

			FTPFile[] files = cl.listDirectories(path);
			
			for (FTPFile ftpFile : files) {
				listaDirectorios.add(ftpFile);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaDirectorios;
	}

	@Override
	public void mostrarInformacionFile(FTPFile file) {
		// TODO Auto-generated method stub

	}

	

}
