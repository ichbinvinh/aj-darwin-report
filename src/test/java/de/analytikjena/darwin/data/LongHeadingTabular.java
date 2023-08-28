package de.analytikjena.darwin.data;

import de.analytikjena.darwin.report.table.TabularImpl;

/**
 * Stellt eine Ableitung von {@link TabularImpl} bereit die die spezifizierte lange Tabelle abbildet.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 * 
 */
public class LongHeadingTabular extends TabularImpl {

	/**
	 * Füge eine spezifizierte lange Tabelle in den Bericht ein.
	 * 
	 * @param header
	 *            Die Überschriften einer Tabelle.
	 * @param data
	 *            Die Daten der Tablle.
	 */
	public void printLongHeadingTabular(String[] header, String[][] data) {
		int colcounter = 0;
		int coltobreak = 5;
		int maxWidthColIndex = 0;
		String maxWidthColContent = ""; //$NON-NLS-1$

		if (nCols != 0)
			coltobreak = nCols;

		if (data != null && header != null && header.length == data[0].length) {

			for (int j = 0; j < data[0].length; j++) {
				if (data[0][j].length() > maxWidthColContent.length()) {
					maxWidthColContent = data[0][j];
					maxWidthColIndex = j;
				}
			}
			insert("\\noindent\\vspace{-0,3mm}\\begin{longtable}{|*{" + coltobreak + "}|"); //$NON-NLS-1$ //$NON-NLS-2$
			add("{m{4cm}|}@{}m{0cm}@{}}"); //$NON-NLS-1$
			add("\\hline"); //$NON-NLS-1$
			for (int h = 0; h < header.length; h++) {
				colcounter++;
				if ((colcounter % coltobreak) == 0) {
					insert("\\centering\\arraybackslash\\textbf{" + header[h] + "}"); //$NON-NLS-1$ //$NON-NLS-2$
					insert("\\\\[0,1cm]\\hline"); //$NON-NLS-1$
					colcounter = 0;
				} else if (h == maxWidthColIndex) {
					insert("\\multicolumn{" + coltobreak + "}{|c|}{\\textbf{" + header[h] + "} \\\\[0,1cm]\\hline"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					colcounter = 0;
				} else
					insert("\\centering\\textbf{" + header[h] + "} & "); //$NON-NLS-1$ //$NON-NLS-2$
			}
			add("\\endhead"); //$NON-NLS-1$

			for (int i = 0; i < data.length; i++) {
				colcounter = 0;
				for (int j = 0; j < data[i].length; j++) {
					colcounter++;
					if ((colcounter % coltobreak) == 0) {
						add(data[i][j]);
						insert(" \\\\[0,1cm]\\hline "); //$NON-NLS-1$
						colcounter = 0;
					} else if (j == maxWidthColIndex) {
						insert("\\multicolumn{" + coltobreak + "}{|c|}{" + data[i][j] + " \\\\[0,1cm]\\hline"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						colcounter = 0;
					} else {
						add(data[i][j]);
						insert(" & "); //$NON-NLS-1$
					}
				}
			}
			add("\\end{longtable}\\\\"); //$NON-NLS-1$
		}
	}
}
