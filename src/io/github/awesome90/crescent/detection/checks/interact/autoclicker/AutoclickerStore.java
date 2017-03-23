package io.github.awesome90.crescent.detection.checks.interact.autoclicker;

import java.util.ArrayList;

public class AutoclickerStore {

	private int totalClicks, currentClicks;
	private double currentClickRate;
	private long totalStartTime, currentStartTime;

	private ArrayList<ClickRate> clickRates;

	public AutoclickerStore() {
		this.totalClicks = currentClicks = 0;
		this.currentClickRate = 0.0;
		this.totalStartTime = System.currentTimeMillis();
		this.currentStartTime = -1;

		this.clickRates = new ArrayList<ClickRate>();
	}

	/**
	 * Called when we want to log a click.
	 */
	public void call() {
		currentClicks++;

		final long current = System.currentTimeMillis();

		if (currentStartTime == -1) {
			currentStartTime = current;
		} else {
			long difference = current - currentStartTime;
			if (difference >= 1000) {

				// Check every second.

				currentClickRate = (double) currentClicks / (difference / 1000.0);

				clickRates.add(new ClickRate(currentClickRate));

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

	public int getClicksInTime(long time) {
		int clicks = 0;

		final long current = System.currentTimeMillis();

		for (ClickRate rate : clickRates) {
			if (current - rate.getTime() <= time) {
				clicks++;
			}
		}

		return clicks;
	}

	/**
	 * @param time
	 *            The amount of time to check for in milliseconds.
	 * @return The range of clicks per second in this time.
	 */
	public double getCPSRangeInTime(long time) {
		final long current = System.currentTimeMillis();

		double largest = 0.0, smallest = 0.0;

		for (ClickRate rate : clickRates) {
			if (current - rate.getTime() <= time) {
				if (rate.getCPS() > largest) {
					largest = rate.getCPS();
				}
				if (rate.getCPS() < smallest) {
					smallest = rate.getCPS();
				}
			}
		}

		return largest - smallest;
	}

}
