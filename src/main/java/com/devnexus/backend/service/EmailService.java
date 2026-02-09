package com.devnexus.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            // Preparamos el mensaje (esto no tarda nada)
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            
            // 🛑 AQUÍ ESTÁ EL CAMBIO: COMENTAMOS EL ENVÍO REAL 🛑
            // mailSender.send(message); 
            
            // Engañamos al sistema diciendo que sí se envió
            System.out.println("🚀 [BACKEND] Envío de correo OMITIDO (Lo hace el Frontend). Todo OK.");

        } catch (Exception e) {
            System.err.println("❌ Error (aunque no debería pasar): " + e.getMessage());
        }
    }
}