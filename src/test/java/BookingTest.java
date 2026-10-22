import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    @Test
    void returnsUserIdProvidedToConstructor() {
        TimeSlot slot = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        Booking booking = new Booking("Seongryeong", "R1", slot);

        assertEquals("Seongryeong", booking.getUserId());
    }

    @Test
    void returnsRoomIdProvidedToConstructor() {
        TimeSlot slot = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        Booking booking = new Booking("Seongryeong", "R1", slot);

        assertEquals("R1", booking.getRoomId());
}
}