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
}
