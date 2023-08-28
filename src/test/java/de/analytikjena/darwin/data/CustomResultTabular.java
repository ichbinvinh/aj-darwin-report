package de.analytikjena.darwin.data;

import de.analytikjena.darwin.report.table.TabularImpl;

/**
 * Stellt eine Ableitung von {@link TabularImpl} bereit die die spezifizierte Tabelle abbildet.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 * 
 */
public class CustomResultTabular extends TabularImpl {

	/**
	 * Füge eine spezifizierte Tabelle der Einzelergebnisse in den Bericht ein.
	 * 
	 * @param header
	 *            Die Überschriften einer Tabelle.
	 * @param data
	 *            Die Daten der Tablle.
	 * @param coltobreak
	 *            Die Anzahl der Spalten der Daten.
	 */
	public void printResultTabular(String[] header, String[][] data) {
		int colcounter = 1;
		int rowcounter = 0;
		int coltobreak = 4;

		if (nCols != 0)
			coltobreak = nCols;

		if (data != null && header != null && header.length == data[0].length) {

			int datarowsize = data.length;
			double tablenumber = (double) datarowsize / (double) coltobreak;

			for (int i = 0; i < (int) Math.ceil(tablenumber); i++) {

				coltobreak = (rowcounter + coltobreak) > datarowsize ? (datarowsize - rowcounter) : coltobreak;

				insert("\\noindent\\vspace{-0,3mm}\\begin{tabular}{|m{2,5cm}|*{" + coltobreak + "}"); //$NON-NLS-1$ //$NON-NLS-2$
				if (colWidth != 0)
					add("{M{" + colWidth + "cm}|}@{}m{0cm}@{}}"); //$NON-NLS-1$ //$NON-NLS-2$
				else
					add("{M{2.5cm}|}@{}m{0cm}@{}}"); //$NON-NLS-1$

				insert("\\cline{2-" + (coltobreak + 1) + "}\\multicolumn{1}{l|}{}"); //$NON-NLS-1$ //$NON-NLS-2$

				for (int j = 0; j < coltobreak; j++) {
					insert(" & \\textbf{" + (colcounter++) + "}"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				insert(" & \\\\[0,1cm]\\cline{1-" + (coltobreak + 1) + "}"); //$NON-NLS-1$ //$NON-NLS-2$

				for (int h = 0; h < header.length; h++) {
					insert("\\textbf{" + header[h] + "}"); //$NON-NLS-1$ //$NON-NLS-2$
					for (int j = rowcounter; j < (rowcounter + coltobreak); j++) {
						insert("&"); //$NON-NLS-1$
						insert(data[j][h]);
					}
					add(" & \\\\[0,1cm]\\cline{1-" + (coltobreak + 1) + "}"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				rowcounter += coltobreak;
				add("\\end{tabular}\\\\"); //$NON-NLS-1$
			}
		} else {
			add("Header's length do not match data's length in class: " + this.toString()); //$NON-NLS-1$
		}
	}
}
