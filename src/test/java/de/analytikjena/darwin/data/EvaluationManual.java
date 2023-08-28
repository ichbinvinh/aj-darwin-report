package de.analytikjena.darwin.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.analytikjena.darwin.report.Header;
import de.analytikjena.darwin.report.PdfGenerator;
import de.analytikjena.darwin.report.TexReport;
import de.analytikjena.darwin.report.chart.Graphdata;
import de.analytikjena.darwin.report.chart.LineChart;
import de.analytikjena.darwin.report.chart.LineChartImpl;
import de.analytikjena.darwin.report.table.Tabular;
import de.analytikjena.darwin.report.table.TabularImpl;

public class EvaluationManual {
	
	 private static final long MEGABYTE = 1024L * 1024L;

	    public static long bytesToMegabytes(long bytes) {
	      return bytes / MEGABYTE;
	    }

	@Test
	public void evaluate() throws IOException {

		// calculate running time of appliacation
		long start = System.currentTimeMillis();
		Runtime runtime = Runtime.getRuntime();
		
		PdfGenerator generator = new PdfGenerator("/home/mi/Dokumente/Bachelorarbeit/Schreiben/latex", "Evaluation.tex"); //$NON-NLS-1$ //$NON-NLS-2$
		TexReport report = null;

		report = writeReport();
		generator.saveTex(report);
		generator.createPDF();

		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");

		long memory = runtime.totalMemory() - runtime.freeMemory();

		System.out.println("Total memory is megabytes: " + bytesToMegabytes(runtime.totalMemory()));
		System.out.println("Free memory is megabytes: " + bytesToMegabytes(runtime.freeMemory()));
		System.out.println("Max memory is megabytes: " + bytesToMegabytes(runtime.maxMemory()));

		System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));
	}

	/**
	 * @return Gibt TexReport zurück.
	 * @throws IOException
	 */
	private static TexReport writeReport() throws IOException {

		TexReport report = new TexReport();
		Header header = new Header();
		report.addHeader(header);

		String csvPath = "/home/mi/Dokumente/Bachelorarbeit/Schreiben/PDFjet/world-communications.csv";
		File csvFile = new File(csvPath);
		// BufferedReader br = new BufferedReader(new FileReader(csvFile));
		// String line = "";
		// while ((line = br.readLine()) != null) {
		// System.out.println(line);
		// }
		List<String> lines = Files.readAllLines(csvFile.toPath(), StandardCharsets.UTF_8);
		String[][] data = new String[lines.size()][lines.get(0).split(";").length];
		int linecounter = 0;
		for (String line : lines) {
			data[linecounter] = line.split(";");
			linecounter++;
		}
		
		LineChart chart = new LineChartImpl();
		chart.setTitle("World population by countries"); //$NON-NLS-1$
		chart.setXlabel("Years"); //$NON-NLS-1$
		chart.setYlabel("Number of schools"); //$NON-NLS-1$
		
		List<Graphdata> dataList = new ArrayList<Graphdata>();
		Graphdata datax = new Graphdata();
		datax.setXvalue(1970);
		datax.setYvalue(15);
		dataList.add(datax);
		
		datax = new Graphdata();
		datax.setXvalue(1980);
		datax.setYvalue(30);
		dataList.add(datax);
		
		datax = new Graphdata();
		datax.setXvalue(1990);
		datax.setYvalue(60);
		dataList.add(datax);
		
		datax = new Graphdata();
		datax.setXvalue(2000);
		datax.setYvalue(120);
		dataList.add(datax);
		
		datax = new Graphdata();
		datax.setXvalue(2010);
		datax.setYvalue(240);
		dataList.add(datax);
		
		datax = new Graphdata();
		datax.setXvalue(2014);
		datax.setYvalue(300);
		dataList.add(datax);

		chart.addData(dataList);
		chart.setXtickMax(2016);
		chart.setXticks(new int[]{1970, 1980, 1990, 2000, 2010, 2014});
		
		report.addGraph(chart);
				

		Tabular longtable = new TabularImpl();
		longtable.printLongtable(data);
		report.addTabular(longtable);
		

		return report;

	}

}
