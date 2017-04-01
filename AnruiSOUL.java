package rdc;
import robocode.*;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Tanzsynfonia - a robot by (Randall)
 */
public class Tanzsynfonia extends Robot
{
		//setColors(Color.red,Color.black,Color.black); // body,gun,radar
		
		boolean peek;	
		double moveAmount;
		int count = 0;			
		double gunTurnAmt;		
		String trackName;	
	/**
	 * run: Tanzsynfonia's default behavior
	 */
	public void run() {
		peek = false;	
		moveAmount = Math.max(getBattleFieldWidth(),getBattleFieldHeight());	
		trackName = null;					
		setAdjustGunForRobotTurn(true);		
		gunTurnAmt = normalRelativeAngle((moveAmount/2) + (getHeading() - getRadarHeading()));
		
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		peek = true;
		turnGunRight(gunTurnAmt);
		turnRight(90);
		
		while (true) {
			turnGunRight(gunTurnAmt);
			count++;
			if (count > 2)					
				gunTurnAmt = -10;
			if (count > 4)
				gunTurnAmt = 10;
			if (count > 8)
				trackName = null;
				
			peek = true;
			ahead(moveAmount);
			peek = false;
			turnRight(90);

		}
}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		if (trackName != null && !e.getName().equals(trackName))
			return;
    	if (trackName == null) 
			trackName = e.getName();
		
		count = 0;
		gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		
		fire(2);
		if (peek)
			scan();
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		if (e.getBearing() > -90 && e.getBearing() < 90)
			back(100);
		else
			ahead(100);
	}
	
	public void onHitRobot(HitRobotEvent e) {
		trackName = e.getName();
		gunTurnAmt = normalRelativeAngle(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		fire(3);
		setInterruptible(true);
	}
	
	public void onRobotDeath(RobotDeathEvent e) {
		if (getOthers() == 0) {
			for (int i = 0; i < 50; i++) {
				turnRight(45);
				turnLeft(85);
			}
		}
	}
	
	public double normalRelativeAngle(double angle) {
		if (angle > -180 && angle <= 180)
			return angle;
		double fixedAngle = angle;
		while (fixedAngle <= -180)
			fixedAngle += 360;
		while (fixedAngle > 180)
			fixedAngle -= 360;
		return fixedAngle;
	}

}
