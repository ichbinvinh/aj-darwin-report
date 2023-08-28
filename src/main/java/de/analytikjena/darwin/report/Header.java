package de.analytikjena.darwin.report;

import java.text.DateFormat;

import de.analytikjena.darwin.report.table.Tabular;

/**
 * Bilde ein Header ab.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 * 
 */
public class Header {

	StringBuilder header = new StringBuilder();

	/**
	 * Konstruktor
	 * 
	 */
	public Header() {
		add("\\lhead{"); //$NON-NLS-1$
		addSmallRight(I18n.getString("Header.printDate") + ": " + getDateTime()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Füge den Titel des Berichtes ein
	 * 
	 * @param txt
	 *            Der Titel.
	 */
	public void addTitle(String txt) {
		add("\\flushleft{\\textbf{\\LARGE{" + txt + "}}}\\\\"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Füge den fetten Text ein.
	 * 
	 * @param txt
	 *            Der Text.
	 */
	public void addBoldText(String txt) {
		add("\\textbf{" + txt + "}"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Füge den kleinen Text an der rechten Seite ein.
	 * 
	 * @param txt
	 *            Der Text.
	 */
	public void addSmallRight(String txt) {
		add("\\flushright{\\small{" + txt + "}}"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Füge den kleinen Text ein.
	 * 
	 * @param txt
	 *            Der Text.
	 */
	public void addSmall(String txt) {
		add("{\\small{" + txt + "}}"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Füge die Tabelle ein.
	 * 
	 * @param table
	 *            Die Tabelle.
	 */
	public void addTabular(Tabular table) {
		add(table.getTabular());
	}

	/**
	 * Füge LaTeX-Text in die neue Zeile hinzu.
	 * 
	 * @param txt
	 *            Latex-Text.
	 */
	private void add(String txt) {
		header.append(txt).append("\n"); //$NON-NLS-1$
	}

	/**
	 * @return Gibt Quellcode als Latex des Headers zurück.
	 */
	public String getLatex() {
		add("}"); //$NON-NLS-1$
		return header.toString();
	}

	/**
	 * @return Gibt Datum und Uhrzeit zurück.
	 */
	private static String getDateTime() {
		return DateFormat.getInstance().format(new java.util.Date());
	}
}
