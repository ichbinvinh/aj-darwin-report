package de.analytikjena.darwin.report.table;

/**
 * Definiert eine Tabelle.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 * 
 */
public interface Tabular {

	/**
	 * Druck die Daten der Tabelle ohne Überschriften aus.
	 * 
	 * @param data
	 *            Die Daten einer Tablle.
	 */
	void printTable(String[][] data);

	/**
	 * Druck die Daten der Tabelle mit Überschriften aus.
	 * 
	 * @param headers
	 *            Die Überschriften einer Tabelle.
	 * @param data
	 *            Die Daten einer Tabelle.
	 */
	void printTable(String[] headers, String[] data);

	/**
	 * Druck die Daten der Tabelle mit Überschriften aus.
	 * 
	 * @param headers
	 *            Die Überschriften einer Tabelle.
	 * @param data
	 *            Die Daten einer Tabelle.
	 */
	void printTable(String[] headers, String[][] data);
	
	/**
	 * Druck die Daten der langen Tabelle ohne Tabelle aus.
	 * 
	 * @param data
	 *            Die Daten einer Tabelle.
	 */
	void printLongtable(String[][] data);

	/**
	 * Entscheidet, ob die Tabelle den Rand haben sollte.
	 * 
	 * @param border
	 *            true: mit dem Rand (Defaultwert). false: ohne Rand.
	 */
	void setBorder(boolean border);

	/**
	 * Setzt die Spaltenanzahl der Tabelle.
	 * 
	 * @param nCols
	 *            Die Spaltenanzahl.
	 */
	void setColumn(int nCols);

	/**
	 * Setzt die Breite der Spalten.
	 * 
	 * @param colwidth
	 *            Die Breite der Spalten.
	 */
	void setColWidth(double colwidth);

	/**
	 * @return Gibt den TeX der Tabelle zurück.
	 */
	String getTabular();

	/**
	 * Hebt die Spalte fett hervor.
	 * 
	 * @param colpos
	 *            Die Position der Spalte.
	 * @param data
	 *            Die Daten einer Tabelle.
	 */
	void setBold(int colpos, String[][] data);
}
