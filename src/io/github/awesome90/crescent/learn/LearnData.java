package io.github.awesome90.crescent.learn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import io.github.awesome90.crescent.Crescent;
import io.github.awesome90.crescent.detection.CheckType;

public class LearnData {

	private static ArrayList<LearnData> learnData = new ArrayList<LearnData>();

	public static LearnData getLearnData(CheckType type) {
		if (!type.canLearn()) {
			return null;
		}

		LearnData data = null;

		for (LearnData check : learnData) {
			if (check.getType() == type) {
				data = check;
			}
		}

		if (data == null) {
			data = new LearnData(type);
			learnData.add(data);
		}

		return data;
	}

	public static final File DIRECTORY = new File(
			Crescent.getInstance().getDataFolder().getPath() + File.separator + "LearnStore");

	private final CheckType type;

	public LearnData(CheckType type) {
		this.type = type;
	}

	public void storeNewData(Learn learn, KnownCheating knownCheating) {
		final FileConfiguration fc = getConfig();

		// Generate a UUID for this check (its identifier).
		UUID uuid = UUID.randomUUID();

		while (fc.isConfigurationSection(uuid.toString())) {
			uuid = UUID.randomUUID();
		}

		fc.createSection(uuid.toString());

		// The path to put the data in the file.
		final String path = uuid.toString() + ".";

		final boolean cheating = knownCheating == KnownCheating.YES;

		// Set the values.
		fc.set(path + "type", type.getName().toLowerCase());
		fc.set(path + "cheating", cheating);
		fc.set(path + "value", learn.getValue());

		String cheatPath = getCheatPath(knownCheating);

		// Get general values.
		final double currentAverage = fc.getDouble(cheatPath + "CurrentAverage");
		final long totalSamples = fc.getLong(cheatPath + "TotalSamples");

		// Calculate the new average.
		double updateAverage = (currentAverage + learn.getValue()) / totalSamples + 1;

		fc.set(cheatPath + "CurrentAverage", updateAverage);
		fc.set(cheatPath + "TotalSamples", totalSamples + 1);

		// Save the file.
		save();
	}

	/**
	 * @param knownCheating
	 *            The player's cheating status.
	 * @return The path string needed for the corresponding cheating boolean
	 *         value.
	 */
	private String getCheatPath(KnownCheating knownCheating) {
		return knownCheating == KnownCheating.YES ? "cheating" : "legitimate";
	}

	private YamlConfiguration getConfig() {
		return YamlConfiguration.loadConfiguration(getRecordFile());
	}

	/**
	 * @param type
	 *            The CheckType that one wants to get the file for.
	 * @return The File object associated with this CheckType.
	 */
	private File getRecordFile() {
		final File storeTo = new File(DIRECTORY.getPath() + File.separator + type.getName().toLowerCase() + ".yml");
		createIfNotExists(storeTo);
		return storeTo;
	}

	private void save() {
		try {
			getConfig().save(getRecordFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a file if it does not already exist.
	 * 
	 * @param file
	 *            The file one wishes to perform the action on.
	 * @return Whether the file has been created or not.
	 */
	private boolean createIfNotExists(File file) {
		try {
			return file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public double getCurrentAverage(KnownCheating knownCheating) {
		return getConfig().getDouble(getCheatPath(knownCheating) + "TotalAverage");
	}

	public void setCurrentAverage(double average) {
		getConfig().set("TotalAverage", average);
	}

	public long getTotalSamples(KnownCheating knownCheating) {
		return getConfig().getLong(getCheatPath(knownCheating) + "TotalSamples");
	}

	public void setTotalSamples(long totalSamples) {
		getConfig().set("TotalAverage", totalSamples);
	}

	public CheckType getType() {
		return type;
	}

}
