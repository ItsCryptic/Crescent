package io.github.awesome90.crescent.detection.checks.damage.criticals;

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
			if (!canDealCritical() && isInvalidCritical()) {
				// This is not a valid critical hit.
				callback(true);
			}
		}
	}

	/**
	 * @return Whether the player can deal a critical hit or not.
	 */
	private boolean canDealCritical() {
		final Player player = profile.getPlayer();
		final Behaviour behaviour = profile.getBehaviour();
		return profile.getBehaviour().getBlockUnderPlayer().getType().isSolid() && player.getFallDistance() > 0.0
				&& !behaviour.isOnGround() && !behaviour.isOnLadder() && !behaviour.isOnVine() && !behaviour.isInWater()
				&& !player.hasPotionEffect(PotionEffectType.BLINDNESS) && !player.isInsideVehicle()
				&& !player.isSprinting();
	}

	private boolean isInvalidCritical() {
		final double y = profile.getPlayer().getLocation().getY();
		return y % 1.9 == 0.0 || y % 0.5 == 1.0;
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
