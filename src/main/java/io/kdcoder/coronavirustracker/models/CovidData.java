package io.kdcoder.coronavirustracker.models;

public class CovidData {
	private String totalCase;
	private String totalRecoverd;
	private String totalDeath;
	public String getTotalCase() {
		return totalCase;
	}
	public void setTotalCase(String totalCase) {
		this.totalCase = totalCase;
	}
	public String getTotalRecoverd() {
		return totalRecoverd;
	}
	public void setTotalRecoverd(String totalRecoverd) {
		this.totalRecoverd = totalRecoverd;
	}
	public String getTotalDeath() {
		return totalDeath;
	}
	public void setTotalDeath(String totalDeath) {
		this.totalDeath = totalDeath;
	}
	@Override
	public String toString() {
		return "CovidData [totalCase=" + totalCase + ", totalRecoverd=" + totalRecoverd + ", totalDeath=" + totalDeath
				+ "]";
	}
}
