package de.analytikjena.darwin.report.chart;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import de.analytikjena.darwin.report.style.PlotStyle;

/**
 * Standardimplementierung von {@link LineChart}.
 * 
 * @author NgNguyen, AJ Langewiesen, 13.11.2015
 * 
 */
public class LineChartImpl implements LineChart {

	private String title;
	private String xlabel;
	private String ylabel;
	private double ytickMax;
	private double xtickMax;
	private int[] xticks;
	private int xstep, ystep;
	private int gradient;
	private int counter;
	private StringBuilder chart;
	private StringBuilder latex;

	/**
	 * Konstruktor.
	 * 
	 */
	public LineChartImpl() {
		this.xtickMax = 0;
		this.ytickMax = 0;
		this.xstep = 0;
		this.ystep = 0;
		gradient = 100;
		counter = 0;
		chart = new StringBuilder();
		latex = new StringBuilder();
	}

	/**
	 * Füge den Dateikopf eines Diagramms ein.
	 */
	private void initChart() {
		add("\\begin{center}"); //$NON-NLS-1$
		add("\\begin{tikzpicture}"); //$NON-NLS-1$
		add("\\begin{axis}["); //$NON-NLS-1$

		if (!StringUtils.isEmpty(title)) {
			add("title=\\textbf{" + title + "},"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (!StringUtils.isEmpty(ylabel)) {
			add("ylabel=\\textbf{" + ylabel + "},"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (!StringUtils.isEmpty(xlabel)) {
			add("xlabel=\\textbf{" + xlabel + "},"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		//add("xmin=0,"); //$NON-NLS-1$
		
		if(xticks.length > 0) {
			latex.append("xtick={"); //$NON-NLS-1$
			for(int i=0;i< xticks.length;i++) {
				if(i < xticks.length -1)
					latex.append(xticks[i]+","); //$NON-NLS-1$
				else
					latex.append(xticks[i]+"},\n"); //$NON-NLS-1$
			}
		}
		
		if (xstep == 0) {
//			add("xmax=" + roundValue(xtickMax) + ","); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			add("xtick={0," + xstep + ",...," + xtickMax + "},"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			add("xmax=" + xtickMax + ","); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (ystep == 0) {
//			add("ymax=" + roundValue(ytickMax) + ","); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			add("ytick={0," + ystep + ",...," + ytickMax + "},"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			add("ymax=" + ytickMax + ","); //$NON-NLS-1$ //$NON-NLS-2$
		}

		add("height=5cm,"); //$NON-NLS-1$
		add("width=13cm,"); //$NON-NLS-1$
		add("grid=major,"); //$NON-NLS-1$
		add("legend entries={1,2,3,4,5,6,7,8,9,10},"); //$NON-NLS-1$
		add("legend pos=outer north east,"); //$NON-NLS-1$
		add("legend style={draw=none},"); //$NON-NLS-1$
		add("scale only axis"); //$NON-NLS-1$
		add("]"); //$NON-NLS-1$
	}

	@Override
	public void addData(List<Graphdata> ldata, String legendentry, PlotStyle pstyle) {

		chart.append("\\addplot [mark=none, smooth"); //$NON-NLS-1$
		if (!StringUtils.isEmpty(pstyle.getStyle())) {
			chart.append(", " + pstyle.getStyle().toLowerCase()); //$NON-NLS-1$
		}
		if (!StringUtils.isEmpty(pstyle.getThickness())) {
			chart.append(", " + pstyle.getThickness().toLowerCase()); //$NON-NLS-1$
		}
		if (!StringUtils.isEmpty(pstyle.getColor())) {
			chart.append(", " + "color=" + pstyle.getColor().toLowerCase() + "!"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			if (pstyle.getShade() != 0) {
				chart.append(pstyle.getShade());
			} else {
				chart.append(100);
			}
		}
		chart.append(" ] coordinates {\n"); //$NON-NLS-1$
		printPlots(ldata);
		chart.append("};\n"); //$NON-NLS-1$
		chart.append("\\addlegendentry{" + legendentry + "}\n"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public void addData(List<Graphdata> ldata, String legendentry) {

		int gradientValue = gradient - counter;
		if (gradientValue < 0) {
			gradientValue = 0;
		}
		chart.append("\\addplot [mark=none, smooth, thick, color=black!" + gradientValue + " ] coordinates {\n"); //$NON-NLS-1$ //$NON-NLS-2$
		printPlots(ldata);
		chart.append("};\n"); //$NON-NLS-1$
		chart.append("\\addlegendentry{" + legendentry + "}\n"); //$NON-NLS-1$ //$NON-NLS-2$
		counter += 20;
	}

	@Override
	public void addData(List<Graphdata> ldata) {

		int gradientValue = gradient - counter;
		if (gradientValue < 0) {
			gradientValue = 0;
		}
		chart.append("\\addplot [mark=none, smooth, thick, color=black!" + gradientValue + " ] coordinates {\n"); //$NON-NLS-1$ //$NON-NLS-2$
		printPlots(ldata);
		chart.append("};\n"); //$NON-NLS-1$
		counter += 20;
	}

	/**
	 * Füge LaTeX-Text in die neue Zeile ein.
	 * 
	 * @param txt
	 *            Latex-Text.
	 */
	private void add(String txt) {
		latex.append(txt).append("\n"); //$NON-NLS-1$
	}

	/**
	 * @param Die
	 *            Eingabewert.
	 * @return Gibt die gerundete Wert der Eingabewert zurück.
	 */
	private double roundValue(double input) {

		double roundedValue;
		double grade = getGrade(input);
		double modValue = input % grade;

		if (modValue == 0d) {
			roundedValue = input;
		} else {
			roundedValue = input + (grade - modValue);
			if (((roundedValue / 4) * 3) > input) {
				roundedValue = ((roundedValue / 4) * 3 - grade / 10) > roundedValue ? ((roundedValue / 4) * 3 - grade / 10)
						: (roundedValue / 4) * 3 + grade / 10;
			}
		}
		return roundedValue;
	}

	/**
	 * 
	 * @param input
	 *            Die Eingabewert.
	 * @return Gibt den Grad der Eingabe zurück. Z.b.: für Eingabe 314, gibt 100 zurück.
	 */
	private double getGrade(double input) {

		double grade = 1;

		if (input * 100000000 < 10) {
			grade = 0.00000001;
		} else if (input * 10000000 < 10) {
			grade = 0.0000001;
		} else if (input * 1000000 < 10) {
			grade = 0.000001;
		} else if (input * 100000 < 10) {
			grade = 0.00001;
		} else if (input * 10000 < 10) {
			grade = 0.0001;
		} else if (input * 1000 < 10) {
			grade = 0.001;
		} else if (input * 100 < 10) {
			grade = 0.01;
		} else if (input * 10 < 10) {
			grade = 0.1;
		} else if (input < 10) {
			grade = 1;
		} else if (input / 10 < 10) {
			grade = 10;
		} else if (input / 100 < 10) {
			grade = 100;
		} else if (input / 1000 < 10) {
			grade = 1000;
		} else if (input / 10000 < 10) {
			grade = 10000;
		} else if (input / 100000 < 10) {
			grade = 100000;
		} else if (input / 1000000 < 10) {
			grade = 1000000;
		} else if (input / 10000000 < 10) {
			grade = 10000000;
		}
		return grade;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setXlabel(String xlabel) {
		this.xlabel = xlabel;
	}

	@Override
	public void setYlabel(String ylabel) {
		this.ylabel = ylabel;
	}

	@Override
	public void setXstep(int xstep) {
		this.xstep = xstep;
	}

	@Override
	public void setYstep(int ystep) {
		this.ystep = ystep;
	}

	public void setXticks(int[] xticks) {
		this.xticks = xticks;
	}

	@Override
	public void setYtickMax(double ytickMax) {
		this.ytickMax = ytickMax;
	}

	@Override
	public void setXtickMax(double xtickMax) {
		this.xtickMax = xtickMax;
	}

	@Override
	public String getChart() {
		initChart();
		add(chart.toString());
		add("\\end{axis}"); //$NON-NLS-1$
		add("\\end{tikzpicture}"); //$NON-NLS-1$
		add("\\end{center}"); //$NON-NLS-1$
		return latex.toString();
	}

	/**
	 * Druckt die Plots auf dem Koordinatensystem aus.
	 * 
	 * @param ldata
	 *            Die Koordinaten.
	 */
	private void printPlots(List<Graphdata> ldata) {
		for (Graphdata data : ldata) {

			xtickMax = data.getXvalue() > xtickMax ? data.getXvalue() : xtickMax;
			ytickMax = data.getYvalue() > ytickMax ? data.getYvalue() : ytickMax;

			chart.append("(" + data.getXvalue() + "," + data.getYvalue() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

}
