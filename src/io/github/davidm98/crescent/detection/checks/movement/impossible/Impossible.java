package io.github.davidm98.crescent.detection.checks.movement.impossible;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class Impossible extends Check {

	public Impossible(Profile profile) {
		super(profile, CheckType.SNEAK);

		versions.add(new ImpossibleA(this));
	}

}
