import java.util.*;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private boolean[] seats = new boolean[TOTAL_SEATS];

    public synchronized boolean bookSeat(int seatNumber, String customerType) {
        if (seatNumber < 0 || seatNumber >= TOTAL_SEATS) {
            System.out.println(customerType + " Booking Failed: Invalid seat number " + seatNumber);
            return false;
        }
        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(customerType + " Successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(customerType + " Booking Failed: Seat " + seatNumber + " already booked.");
            return false;
        }
    }

    public void displaySeats() {
        System.out.print("Current Seat Status: ");
        for (boolean seat : seats) {
            System.out.print(seat ? "[X] " : "[O] ");
        }
        System.out.println();
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private int seatNumber;
    private String customerType;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customerType, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerType = customerType;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerType);
    }
}

public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();

        // Creating booking threads with different priorities
        List<Thread> bookings = new ArrayList<>();
        bookings.add(new BookingThread(system, 2, "VIP", Thread.MAX_PRIORITY));
        bookings.add(new BookingThread(system, 5, "VIP", Thread.MAX_PRIORITY));
        bookings.add(new BookingThread(system, 5, "Regular", Thread.NORM_PRIORITY));
        bookings.add(new BookingThread(system, 7, "Regular", Thread.NORM_PRIORITY));
        bookings.add(new BookingThread(system, 2, "Regular", Thread.MIN_PRIORITY));

        // Starting the booking threads
        for (Thread booking : bookings) {
            booking.start();
        }

        // Waiting for all threads to finish
        for (Thread booking : bookings) {
            try {
                booking.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Display final seat status
        system.displaySeats();
    }
}
