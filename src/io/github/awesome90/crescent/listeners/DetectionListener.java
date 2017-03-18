package io.github.awesome90.crescent.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.detection.checks.CheckVersion;
import io.github.awesome90.crescent.info.Profile;

public class DetectionListener implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		final Player player = event.getPlayer();

		getCheckVersion(player, CheckType.SPEED, "A").call(event);

		getCheckVersion(player, CheckType.FLY, "A").call(event);

		getCheckVersion(player, CheckType.WATERWALK, "A").call(event);

		getCheckVersion(player, CheckType.NOFALL, "A").call(event);

		getCheckVersion(player, CheckType.SNEAK, "A").call(event);

		getCheckVersion(player, CheckType.PACKETS, "A").call(event);
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();

			getCheckVersion(player, CheckType.KILLAURA, "A").call(event);
		}

		if (event.getDamager() instanceof Player) {
			final Player player = (Player) event.getDamager();

			getCheckVersion(player, CheckType.REACH, "A").call(event);

			getCheckVersion(player, CheckType.CRITICALS, "A").call(event);

			getCheckVersion(player, CheckType.AUTOCLICKER, "Damage").call(event);

			getCheckVersion(player, CheckType.KILLAURA, "A").call(event);

			getCheckVersion(player, CheckType.KILLAURA, "B").call(event);

			getCheckVersion(player, CheckType.KILLAURA, "C").call(event);

			getCheckVersion(player, CheckType.KILLAURA, "Learn").call(event);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}

		final Player player = (Player) event.getEntity();

		getCheckVersion(player, CheckType.ANTIDAMAGE, "A").call(event);
	}

	@EventHandler
	public void onPlayerInteract(EntityShootBowEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}

		final Player player = (Player) event.getEntity();

		getCheckVersion(player, CheckType.FASTBOW, "A").call(event);
	}

	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}

		final Player player = (Player) event.getEntity();

		getCheckVersion(player, CheckType.FASTHEAL, "A").call(event);
	}

	@EventHandler
	public void onPlayerVelocity(PlayerVelocityEvent event) {
		final Player player = event.getPlayer();

		getCheckVersion(player, CheckType.ANTIVELOCITY, "A").call(event);
	}

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		final Player player = event.getPlayer();

		getCheckVersion(player, CheckType.SNEAK, "A").call(event);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		final Player player = event.getPlayer();

		getCheckVersion(player, CheckType.LIQUIDS, "A").call(event);
	}

	@EventHandler
	public void onPlayerInteract(PlayerAnimationEvent event) {
		final Player player = event.getPlayer();

		getCheckVersion(player, CheckType.AUTOCLICKER, "Interact").call(event);
	}

	private Profile getProfile(Player player) {
		return Profile.getProfile(player.getUniqueId());
	}

	private CheckVersion getCheckVersion(Player player, CheckType type, String version) {
		return getProfile(player).getCheck(type).getCheckVersion(version);
	}

}
