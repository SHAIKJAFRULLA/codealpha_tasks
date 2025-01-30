import java.util.*;

class Room {
    int roomId;
    String category;
    double price;
    boolean isAvailable;

    Room(int roomId, String category, double price) {
        this.roomId = roomId;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room " + roomId + " - " + category + " - $" + price + "/night - " + (isAvailable ? "Available" : "Booked");
    }
}

class Booking {
    int roomId;
    String guestName;
    String checkIn;
    String checkOut;
    double paymentAmount;

    Booking(int roomId, String guestName, String checkIn, String checkOut, double paymentAmount) {
        this.roomId = roomId;
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return "Room " + roomId + " | Guest: " + guestName + " | Check-in: " + checkIn + " | Check-out: " + checkOut + " | Payment: $" + paymentAmount;
    }
}

class Hotel {
    String name;
    List<Room> rooms;
    List<Booking> bookings;

    Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    void addRoom(Room room) {
        rooms.add(room);
    }

    List<Room> searchRooms(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable && (category == null || room.category.equalsIgnoreCase(category))) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    String makeReservation(int roomId, String guestName, String checkIn, String checkOut, double paymentAmount) {
        for (Room room : rooms) {
            if (room.roomId == roomId && room.isAvailable) {
                room.isAvailable = false;
                bookings.add(new Booking(roomId, guestName, checkIn, checkOut, paymentAmount));
                return "Reservation successful for " + guestName + " in Room " + roomId + " from " + checkIn + " to " + checkOut + ".";
            }
        }
        return "Room not available or invalid room ID.";
    }

    List<String> viewBookings() {
        List<String> bookingDetails = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDetails.add(booking.toString());
        }
        return bookingDetails;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel("Grand Stay Hotel");

        hotel.addRoom(new Room(101, "Single", 100));
        hotel.addRoom(new Room(102, "Double", 150));
        hotel.addRoom(new Room(103, "Suite", 300));

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Rooms:");
                    for (Room room : hotel.searchRooms(null)) {
                        System.out.println(room);
                    }
                    break;
                case 2:
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Guest Name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
                    String checkIn = scanner.nextLine();
                    System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
                    String checkOut = scanner.nextLine();
                    System.out.print("Enter Payment Amount: ");
                    double paymentAmount = scanner.nextDouble();

                    System.out.println(hotel.makeReservation(roomId, guestName, checkIn, checkOut, paymentAmount));
                    break;
                case 3:
                    System.out.println("\nBookings:");
                    for (String booking : hotel.viewBookings()) {
                        System.out.println(booking);
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
