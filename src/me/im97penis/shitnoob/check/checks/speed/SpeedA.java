package me.im97penis.shitnoob.check.checks.speed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.im97penis.shitnoob.util.ListUtil;

public class SpeedA extends Speed {

	private final Map<UUID, List<Long>> movements;
	private final Map<UUID, List<Long>> average;
	
	public SpeedA() {
		super("A", "Average delay check (low=speed)");
		this.movements = new HashMap<>();
		this.average = new HashMap<>();
	}

	@Override
	public void check(Player player) {
		if(player.isFlying())
			return;
			
		if (movements.getOrDefault(player.getUniqueId(), new ArrayList<>()).size() >= 2) {
			long timeDifference = System.currentTimeMillis() - movements.get(player.getUniqueId()).get(1);
			List<Long> averages = average.getOrDefault(player.getUniqueId(), new ArrayList<>());

			averages.add(timeDifference);
			this.average.put(player.getUniqueId(), averages);
			movements.get(player.getUniqueId()).clear();
			return;
		}

		if (average.get(player.getUniqueId()) != null && average.get(player.getUniqueId()).size() >= 20) {
			List<Long> averages = average.get(player.getUniqueId());
			double average = (ListUtil.getTotal(averages) / averages.size());
			
			if(average <= 31) {
				log(2, player, "Timer, a=" + average);
			}
			
			averages.clear();
			this.average.put(player.getUniqueId(), averages);
		}

		if (!movements.containsKey(player.getUniqueId()))
			movements.put(player.getUniqueId(), new ArrayList<>());

		movements.get(player.getUniqueId()).add(System.currentTimeMillis());
	}

}
