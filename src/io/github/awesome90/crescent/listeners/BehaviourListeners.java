package io.github.awesome90.crescent.listeners;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.awesome90.crescent.behaviour.Behaviour;
import io.github.awesome90.crescent.info.Profile;

public class BehaviourListeners implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		final Player player = event.getPlayer();

		final Material from = event.getFrom().getBlock().getRelative(BlockFace.DOWN).getType();

		final Behaviour behaviour = Profile.getProfile(player.getUniqueId()).getBehaviour();

		final long current = System.currentTimeMillis();

		if (player.isSprinting()) {
			behaviour.getMotion().setLastSprint(current);
		}
		if (player.isFlying()) {
			behaviour.getMotion().setLastFly(current);
		}

		if (from.isSolid() || behaviour.getMotion().getLastY() == -1.0 || !behaviour.getMotion().isDescending()) {
			behaviour.getMotion().setLastY(player.getLocation().getY());
		}

		if (!behaviour.isOnGround()) {
			behaviour.getMotion().setLastYDiff(event.getTo().getY() - event.getFrom().getY());
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}

		if (event.getCause() == DamageCause.FALL) {
			final Player player = (Player) event.getEntity();

			final Behaviour behaviour = Profile.getProfile(player.getUniqueId()).getBehaviour();

			behaviour.getMotion().setLastY(player.getLocation().getY());
		}
	}

}
