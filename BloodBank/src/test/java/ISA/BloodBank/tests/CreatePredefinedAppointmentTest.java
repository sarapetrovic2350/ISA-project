package ISA.BloodBank.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ISA.BloodBank.dto.AppointmentDTO;
import ISA.BloodBank.service.AppointmentService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CreatePredefinedAppointmentTest {

	@Autowired
	private AppointmentService appointmentService;

	private int index = 0;
	@Test(expected = InterruptedException.class)
	public void createFreeAppointment() throws Throwable {
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<?> future1 = executor.submit(() -> {
			System.out.println("Stvoren Thread 1");
			AppointmentDTO appointment = new AppointmentDTO( "10", "2023-02-29" ,"14-30", "4");
			try { Thread.sleep(6000); } catch (InterruptedException e) {}
			System.out.println("Pokrenut Thread 1");
			try {
				appointmentService.createPredefinedAppointment(appointment);
			} catch (Exception e) {
				index = 1;
				throw new RuntimeException(e);
			}
		});

		executor.submit(() -> {
			System.out.println("Stvoren Thread 2");
			AppointmentDTO appointment = new AppointmentDTO( "10","2023-02-30", "14-30", "4");
			try { Thread.sleep(3000); } catch (InterruptedException e) {}
			System.out.println("Pokrenut Thread 2");
			try {
				appointmentService.createPredefinedAppointment(appointment);
			} catch (Exception e) {
				index = 2;
				throw new RuntimeException(e);
			}
		});

		executor.submit(() -> {
			System.out.println("Stvoren Thread 3");
			AppointmentDTO appointment = new AppointmentDTO( "10","2023-02-31", "12-30", "4");
			System.out.println("Pokrenut Thread 3");
			try {
				appointmentService.createPredefinedAppointment(appointment);
			} catch (Exception e) {
				index = 3;
				throw new RuntimeException(e);
			}
		});

		try {
			future1.get();
		} catch (ExecutionException e) {
			System.out.println("Exception from thread " + index + ": " + e.getCause().getCause().getClass());
			throw e.getCause().getCause();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
}
