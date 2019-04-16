package me.im97penis.shitnoob.check;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import me.im97penis.shitnoob.check.checks.autoclicker.AutoclickerA;
import me.im97penis.shitnoob.check.checks.reach.ReachD;
import me.im97penis.shitnoob.check.checks.speed.SpeedA;

public class CheckManager {

	@Getter
	private final Set<Check> checks;

	public CheckManager() {
		this.checks = new HashSet<>();
		this.checks.add(new AutoclickerA());
		this.checks.add(new ReachD());
		this.checks.add(new SpeedA());
		/*this.checks.add(new AutoclickerB());*/
		/*
		 * TODO: add checks
		 * 
		 */
	}

}
