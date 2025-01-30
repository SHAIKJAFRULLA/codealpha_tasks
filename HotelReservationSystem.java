import java.util.ArrayList;
import java.util.List;

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
        Hotel hotel = new Hotel("Grand Stay Hotel");

        // Adding rooms
        hotel.addRoom(new Room(101, "Single", 100));
        hotel.addRoom(new Room(102, "Double", 150));
        hotel.addRoom(new Room(103, "Suite", 300));

        System.out.println("\nAvailable Rooms:");
        for (Room room : hotel.searchRooms(null)) {
            System.out.println(room);
        }

        // Making a reservation
        System.out.println("\nMaking Reservation:");
        System.out.println(hotel.makeReservation(101, "John Doe", "2025-01-25", "2025-01-28", 300));

        System.out.println("\nAvailable Rooms After Reservation:");
        for (Room room : hotel.searchRooms(null)) {
            System.out.println(room);
        }

        System.out.println("\nBookings:");
        for (String booking : hotel.viewBookings()) {
            System.out.println(booking);
        }
    }