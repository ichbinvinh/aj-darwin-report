package de.analytikjena.darwin.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bilde einen Erzeuger der PDF-Datei ab.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 * 
 */
public class PdfGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(PdfGenerator.class);
	private String workingPath;
	private String texFilePath;
	private File pdfFile;

	/**
	 * Konstruktor.
	 * 
	 */
	public PdfGenerator(String workingPath, String texFileName) {
		pdfFile = null;
		this.workingPath = workingPath;
		texFilePath = workingPath + File.separator + texFileName;
	}

	/**
	 * Erzeuge die PDF-Datei.
	 */
	public void createPDF() {

		try {
			if (!new File(workingPath).exists()) {
				new File(workingPath).mkdir();
			}

			if (!new File(workingPath).exists()) {
				new File(workingPath).mkdir();
			}

			if (new File(texFilePath).isFile()) {
				ProcessBuilder processbuilder = new ProcessBuilder(new String[] { "pdflatex", texFilePath }); //$NON-NLS-1$
				processbuilder.directory(new File(workingPath));
				Process pdfCreator = processbuilder.start();

				InputStream is = pdfCreator.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line = null;

				while ((line = br.readLine()) != null) {
					LOGGER.info(line + "\n"); //$NON-NLS-1$
				}

				if (pdfCreator.waitFor() == 0) {
					pdfCreator.destroy();
					determiningFiles(new File(texFilePath));
					LOGGER.info(String.format("No errors occurred when try to generate file: " + pdfFile.getName())); //$NON-NLS-1$
					processbuilder.command("evince", pdfFile.getAbsolutePath()); //$NON-NLS-1$
					processbuilder.start();
				}
			} else {
				LOGGER.error(String.format("The file does not exist or is not a file: " + texFilePath)); //$NON-NLS-1$
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Überprüfe PDF-Datei.
	 * 
	 * @param texFile
	 *            Der LaTex-Datei.
	 */
	private void determiningFiles(File texFile) {
		File file2 = null;
		int i = texFile.getName().lastIndexOf("."); //$NON-NLS-1$
		if (i != -1) {
			file2 = new File((new StringBuilder()).append(texFile.getParent()).append(File.separator)
					.append(texFile.getName().substring(0, i)).append(".pdf").toString()); //$NON-NLS-1$
		} else {
			file2 = new File((new StringBuilder()).append(texFile.getParent()).append(File.separator).append(texFile.getName())
					.append(".pdf").toString()); //$NON-NLS-1$
		}
		if (file2.isFile()) {
			pdfFile = file2;
		} else {
			LOGGER.error("The pdf file could not be created or does not exist!"); //$NON-NLS-1$
		}
	}

	/**
	 * Schreibe den Bericht in die LaTex-Datei.
	 * 
	 * @param report
	 *            Der Bericht.
	 */
	public void saveTex(TexReport report) {

		try {
			File texfile = new File(texFilePath);
			texfile.createNewFile();
			FileOutputStream fos = new FileOutputStream(texfile);
			Writer out = new OutputStreamWriter(fos, "UTF8"); //$NON-NLS-1$
			out.write(report.getLatex());
			out.close();

		} catch (IOException ioX) {
			ioX.printStackTrace();
		}
	}
}
