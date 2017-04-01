package rdc;
import robocode.*;
import java.awt.*;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Tanzsynfonia - a robot by (Randall)
 */
public class TanzsynfoniaMkII extends AdvancedRobot {

    boolean peek;
    double moveAmount, gunTurnAmt, degree;
    int shotsFired = 0, count = 0;
    String trackName;
	/**
	 * run: Tanzsynfonia's default behavior
	 */
	public void run() {
		setColors(Color.orange,Color.black,Color.black); // body,gun,radar

 		peek = false;
		moveAmount = Math.max(getBattleFieldWidth(),getBattleFieldHeight());
        degree  = 90;

		//setAdjustGunForRobotTurn(true);
		//setAdjustRadarForRobotTurn(true);

		turnLeft(getHeading() % 90);
		ahead(moveAmount);
        peek = true;
		turnGunRight(degree);
		//turnRadarRight(degree);
		turnRight(degree);

		while (true) {
			count++;
			if (count > 10)
				degree *= -1;
			else if (count > 15)
				degree *= -1;
            else if (count > 20) {
                trackName = null;
           //     turnRadarRight(degree);
			}

            peek = true;
            ahead(moveAmount);
			peek = false;
            turnRight(degree);


           // turnGunRight(degree);
        }
}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
//        if(shotsFired == 3){
//            shotsFired = 0;
//            trackName = null;
//            return;
//        }
//
//        if (trackName != null && !e.getName().equals(trackName))
//            return;
//        if (trackName == null)
//            trackName = e.getName();
//
//	    gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));
//        turnGunRight(gunTurnAmt);
//        //turnRadarRight(gunTurnAmt);
//		if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10){
//			fire(Math.min(400 / e.getDistance(), 3));
//            shotsFired++;
//        }
//
//        if(peek)
//            turnRadarRight(degree*2);
//
//		count = 0;
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
//		trackName = e.getName();
//		gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));
//		turnGunRight(gunTurnAmt);
//		fire(Math.min(400 / e.getBearing(), 3));
//        if (e.getBearing() > -90 && e.getBearing() < 90) {
//            moveAmount *= -1;
//            degree *= -1;
//        }
//	}
//
//	public void onHitRobot(HitRobotEvent e) {
//        trackName = e.getName();
//        gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));
//		turnGunRight(gunTurnAmt);
//		//fire(Math.min(400 / e.getBearing(), 3));
//        if (e.getBearing() > -90 && e.getBearing() < 90) {
//            moveAmount *= -1;
//            degree *= -1;
//        }
	}

	public void onRobotDeath(RobotDeathEvent e) {
//		trackName = null;
//		if (getOthers() == 0) {
//			for (int i = 0; i < 50; i++) {
//				turnRight(45);
//                ahead(100);
//				turnLeft(45);
//			}
//		}
	}

	public double normalRelativeAngle(double angle) {
//		if (angle > -180 && angle <= 180)
//			return angle;
//		double fixedAngle = angle;
//		while (fixedAngle <= -180)
//			fixedAngle += 360;
//		while (fixedAngle > 180)
//			fixedAngle -= 360;
		return 0;//fixedAngle;
	}

}

