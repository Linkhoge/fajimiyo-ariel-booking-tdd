import java.time.*;

class SlotMustNotBeTooFarAhead implements BookingRule {
    private final Clock clock;

    SlotMustNotBeTooFarAhead(Clock clock) {
        this.clock = clock;
    }

    public void check(String userId, String roomId, TimeSlot slot) {
        LocalDate latestDate = LocalDate.now(clock).plusDays(14);

        if (slot.getStart().toLocalDate().isAfter(latestDate)) {
            throw new IllegalArgumentException(
                "Booking cannot start more than fourteen days ahead");
        }
    }
}