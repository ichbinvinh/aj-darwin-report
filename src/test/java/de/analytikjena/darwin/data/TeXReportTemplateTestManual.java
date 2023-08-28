package de.analytikjena.darwin.data;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;

import org.junit.Test;

import de.analytikjena.darwin.report.TemplateConverter;
import de.analytikjena.darwin.report.PdfGenerator;

/**
 * Demonstriere wie ein Bericht TexReport von einer Vorlage erzeugt wird.
 * 
 * @author NgNguyen, AJ Langewiesen, 25.04.2016
 * 
 */
public class TeXReportTemplateTestManual {

	@Test
	public void testTeXReporTemplate() throws InterruptedException {
		
		String workingPath = "/home/mi/Dokumente/templates/marketTemplate"; //$NON-NLS-1$
        File templateFile = new File(workingPath + File.separator + "TexReport_Template.tex"); //$NON-NLS-1$
        File texReport = new File(workingPath + File.separator + "FilledTexReport.tex"); //$NON-NLS-1$
        
        try {
        	TemplateConverter converter = new TemplateConverter(workingPath);
            
            converter.replace("name", "5N\\ 5S"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("analysengruppe", "neuer ABD\\ TN\\ TS"); //$NON-NLS-1$ //$NON-NLS-2$
            
            converter.replace("druckzeitpunkt", DateFormat.getInstance().format(new java.util.Date())); //$NON-NLS-1$
            
            converter.replace("analysenzeitpunkt", "20.02.2005"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("method", "TN/TS\\_hori\\_liq (6)"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("entnahmeposition", "2"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("messer", "User"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("bestimmungen", "10 (10-10)"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("typ", "Probe"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("ofentemparatur", "1050°C"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("status", "Analyse beendet"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("verduennung", "1 in 1"); //$NON-NLS-1$ //$NON-NLS-2$
           
            converter.replace("tn_ergebnis", "13,8mg/l"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("tn_effektivintegral", "89.768AU"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("tn_sd", "576,60 µ g/l"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("tn_vk", "3,43\\%"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("tn_k0", "0"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("tn_k1", "7,5e-6"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("tn_tp", "1"); //$NON-NLS-1$ //$NON-NLS-2$

            converter.replace("ts_ergebnis", "13,8mg/l"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("ts_effektivintegral", "89.768AU"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("ts_sd", "576,60 µ g/l"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("ts_vk", "3,43\\%"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("ts_k0", "0"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("ts_k1", "7,5e-6"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("ts_tp", "1"); //$NON-NLS-1$ //$NON-NLS-2$
            
            converter.replace("probenvolumen", "40µl"); //$NON-NLS-1$ //$NON-NLS-2$
        
            converter.replace("tn_bw_verduennung", "0,000AU/ml"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("tn_bw_schiffchen", "0,000AU"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("ts_bw_verduennung", "0,000AU/ml"); //$NON-NLS-1$ //$NON-NLS-2$
            converter.replace("ts_bw_schiffchen", "0,000AU"); //$NON-NLS-1$ //$NON-NLS-2$
            
            converter.merge(templateFile, texReport);
            
            PdfGenerator generator = new PdfGenerator(workingPath, texReport.getName());
            generator.createPDF();
    	
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

	}

}
