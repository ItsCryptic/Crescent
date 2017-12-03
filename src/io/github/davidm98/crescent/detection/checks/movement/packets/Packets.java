package io.github.davidm98.crescent.detection.checks.movement.packets;

import io.github.davidm98.crescent.detection.CheckType;
import io.github.davidm98.crescent.detection.checks.Check;
import io.github.davidm98.crescent.info.Profile;

public class Packets extends Check {

	public Packets(Profile profile) {
		super(profile, CheckType.PACKETS);

		versions.add(new PacketsA(this));
	}

}
