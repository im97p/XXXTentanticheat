package me.im97penis.shitnoob.check;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.im97penis.shitnoob.AnticheatPlugin;
import me.im97penis.shitnoob.check.violation.Violation;

@Getter
@AllArgsConstructor
public abstract class Check {

	private final String name, identifier, description;
	private final CheckType checkType;
	private final boolean bannable;
	private final int violationsForBan;

	public abstract void onEvent(Event event);

//	private static final String BAR = "&b&m*&b&m-----------------------------------&b&m*";

	public String getFullName() {
		return name + identifier;
	}

	public void log(int violations, Player flagged) {
		AnticheatPlugin.getPlugin().getProfileManager().getById(flagged.getUniqueId())
				.addViolation(new Violation(this, System.currentTimeMillis()));
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.hasPermission("ac.alert")) {
//				player.sendMessage(
//						ChatColor.DARK_AQUA.toString() + '[' + ChatColor.AQUA + "XXXTentanticheat" + ChatColor.DARK_AQUA
//								+ "] Player " + ChatColor.DARK_AQUA + flagged.getName() + ChatColor.AQUA + " failed "
//								+ ChatColor.DARK_AQUA + getFullName() + ChatColor.AQUA + " (VL " + violations + ")");
				player.sendMessage(ChatColor.GOLD.toString() + "XXXTentanticheat" + ChatColor.GRAY.toString() + " » "
						+ ChatColor.YELLOW + flagged.getName() + " failed " + ChatColor.GOLD + getFullName()
						+ ChatColor.GOLD + " (" + ChatColor.YELLOW + "VL " + violations + ChatColor.GOLD + ")");
			}
		}

		if (violations >= this.violationsForBan && this.bannable)
			this.ban(flagged);
	}

	public void log(int violations, Player flagged, String message) {
		AnticheatPlugin.getPlugin().getProfileManager().getById(flagged.getUniqueId())
				.addViolation(new Violation(this, System.currentTimeMillis()));
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.hasPermission("ac.alert")) {
//				player.sendMessage(ChatColor.DARK_AQUA.toString() + '[' + ChatColor.AQUA + "XXXTentanticheat"
//						+ ChatColor.DARK_AQUA + "] Player " + ChatColor.DARK_AQUA + flagged.getName() + ChatColor.AQUA
//						+ " failed " + ChatColor.DARK_AQUA + getFullName() + ChatColor.AQUA + " (VL " + violations
//						+ ", " + message + ")");
				player.sendMessage(ChatColor.GOLD.toString() + "XXXTentanticheat" + ChatColor.GRAY.toString() + " » "
						+ ChatColor.YELLOW + flagged.getName() + " failed " + ChatColor.GOLD + getFullName()
						+ ChatColor.GOLD + " (" + ChatColor.YELLOW + "VL " + violations + ", " + message + ChatColor.GOLD + ')');
			}
		}

		if (violations >= this.violationsForBan && this.bannable)
			this.ban(flagged);
	}

	private void ban(Player player) {
//		if (!(player.getName().equalsIgnoreCase("97p")) /*|| player.getName().equalsIgnoreCase("realbigshaq"))*/) {
//		Bukkit.broadcastMessage(translate(BAR));
//		Bukkit.broadcastMessage(translate("&bXXXTentanticheat &3has got &b" + player.getName()));
//		Bukkit.broadcastMessage(translate(BAR));

		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + player.getName() + " Unfair Advantage -s");
	}
//	}

//	private String translate(String string) {
//		return ChatColor.translateAlternateColorCodes('&', string);
//	}

}
