package io.github.awesome90.crescent.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class Helper {

	public static double getDistanceSquaredXZ(Location from, Location to) {
		final double diffX = Math.abs(from.getX() - to.getX()), diffY = Math.abs(from.getY() - to.getY());
		return diffX * diffX + diffY * diffY;
	}

	public static double getAngle(Entity from, Entity to) {
		final Location original = from.getLocation();
		final Location change = original.clone();

		final Vector start = change.toVector();
		final Vector target = to.getLocation().toVector();

		// Get the difference between the two locations and set this as the
		// direction.
		change.setDirection(target.subtract(start));

		/*
		 * Find the remainder of the difference in yaw divided by 180. This is
		 * the angle of attack. This should never be negative.
		 */
		return Math.abs((change.getYaw() - original.getYaw()) % 180);
	}

}
