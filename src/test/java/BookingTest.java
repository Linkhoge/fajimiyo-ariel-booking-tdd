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

    @Test
    void returnsSlotProvidedToConstructor() {
        TimeSlot slot = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        Booking booking = new Booking("Seongryeong", "R1", slot);

        assertEquals(slot, booking.getSlot());
    }

    @Test
    void bookingsWithSameUserRoomAndSlotAreEqual() {
        TimeSlot firstSlot = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        TimeSlot secondSlot = new TimeSlot(
            LocalDateTime.of(2027, 1, 11, 10, 0),
            LocalDateTime.of(2027, 1, 11, 11, 0)
        );

        Booking first = new Booking("Seongryeong", "R1", firstSlot);
        Booking second = new Booking("Seongryeong", "R1", secondSlot);

        assertEquals(first, second);
    }
}