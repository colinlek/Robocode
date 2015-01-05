package colin;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
import robocode.*;

import java.awt.Color;
import java.util.*;

public class Predator extends AdvancedRobot {

	double lowEnergy = 100.0;
	ScannedRobotEvent nextVictim;
	// Map of enemies. The robot name (a String) is the key
	// and each value is a ScannedRobotEvent
	Map<String, ScannedRobotEvent> enemyData = new HashMap<String, ScannedRobotEvent>();

	//
	// run: Predator's default behavior
	//
	public void run() {
		setColors(Color.yellow, Color.red, Color.black);

		while (true) {
			double distance = Math.random() * 300;
			double angle = Math.random() * 45;
			turnRight(angle);
			ahead(distance);
			ahead(100);
			turnGunRight(90);
			back(100);
			turnGunRight(90);
			turnRadarRight(360);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		// Store the enemy name in the hash map, where the key = robot name
		enemyData.put(e.getName(), e);

		// Remove old enemy data that is more that 10 turns old
		for (ScannedRobotEvent sre : enemyData.values()) {
			if (sre.getTime() < (getTime() - 10)) {
				enemyData.remove(sre);
			}
		}

		// Now, find the weakest robot (least energy)
		double minEnergy = Double.MAX_VALUE; // Initialize to max value

		for (ScannedRobotEvent sre : enemyData.values()) {
			if (sre.getEnergy() < minEnergy) {
				minEnergy = sre.getEnergy();
				nextVictim = sre;
			}
		}

		if (e.equals(nextVictim)) { // if the scanned enemy is the weakest,
									// target it and fire
			double absoluteBearing = getHeading() + e.getBearing();
			turnGunRight(Math.toDegrees(Utils.normalRelativeAngle(Math
					.toRadians(absoluteBearing - getGunHeading()))));

			double distance = e.getDistance();
			if (distance < 200) {
				setFire(3.5);
			} else if (distance < 500) {
				setFire(2.5);
			} else if (distance < 800) {
				setFire(1.5);
			} else {
				setFire(0.5);
			}
		}
	}

	public void onHitByBullet(HitByBulletEvent e) {
		turnGunRight(e.getBearing());

		setFire(2);
	}
}
