package io.github.awesome90.crescent.detection.checks.damage.antidamage;

import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.detection.checks.Check;
import io.github.awesome90.crescent.info.Profile;

public class AntiDamage extends Check {

	public AntiDamage(Profile profile) {
		super(profile, CheckType.ANTIDAMAGE);

		versions.add(new AntiDamageA(this));
	}

}
