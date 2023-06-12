package com.app.scheduler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.email.EmailService;
import com.app.pojos.Prescription;
import com.app.service.PrescriptionService;

@Component
public class ReminderScheduler {

	@Autowired
	private PrescriptionService prescriptionService;

	@Autowired
	private EmailService emailService;

	@Scheduled(fixedDelay = 60000)
	public void remindPrescriptions() {
		System.out.println("in schedular");
		List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
		LocalTime currentTime = LocalTime.now();
		for (Prescription prescription : prescriptions) {
			String currentTimeString = currentTime.toString().substring(0, 5);
	        LocalTime localTime = LocalTime.parse(prescription.getReminderTime().toString(), DateTimeFormatter.ofPattern("HH:mm"));
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSSSS");
	        String outputTimeString = localTime.format(outputFormatter);
	        String finalOutputTimeString = outputTimeString.substring(0, 5);
			boolean comparisonResult = currentTimeString.equals(finalOutputTimeString);
			
			System.out.println(currentTimeString);
			System.out.println(finalOutputTimeString);
			if (comparisonResult) {
				System.out.println("Reminder for Prescription: " + prescription.getName());
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(prescription.getUser().getEmail());
				mailMessage.setSubject("Prescription reminder for " + prescription.getName());
				mailMessage.setText(
						"Hi " + prescription.getUser().getFirstName() + " It's time to take " + prescription.getName());
				emailService.sendEmail(mailMessage);
			}
		}
	}

}