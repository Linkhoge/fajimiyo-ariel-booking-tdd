class SlotMustNotExceedMaxDuration implements BookingRule {
    public void check(String userId, String roomId, TimeSlot slot) {
        if (slot.duration().compareTo(BookingService.MAX_SLOT) > 0) {
            throw new IllegalArgumentException("Slot cannot be longer than three hours");
        }
    }
}