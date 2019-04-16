package me.im97penis.shitnoob.check.checks.autoclicker;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.im97penis.shitnoob.check.violation.Violation;

public class AutoclickerA extends Autoclicker {

	private final Map<UUID, Double> count;

	public AutoclickerA() {
		super("A", "Uses mass click pattern testing.", false, 5);
		this.count = new HashMap<>();
	}

	@Override
	protected void check(Player damager) {
		ClickProfile profile = getById(damager.getUniqueId());
		ClickEntry entry = profile.getClickEntry();

		if (!entry.allSame() && entry.allAbove1() && entry.getLastClick4() == entry.getCurrentClick()
				&& entry.getLastClick3() == entry.getLastClick() && entry.getLastClick2() == entry.getLastClick1())
			profile.addViolation(new Violation(this, System.currentTimeMillis()));

		double count = this.count.getOrDefault(profile.getUniqueId(), 0.0D);
		profile.getViolations().removeIf(violation -> violation.getTime() < (System.currentTimeMillis() - 4000L));

		if (profile.getAveragePerioidViolations() >= 6) {
			if (profile.getAveragePerioidViolations() >= 10) {
				log(profile.getAveragePerioidViolations(), damager, "Pattern");
				count += 0.2;
			} else {
				if ((count += 1) >= 2) {
					log(profile.getAveragePerioidViolations(), damager, "Pattern");
					count = 0;
				}
			}
			profile.setAveragePerioidViolations(0);
		} else {
			if (count > 0)
				count -= 0.0036;
		}
		count = count < 0 ? 0 : count;
		this.count.put(profile.getUniqueId(), count);
	}

}
