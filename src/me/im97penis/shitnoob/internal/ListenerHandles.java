package me.im97penis.shitnoob.internal;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import lombok.AllArgsConstructor;
import me.im97penis.shitnoob.AnticheatPlugin;
import me.im97penis.shitnoob.profile.Profile;

@AllArgsConstructor
public class ListenerHandles implements Listener {

	private final AnticheatPlugin plugin;

	@EventHandler(priority = EventPriority.LOW)
	public void onInteract(PlayerInteractEvent event) {
		this.plugin.getCheckManager().getChecks().forEach(check -> check.onEvent(event));
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onMove(PlayerMoveEvent event) {
		double fromX = event.getFrom().getX(), fromZ = event.getFrom().getZ(), toX = event.getTo().getX(),
				toZ = event.getTo().getZ();

		if (fromX != toX || fromZ != toZ)
			plugin.getProfileManager().getById(event.getPlayer().getUniqueId())
					.setLastMovement(System.currentTimeMillis());
		
		this.plugin.getCheckManager().getChecks().forEach(check -> check.onEvent(event));
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onHit(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
			this.plugin.getCheckManager().getChecks().forEach(check -> check.onEvent(event));

			Player damager = (Player) event.getDamager();
			Player damaged = (Player) event.getEntity();

			Profile damagerProfile = plugin.getProfileManager().getById(damager.getUniqueId());
			Profile damagedProfile = plugin.getProfileManager().getById(damaged.getUniqueId());

			damagedProfile.setCurrentComboSize(0);
			damagerProfile.setCurrentComboSize(damagerProfile.getCurrentComboSize() + 1);

		}
	}
}
