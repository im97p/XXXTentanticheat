package me.im97penis.shitnoob.util;

import org.bukkit.entity.Player;

public class EntityUtil {

	public static float calculateAngle(Player a, Player b) {
		double x = b.getLocation().getX() - a.getLocation().getX();
		// double y = b.getLocation().getY() - a.getLocation().getY();
		double z = b.getLocation().getZ() - a.getLocation().getZ();

		float yaw = (float) Math.toDegrees(-Math.atan(x / z));
		if (z < 0.0 && x < 0.0)
			yaw = (float) (90.0 + Math.toDegrees(Math.atan(z / x)));
		else if (z < 0.0 && x > 0.0)
			yaw = (float) (-90.0 + Math.toDegrees(Math.atan(z / x)));

		return wrapAngleTo180_float(yaw - a.getLocation().getYaw());
	}

	public static float wrapAngleTo180_float(float p_761420) {
		p_761420 %= 360.0F;

		if (p_761420 >= 180.0F) {
			p_761420 -= 360.0F;
		}

		if (p_761420 < -180.0F) {
			p_761420 += 360.0F;
		}

		return p_761420;
	}
}
