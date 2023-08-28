/**
 * 
 */
package de.analytikjena.darwin.data;

/**
 * @author mi
 *
 */
public class Reportsummary {
	
	String name;
	String result;
	String effectiveIntegral;
	String Sd;
	String Vk;
	String K0;
	String K1;
	String Tp;
	
	public String[] toStringArray() {
	    return new String[] { getName(), getResult(), getEffectiveIntegral(), getSd(), getVk() , getK0(), getK1(), getTp() };
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getEffectiveIntegral() {
		return effectiveIntegral;
	}
	public void setEffectiveIntegral(String effectiveIntegral) {
		this.effectiveIntegral = effectiveIntegral;
	}
	public String getSd() {
		return Sd;
	}
	public void setSd(String sd) {
		Sd = sd;
	}
	public String getVk() {
		return Vk;
	}
	public void setVk(String vk) {
		Vk = vk;
	}
	public String getK0() {
		return K0;
	}
	public void setK0(String k0) {
		K0 = k0;
	}
	public String getK1() {
		return K1;
	}
	public void setK1(String k1) {
		K1 = k1;
	}
	public String getTp() {
		return Tp;
	}
	public void setTp(String tp) {
		Tp = tp;
	}

}
