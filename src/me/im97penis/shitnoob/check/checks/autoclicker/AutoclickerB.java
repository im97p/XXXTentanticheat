package me.im97penis.shitnoob.check.checks.autoclicker;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AutoclickerB extends Autoclicker {

	private final Map<UUID, Long> lastClicks;
	private final Map<UUID, Integer> violations;

	public AutoclickerB() {
		super("B", "Double click check", false, 0);
		this.lastClicks = new HashMap<>();
		this.violations = new HashMap<>();
	}

	@Override
	public void onEvent(Event event) {
		if (event instanceof PlayerInteractEvent) {
			PlayerInteractEvent interact = (PlayerInteractEvent) event;

			if (interact.getAction() != Action.LEFT_CLICK_AIR)
				return;

			Player player = interact.getPlayer();

			if (lastClicks.containsKey(player.getUniqueId())) {
				long delay = System.currentTimeMillis() - lastClicks.get(player.getUniqueId());
				
				if(delay < 5L) {
					int violations = this.violations.getOrDefault(player.getUniqueId(), 0);
					violations++;
					
					this.violations.put(player.getUniqueId(), violations);
					log(violations, player, "Doubleclick");
				}
			}
			lastClicks.put(player.getUniqueId(), System.currentTimeMillis());
		}
	}

	@Override
	protected void check(Player player) {
		/**
		 * TODO: Unused
		 */
	}

}
