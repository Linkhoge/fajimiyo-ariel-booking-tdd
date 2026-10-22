import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class TimeSlotTest {

    @Test
    void endBeforeStartIsRejected() {
        LocalDateTime ten = LocalDateTime.of(2027, 1, 11, 10, 0);
        LocalDateTime nine = LocalDateTime.of(2027, 1, 11, 9, 0);
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(ten, nine));
    }

    @Test
    void endEqualToStartIsRejected() {
        LocalDateTime ten = LocalDateTime.of(2027, 1, 11, 10, 0);
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(ten, ten));
    }

    @Test
    void nullStartIsRejected() {
        LocalDateTime ten = LocalDateTime.of(2027, 1, 11, 10, 0);
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(null, ten));
    }

    @Test
    void nullEndIsRejected() {
        LocalDateTime ten = LocalDateTime.of(2027, 1, 11, 10, 0);
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(ten, null));
    }

    @Test
    void durationOfNinetyMinutes() {
        LocalDateTime ten = LocalDateTime.of(2027, 1, 11, 10, 0);
        LocalDateTime elevenThirty = LocalDateTime.of(2027, 1, 11, 11, 30);
        TimeSlot slot = new TimeSlot(ten, elevenThirty);
        assertEquals(java.time.Duration.ofMinutes(90), slot.duration());
    }

    @Test
    void slotsWithSameTimesAreEqualAndHaveSameHashCode() {
    TimeSlot first = slot(10, 11);
    TimeSlot second = slot(10, 11);

    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
    }

    private static TimeSlot slot(int startHour, int endHour) {
        return new TimeSlot(LocalDateTime.of(2027, 1, 11, startHour, 0),
                            LocalDateTime.of(2027, 1, 11, endHour, 0));
    }

    @ParameterizedTest(name = "{0}-{1} vs {2}-{3} overlaps: {4}")
    @CsvSource({
    "10, 11, 11, 12, false", // touching
    "10, 12, 11, 13, true",  // partly overlapping
    "8, 9, 10, 12, false",   // A before B
    "11, 12, 10, 13, true",  // A inside B
    "10, 12, 10, 12, true",  // identical
    "11, 12, 9, 10, false"   // A after B
   })
    void overlapCases(int aStart, int aEnd, int bStart, int bEnd, boolean expected) {
        assertEquals(expected, slot(aStart, aEnd).overlaps(slot(bStart, bEnd)));
        assertEquals(expected, slot(bStart, bEnd).overlaps(slot(aStart, aEnd)));
    }
}