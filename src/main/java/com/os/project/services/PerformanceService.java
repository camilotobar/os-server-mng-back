package com.os.project.services;

import com.os.project.dto.PerformanceDTO;
import com.os.project.dto.ProcessDTO;
import com.os.project.dto.ProcessDTOWin;

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    	try {
    		String[] command = {"powershell.exe", "/C", "kill " + pid};
            ProcessBuilder probuilder = new ProcessBuilder( command );
            Process p = probuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return executePowershell();
    }

    public PerformanceDTO executePowershell(){
        PerformanceDTO performance = new PerformanceDTO();
        performance.setProcesseswin(new ArrayList<>());
        try {
            String[] command = {"powershell.exe", "/C", "Get-Process | Select-Object id, name, handles, npm, pm, ws, vm, si, cpu"};
            ProcessBuilder probuilder = new ProcessBuilder( command );
            
            Process process = probuilder.start();
            
            //Read out dir output
            InputStreamReader isr = new InputStreamReader(process.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String line;
            String idActual = "-1";
            HashMap<String, ProcessDTOWin> map = new HashMap<>();
            while ((line = br.readLine()) != null) {
            	String[] arr = line.trim().replaceAll("\\s+", " ").split(" ");
            	if (arr.length>1) {
            		if (arr[0].equals("Id")) {
            			idActual = arr[2];
            			ProcessDTOWin p = new ProcessDTOWin();
            			p.setPid(idActual);
            			map.put(idActual, p);
            		}
            		ProcessDTOWin p = map.get(idActual);
            		if (p!=null) {
            			switch (arr[0]) {
    					case "Name":
    						p.setName(arr[2]);
    						break;
    					case "Handles":
    						p.setHandles(arr[2]);
    						break;
    					case "NPM":
    						p.setNpm(arr[2]);
    						break;
    					case "PM":
    						p.setPm(arr[2]);
    						break;
    					case "WS":
    						p.setWs(arr[2]);
    						break;
    					case "VM":
    						p.setVm(arr[2]);
    						break;
    					case "SI":
    						p.setSi(arr[2]);
    						break;
    					case "CPU":
    						if (arr.length==2) {
    							p.setCpu("No data");
    						}
    						else {
    							p.setCpu(arr[2]);
    						}
    						break;
    					default:
    						break;
    					}
            		}
            	}
            }
            for (ProcessDTOWin p : map.values()) {
				performance.getProcesseswin().add(p);
			}
            //Wait to get exit value
            try {
                int exitValue = process.waitFor();
                System.out.println("\n\nExit Value is " + exitValue);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    	return performance;
    }

}
