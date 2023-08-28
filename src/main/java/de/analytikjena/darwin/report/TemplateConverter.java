package de.analytikjena.darwin.report;

import java.io.*;
import java.nio.charset.Charset;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Konvertiere eine Vorlage mit den Platzhaltern in eine füllende TeX-Datei.
 * 
 * @author NgNguyen, AJ Langewiesen, 25.04.2016
 * 
 */
public class TemplateConverter {
	private static final Logger LOGGER = LoggerFactory.getLogger(TemplateConverter.class);
	private VelocityContext contextData;
	private VelocityEngine velocityEngine;
	
	/**
	 * Konstruktor.
	 * 
	 * @param workingPath
	 *            Das Verzeichnis, wo sich die Vorlage befindet.
	 */
	public TemplateConverter(String workingPath) {

		velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("file.resource.loader.path", workingPath); //$NON-NLS-1$
		velocityEngine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogChute"); //$NON-NLS-1$ //$NON-NLS-2$
		
		velocityEngine.init();

		contextData = new VelocityContext();
	}

	/**
	 * Füge eine Variable in der Vorlage mit aktuellem Wert in den Velocity-Kontext(Hashtable) hinzu. 
	 * 
	 * @param key
	 *            Name der Variable ohne Dollar-Zeichen in der Vorlage
	 * @param value
	 *            Der Wert zur Ersetzung.
	 */
	public void replace(String key, Object value) {
		contextData.put(key, value);
	}
	
	/**
	 * Ersetze alle Variablen in der Vorlage mit entsprechenden Werten im Velocity-Kontext.
	 * 
	 * @param templateTeX
	 *            Die Vorlage-Datei
	 * @param filledTeX
	 *            Die gefüllte TeX-Datei nach der Ersetzung
	 */
	public boolean merge(File templateTeX, File filledTeX) throws UnsupportedEncodingException, IOException {
		if (filledTeX.getParentFile() == null && !filledTeX.isDirectory() && !filledTeX.getParentFile().mkdirs()) {
			LOGGER.error("Could not create directory: " + filledTeX.getParentFile().getAbsolutePath()); //$NON-NLS-1$
			return false;
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filledTeX), Charset.defaultCharset()
				.name()));

		boolean ismerged = velocityEngine.mergeTemplate(templateTeX.getName(), Charset.defaultCharset().name(), contextData, bw);

		if (!ismerged) {

			LOGGER.error("Errors occurred when try to merge template!"); //$NON-NLS-1$
			bw.flush();
			bw.close();
			return false;

		} else {
			LOGGER.info("No errors occurred when try to merge template!"); //$NON-NLS-1$
			bw.flush();
			bw.close();
			return true;
		}
	}
	
	/**
	 * Lösche alle Schlüssel-Wert-Paaren im Velocity-Kontext.
	 * 
	 */
	public void clear() {
		contextData = new VelocityContext();
	}
}
