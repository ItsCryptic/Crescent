package io.github.davidm98.crescent.detection.checks.damage.killaura;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class Killaura extends Check {

	public Killaura(Profile profile) {
		super(profile, CheckType.KILLAURA);

		versions.add(new KillauraA(this));
		versions.add(new KillauraB(this));
		versions.add(new KillauraC(this));
		versions.add(new KillauraLearn(this));
	}

}
