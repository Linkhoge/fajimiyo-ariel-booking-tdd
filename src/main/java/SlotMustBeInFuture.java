import java.time.*;

class SlotMustBeInFuture implements BookingRule {
    private final Clock clock;

    SlotMustBeInFuture(Clock clock) { this.clock = clock; }

    public void check(String userId, String roomId, TimeSlot slot) {
        if (!slot.getStart().isAfter(LocalDateTime.now(clock))) {
            throw new IllegalArgumentException("Slot must be in the future");
        }
    }
}