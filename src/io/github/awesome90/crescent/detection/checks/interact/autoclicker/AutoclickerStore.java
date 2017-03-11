package io.github.awesome90.crescent.detection.checks.interact.autoclicker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class AutoclickerStore {

	private int totalClicks, currentClicks;
	private double currentClickRate;
	private long totalStartTime, currentStartTime;

	public AutoclickerStore() {
		this.totalClicks = currentClicks = 0;
		this.currentClickRate = 0.0;
		this.totalStartTime = System.currentTimeMillis();
		this.currentStartTime = -1;
	}

	/**
	 * Called when we want to log a click.
	 */
	public void call() {
		currentClicks++;

		if (currentStartTime == -1) {
			currentStartTime = System.currentTimeMillis();
		} else {
			long difference = System.currentTimeMillis() - currentStartTime;
			if (difference >= 5000) {

				// Check every two seconds.
				currentClickRate = (double) currentClicks / (difference / 1000.0);
				Bukkit.broadcastMessage(ChatColor.GOLD + "cps: " + currentClickRate);
				currentClicks = 0;
				currentStartTime = System.currentTimeMillis();
			}
		}

		totalClicks++;
	}

	public double getCurrentClickRate() {
		return currentClickRate;
	}

	public double getTotalClickRate() {
		return totalClicks / (System.currentTimeMillis() - totalStartTime);
	}

}
