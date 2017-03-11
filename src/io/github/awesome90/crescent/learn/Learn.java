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
		final double cheatingAverage = data.getCurrentAverage(KnownCheating.YES);
		final double legitimateAverage = data.getCurrentAverage(KnownCheating.NO);

		double difference = Math.abs((value - cheatingAverage) - (value - legitimateAverage));

		return (difference / (cheatingAverage + legitimateAverage)) * 100.0;
	}

	public CheckType getType() {
		return type;
	}

	public double getValue() {
		return value;
	}

}
