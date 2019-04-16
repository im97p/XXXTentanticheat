package me.im97penis.shitnoob.check.checks.reach;

import org.bukkit.entity.Player;

import me.im97penis.shitnoob.check.Check;
import me.im97penis.shitnoob.check.CheckType;

public abstract class Reach extends Check {

	public Reach(String identifier, String description, boolean bannable, int violationsForBan) {
		super("Reach", identifier, description, CheckType.COMBAT, bannable, violationsForBan);
	}
	
	public abstract void check(Player damager, Player damaged);

}
