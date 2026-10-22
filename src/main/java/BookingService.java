import java.time.*;
import java.util.List;
import java.util.Comparator;
public class BookingService {
    public static final Duration MAX_SLOT = Duration.ofHours(3);

    private final IRoomRepository rooms;
    private final IBookingRepository bookings;
    private final Clock clock;

    public BookingService(IRoomRepository rooms, IBookingRepository bookings, Clock clock) {
        this.rooms = rooms;
        this.bookings = bookings;
        this.clock = clock;
    }

    /** Book a room for a user. Returns the new booking. */
    public Booking book(String userId, String roomId, TimeSlot slot) {
        Room room = rooms.findById(roomId);

        if (room == null) {
            throw new IllegalArgumentException("Room does not exist");
        }

        LocalDateTime now = LocalDateTime.now(clock);

        if (slot.getStart().isBefore(now)) {
            throw new IllegalArgumentException("Slot must be in the future");
        }

        if (slot.duration().compareTo(MAX_SLOT) > 0) {
            throw new IllegalArgumentException("Slot cannot be longer than three hours");
        }

        List<Booking> roomBookings = bookings.findByRoom(roomId);

        for (Booking existingBooking : roomBookings) {
            if (existingBooking.getSlot().overlaps(slot)) {
                throw new IllegalStateException("Room already has an overlapping booking");
            }
        }

        List<Booking> userBookings = bookings.findByUser(userId);

        for (Booking existingBooking : userBookings) {
            if (existingBooking.getSlot().overlaps(slot)) {
                throw new IllegalStateException("User already has an overlapping booking");
            }
        }

        Booking booking = new Booking(userId, roomId, slot);
        bookings.save(booking);

        return booking;
    }

    /** Cancel a user's booking. */
    public void cancel(String userId, String roomId, TimeSlot slot) {
        Booking booking = new Booking(userId, roomId, slot);

        if (!bookings.findByUser(userId).contains(booking)) {
            throw new IllegalArgumentException("Booking does not exist");
        }

        bookings.delete(booking);
    }

    /** A user's bookings, earliest first. */
    public List<Booking> bookingsFor(String userId) {
        return bookings.findByUser(userId).stream()
            .sorted(Comparator.comparing(b -> b.getSlot().getStart()))
            .toList();
    }
}