package io.github.awesome90.crescent.util;

import org.bukkit.Location;

public class Helper {

	public static double getDistanceSquaredXZ(Location from, Location to) {
		final double diffX = Math.abs(from.getX() - to.getX()), diffY = Math.abs(from.getY() - to.getY());
		return diffX * diffX + diffY * diffY;
	}

}
