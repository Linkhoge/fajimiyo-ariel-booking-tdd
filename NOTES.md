# NOTES

## Gaps

| Rule | What was unclear | Ruling | My test for it |
| ---- | ---------------- | ------ | -------------- |
| R6 | Can we cancel a booking that has already started or finished? | No. Lecturer confirmed this in the shared comments. | Not added yet. |
| R7 | Should bookingsFor include past bookings? | No. Lecturer confirmed this in the shared comments. | Not added yet. |
| R6 | If a booking has started and we reject the cancellation, which exception should we throw? | Not yet confirmed. | Not added yet. |
| R5 | Are user IDs case-sensitive? Do "Alice" and "alice" count as the same user when checking overlapping bookings? | Not yet confirmed. | Not added yet. |

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

### 2 — To complete

- What I asked:
- What it produced:
- What was wrong:
- The test that caught it:

### 3 — To complete

- What I asked:
- What it produced:
- What was wrong:
- The test that caught it: