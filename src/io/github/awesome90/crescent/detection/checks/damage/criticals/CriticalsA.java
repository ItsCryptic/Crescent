package io.github.awesome90.crescent.detection.checks.damage.criticals;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import io.github.awesome90.crescent.behaviour.Behaviour;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class CriticalsA extends CheckVersion {

	public CriticalsA(Check check) {
		super(check, "A", "Checks if a player is distributing critical hits without the needed conditions to do so.");
	}

	@Override
	public void call(Event event) {
		if (event instanceof EntityDamageByEntityEvent) {
			Bukkit.broadcastMessage("is critical: " + isCritical() + ", valid: " + isValidCritical());
			if (isCritical() && !isValidCritical()) {
				// This is not a valid critical hit.
				callback(true);
			}
		}
	}

	/**
	 * @return Whether the hit is a critical hit or not.
	 */
	private boolean isCritical() {
		final Player player = profile.getPlayer();
		final Behaviour behaviour = profile.getBehaviour();
		Bukkit.broadcastMessage("FALL DISTANCE: " + profile.getBehaviour().getMotion().getFallDistance());
		return player.getFallDistance() > 0.0 && !behaviour.isOnLadder() && !behaviour.isOnVine()
				&& !behaviour.isInWater() && !player.hasPotionEffect(PotionEffectType.BLINDNESS)
				&& !player.isInsideVehicle() && !player.isSprinting();
	}

	private boolean isValidCritical() {
		final double y = profile.getPlayer().getLocation().getY();
		return !profile.getBehaviour().getBlockUnderPlayer().getType().isSolid() && (y % 1.0 != 0.0 || y % 0.5 != 1.0);
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
