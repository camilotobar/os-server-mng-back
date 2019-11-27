package com.os.project.services;

import com.os.project.dto.PerformanceDTO;
import com.os.project.dto.ProcessDTO;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class PerformanceService {

    public PerformanceDTO getTopPerformanceLinux(){
        return executeTop();
    }

    public PerformanceDTO killLinux(String pid) {
        try {
            ProcessBuilder pb = new ProcessBuilder("src/killprocess.sh", pid);
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return executeTop();
    }

    // FOR LINUX =========================================================
    public PerformanceDTO executeTop(){

        PerformanceDTO performance = new PerformanceDTO();
        performance.setProcesses(new ArrayList<>());

        try {
            ProcessBuilder pb = new ProcessBuilder("src/top.sh");
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = null;
            int count = 7;
            while ((line = reader.readLine()) != null)
            {
                // For process processing
                if(count <=  0) {
                    performance.getProcesses().add(createProcessDTO(line));
                } else {
                    String[] data = line.trim().replaceAll(",", "").replaceAll("\\s+", " ").split(" ");
                    //System.out.println(Arrays.toString(data));

                    if(count == 6){
                        performance.setTasks(Integer.parseInt(data[1]));
                        performance.setTasksRunning(Integer.parseInt(data[3]));
                        performance.setTasksSleeping(Integer.parseInt(data[5]));
                        performance.setTasksStopped(Integer.parseInt(data[7]));
                        performance.setTasksZombies(Integer.parseInt(data[9]));
                    }
                    if(count == 5){
                        performance.setCpuPercentage(Double.parseDouble(data[1]));
                    }
                    if(count == 4){
                        performance.setMemoryTotal(Double.parseDouble(data[3]));
                        performance.setMemoryFree(Double.parseDouble(data[5]));
                        performance.setMemoryUsed(Double.parseDouble(data[7]));
                    }
                    if(count == 3){
                        performance.setSwapTotal(Double.parseDouble(data[2]));
                        performance.setSwapFree(Double.parseDouble(data[4]));
                        performance.setSwapUsed(Double.parseDouble(data[6]));
                    }

                }
                count--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return performance;
    }

    public ProcessDTO createProcessDTO(String line){
        String[] data = line.trim().replaceAll("\\s+", " ").split(" ");

        String PID = data[0];
        String user = data[1];
        double PR = Double.parseDouble(data[2]);
        double NI = Double.parseDouble(data[3]);
        String VIRT = data[4];
        String RES = data[5];
        String SHR = data[6];
        String S = data[7];
        double cpuUsedPercentage = Double.parseDouble(data[8]);
        double memoryUsedPercentage = Double.parseDouble(data[9]);
        String time = data[10];
        String command = data[11];

        ProcessDTO dto = new ProcessDTO(PID, user, PR, NI, VIRT, RES, SHR, S, cpuUsedPercentage, memoryUsedPercentage, time, command);
        return dto;
    }


    // FOR WINDOWS =========================================================
    public PerformanceDTO getTopPerformanceWindows(){
        return executePowershell();
    }

    public PerformanceDTO killWindows(String pid) {
        // TO-DO
        return executePowershell();
    }

    public PerformanceDTO executePowershell(){
        return null;
    }


}
