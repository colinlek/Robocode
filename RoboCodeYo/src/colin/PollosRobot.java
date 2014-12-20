package colin;

import robocode.*;
import java.awt.Color;

public class PollosRobot extends AdvancedRobot {

       /**
       * run: Pollo's default behavior
       */
       public void run() {

       
             // body,gun,radar
             setColors(Color.yellow,Color.blue,Color.gray,Color.red,Color.green); //
             

             // Robot main loop
             while (true) {
                    double distance = Math.random() * 300;
                    double angle = Math.random() * 45;
                    turnRight(angle);
                    ahead(distance);
                    ahead(100);
                    turnGunRight(90);
                    back(100);
                    turnGunRight(90);
             }
       }

       /**
       * onScannedRobot: What to do when you see another robot
       */
       public void onScannedRobot(ScannedRobotEvent e) {
             // Replace the next line with any behavior you would like
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
