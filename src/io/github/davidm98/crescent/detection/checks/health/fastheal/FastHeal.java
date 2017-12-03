package io.github.davidm98.crescent.detection.checks.health.fastheal;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class FastHeal extends Check {

	public FastHeal(Profile profile) {
		super(profile, CheckType.FASTHEAL);
		versions.add(new FastHealA(this));
	}

}
