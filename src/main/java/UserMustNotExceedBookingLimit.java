import java.time.*;

class UserMustNotExceedBookingLimit implements BookingRule {
    private final IBookingRepository bookings;
    private final Clock clock;

    UserMustNotExceedBookingLimit(IBookingRepository bookings, Clock clock) {
        this.bookings = bookings;
        this.clock = clock;
    }

    public void check(String userId, String roomId, TimeSlot slot) {
        LocalDateTime now = LocalDateTime.now(clock);
        int count = 0;

        for (Booking booking : bookings.findByUser(userId)) {
            if (booking.getSlot().getEnd().isAfter(now)) {
                count++;
            }
        }

        if (count >= 3) {
            throw new IllegalStateException(
                "User cannot hold more than three bookings that have not ended");
        }
    }
}