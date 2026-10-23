import java.time.*;
import java.util.List;
import java.util.Comparator;

public class BookingService {
    public static final Duration MAX_SLOT = Duration.ofHours(3);

    private final IRoomRepository rooms;
    private final IBookingRepository bookings;
    private final Clock clock;
    private final List<BookingRule> rules;

    public BookingService(IRoomRepository rooms, IBookingRepository bookings, Clock clock) {
        this.rooms = rooms;
        this.bookings = bookings;
        this.clock = clock;
        this.rules = List.of(
            new RoomMustExist(rooms),
            new SlotMustBeInFuture(clock),
            new SlotMustNotExceedMaxDuration(),
            new RoomMustNotHaveOverlap(bookings),
            new UserMustNotHaveOverlap(bookings),
            new UserMustNotExceedBookingLimit(bookings, clock),
            new SlotMustNotBeTooFarAhead(clock)
        );
    }

    public Booking book(String userId, String roomId, TimeSlot slot) {
        for (BookingRule rule : rules) {
            rule.check(userId, roomId, slot);
        }
        Booking booking = new Booking(userId, roomId, slot);
        bookings.save(booking);
        return booking;
    }

    public void cancel(String userId, String roomId, TimeSlot slot) {
        Booking booking = new Booking(userId, roomId, slot);
        if (!bookings.findByUser(userId).contains(booking)) {
            throw new IllegalArgumentException("Booking does not exist");
        }
        bookings.delete(booking);
    }

    public List<Booking> bookingsFor(String userId) {
        return bookings.findByUser(userId).stream()
            .sorted(Comparator.comparing(b -> b.getSlot().getStart()))
            .toList();
    }
}