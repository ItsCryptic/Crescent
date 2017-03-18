package io.github.awesome90.crescent.listeners;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.awesome90.crescent.behaviour.Behaviour;
import io.github.awesome90.crescent.info.Profile;

public class BehaviourListeners implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		final Player player = event.getPlayer();

		final Material from = event.getFrom().getBlock().getRelative(BlockFace.DOWN).getType();
		final Material to = event.getTo().getBlock().getRelative(BlockFace.DOWN).getType();

		final Behaviour behaviour = Profile.getProfile(player.getUniqueId()).getBehaviour();

		if (behaviour.getLastY() == -1.0 || (from.isSolid() && to == Material.AIR)) {
			behaviour.setLastY(player.getLocation().getY());
			return;
		}
	}

}
