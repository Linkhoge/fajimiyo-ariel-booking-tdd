# NOTES

## Gaps

| Rule | What was unclear | Ruling | My test for it |
| ---- | ---------------- | ------ | -------------- |
| R5 | Are user IDs case-sensitive? Do "Alice" and "alice" count as the same user when checking overlapping bookings? | Yes everything is case sensitive | Not added yet. |

## AI corrections (at least three)

### 1 — Duplicate R5 test after unclear AI instructions

- What I asked: Whether to add the R5 test below the previous test.
- What it produced: Instructions that did not clearly say to add
  the R5 method only if it was not already present.
- What was wrong: I ended up with two methods named
  overlappingBookingsForSameUserAreRejected().
- The test that caught it: None — javac caught the duplicate-method
  compilation error before tests could run.
- Correction: Removed the duplicate and kept one R5 test.

### 2 — R6 mutation: missing deletion

- What I asked: Generate a cancellation implementation without removing bookings.
- What it produced: A version that checked the booking existed
  but didnt delete anything.
- What was wrong: Cancellation returned without removing the
  booking, so the room slot remained occupied.
- The test that caught it: cancellingBookingFreesTheSlot().
- Observed failure: Rebooking the cancelled slot threw
  IllegalStateException.
- Correction: Restored bookings.delete(booking) and confirmed
  the full test suite passed.
- Classification: Intentional mutation

### 3 — Intentional AI-generated R7 mutation: lost time precision

- What I asked: Generate an alternative way to continue with R7 requirement
- What it produced: A comparator using
  booking.getSlot().getStart().toLocalDate().
- What was wrong: Converting the start to LocalDate discarded
  the time. Bookings on the same day compared equally and
  retained insertion order instead of chronological order.
- The test that caught it:
  bookingsForReturnsBookingsInStartTimeOrder().
- Observed failure: The 14:00 booking appeared before the
  10:00 booking on the same date.
- Correction: Removed toLocalDate() so the comparator used
  the complete start date and time. Reran the full suite
  and confirmed all tests passed. (original)
- Classification: Intentional bug hunting and curiousity