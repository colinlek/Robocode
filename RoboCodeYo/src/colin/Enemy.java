package colin;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class Enemy extends AdvancedRobot {

	private double bearing = 0;
	private double distance = 0;
	private double energy = 0;
	private double heading = 0;
	private String name = null;
	private double velocity = 0;
	private double current = 1;

	public Enemy(String name) {
		this.name = name;
	}

	public Enemy() {
		reset();
	}

	public void setTargeted(boolean b) {
		if (b)
			current = 2;
		else
			current = 1;
	}

	public double getBearing() {
		return bearing;
	}

	public double getDistance() {
		return distance;
	}

	public double getEnergy() {
		return energy;
	}

	public double getHeading() {
		return heading;
	}

	public String getName() {
		return name;
	}

	public double getVelocity() {
		return velocity;
	}

	public void update(ScannedRobotEvent e) {
		bearing = e.getBearing();
		distance = e.getDistance();
		energy = e.getEnergy();
		heading = e.getHeading();
		name = e.getName();
		velocity = e.getVelocity();
		System.out.println(energy);
		System.out.println(name);
		System.out.println(distance);
		System.out.println(velocity);

	}

	public void reset() {
		bearing = 0;
		distance = 0;
		energy = 0;
		heading = 0;
		name = "";
		velocity = 0;
	}

	public boolean none() {
		return true;
	}

}
