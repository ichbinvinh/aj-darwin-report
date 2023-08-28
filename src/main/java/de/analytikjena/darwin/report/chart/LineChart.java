package de.analytikjena.darwin.report.chart;

import java.util.List;

import de.analytikjena.darwin.report.style.PlotStyle;

/**
 * Definiert ein Liniendiagramm.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 * 
 */
public interface LineChart {

	/**
	 * Fügt die Beschriftung des Diagramms hinzu.
	 * 
	 * @param title
	 *            Die Beschriftung.
	 */
	void setTitle(String title);

	/**
	 * Fügt die Beschriftung der x-Achse hinzu.
	 * 
	 * @param xlabel
	 *            Die Beschriftung.
	 */
	void setXlabel(String xlabel);

	/**
	 * Fügt die Beschriftung der y-Achse hinzu.
	 * 
	 * @param ylabel
	 *            Die Beschriftung.
	 */
	void setYlabel(String ylabel);

	/**
	 * Bestimme den Wert des Ticks an der x-Achse.
	 * 
	 * @param xstep
	 *            Der Wert des Ticks.
	 */
	void setXstep(int xstep);

	/**
	 * Bestimme den Wert des Ticks an der y-Achse.
	 * 
	 * @param ystep
	 *            Der Wert des Ticks.
	 */
	public void setYstep(int ystep);

	/**
	 * Bestimme den maximalen Wert an der y-Achse.
	 * 
	 * @param ytickMax
	 *            Der maximale Wert.
	 */
	public void setYtickMax(double ytickMax);

	/**
	 * Bestimme den maximalen Wert an der x-Achse.
	 * 
	 * @param xtickMax
	 *            Der maximale Wert.
	 */
	public void setXtickMax(double xtickMax);
	
	/**
	 * Bestimme die Werte an der x-Achse.
	 * 
	 * @param xticks
	 *            Die bestimmte Werte.
	 */
	public void setXticks(int[] xticks);
	

	/**
	 * Füge Daten für Diagramm ein.
	 * 
	 * @param ldata
	 *            Eine Liste von Objekt Messwert.
	 */
	void addData(List<Graphdata> data);

	/**
	 * Füge Daten für Diagramm ein.
	 * 
	 * @param ldata
	 *            Eine Liste von Objekt Messwert.
	 * @param legendentry
	 *            Die Legende.
	 */
	void addData(List<Graphdata> data, String legendentry);

	/**
	 * Füge Daten für Diagramm ein.
	 * 
	 * @param ldata
	 *            Eine Liste von Objekt Messwert.
	 * @param legendentry
	 *            Die Legende.
	 * @param pstyle
	 *            Der Diagrammstil.
	 * 
	 */
	void addData(List<Graphdata> data, String legendentry, PlotStyle pstyle);

	/**
	 * @return Gibt den Quellcode des Diagramms zurück.
	 */
	String getChart();

}
