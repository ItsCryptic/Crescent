package io.github.awesome90.crescent.detection.checks.interact.autoclicker;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;

import io.github.awesome90.crescent.Crescent;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.detection.checks.CheckVersion;

public class AutoclickerA extends CheckVersion {

	private static final int REVIEW_TIME = Crescent.getInstance().getConfig().getInt("autoclicker.a.reviewTime");
	private static final int NEEDED_SAMPLES = Crescent.getInstance().getConfig().getInt("autoclicker.a.neededSamples");
	private static final double ALLOWED_INTERVAL = Crescent.getInstance().getConfig()
			.getDouble("autoclicker.a.allowedInterval");

	private AutoclickerStore store;

	public AutoclickerA(Check check) {
		super(check, "A", "Calculates CPS from data collected from the player swinging their arm.");
		this.store = new AutoclickerStore();
	}

	@Override
	public void call(Event event) {
		if (event instanceof PlayerAnimationEvent) {
			final PlayerAnimationEvent pae = (PlayerAnimationEvent) event;

			if (pae.getAnimationType() == PlayerAnimationType.ARM_SWING) {
				store.call();

				final ArrayList<Double> clicks = store.getCPSInTime(REVIEW_TIME);

				if (clicks.size() < NEEDED_SAMPLES) {
					return;
				}
				// Check for oscillating autoclickers.

				// Every time we identify something suspicious, add to this.
				int chance = 0;

				ArrayList<Double> differences = new ArrayList<Double>();
				for (int i = 0; i < clicks.size(); i++) {
					if (i != 0) {
						final double current = clicks.get(i), previous = clicks.get(i - 1);

						if (previous < current) {
							chance++;
						}

						if (i % 1 == 0) {
							// Use absolute so we can identify if the
							// autoclicker
							// slows down too.
							final double difference = Math.abs(current - previous);

							if (differences.size() != 0) {
								// Compare this difference to previous
								// differences.
								for (int p = 0; p < differences.size(); p++) {
									final double differenceBetweenDifferences = Math
											.abs(difference - differences.get(p));
									if (differenceBetweenDifferences == 0.0
											|| differenceBetweenDifferences < ALLOWED_INTERVAL) {
										chance++;
									}
								}
							}

							differences.add(difference);
						}
					}
				}

				final Double[] sorted = clicks.toArray(new Double[clicks.size()]);

				final double smallest = sorted[0], largest = sorted[sorted.length - 1];

				if (largest - smallest == 0.0) {
					chance += sorted.length;
				}

				Bukkit.broadcastMessage("autoclicker chance: " + chance);
			}
		}
	}

	@Override
	public double checkCurrentCertainty() {
		return 0;
	}

	public AutoclickerStore getStore() {
		return store;
	}

}
