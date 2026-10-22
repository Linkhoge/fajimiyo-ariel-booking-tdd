public class Booking {
    private final String userId;

    public Booking(String userId, String roomId, TimeSlot slot) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRoomId() {
        return null;
    }
}