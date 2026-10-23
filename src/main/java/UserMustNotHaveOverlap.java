class UserMustNotHaveOverlap implements BookingRule {
    private final IBookingRepository bookings;

    UserMustNotHaveOverlap(IBookingRepository bookings) { this.bookings = bookings; }

    public void check(String userId, String roomId, TimeSlot slot) {
        for (Booking b : bookings.findByUser(userId)) {
            if (b.getSlot().overlaps(slot)) {
                throw new IllegalStateException("User already has an overlapping booking");
            }
        }
    }
}