package ISA.BloodBank.tests;

import ISA.BloodBank.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ScheduleCalendarSearchDeclineTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    @Transactional
    public void testPessimisticLockingScenario() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);

        Long centerId = 1L; // Use the appropriate medical center ID
        Long appointmentId = 3L; // Use the appropriate appointment ID

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1 Started");
                try {
                    Thread.sleep(0);
                    appointmentService.getAllAvailableAppointmentsByMedicalCenterId(centerId);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1 Finished");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2 Started");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                assertThrows(PessimisticLockingFailureException.class, () -> {
                    appointmentService.cancelScheduledAppointment(appointmentId);
                });

                System.out.println("Thread 2 Finished");
            }
        });

        t1.start();
        t2.start();
        t2.join();
        t1.join();

        /*Future<?> future1 = executor.submit(() -> {
            System.out.println("Thread 1 Started");
            try {
                Thread.sleep(5000);
                appointmentService.getAllAvailableAppointmentsByMedicalCenterId(centerId);

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 Finished");
        });

        Future<?> future2 = executor.submit(() -> {
            System.out.println("Thread 2 Started");
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
            }
            assertThrows(PessimisticLockingFailureException.class, () -> {
                appointmentService.cancelScheduledAppointment(appointmentId);
            });

            System.out.println("Thread 2 Finished");
        });

        try {
            future1.get();
        } catch (Throwable throwable) {
            throw throwable;
        }

        try {
            future2.get();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            executor.shutdown();
        }
        /*Runnable fetchAppointmentsTask = () -> {
            try {
                // Fetch available appointments
                System.out.println("Thread 1 Started");
                appointmentService.getAllAvailableAppointmentsByMedicalCenterId(centerId);
            } finally {
                latch.countDown();
            }
        };

        Runnable cancelAppointmentTask = () -> {
            try {
                // Wait for the fetch to start
                System.out.println("Thread 2 Started");
                latch.await();

                // Attempt to cancel the appointment, expecting a PessimisticLockingFailureException
                assertThrows(PessimisticLockingFailureException.class, () -> {
                    appointmentService.cancelScheduledAppointment(appointmentId);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executor.submit(fetchAppointmentsTask);
        executor.submit(cancelAppointmentTask);

        // Wait for tasks to finish
        latch.await();

        executor.shutdown();
    }*/

    }
}


