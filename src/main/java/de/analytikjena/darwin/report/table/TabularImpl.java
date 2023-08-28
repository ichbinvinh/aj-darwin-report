package de.analytikjena.darwin.report.table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.analytikjena.darwin.report.TexReport;

/**
 * Standardimplementierung von {@link Tabular}.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 * 
 */
public class TabularImpl implements Tabular {
	private static final Logger LOGGER = LoggerFactory.getLogger(TabularImpl.class);
	protected int nCols = 0;
	private boolean border = true;
	protected double colWidth = 0;
	private boolean[] colBold = new boolean[0];

	private StringBuilder latex = new StringBuilder();
	
	@Override
	public void setBorder(boolean border) {
		this.border = border;
	}

	@Override
	public void setColumn(int nCols) {
		this.nCols = nCols;
	}

	@Override
	public void setColWidth(double colWidth) {
		this.colWidth = colWidth;
	}
	
	@Override
	public void setBold(int colpos, String[][] data) {
		if(colBold.length >0) {
			if(colpos < data[0].length)
				colBold[colpos] = true;	
		} else {
			colBold = new boolean[data[0].length];
			if(colpos < data[0].length)
				colBold[colpos] = true;	
		}
	}

	/**
	 * Füge den Dateikopf einer Tabelle hinzu.
	 */
	private void beginTabular(int nCols) {
		add("\\begin{flushleft}"); //$NON-NLS-1$
		insert("\\begin{tabular}"); //$NON-NLS-1$

		insert("{"); //$NON-NLS-1$
		if (border) {
			insert("*{" + nCols + "}"); //$NON-NLS-1$ //$NON-NLS-2$
			if (colWidth != 0)
				insert("{|p{" + colWidth + "cm}}|@{}m{0cm}@{}}"); //$NON-NLS-1$ //$NON-NLS-2$
			else
				insert("{|l}|@{}m{0cm}@{}}"); //$NON-NLS-1$
		} else {
			insert("*{" + nCols + "}"); //$NON-NLS-1$ //$NON-NLS-2$
			if (colWidth != 0)
				insert("{p{" + colWidth + "cm}}}"); //$NON-NLS-1$ //$NON-NLS-2$
			else
				insert("{l}}"); //$NON-NLS-1$
		}
	}

	@Override
	public void printTable(String[][] data) {
		beginTabular(data[0].length);
		try {
			if (data != null) {
				if (border)
					add("\\hline"); //$NON-NLS-1$
				for (int i = 0; i < data.length; i++) {
					for (int j = 0; j < data[0].length; j++) {
						insert(TexReport.replaceSpecialCharacters(data[i][j]) + " & "); //$NON-NLS-1$
					}
					if (border)
						insert("\\\\[0,1cm]\\hline\n"); //$NON-NLS-1$
					else {
						latex.deleteCharAt(latex.lastIndexOf("&")); //$NON-NLS-1$
						insert("\\\\\n"); //$NON-NLS-1$
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		endTabular();
	}

	@Override
	public void printTable(String[] headers, String[] data) {

		if (nCols == 0) {
			nCols = data.length;
		}

		if (nCols == 1) {
			nCols++;
		}

		beginTabular(nCols);
		if (headers.length != data.length) {
			LOGGER.error("Invalid argument for header or data of table"); //$NON-NLS-1$
		} else {
			try {
				int colcounter = 0;
				if (headers != null && data != null) {
					for (int j = 0; j < headers.length; j++) {
						colcounter++;
						insert("\\bf{" + headers[j] + "}"); //$NON-NLS-1$ //$NON-NLS-2$
						if (colcounter == (nCols / 2)) {
							insert(" & " + TexReport.replaceSpecialCharacters(data[j]) + "\\\\[0,1cm]\n"); //$NON-NLS-1$ //$NON-NLS-2$
							colcounter = 0;
						} else
							insert(" & " + TexReport.replaceSpecialCharacters(data[j]) + " & "); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		endTabular();
	}

	@Override
	public void printTable(String[] headers, String[][] data) {
		beginTabular(data[0].length);
		if (headers.length != data[0].length) {
			LOGGER.error("Invalid argument for header or data of table"); //$NON-NLS-1$
		} else {
			try {
				add("\\hline"); //$NON-NLS-1$
				for (int i = 0; i < headers.length; i++) {
					insert("\\bf{" + headers[i] + "} &"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				add("\\\\[0,1cm]"); //$NON-NLS-1$
				for (int i = 0; i < data.length; i++) {
					add("\\hline"); //$NON-NLS-1$
					for (int j = 0; j < data[i].length; j++) {
						
						if(colBold[j] == true)
							insert("\\bf{"+TexReport.replaceSpecialCharacters(data[i][j]) + "} & "); //$NON-NLS-1$ //$NON-NLS-2$
						else 
							insert(TexReport.replaceSpecialCharacters(data[i][j]) + " & "); //$NON-NLS-1$
						
						if (j == (data[i].length - 1)) {
							add("\\\\[0,1cm]"); //$NON-NLS-1$
						}
					}
				}
				add("\\hline"); //$NON-NLS-1$
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		endTabular();
	}

	@Override
	public String getTabular() {
		return latex.toString();
	}

	/**
	 * Beendet die Tabelle.
	 */
	void endTabular() {
		add("\\end{tabular}"); //$NON-NLS-1$
		insert("\\end{flushleft}"); //$NON-NLS-1$
	}

	/**
	 * Füge LaTeX-Text in die neue Zeile hinzu.
	 * 
	 * @param txt
	 *            Latex-Text.
	 */
	protected void add(String txt) {
		latex.append(txt).append("\n"); //$NON-NLS-1$
	}

	/**
	 * Füge LaTeX-Text in die selbe Zeile hinzu.
	 * 
	 * @param txt
	 *            Latex-Text.
	 */
	protected void insert(String txt) {
		latex.append(txt);
	}

	@Override
	public void printLongtable(String[][] data) {
		add("\\begin{longtable}"); //$NON-NLS-1$

		insert("{"); //$NON-NLS-1$
			insert("*{" + data[0].length + "}"); //$NON-NLS-1$ //$NON-NLS-2$
//			insert("{|l}|@{}m{0cm}@{}}"); //$NON-NLS-1$
			insert("{|p{2,7cm}}|@{}m{0cm}@{}}"); //$NON-NLS-1$ //$NON-NLS-2$
		add("\\hline"); //$NON-NLS-1$
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				insert(TexReport.replaceSpecialCharacters(data[i][j]) + " & "); //$NON-NLS-1$
			}
			latex.deleteCharAt(latex.lastIndexOf("&")); //$NON-NLS-1$
			add("\\\\\\hline"); //$NON-NLS-1$
			
		}
		
		add("\\end{longtable}"); //$NON-NLS-1$
	}
}
