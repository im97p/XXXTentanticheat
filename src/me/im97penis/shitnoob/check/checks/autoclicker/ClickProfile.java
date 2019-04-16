package me.im97penis.shitnoob.check.checks.autoclicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import me.im97penis.shitnoob.check.violation.Violation;
import me.im97penis.shitnoob.util.MapUtil;

@Getter
public class ClickProfile {

	private final UUID uniqueId;
	private final Set<Long> clicks;
	private final ClickEntry clickEntry;
	private final List<Violation> violations;
	private final Map<Long, Violation> periodCachedViolations;
	
	@Setter
	private int averagePerioidViolations;
	
	public ClickProfile(UUID uniqueId) {
		this.uniqueId = uniqueId;
		this.clicks = new HashSet<>();
		this.clickEntry = new ClickEntry(System.currentTimeMillis(), 0, 0, 0, 0, 0, 0);
		this.violations = new ArrayList<>();
		this.periodCachedViolations = new HashMap<>();
	}

	public void addViolation(Violation violation) {
		this.violations.add(violation);
		this.periodCachedViolations.put(System.currentTimeMillis(), violation);
		
		Long started = MapUtil.getLowestValue(periodCachedViolations);
		
		if(System.currentTimeMillis() < started + 8000L)
			return;
		
		int violations = periodCachedViolations.values().size();
		this.averagePerioidViolations = violations;
		
		periodCachedViolations.clear();
	}

	public void addClick() {
		this.clicks.add(System.currentTimeMillis());
	}

	public int getClicks() {
		Iterator<Long> it = clicks.iterator();

		while (it.hasNext()) {
			if (it.next() < (System.currentTimeMillis() - 1000L)) {
				it.remove();
			}
		}

		int clicks = this.clicks.size();
		this.clickEntry.shift(clicks);

		return clicks;
	}

}
