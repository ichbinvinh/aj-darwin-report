package de.analytikjena.darwin.report;

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.analytikjena.darwin.report.chart.LineChart;
import de.analytikjena.darwin.report.image.Image;
import de.analytikjena.darwin.report.table.Tabular;

/**
 * Bilde einen Bericht ab.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 * 
 */
public class TexReport {

	private StringBuilder header, body;

	protected ArrayList<String> packages;

	/**
	 * Konstruktor.
	 * 
	 */
	public TexReport() {
		this.header = new StringBuilder();
		this.body = new StringBuilder();
		this.packages = new ArrayList<String>();
		addPackages();
	}

	/**
	 * @return Gibt den Dateikopf eines Berichtes zurück.
	 */
	private String initLatex() {

		StringBuilder latex = new StringBuilder();

		latex.append("\\documentclass[7pt,a3,5paper]{report}\n"); //$NON-NLS-1$

		for (String latexPackage : packages) {
			latex.append("\\usepackage").append(latexPackage).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		latex.append("\\pagestyle{fancy}\n"); //$NON-NLS-1$
		latex.append("\\pgfplotsset{try min ticks=7}\n"); //$NON-NLS-1$

		DecimalFormat nf = new DecimalFormat();
		latex.append("\\pgfplotsset{\n"); //$NON-NLS-1$
		latex.append("tick label style={/pgf/number format/1000 sep={{" + nf.getDecimalFormatSymbols().getGroupingSeparator() //$NON-NLS-1$
				+ "}}}"); //$NON-NLS-1$
		latex.append("}\n"); //$NON-NLS-1$

		latex.append("\\tikzstyle{every picture}+=[font=\\sansmath\\fdsfamily]\n"); //$NON-NLS-1$
		latex.append("%remove horizonal line at the head\n"); //$NON-NLS-1$
		latex.append("\\renewcommand\\headrule{}\\cfoot{}\n"); //$NON-NLS-1$
		latex.append("\\rfoot{" + I18n.getString("TexReport.site") + " \\thepage~" + I18n.getString("TexReport.of") + "~\\pageref{LastPage}}\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		latex.append("\\newcolumntype{M}[1]{>{\\centering\\arraybackslash}m{#1}}\n"); //$NON-NLS-1$
		latex.append("\\headheight=1 in\n"); //$NON-NLS-1$
		latex.append("\\textheight=8 in\n"); //$NON-NLS-1$

		return latex.toString();
	}

	/**
	 * @return Gibt den TeX eines Berichtes zurück.
	 */
	public String getLatex() {

		StringBuilder latex = new StringBuilder();

		latex.append(initLatex());

		latex.append(header.toString());

		latex.append("\\begin{document} \n"); //$NON-NLS-1$
		latex.append("\\vspace*{-10mm}\n"); //$NON-NLS-1$

		latex.append(body.toString());

		latex.append("\\end{document} \n"); //$NON-NLS-1$

		return latex.toString();
	}

	/**
	 * Füge Plugin-Paketen in den Dateikopf des Berichtes ein.
	 */
	private void addPackages() {

		packages.add("[utf8]{inputenc}"); //$NON-NLS-1$
		//packages.add("[left=2.64cm,right=2.54cm,top=3cm,bottom=2cm]{geometry}"); //$NON-NLS-1$
		packages.add("[left=0.5cm,right=0.5cm,top=1cm,bottom=0cm]{geometry}"); //$NON-NLS-1$
		packages.add("{fancyhdr}"); //$NON-NLS-1$
		packages.add("{datetime}"); //$NON-NLS-1$
		packages.add("[default]{droidsans}"); //$NON-NLS-1$
		packages.add("{sansmath}"); //$NON-NLS-1$
		packages.add("{kotex}"); //$NON-NLS-1$
		packages.add("{multicol}"); //$NON-NLS-1$
		packages.add("{multirow}"); //$NON-NLS-1$
		packages.add("{textcomp}"); //$NON-NLS-1$
		packages.add("{lastpage}"); //$NON-NLS-1$
		packages.add("{pgfplots}"); //$NON-NLS-1$
		packages.add("{array}"); //$NON-NLS-1$
		packages.add("{longtable}"); //$NON-NLS-1$

	}

	/**
	 * Füge einen Header in den Bericht ein.
	 * 
	 * @param header
	 *            Der Header
	 */
	public void addHeader(Header header) {
		this.header.append(header.getLatex());
	}

	/**
	 * Füge eine Tabelle in den Bericht ein.
	 * 
	 * @param table
	 *            Die Tabelle
	 */
	public void addTabular(Tabular table) {
		add(table.getTabular());

	}

	/**
	 * Füge ein Diagramm in den Bericht ein.
	 * 
	 * @param linechart
	 *            Das Diagramm.
	 */
	public void addGraph(LineChart linechart) {
		add(linechart.getChart());
	}

	/**
	 * Füge ein Bild in den Bericht ein.
	 * 
	 * @param image
	 *            Das Bild.
	 */
	public void addImage(Image image) {
		add(image.getImage());
	}

	/**
	 * Brich eine Seite des Berichtes ab, alle restlichen Inhalten werden in der neuen Seite dargestellt.
	 */
	public void addNewPage() {
		add("\\newpage"); //$NON-NLS-1$
	}

	/**
	 * Füge den fetten Text in den Bericht ein.
	 * 
	 * @param txt
	 *            Der Text
	 */
	public void addBold(String txt) {
		body.append("\\noindent\\textbf{" + txt + "}\n"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Füge den kursiven Text in den Bericht ein.
	 * 
	 * @param txt
	 *            Der Text
	 */
	public void addItalic(String txt) {
		addNewline();
		body.append("\\noindent\\textit{" + txt + "}\n"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Füge den unterstreichte Text in den Bericht ein.
	 * 
	 * @param txt
	 *            Der Text
	 */
	public void addUnterline(String txt) {
		addNewline();
		body.append("\\noindent\\underline{" + txt + "}\n"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Brich eine Zeil ab.
	 */
	public void addNewline() {
		body.append("\\leavevmode\\newline\n"); //$NON-NLS-1$
	}

	/**
	 * Erzeugt innerhalb einer Zeile einen horizontalen Zwischenraum mit der angegebenen Breite.
	 * 
	 * @param width
	 *            die Breite.
	 */
	public void addHorizontalSpace(int width) {
		body.append("\\hspace{" + width + "pt}"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Füge vor dem Sonderzeichen ein Doppel von Backslash ein.
	 * 
	 * @param val
	 *            Der Text enthält das Sonderzeichen.
	 * 
	 * @return Gibt den Text mit einem Doppel von eingefügtem Backslash zurück.
	 */
	public static String replaceSpecialCharacters(String val) {

		StringBuilder sB = new StringBuilder();

		char[] string2char = val.toCharArray();

		ArrayList<Character> specialCharacters = getSpecialCharacters();

		for (Character c : string2char) {

			if (specialCharacters.contains(c)) {
				sB.append("\\").append(c); //$NON-NLS-1$
			} else {
				sB.append(c);
			}
		}
		return sB.toString();
	}

	/**
	 * @return Gibt eine Menge von Sonderzeichen zurück, die nicht direkt im Latex dargestellt werden.
	 */
	public static ArrayList<Character> getSpecialCharacters() {
		ArrayList<Character> specialCharacters = new ArrayList<Character>();

		specialCharacters.add(new Character('\\'));
		specialCharacters.add(new Character('#'));
		specialCharacters.add(new Character('$'));
		specialCharacters.add(new Character('%'));
		specialCharacters.add(new Character('^'));
		specialCharacters.add(new Character('&'));
		specialCharacters.add(new Character('_'));
		specialCharacters.add(new Character('{'));
		specialCharacters.add(new Character('}'));
		specialCharacters.add(new Character('~'));

		return specialCharacters;
	}

	/**
	 * Füge LaTeX-Text in die neue Zeile ein.
	 * 
	 * @param txt
	 *            Latex-Text.
	 */
	public void add(String latex) {
		body.append(latex).append("\n"); //$NON-NLS-1$
	}

	/**
	 * Füge LaTeX-Text in die selbe Zeile ein.
	 * 
	 * @param txt
	 *            Latex-Text.
	 */
	public void insert(String latex) {
		body.append(latex);
	}
}
