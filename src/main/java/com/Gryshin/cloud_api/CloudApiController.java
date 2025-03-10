package com.Gryshin.cloud_api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

@Controller
public class CloudApiController {

    @GetMapping("/status")
    public String getPlatformStatus(Model model) {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long[] ticks = processor.getSystemCpuLoadTicks();
        double cpuLoad = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;

        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long totalMemory = memory.getTotal();
        long availableMemory = memory.getAvailable();
        double memoryUsage = ((double) (totalMemory - availableMemory) / totalMemory) * 100;

        model.addAttribute("cpuLoad", String.format("%.2f", cpuLoad));
        model.addAttribute("memoryUsage", String.format("%.2f", memoryUsage));

        return "status";
    }
}
