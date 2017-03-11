package io.github.awesome90.crescent.detection.checks.movement.blink;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.awesome90.crescent.Crescent;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class PacketsA extends CheckVersion {

	private static final double MAX_PACKETS_PER_TICK = Crescent.getInstance().getConfig()
			.getDouble("packets.a.maxPacketsPerTick");

	private long lastTime;
	private int movements;

	public PacketsA(Check check) {
		super(check, "A", "Checks the packets that the player is sending against their latency.");
		this.lastTime = -1;
		this.movements = 0;
	}

	@Override
	public void call(Event event) {
		if (event instanceof PlayerMoveEvent) {
			if (lastTime != -1) {
				final long difference = System.currentTimeMillis() - lastTime;

				if (difference >= 1000) {
					// Check every second.

					/*
					 * The amount of movement packets sent every tick.
					 * 
					 * I have tested the value this gives and the normal client
					 * sends about 1 packet per tick.
					 */
					final double movementsPerTick = (((double) movements / (double) difference) * 1000.0) / 20.0;

					if (movementsPerTick > MAX_PACKETS_PER_TICK) {
						// The player is sending more packets than allowed.

						/*
						 * There has to be a more efficient way to do this! Add
						 * one to the ping to avoid dividing by zero.
						 */
						final double certaintyPercentage = ((movementsPerTick / (profile.getPing() + 1)) * 100.0) / 2.0;

						/*
						 * If we're over 25% sure, it's likely that the player
						 * is cheating.
						 */
						if (certaintyPercentage > 25.0) {
							callback(true);
						}

						callback(true);
					}

					// Reset everything.
					lastTime = System.currentTimeMillis();
					movements = 0;
				}
			} else {
				lastTime = System.currentTimeMillis();
			}

			movements++;
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

}
