package ISA.BloodBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ISA.BloodBank.model.Appointment;

@Service
public class EmailService {
	
	private JavaMailSender javaMailSender;

	private Environment env;

	@Autowired
	public EmailService(JavaMailSender javaMailSender, Environment env) {
		this.javaMailSender = javaMailSender;
		this.env = env;
	}

	public void sendNotificationAsync(String emailAddress, String subject, String text) {
		System.out.println(env.getProperty("spring.mail.username"));
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailAddress);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(subject);
		mail.setText(text);
		javaMailSender.send(mail);
		System.out.println("Email successfully sent!");
	}
	public void sendNotificationForScheduledAppointment(String registeredUserEmail, Appointment appointment) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(registeredUserEmail);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("New scheduled appointment");
		mail.setText("Your appointment is scheduled for: " + appointment.getDate() + "\nDuration in minutes: " + appointment.getDuration());
		javaMailSender.send(mail);
		System.out.println("Email successfully sent!");
	}
	
}
