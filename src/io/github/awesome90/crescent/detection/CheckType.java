package io.github.awesome90.crescent.detection;

import org.bukkit.configuration.file.FileConfiguration;

import io.github.awesome90.crescent.Crescent;

public enum CheckType {
	WATERWALK("WaterWalk", 10), NOFALL("NoFall", 10), ANTIVELOCITY("AntiVelocity", 10), SNEAK("Sneak", 10), FLY("Fly",
			10), FASTBOW("Fastbow", 10), SPEED("Speed", 10), KILLAURA("Killaura", 10), FASTHEAL("FastHeal",
					10), HIGHJUMP("HighJump", 10), CRITICALS("Criticals", 10), TIMER("Timer",
							10), REACH("Reach", 10), INVENTORYTWEAKS("InventoryTweaks", 10), PACKETS("Blink", 10);

	private final String name;
	private final int normalCheatConsider;
	private final int cheatConsider;
	private final boolean prevent;

	private CheckType(String name, int normalCheatConsider) {
		this.name = name;
		this.normalCheatConsider = normalCheatConsider;

		final FileConfiguration fc = Crescent.getInstance().getConfig();

		this.cheatConsider = fc.getInt(name + ".cheatConsider");
		this.prevent = fc.getBoolean(name + ".prevent");
	}

	/**
	 * @return The user-friendly name of the check.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return The suggested, default, value for cheatConsider;
	 */
	public final int getNormalCheatConsider() {
		return normalCheatConsider;
	}

	/**
	 * @return The number of checks that need to be set off for the player to
	 *         fully be considered cheating.
	 */
	public final int getCheatConsider() {
		return cheatConsider;
	}

	/**
	 * @return Whether the player should be prevented from performing an action
	 *         (e.g. teleported back).
	 */
	public final boolean shouldPrevent() {
		return prevent;
	}
}
