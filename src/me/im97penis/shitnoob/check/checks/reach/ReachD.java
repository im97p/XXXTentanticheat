package me.im97penis.shitnoob.check.checks.reach;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.im97penis.shitnoob.AnticheatPlugin;
import me.im97penis.shitnoob.profile.Profile;
import me.im97penis.shitnoob.util.MathUtil;
import net.minecraft.server.v1_8_R3.EntityPlayer;

public class ReachD extends Reach {

	private final Map<UUID, Integer> violations;
	private final Map<UUID, Set<Pair<Long, Double>>> counts;
	private final Map<UUID, Integer> subCount;
	
	public ReachD() {
		super("D", "3.1 reach detection", false, 5);
		this.violations = new HashMap<>();
		this.counts = new HashMap<>();
		this.subCount = new HashMap<>();
	}

	@Override
	public void onEvent(Event event) {
		if (event instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent damage = (EntityDamageByEntityEvent) event;

			Player damager = (Player) damage.getDamager();
			Player damaged = (Player) damage.getEntity();

			if (damager.getGameMode() == GameMode.CREATIVE)
				return;

			this.check(damager, damaged);
		}
	}

	@Override
	public void check(Player damager, Player damaged) {
		double reach = MathUtil.round(damager.getLocation().distance(damaged.getLocation()), 1);

		Profile profile = AnticheatPlugin.getPlugin().getProfileManager().getById(damager.getUniqueId());

		double maxReach = 3.5;
		double yDifference = MathUtil.round(Math.abs(damager.getLocation().getY() - damaged.getLocation().getY()), 1);

		maxReach += yDifference;
		maxReach += (damager.isSneaking() || damaged.isSneaking()) ? 0.08 : 0.0D;

		PotionEffect speed = null;
		
		if ((speed = damager.getActivePotionEffects().stream()
				.filter(effect -> effect.getType() == PotionEffectType.SPEED).findAny().orElse(null)) != null) {
			maxReach += speed.getAmplifier() + 0.5;
		}
		
		int damagedPing = ((EntityPlayer) ((CraftPlayer) damaged).getHandle()).ping;
		int damagerPing = ((EntityPlayer) ((CraftPlayer) damager).getHandle()).ping;
			
		double pingDifference = Math.round(Math.abs((damagedPing - damagerPing) / 100)) + 0.105;
		
		maxReach += pingDifference;
		
		if (reach >= maxReach) {
			Set<Pair<Long, Double>> time = counts.getOrDefault(damager.getUniqueId(), new HashSet<>());

			Iterator<Pair<Long, Double>> it = time.iterator();

			while (it.hasNext()) {
				Pair<Long, Double> next = it.next();

				double range = next.getValue();
				long timeStored = next.getKey();

				if ((range - 0.4) < 3.4 && profile.getCurrentComboSize() >= 4) {
					it.remove();
					continue;
				}

				if (range >= 2 && range < maxReach || System.currentTimeMillis() - timeStored > 25000L) {
					it.remove();
					continue;
				}
			}

			time.add(new Pair<>(System.currentTimeMillis(), reach));
			counts.put(damager.getUniqueId(), time);

			if (time.size() >= 7) { // possibly turn up to 7
				this.subCount.put(damager.getUniqueId(), this.subCount.getOrDefault(damager.getUniqueId(), 0) + 1);
				time.clear();
				
				counts.put(damager.getUniqueId(), time);
			}
				
			if(subCount.size() >= 5) {
				int violations = this.violations.getOrDefault(damager.getUniqueId(), 0);

				log(violations++, damager, "r=" + (reach - 0.4) + ", c=" + time.size() + ", pd=" + pingDifference);

				this.violations.put(damager.getUniqueId(), violations);
				subCount.clear();
			}
			
//			if (damager.getName().equalsIgnoreCase("tacts"))
//				damager.sendMessage("r=" + (reach - 0.4) + ", mR=" + maxReach + ", c=" + time.size() + ", pd=" + pingDifference);
			return;
		}
//		if (damager.getName().equalsIgnoreCase("tacts"))
//			damager.sendMessage("r=" + (reach - 0.4) + ", mR=" + maxReach);
	}

}
