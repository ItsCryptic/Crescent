package io.github.awesome90.crescent.learn;

import io.github.awesome90.crescent.detection.CheckType;

public class Learn {

	private final CheckType type;
	private final LearnData data;
	private final boolean cheating;
	private final double value;

	public Learn(CheckType type, boolean cheating, double value) {
		this.type = type;
		this.data = LearnData.getLearnData(type);
		this.cheating = cheating;
		this.value = value;
	}

	/**
	 * @param learn
	 *            The Learn object to store.
	 */
	public void storeData() {
		data.storeNewData(this);
	}

	/**
	 * @return The percentage difference to other, previous, checks.
	 */
	public double compare() {
		final double average = data.getCurrentAverage(cheating);

		double difference = value - average;

		if (difference < 0) {
			/*
			 * Due to the fact that the higher the value, the more likely they
			 * are to be cheating. If they are below the average (like in this
			 * scenario, they are almost completely unlikely to be cheating).
			 */
			difference = 0.0;
		}

		return (difference / average) * 100.0;
	}

	public CheckType getType() {
		return type;
	}

	public boolean isCheating() {
		return cheating;
	}

	public double getValue() {
		return value;
	}

}
