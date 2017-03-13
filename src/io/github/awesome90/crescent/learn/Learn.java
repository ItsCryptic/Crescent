package io.github.awesome90.crescent.learn;

import io.github.awesome90.crescent.detection.CheckType;

public class Learn {

	private final CheckType type;
	private final LearnData data;
	private final double value;

	public Learn(CheckType type, double value) {
		this.type = type;
		this.data = LearnData.getLearnData(type);
		this.value = value;
	}

	/**
	 * @param learn
	 *            The Learn object to store.
	 */
	public void storeData(KnownCheating knownCheating) {
		data.storeNewData(this, knownCheating);
	}

	/**
	 * @return The percentage difference to other, previous, checks.
	 */
	public double compare() {
		double certainty = 0.0;

		final double cheatingLowRange = data.getCurrentHighRange(KnownCheating.YES);
		final double cheatingAverage = data.getCurrentLowRange(KnownCheating.YES);

		// The midpoint between the low range and mean of the cheating spectrum
		// and.
		final double midpoint = (cheatingLowRange + cheatingAverage) / 2.0;

		certainty = (value / midpoint) * 100.0;

		if (value > cheatingAverage) {
			certainty *= 1.5;
		} else if (value > cheatingLowRange) {
			certainty *= 1.25;
		}

		// It can't be negatively sure!
		if (certainty < 0.0) {
			certainty = 0.0;
		}

		return certainty;
	}

	public CheckType getType() {
		return type;
	}

	public double getValue() {
		return value;
	}

}
