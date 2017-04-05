package io.github.awesome90.crescent.detection.checks.movement.waterwalk;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.awesome90.crescent.Crescent;
import io.github.awesome90.crescent.behaviour.Behaviour;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class WaterWalkA extends CheckVersion {

	private static final int WALK_TIME = Crescent.getInstance().getConfig().getInt("waterwalk.a.walkTime");

	public WaterWalkA(Check check) {
		super(check, "A", "Checks whether the player is walking on water");
	}

	@Override
	public void call(Event event) {
		if (event instanceof PlayerMoveEvent) {
			final PlayerMoveEvent pme = (PlayerMoveEvent) event;

			final Behaviour behaviour = profile.getBehaviour();
			final GameMode mode = profile.getPlayer().getGameMode();

			if ((mode != GameMode.CREATIVE && mode != GameMode.SPECTATOR) && !profile.getPlayer().isInsideVehicle()
					&& behaviour.isOnLiquidBlock() && !behaviour.isInWater()) {
				/*
				 * Do not execute this statement if the player is not descending
				 * (this could lead to false positives) and if the player is in
				 * water (and not standing on it).
				 */

				final Material from = getMaterialDown(pme.getFrom());
				final Material to = getMaterialDown(pme.getTo());

				if (from == Material.WATER_LILY || to == Material.WATER_LILY) {
					return;
				}

				final double fromY = pme.getFrom().getY(), toY = pme.getTo().getY();

				// If the player is walking on a block.
				if ((fromY % 1.0 == 0.0 || fromY % 0.5 == 0.0) && (toY % 1.0 == 0.0 || toY % 0.5 == 0.0)) {
					callback(true);
				}
			}

			callback(false);
		}
	}

	/**
	 * @return The percentage of a minute that the player has stood on water
	 *         for.
	 */
	@Override
	public double checkCurrentCertainty() {
		return 0.0;
	}

	private Material getMaterialDown(Location location) {
		return location.getBlock().getRelative(BlockFace.DOWN).getType();
	}

	private boolean isWater(Material material) {
		return material == Material.WATER || material == Material.STATIONARY_WATER;
	}
}
