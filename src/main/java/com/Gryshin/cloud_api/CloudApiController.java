package com.Gryshin.cloud_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

@RestController
@RequestMapping("/api")  // Базовий шлях для всіх запитів
public class CloudApiController {

    @GetMapping("/status")  // При GET-запиті на /api/status
    public String getPlatformStatus() {
        SystemInfo systemInfo = new SystemInfo();

        // Отримуємо інформацію про процесор
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        // Завантаження процесора за останніми тиками
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        try {
            Thread.sleep(1000);  // Затримка на 1 секунду для точності вимірювання
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long[] ticks = processor.getSystemCpuLoadTicks();
        double cpuLoad = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;  // Завантаження процесора в процентах

        // Отримуємо інформацію про пам'ять
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long totalMemory = memory.getTotal();
        long availableMemory = memory.getAvailable();
        double memoryUsage = ((double) (totalMemory - availableMemory) / totalMemory) * 100; // Використання пам'яті у відсотках

        return String.format("Платформа працює стабільно.\nЗавантаження процесора: %.2f%%\nВикористання пам'яті: %.2f%%", cpuLoad, memoryUsage);
    }
}
