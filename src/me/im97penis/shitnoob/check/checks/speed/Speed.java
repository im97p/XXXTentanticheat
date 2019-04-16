package me.im97penis.shitnoob.check.checks.speed;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import me.im97penis.shitnoob.check.Check;
import me.im97penis.shitnoob.check.CheckType;

public abstract class Speed extends Check {

	public Speed(String identifier, String description) {
		super("Speed", identifier, description, CheckType.MOVEMENT, false, 0);
	}

	@Override
	public void onEvent(Event event) {
		if (event instanceof PlayerMoveEvent) {
			PlayerMoveEvent move = (PlayerMoveEvent) event;

			Location to = move.getTo(), from = move.getFrom();
			double toX = to.getX(), toZ = to.getZ(), toY = to.getY(), fromX = from.getX(), fromZ = from.getZ(),
					fromY = from.getY();

			if (toX != fromX || toZ != fromZ || toY != fromY)
				this.check(move.getPlayer());
		}
	}

	public abstract void check(Player player);

}
