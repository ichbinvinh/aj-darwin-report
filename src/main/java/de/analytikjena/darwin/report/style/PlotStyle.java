/**
 * 
 */
package de.analytikjena.darwin.report.style;

/**
 * Bildet Eigenschaften(Colors, Styles, Thickness) für ein Liniendiagramm bereit.
 * 
 * @author NgNguyen, AJ Langewiesen, 25.11.2015
 *
 */
public class PlotStyle {
	
	/**
	 * Spezifiert die Farbe für ein Liniendiagramm.
	 * 
	 * @author NgNguyen, AJ Langewiesen, 25.11.2015
	 *
	 */
	public enum Colors {
		BLACK,
		BLUE,
		CYAN,
		DARKGRAY,
		GRAY,
		GREEN,
		LIGHTGRAY,
		MAGENTA,
		ORANGE,
		PINK,
		RED,
		YELLOW;
	}
	
	/**
	 * Spezifiert den Stil für ein Liniendiagramm.
	 * 
	 * @author NgNguyen, AJ Langewiesen, 25.11.2015
	 *
	 */
	public enum Styles {
		/**
		 * Die Linie wird gestrichelt.
		 */
		DASHED,
		/**
		 * Die Linie wird gepunktet.
		 */
		DOTTED;
	}
	
	/**
	 * Spezifiert die Dicke für ein Liniendiagramm.
	 * 
	 * @author NgNguyen, AJ Langewiesen, 25.11.2015
	 *
	 */
	public enum Thickness {
		/**
		 * Die Linie wird dünn.
		 */
		THIN,
		/**
		 * Die Linie wird dick.
		 */
		THICK;
	}
	
	private String color;
	private String style;
	private String thickness;
	private int shade = 0;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getThickness() {
		return thickness;
	}
	public void setThickness(String thickness) {
		this.thickness = thickness;
	}
	public int getShade() {
		return shade;
	}
	public void setShade(int shade) {
		this.shade = shade;
	}
}
