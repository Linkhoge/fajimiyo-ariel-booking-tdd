class RoomMustNotHaveOverlap implements BookingRule {
    private final IBookingRepository bookings;

    RoomMustNotHaveOverlap(IBookingRepository bookings) { this.bookings = bookings; }

    public void check(String userId, String roomId, TimeSlot slot) {
        for (Booking b : bookings.findByRoom(roomId)) {
            if (b.getSlot().overlaps(slot)) {
                throw new IllegalStateException("Room already has an overlapping booking");
            }
        }
    }
}