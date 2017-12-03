package io.github.davidm98.crescent.detection.checks.damage.criticals;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class Criticals extends Check {

	public Criticals(Profile profile) {
		super(profile, CheckType.CRITICALS);

		versions.add(new CriticalsA(this));
	}

}
