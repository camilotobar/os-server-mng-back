package com.os.project.dto;

public class ProcessDTOWin {

	private String pid;
	private String name;
	private String handles;
	private String npm;
	private String pm;
	private String ws;
	private String vm;
	private String si;
	private String cpu;
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String id) {
		this.pid = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHandles() {
		return handles;
	}
	public void setHandles(String handles) {
		this.handles = handles;
	}
	public String getNpm() {
		return npm;
	}
	public void setNpm(String npm) {
		this.npm = npm;
	}
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	public String getWs() {
		return ws;
	}
	public void setWs(String ws) {
		this.ws = ws;
	}
	public String getVm() {
		return vm;
	}
	public void setVm(String vm) {
		this.vm = vm;
	}
	public String getSi() {
		return si;
	}
	public void setSi(String si) {
		this.si = si;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	
}
