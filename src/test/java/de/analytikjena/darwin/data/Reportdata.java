package de.analytikjena.darwin.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.analytikjena.darwin.report.chart.Graphdata;

/**
 * @author mi
 * 
 */
public class Reportdata {

	public String getTitle() {
		return "Analyse"; //$NON-NLS-1$
	}

	public String getName() {
		return "5N_5S"; //$NON-NLS-1$
	}

	public String getAnalysisgroup() {
		return "neuer ABD_TN_TS"; //$NON-NLS-1$
	}

	public String getSamplesvolumn() {
		return "40µl"; //$NON-NLS-1$
	}

	public ReportInfo getReportinfo() {

		ReportInfo reportInfo = new ReportInfo();
		reportInfo.setDatetime("20.02.2005"); //$NON-NLS-1$
		reportInfo.setUser("User"); //$NON-NLS-1$
		reportInfo.setType("Probe"); //$NON-NLS-1$
		reportInfo.setStatus("Analyse beendet"); //$NON-NLS-1$
		reportInfo.setMethod("TN/TS\\_hori\\_liq (6)"); //$NON-NLS-1$
		reportInfo.setTakingPosition("2"); //$NON-NLS-1$
		reportInfo.setDetermination("10 (10-10)"); //$NON-NLS-1$
		reportInfo.setOvenTemparature("1050°C"); //$NON-NLS-1$
		reportInfo.setDilution("1 in 1"); //$NON-NLS-1$

		return reportInfo;

	}

	public List<Reportsummary> getReportsummary() {

		List<Reportsummary> ls = new ArrayList<Reportsummary>();

		Reportsummary TN = new Reportsummary();
		TN.setName("TN"); //$NON-NLS-1$
		TN.setResult("13,8mg/l"); //$NON-NLS-1$
		TN.setEffectiveIntegral("89.768AU"); //$NON-NLS-1$
		TN.setSd("576,60 µ g/l"); //$NON-NLS-1$
		TN.setVk("3,43\\%"); //$NON-NLS-1$
		TN.setK0("0"); //$NON-NLS-1$
		TN.setK1("7,5e-6"); //$NON-NLS-1$
		TN.setTp("1"); //$NON-NLS-1$

		Reportsummary TS = new Reportsummary();
		TS.setName("TS"); //$NON-NLS-1$
		TS.setResult("13,8mg/l"); //$NON-NLS-1$
		TS.setEffectiveIntegral("89.768AU"); //$NON-NLS-1$
		TS.setSd("576,60 µ g/l"); //$NON-NLS-1$
		TS.setVk("3,43\\%"); //$NON-NLS-1$
		TS.setK0("0"); //$NON-NLS-1$
		TS.setK1("7,5e-6"); //$NON-NLS-1$
		TS.setTp("1"); //$NON-NLS-1$

		ls.add(TN);
		ls.add(TS);

		return ls;
	}

	public String[][] getSingleResults(int rownumber) {

		String[][] results = new String[rownumber][4];

		for (int i = 0; i < rownumber; i++) {
			for (int j = 0; j < 4; j++) {
				results[i][j] = (i + 1) + "X 18,12mg/l"; //$NON-NLS-1$
			}
		}

		return results;
	}

	public String[][] getCustomSingleResults(int rownumber) {

		String[][] results = new String[rownumber][5];

		for (int i = 0; i < rownumber; i++) {
			for (int j = 0; j < 5; j++) {
				results[i][j] = (i + 1) + "X 18,12mg/l"; //$NON-NLS-1$
			}
		}

		return results;
	}

	public Map<String, Blankvalue> getBlankvalue() {

		Map<String, Blankvalue> mb = new HashMap<String, Blankvalue>();

		Blankvalue tn = new Blankvalue();
		tn.setDilution("0,000AU/ml"); //$NON-NLS-1$
		tn.setSmallship("0,000AU"); //$NON-NLS-1$

		Blankvalue ts = new Blankvalue();
		ts.setDilution("0,000AU/ml"); //$NON-NLS-1$
		ts.setSmallship("0,000AU"); //$NON-NLS-1$

		mb.put("tn", tn); //$NON-NLS-1$
		mb.put("ts", ts); //$NON-NLS-1$

		return mb;

	}

	public List<Graphdata> getMeasurement() {

		List<Graphdata> lmeasurement = new ArrayList<Graphdata>();
		Graphdata measurement;

		measurement = new Graphdata();
		measurement.setXvalue(0);
		measurement.setYvalue(0);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(45);
		measurement.setYvalue(0);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(100);
		measurement.setYvalue(40);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(120);
		measurement.setYvalue(100);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(130);
		measurement.setYvalue(200.55);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(140);
		measurement.setYvalue(300.34);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(145);
		measurement.setYvalue(400);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(150);
		measurement.setYvalue(500);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(180);
		measurement.setYvalue(540);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(200);
		measurement.setYvalue(470);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(210);
		measurement.setYvalue(400);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(215);
		measurement.setYvalue(300);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(220);
		measurement.setYvalue(200);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(240);
		measurement.setYvalue(100);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(270);
		measurement.setYvalue(30);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(300);
		measurement.setYvalue(10);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(325);
		measurement.setYvalue(40);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(380);
		measurement.setYvalue(10);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(540);
		measurement.setYvalue(0);
		lmeasurement.add(measurement);

		measurement = new Graphdata();
		measurement.setXvalue(720);
		measurement.setYvalue(0);
		lmeasurement.add(measurement);

		return lmeasurement;
	}

