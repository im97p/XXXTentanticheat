package me.im97penis.shitnoob.check.checks.autoclicker;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.im97penis.shitnoob.check.Check;
import me.im97penis.shitnoob.check.CheckType;

public abstract class Autoclicker extends Check {

	private final Set<ClickProfile> clickProfiles;

	public Autoclicker(String identifier, String description, boolean bannable, int violationsForBan) {
		super("Autoclicker", identifier, description, CheckType.COMBAT, bannable, violationsForBan);
		this.clickProfiles = new HashSet<>();
	}

	protected abstract void check(Player player);
	
	public ClickProfile getById(UUID uniqueId) {
		return clickProfiles.stream().filter(profile -> profile.getUniqueId().equals(uniqueId)).findAny()
				.orElseGet(() -> {
					ClickProfile profile = new ClickProfile(uniqueId);

					clickProfiles.add(profile);

					return profile;
				});
	}

	@Override
	public void onEvent(Event event) {
		if(event instanceof PlayerInteractEvent) {
			PlayerInteractEvent interact = (PlayerInteractEvent) event;
			
			if(interact.getAction() != Action.LEFT_CLICK_AIR && interact.getAction() != Action.LEFT_CLICK_BLOCK)
				return;
			
			Player player = interact.getPlayer();
			
			getById(player.getUniqueId()).addClick();
			getById(player.getUniqueId()).getClicks();
			check(player);
		}
	}

}
