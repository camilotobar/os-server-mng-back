package com.os.project.dto;

import lombok.Data;

@Data
public class ProcessDTO {

    private String PID;
    private String user;
    private double PR;
    private double NI;
    private String VIRT;
    private String RES;
    private String SHR;
    private String S;

    // General
    private double cpuUsedPercentage;
    private double memoryUsedPercentage;
    private String time;
    private String command;

    public ProcessDTO(String PID, String user, double PR, double NI, String VIRT, String RES, String SHR, String s, double cpuUsedPercentage, double memoryUsedPercentage, String time, String command) {
        this.PID = PID;
        this.user = user;
        this.PR = PR;
        this.NI = NI;
        this.VIRT = VIRT;
        this.RES = RES;
        this.SHR = SHR;
        S = s;
        this.cpuUsedPercentage = cpuUsedPercentage;
        this.memoryUsedPercentage = memoryUsedPercentage;
        this.time = time;
        this.command = command;
    }
}