	public String[][] getDataForLongHeadingReport() {
		String[][] sampledata = {
				{
						"TS", //$NON-NLS-1$
						"55,91mg/l", //$NON-NLS-1$
						"1", //$NON-NLS-1$
						"149.100AU", //$NON-NLS-1$
						"c=(1,5e-5*I+0) / V", //$NON-NLS-1$
						"leer", //$NON-NLS-1$
						"10.04.2015 14:47:41", //$NON-NLS-1$
						"1 (1)", //$NON-NLS-1$
						"von Hand", //$NON-NLS-1$
						"1 in 1", //$NON-NLS-1$
						"TN: BW Verdünnung 0,000AU/µl, BW Schiffchen 0,000AU; TS: BW Verdünnung 0,000AU/µl, BW Schiffchen 0,000AU", //$NON-NLS-1$
						"TN", "50,16mg/l", "1", "267.529AU", "c=(7,5e-6*I+0) / V" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				{
						"TS", //$NON-NLS-1$
						"55,91mg/l", //$NON-NLS-1$
						"1", //$NON-NLS-1$
						"149.100AU", //$NON-NLS-1$
						"c=(1,5e-5*I+0) / V", //$NON-NLS-1$
						"leer", //$NON-NLS-1$
						"10.04.2015 14:47:41", //$NON-NLS-1$
						"1 (1)", //$NON-NLS-1$
						"von Hand", //$NON-NLS-1$
						"1 in 1", //$NON-NLS-1$
						"TN: BW Verdünnung 0,000AU/µl, BW Schiffchen 0,000AU; TS: BW Verdünnung 0,000AU/µl, BW Schiffchen 0,000AU", //$NON-NLS-1$
						"TN", "50,16mg/l", "1", "267.529AU", "c=(7,5e-6*I+0) / V" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				{
						"TS", //$NON-NLS-1$
						"55,91mg/l", //$NON-NLS-1$
						"1", //$NON-NLS-1$
						"149.100AU", //$NON-NLS-1$
						"c=(1,5e-5*I+0) / V", //$NON-NLS-1$
						"leer", //$NON-NLS-1$
						"10.04.2015 14:47:41", //$NON-NLS-1$
						"1 (1)", //$NON-NLS-1$
						"von Hand", //$NON-NLS-1$
						"1 in 1", //$NON-NLS-1$
						"TN: BW Verdünnung 0,000AU/µl, BW Schiffchen 0,000AU; TS: BW Verdünnung 0,000AU/µl, BW Schiffchen 0,000AU", //$NON-NLS-1$
						"TN", "50,16mg/l", "1", "267.529AU", "c=(7,5e-6*I+0) / V" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				{
						"TS", //$NON-NLS-1$
						"55,91mg/l", //$NON-NLS-1$
						"1", //$NON-NLS-1$
						"149.100AU", //$NON-NLS-1$
						"c=(1,5e-5*I+0) / V", //$NON-NLS-1$
						"leer", //$NON-NLS-1$
						"10.04.2015 14:47:41", //$NON-NLS-1$
						"1 (1)", //$NON-NLS-1$
						"von Hand", //$NON-NLS-1$
						"1 in 1", //$NON-NLS-1$
						"TN: BW Verdünnung 0,000AU/µl, BW Schiffchen 0,000AU; TS: BW Verdünnung 0,000AU/µl, BW Schiffchen 0,000AU", //$NON-NLS-1$
						"TN", "50,16mg/l", "1", "267.529AU", "c=(7,5e-6*I+0) / V" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				{
						"TS", //$NON-NLS-1$
						"55,91mg/l", //$NON-NLS-1$
						"1", //$NON-NLS-1$
						"149.100AU", //$NON-NLS-1$
						"c=(1,5e-5*I+0) / V", //$NON-NLS-1$
						"leer", //$NON-NLS-1$
						"10.04.2015 14:47:41", //$NON-NLS-1$
						"1 (1)", //$NON-NLS-1$
						"von Hand", //$NON-NLS-1$
						"1 in 1", //$NON-NLS-1$
						"TN: BW Verdünnung 0,000AU/µl, BW Schiffchen 0,000AU; TS: BW Verdünnung 0,000AU/µl, BW Schiffchen 0,000AU", //$NON-NLS-1$
						"TN", "50,16mg/l", "1", "267.529AU", "c=(7,5e-6*I+0) / V" } }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		return sampledata;
	}
}
