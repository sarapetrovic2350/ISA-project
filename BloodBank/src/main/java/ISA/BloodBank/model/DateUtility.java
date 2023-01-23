package ISA.BloodBank.model;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public final class DateUtility {
	private DateUtility() {
	}

	public static LocalDate getStartOfNextMonth() {
		return LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
	}
}
