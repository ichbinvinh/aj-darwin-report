package de.analytikjena.darwin.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import de.analytikjena.darwin.report.Header;
import de.analytikjena.darwin.report.TexReport;
import de.analytikjena.darwin.report.PdfGenerator;
import de.analytikjena.darwin.report.chart.Graphdata;
import de.analytikjena.darwin.report.chart.LineChart;
import de.analytikjena.darwin.report.chart.LineChartImpl;
import de.analytikjena.darwin.report.style.PlotStyle;
import de.analytikjena.darwin.report.table.Tabular;
import de.analytikjena.darwin.report.table.TabularImpl;
import de.analytikjena.darwin.report.table.CustomTabular;

/**
 * Demonstriere wie ein Bericht TexReport erzeugt wird.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 * 
 */
public class TeXReportTestManual {

	@Test
	public void testReportGenerator() throws InterruptedException {

		PdfGenerator generator = new PdfGenerator("/var/local/darwin", "TexReport"); //$NON-NLS-1$ //$NON-NLS-2$
		TexReport report = null;

		report = writeReport();
		generator.saveTex(report);
		generator.createPDF();
	}

	/**
	 * @return Gibt TexReport zurück.
	 */
	private static TexReport writeReport() {

		TexReport report = new TexReport();

		Reportdata rd = new Reportdata();

		Header header = new Header();
		header.addTitle(rd.getTitle());

		Tabular tabheader = new TabularImpl();
		tabheader.setBorder(false);
		String[] heading = { "Name: ", "AnalysenGruppe: " }; //$NON-NLS-1$ //$NON-NLS-2$
		String[] data = { rd.getName(), rd.getAnalysisgroup() };
		tabheader.printTable(heading, data);
		header.addTabular(tabheader);
		report.addHeader(header);

		report.addNewline();
		report.addNewline();
		report.addNewline();
		report.addUnterline("Key-Value Tabelle mit 4 Spalten:"); //$NON-NLS-1$
		ReportInfo reportInfo = rd.getReportinfo();
		Tabular tab1 = new TabularImpl();
		tab1.setBorder(false);
		tab1.setColumn(4);
		// tab1.setColWidth(3.5);
		String[] headings1 = { "Analysenzeitpunkt: ", "Methode: ", "", "Entnahmeposition:", "gemessen von: ", "Bestimmungen: ", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				"Typ: ", "Ofentemparatur:", "Status: ", "Verdünnung: " }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		String[] data1 = { reportInfo.getDatetime(), reportInfo.getMethod(), "", reportInfo.getTakingPosition(), //$NON-NLS-1$
				reportInfo.getUser(), reportInfo.getDetermination(), reportInfo.getType(), reportInfo.getOvenTeparature(),
				reportInfo.getStatus(), reportInfo.getDilution() };
		tab1.printTable(headings1, data1);
		report.addTabular(tab1);

		report.addNewline();
		report.addUnterline("Key-Value Tabelle mit 2 Spalten:"); //$NON-NLS-1$
		Tabular tab2s = new TabularImpl();
		tab2s.setBorder(false);
		tab2s.setColumn(2);
		String[] headings2s = { "Analysenzeitpunkt: ", "Methode: ", "", "Entnahmeposition:", "gemessen von: ", "Bestimmungen: ", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				"Typ: ", "Ofentemparatur:", "Status: ", "Verdünnung: " }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		String[] data2s = { reportInfo.getDatetime(), reportInfo.getMethod(), "", reportInfo.getTakingPosition(), //$NON-NLS-1$
				reportInfo.getUser(), reportInfo.getDetermination(), reportInfo.getType(), reportInfo.getOvenTeparature(),
				reportInfo.getStatus(), reportInfo.getDilution() };
		tab2s.printTable(headings2s, data2s);
		report.addTabular(tab2s);

		report.addUnterline("Tabelle mit dem Rand:"); //$NON-NLS-1$
		report.addNewline();
		List<Reportsummary> ls = rd.getReportsummary();

		String[][] data2 = new String[ls.size()][];
		for (int i = 0; i < ls.size(); i++) {
			data2[i] = ls.get(i).toStringArray();
		}

		String[] headings2 = { "", "Ergebnis", "Effektivintegral", "SD", "VK", "K0", "K1", "TP" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$

		Tabular tab2 = new TabularImpl();
		tab2.setBold(1, data2);
		tab2.setBold(2, data2);
		tab2.setBold(5, data2);
		tab2.printTable(headings2, data2);
		report.addTabular(tab2);

		report.addUnterline("Kreuztabelle mit dem Umbruch, Standard-Spaltenanzahl: 4, Standard-Spaltenbreite ist 2,5 cm:"); //$NON-NLS-1$
		report.addNewline();
		report.addNewline();
		report.addBold("Einzelergebnisse:"); //$NON-NLS-1$
		report.addNewline();

		CustomTabular resultTable = new CustomTabular();
		resultTable.printResultTabular(rd.getSingleResults(11));
		report.addTabular(resultTable);

		report.addNewline();
		report.addNewline();
		report.add("Kreuztabelle mit 5 Spalten , Spaltenbreite ist 2,3 cm:"); //$NON-NLS-1$
		report.addNewline();

		CustomTabular resultTable2 = new CustomTabular();
		resultTable2.setColWidth(2.3);
		resultTable2.setColumn(5);
		resultTable2.printResultTabular(rd.getSingleResults(6));
		report.addTabular(resultTable2);

		report.addNewline();
		report.addUnterline("Komplexe Tabelle mit 8 Ergebnisse, Spaltenanzahl: 3, Spaltenbreite ist 3 cm:"); //$NON-NLS-1$
		report.addNewline();
		report.addNewline();
		CustomResultTabular resultTable3 = new CustomResultTabular();
		String[] resultheading3 = { "Ergebnis", "Effektivintegral", "Ergebnis", "Effektivintegral", "Test" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		resultTable3.setColumn(3);
		resultTable3.setColWidth(3);
		resultTable3.printResultTabular(resultheading3, rd.getCustomSingleResults(8));
		report.addTabular(resultTable3);

		report.addNewline();
		report.addNewline();
		report.addBold("Operanden:"); //$NON-NLS-1$
		report.addNewline();
		report.add("Probenvolumen:"); //$NON-NLS-1$
		report.addHorizontalSpace(40);
		report.insert(rd.getSamplesvolumn());
		report.addNewline();
		report.addNewline();
		report.addBold("Blindwerte:"); //$NON-NLS-1$

		Map<String, Blankvalue> mb = rd.getBlankvalue();

		String[][] blindwerte = { { "TN", "BW Verdünnung", mb.get("tn").getDilution() }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				{ "TN", "BW Schiffchen", mb.get("tn").getSmallship() }, { "TS", "BW Verdünnung", mb.get("ts").getDilution() }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				{ "TS", "BW Schiffchen", mb.get("ts").getSmallship() } }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		report.addNewline();
		report.addUnterline("Tabelle ohne Rand, die Breite der Spalten ist 3.5cm:"); //$NON-NLS-1$
		report.addNewline();

		Tabular blindwertTab = new TabularImpl();
		blindwertTab.setBorder(false);
		blindwertTab.setColWidth(3.5);
		blindwertTab.setBold(1, blindwerte);
		blindwertTab.printTable(blindwerte);
		report.addTabular(blindwertTab);

		report.addNewline();
		report.addUnterline("Tabelle mit dem Rand, die Breite der Spalten ist 3.5cm:"); //$NON-NLS-1$
		report.addNewline();

		Tabular blindwertTab2 = new TabularImpl();
		blindwertTab2.setColWidth(3.5);
		blindwertTab2.printTable(blindwerte);
		report.addTabular(blindwertTab2);

		report.addNewline();
		report.addUnterline("Tabelle mit dem Rand ohne Bestimmung der Spaltenbreite:"); //$NON-NLS-1$
		report.addNewline();

		blindwertTab2 = new TabularImpl();
		blindwertTab2.printTable(blindwerte);
		report.addTabular(blindwertTab2);

		report.addNewline();
		report.addUnterline(""); //$NON-NLS-1$
		report.addNewline();

		List<Graphdata> messwerteTN = rd.getMeasurement();
		LineChart linechart = new LineChartImpl();
		linechart.setTitle("Liniendiagramm mit automatischer Werten auf der Achsen:"); //$NON-NLS-1$
		linechart.setXlabel("Zeit [s]"); //$NON-NLS-1$
		linechart.setYlabel("Messwert [Count]"); //$NON-NLS-1$
		linechart.addData(messwerteTN);
		report.addGraph(linechart);

		List<Graphdata> messwerteTN1 = new ArrayList<Graphdata>();
		Graphdata messwert1;

		for (Graphdata mw : messwerteTN) {
			messwert1 = new Graphdata();
			messwert1.setXvalue(mw.getXvalue() + 10);
			messwert1.setYvalue(mw.getYvalue());
			messwerteTN1.add(messwert1);
		}

		List<Graphdata> messwerteTN2 = new ArrayList<Graphdata>();
		Graphdata messwert2;

		for (Graphdata mw : messwerteTN) {
			messwert2 = new Graphdata();
			messwert2.setXvalue(mw.getXvalue() + 20);
			messwert2.setYvalue(mw.getYvalue());
			messwerteTN2.add(messwert2);
		}

		LineChart charts = new LineChartImpl();
		charts.setTitle("Messkurve"); //$NON-NLS-1$
		charts.setXlabel("Zeit [s]"); //$NON-NLS-1$
		charts.setYlabel("Messwert [Count]"); //$NON-NLS-1$

		PlotStyle pstyle1 = new PlotStyle();
		pstyle1.setColor(PlotStyle.Colors.CYAN.toString());
		pstyle1.setThickness(PlotStyle.Thickness.THICK.toString());
		pstyle1.setStyle(PlotStyle.Styles.DASHED.toString());
		pstyle1.setShade(100);

		PlotStyle pstyle2 = new PlotStyle();
		pstyle2.setColor(PlotStyle.Colors.BLUE.toString());
		pstyle2.setThickness(PlotStyle.Thickness.THICK.toString());
		pstyle2.setStyle(PlotStyle.Styles.DOTTED.toString());
		pstyle2.setShade(60);

		PlotStyle pstyle3 = new PlotStyle();
		pstyle3.setColor(PlotStyle.Colors.MAGENTA.toString());

		charts.addData(messwerteTN, "Eins", pstyle1); //$NON-NLS-1$
		charts.addData(messwerteTN1, "Zwei", pstyle2); //$NON-NLS-1$
		charts.addData(messwerteTN2, "Drei3", pstyle3); //$NON-NLS-1$

		report.addGraph(charts);

		List<Graphdata> messwerteTN3 = new ArrayList<Graphdata>();
		Graphdata messwert3;

		for (Graphdata mw : messwerteTN) {
			messwert3 = new Graphdata();
			messwert3.setXvalue(mw.getXvalue());
			messwert3.setYvalue(mw.getYvalue() * 3 / 10);
			messwerteTN3.add(messwert3);
		}

		LineChart mychart = new LineChartImpl();
		mychart.setTitle("Messkurve 3"); //$NON-NLS-1$
		mychart.setXlabel("Zeit [s]"); //$NON-NLS-1$
		mychart.setYlabel("Messwert [Count]"); //$NON-NLS-1$
		mychart.addData(messwerteTN3);

		report.addGraph(mychart);

		List<Graphdata> messwerteTN4 = new ArrayList<Graphdata>();
		Graphdata messwert4;

		for (Graphdata mw : messwerteTN) {
			messwert4 = new Graphdata();
			messwert4.setXvalue(mw.getXvalue());
			messwert4.setYvalue(mw.getYvalue() / 1000000000);
			messwerteTN4.add(messwert4);
		}

		LineChart mychart2 = new LineChartImpl();
		mychart2.setTitle("Messkurve 4"); //$NON-NLS-1$
		mychart2.setXlabel("Zeit [s]"); //$NON-NLS-1$
		mychart2.setYlabel("Messwert [Count]"); //$NON-NLS-1$

		mychart2.addData(messwerteTN4);

		report.addGraph(mychart2);

		List<Graphdata> messwerteTN5 = new ArrayList<Graphdata>();
		Graphdata messwert5;

		for (Graphdata mw : messwerteTN) {
			messwert5 = new Graphdata();
			messwert5.setXvalue(mw.getXvalue());
			messwert5.setYvalue(mw.getYvalue() * 4);
			messwerteTN5.add(messwert5);
		}

		LineChart mychart3 = new LineChartImpl();
		mychart3.setTitle("Liniendiagramm mit bestimmter Werten auf der Achsen"); //$NON-NLS-1$
		mychart3.setXlabel("Zeit [s]"); //$NON-NLS-1$
		mychart3.setYlabel("Messwert [Count]"); //$NON-NLS-1$

		mychart3.addData(messwerteTN5);
		mychart3.setYtickMax(2500);
		mychart3.setYstep(500);
		mychart3.setXstep(180);
		mychart3.setXtickMax(900);

		report.addGraph(mychart3);

		return report;

	}

}
