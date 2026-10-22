public class Booking {
    private final String userId;
    private final String roomId;

    public Booking(String userId, String roomId, TimeSlot slot) {
        this.userId = userId;
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRoomId() {
        return roomId;
    }
}