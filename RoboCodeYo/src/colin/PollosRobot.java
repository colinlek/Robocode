package colin;

import robocode.*;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;

public class PollosRobot extends AdvancedRobot {

	private HashMap targets;
	private Enemy currentTarget;

	/**
	 * run: Pollo's predator default behavior
	 */
	public void run() {

		// body,gun,radar
		setColors(Color.yellow, Color.blue, Color.gray, Color.red, Color.green); //
		targets = new HashMap();
		// Robot main loop
		while (true) {
			attack();
			// double distance = Math.random() * 300;
			// double angle = Math.random() * 45;
			// turnRight(angle);
			// ahead(distance);
			// ahead(100);
			// turnGunRight(90);
			// back(100);
			// turnGunRight(90);
		}

		// Do an initial radar scan of entire field to pick up our enemy robot.
	//	turnRadarRight(1080);
	}

	private Enemy getEnemy(String name) {
		return (Enemy) targets.get(name);
	}

	private void attack() {
		// get current threat
	//	Vector m = getPosition();
		Enemy target = getBestTarget();
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		// add target to table
		String name = e.getName();
		if (!targets.containsKey(name)) {
			targets.put(name, new Enemy(name));
		}
		// update target scan
		Enemy target = getEnemy(name);
		target.update(e);

	}
	


	private Enemy getBestTarget() {
		double maxThreat = 0;
		Enemy target = null;
		for (Iterator i = targets.values().iterator(); i.hasNext();) {
			Enemy enemy = (Enemy) i.next();
			double threat = enemy.getEnergy();
//			if ((enemy == null) || (threat > maxThreat)) {
				if ((enemy == null) || (threat > maxThreat)) {
				target = enemy;
				maxThreat = threat;
			}
		}
		if (currentTarget != null)
			currentTarget.setTargeted(false);
		currentTarget = target;
		if (currentTarget != null)
			currentTarget.setTargeted(true);
		return target;
	}

	// // calculate threat bearing
	// Enemy threat = getWorstThreat();
	//
	// private Enemy getWorstThreat()
	// {
	// double maxThreat = 0;
	// Enemy target = null;
	// for (Iterator i = targets.values().iterator(); i.hasNext(); )
	// // {
	// // Enemy enemy = (Enemy)i.next();
	// // double threat = enemy.getThreat();
	// // if ((enemy == null) || (threat > maxThreat))
	// // {
	// // target = enemy;
	// // maxThreat = threat;
	// // }
	// // }
	// return target;
	// }

	public void fire(ScannedRobotEvent e) {
		double distance = e.getDistance();

		if (distance < 200) {
			fire(3.5);
		} else if (distance < 500) {
			fire(2.5);
		} else if (distance < 800) {
			fire(1.5);
		} else {
			fire(0.5);
		}

	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);

	}
}
