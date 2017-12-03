package io.github.davidm98.crescent.detection.checks.interact.autoclicker;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class Autoclicker extends Check {

	public Autoclicker(Profile profile) {
		super(profile, CheckType.AUTOCLICKER);

		versions.add(new AutoclickerA(this));
	}
	
}
