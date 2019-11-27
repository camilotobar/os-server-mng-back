package com.os.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class PerformanceDTO {

    // CPU Stats
    private int tasks;
    private int tasksRunning;
    private int tasksSleeping;
    private int tasksStopped;
    private int tasksZombies;
    private double cpuPercentage;

    // Memory Stats
    private double memoryTotal;
    private double swapTotal;
    private double swapUsed;
    private double memoryUsed;
    private double swapFree;
    private double memoryFree;

    // Processes
    private List<ProcessDTO> processes;
    
    private List<ProcessDTOWin> processeswin;

	public int getTasks() {
		return tasks;
	}

	public void setTasks(int tasks) {
		this.tasks = tasks;
	}

	public int getTasksRunning() {
		return tasksRunning;
	}

	public void setTasksRunning(int tasksRunning) {
		this.tasksRunning = tasksRunning;
	}

	public int getTasksSleeping() {
		return tasksSleeping;
	}

	public void setTasksSleeping(int tasksSleeping) {
		this.tasksSleeping = tasksSleeping;
	}

	public int getTasksStopped() {
		return tasksStopped;
	}

	public void setTasksStopped(int tasksStopped) {
		this.tasksStopped = tasksStopped;
	}

	public int getTasksZombies() {
		return tasksZombies;
	}

	public void setTasksZombies(int tasksZombies) {
		this.tasksZombies = tasksZombies;
	}

	public double getCpuPercentage() {
		return cpuPercentage;
	}

	public void setCpuPercentage(double cpuPercentage) {
		this.cpuPercentage = cpuPercentage;
	}

	public double getMemoryTotal() {
		return memoryTotal;
	}

	public void setMemoryTotal(double memoryTotal) {
		this.memoryTotal = memoryTotal;
	}

	public double getSwapTotal() {
		return swapTotal;
	}

	public void setSwapTotal(double swapTotal) {
		this.swapTotal = swapTotal;
	}

	public double getSwapUsed() {
		return swapUsed;
	}

	public void setSwapUsed(double swapUsed) {
		this.swapUsed = swapUsed;
	}

	public double getMemoryUsed() {
		return memoryUsed;
	}

	public void setMemoryUsed(double memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

	public double getSwapFree() {
		return swapFree;
	}

	public void setSwapFree(double swapFree) {
		this.swapFree = swapFree;
	}

	public double getMemoryFree() {
		return memoryFree;
	}

	public void setMemoryFree(double memoryFree) {
		this.memoryFree = memoryFree;
	}

	public List<ProcessDTO> getProcesses() {
		return processes;
	}

	public void setProcesses(List<ProcessDTO> processes) {
		this.processes = processes;
	}

	public List<ProcessDTOWin> getProcesseswin() {
		return processeswin;
	}

	public void setProcesseswin(List<ProcessDTOWin> processeswin) {
		this.processeswin = processeswin;
	}
	
	
}
