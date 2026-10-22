public class Booking {
    private final String userId;
    private final String roomId;
    private final TimeSlot slot;

    public Booking(String userId, String roomId, TimeSlot slot) {
        this.userId = userId;
        this.roomId = roomId;
        this.slot = slot;
    }

    public String getUserId() {
        return userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Booking other)) {
            return false;
        }

        return java.util.Objects.equals(userId, other.userId)
            && java.util.Objects.equals(roomId, other.roomId)
            && java.util.Objects.equals(slot, other.slot);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(userId, roomId, slot);
    }
}