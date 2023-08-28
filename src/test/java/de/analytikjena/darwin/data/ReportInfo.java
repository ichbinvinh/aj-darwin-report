/**
 * 
 */
package de.analytikjena.darwin.data;

/**
 * @author mi
 *
 */
public class ReportInfo {
	
	private String datetime;
	private String user;
	private String type;
	private String status;
	private String method;
	private String takingPosition;
	private String determination;
	private String ovenTemperature;
	private String dilution;
	
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getTakingPosition() {
		return takingPosition;
	}
	public void setTakingPosition(String takingPosition) {
		this.takingPosition = takingPosition;
	}
	public String getDetermination() {
		return determination;
	}
	public void setDetermination(String determination) {
		this.determination = determination;
	}
	public String getOvenTeparature() {
		return ovenTemperature;
	}
	public void setOvenTemparature(String ovenTemperature) {
		this.ovenTemperature = ovenTemperature;
	}
	public String getDilution() {
		return dilution;
	}
	public void setDilution(String dilution) {
		this.dilution = dilution;
	}

}
