package de.analytikjena.darwin.report.table;

import de.analytikjena.darwin.report.I18n;

/**
 * Stellt eine Ableitung von {@link TabularImpl} bereit die die spezifizierte Tabelle abbildet.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 * 
 */
public class CustomTabular extends TabularImpl {

	/**
	 * Füge eine spezifizierte Tabelle der Einzelergebnisse in den Bericht ein.
	 * 
	 * @param data
	 *            Die Daten der Tablle.
	 * @param coltobreak
	 *            Die Anzahl der Spalten der Daten.
	 */
	public void printResultTabular(String[][] data) {
		int colcounter = 1;
		int rowcounter = 0;
		int coltobreak = 4;

		if (nCols != 0)
			coltobreak = nCols;

		if (data != null) {

			int datarowsize = data.length;
			double tablenumber = (double) datarowsize / (double) coltobreak;

			for (int i = 0; i < (int) Math.ceil(tablenumber); i++) {

				coltobreak = (rowcounter + coltobreak) > datarowsize ? (datarowsize - rowcounter) : coltobreak;

				insert("\\noindent\\vspace{-0,3mm}\\begin{tabular}{|m{0,5cm}|m{2,5cm}|*{" + coltobreak + "}"); //$NON-NLS-1$ //$NON-NLS-2$
				if (colWidth != 0)
					add("{M{" + colWidth + "cm}|}@{}m{0cm}@{}}"); //$NON-NLS-1$ //$NON-NLS-2$
				else
					add("{M{2,5cm}|}@{}m{0cm}@{}}"); //$NON-NLS-1$

				insert("\\cline{3-" + (coltobreak + 2) + "}\\multicolumn{2}{l|}{}"); //$NON-NLS-1$ //$NON-NLS-2$
				for (int j = 0; j < coltobreak; j++) {
					insert(" & \\textbf{" + (colcounter++) + "}"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				insert(" & \\\\[0,1cm]\\cline{1-" + (coltobreak + 2) + "}"); //$NON-NLS-1$ //$NON-NLS-2$
				insert("\\multirow{2}{*}{\\textbf{" + I18n.getString("CustomTabular.tn") + "}} & \\textbf{" + I18n.getString("CustomTabular.result") + "}"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

				for (int j = rowcounter; j < (rowcounter + coltobreak); j++) {
					insert(" & "); //$NON-NLS-1$
					insert(data[j][0]);
				}
				insert(" & \\\\[0,1cm]\\cline{2-" + (coltobreak + 2) + "}"); //$NON-NLS-1$ //$NON-NLS-2$
				insert(" & \\textbf{" + I18n.getString("CustomTabular.effectiveintegral") + "}"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				for (int j = rowcounter; j < (rowcounter + coltobreak); j++) {
					insert("&"); //$NON-NLS-1$
					insert(data[j][1]);
				}
				insert("\\\\[0,1cm]\\cline{1-" + (coltobreak + 2) + "}"); //$NON-NLS-1$ //$NON-NLS-2$
				insert("\\multirow{2}{*}{\\textbf{" + I18n.getString("CustomTabular.ts") + "}} & \\textbf{" + I18n.getString("CustomTabular.result") + "}"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				for (int j = rowcounter; j < (rowcounter + coltobreak); j++) {
					insert("&"); //$NON-NLS-1$
					insert(data[j][1]);
				}
				insert("\\\\[0,1cm]\\cline{2-" + (coltobreak + 2) + "}"); //$NON-NLS-1$ //$NON-NLS-2$
				insert(" & \\textbf{" + I18n.getString("CustomTabular.effectiveintegral") + "}"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				for (int j = rowcounter; j < (rowcounter + coltobreak); j++) {
					insert("&"); //$NON-NLS-1$
					insert(data[j][3]);
				}
				add("\\\\[0,1cm]\\cline{1-" + (coltobreak + 2) + "}"); //$NON-NLS-1$ //$NON-NLS-2$
				rowcounter += coltobreak;

				add("\\end{tabular}\\\\"); //$NON-NLS-1$
			}
		} else {
			add("Data is null when try to print table " + this.toString()); //$NON-NLS-1$
		}
	}
}
