package com.os.project.controllers;

import com.os.project.dto.PerformanceDTO;
import com.os.project.services.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
public class PerformanceController {

    @Autowired
    public PerformanceService performanceService;

    @GetMapping("/performanceOnLinux")
    public PerformanceDTO getTopPerformanceLinux(){
        return performanceService.getTopPerformanceLinux();
    }

    @GetMapping("/performanceOnWindows")
    public PerformanceDTO getTopPerformanceWindows(){
        return performanceService.getTopPerformanceWindows();
    }

    @GetMapping("/killOnLinux")
    public PerformanceDTO getTopPerformanceLinux(@RequestParam("pid") String pid){
        return performanceService.killLinux(pid);
    }

    @GetMapping("/killOnWindows")
    public PerformanceDTO getTopPerformanceWindows(@RequestParam("pid") String pid){
        return performanceService.killWindows(pid);
    }
}
