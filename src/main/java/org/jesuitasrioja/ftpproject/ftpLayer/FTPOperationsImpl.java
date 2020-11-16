package org.jesuitasrioja.ftpproject.ftpLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

public class FTPOperationsImpl implements FTPOperations {

	Logger logger = Logger.getLogger(FTPOperationsImpl.class);
	private static Properties p = new Properties();
	private FTPClient cl = new FTPClient();
	
	
	
	public FTPOperationsImpl() {
		try {
			p.load(new FileInputStream("src/main/java/credentials.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connectToFTPServer() {
		try {
			cl.connect(p.getProperty("direction"), 21);
			cl.login(p.getProperty("user"), p.getProperty("pass"));
			cl.enterLocalPassiveMode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void disconectFromFTPServer() {
		try {
			cl.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String directorioDeTrabajo() {
		String cadenaRetorno = null;
		try {
			cadenaRetorno = cl.printWorkingDirectory();
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
			
			cl.changeWorkingDirectory(path);
			if(cl.getReplyCode() == 250) {
				flag = true;
				logger.info(cl.getReplyString());
			}
			
			if(cl.getReplyCode() == 550) {
				logger.error(cl.getReplyString());
			}
			
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
		
		try {
			

			cl.setFileType(FTP.BINARY_FILE_TYPE);
			File fileToUpload = new File(path);
			cl.storeFile(fileToUpload.getName(), new FileInputStream(fileToUpload));
			if(cl.getReplyCode()==226) {
				flag = true;
				logger.info(cl.getReplyString());
			}
			
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
			

			cl.retrieveFile(file, new FileOutputStream(new File(path)));
			if(cl.getReplyCode() == 226) {
				flag = true;
				logger.info(cl.getReplyString());
			}
			
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
	public Boolean eliminarFichero(String file) {
		boolean flag = false;
		try {
			
			cl.deleteFile(file);
			flag = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Boolean crearCarpeta(String nombreCarpeta) {
		boolean flag = false;
		try {
			cl.makeDirectory(nombreCarpeta);
			if(cl.getReplyCode() == 257) {
				logger.info(cl.getReplyString());
				flag = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public Boolean eliminarCarpeta(String nombreCarpeta) {
		boolean flag = false;
		try {
			
			cl.removeDirectory(nombreCarpeta);
			if(cl.getReplyCode() == 250) {
				flag = true;
				logger.info(cl.getReplyString());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public List<FTPFile> listaFicherosCarpeta(String path) {
		List<FTPFile> listaFicheros = null;
		
		try {
			

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
		
		try {
		

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

		System.out.println(
				"Nombre: " + file.getName() + ", Tamanio: " + file.getSize() + ", Tipo: " + file.getType()
						+ ", Usuario: " + file.getUser() + ", Ultima modificacion: " + file.getTimestamp());

	}

}
