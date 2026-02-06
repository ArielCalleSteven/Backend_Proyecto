package com.devnexus.backend.service; 

import com.devnexus.backend.repository.AsesoriaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ReminderService {

    @Autowired
    private AsesoriaRepository asesoriaRepository;

    @Autowired
    private AsesoriaService asesoriaService; 
    @Scheduled(fixedRate = 3600000)
    public void enviarRecordatoriosAutomaticos() {
        System.out.println("⏰ [CRON JOB]: Revisando citas próximas para enviar recordatorios...");
    }
}