package rdc;

import robocode.*;

import java.awt.*;

/**
 * Created by randallcrame on 2/28/17.
 */
public class TanzsynfoniaMkIII extends AdvancedRobot {

    private int timeSinceLastScan = 5, dropTarget;
    static double enemyAbsoluteBearing;
    int moveDirection = 1;
    double moveAmount;
    String trackName;

    public void run() {
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        setColors(Color.orange, Color.black, Color.black); // body,gun,radar

        moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());

        turnLeft(getHeading() % 90);
        ahead(moveAmount);

        do {
            dropTarget++;
            if (dropTarget > 20) {
                trackName = null;
            }
            if (trackName == null)
                setBodyColor(Color.RED);
            else
                setBodyColor(Color.GREEN);
            doScanner();
            execute();
        } while (true);
    }

    public void doScanner() {
        timeSinceLastScan++;

        double radarOffset = Double.POSITIVE_INFINITY;
        if (timeSinceLastScan < 3) {
            radarOffset = robocode.util.Utils.normalRelativeAngle(getRadarHeadingRadians()
                    - enemyAbsoluteBearing);
            radarOffset += sign(radarOffset) * 0.02;
        }

        setTurnRadarLeftRadians(radarOffset);
        setTurnGunLeftRadians(getGunHeadingRadians() - getRadarHeadingRadians());
    }

    int sign(double v) {
        return v > 0 ? 1 : -1;
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (trackName != null && !e.getName().equals(trackName))
            return;
        if (trackName == null || e.getDistance() <= 250)
            trackName = e.getName();

        enemyAbsoluteBearing = getHeadingRadians() + e.getBearingRadians();
        timeSinceLastScan = 0;

        if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10 && e.getDistance() <= 400){
			fire(Math.min(400 / e.getDistance(), 3));
			dropTarget = 0;
        }
        setAhead(100 * moveDirection);
    }

    public void onHitRobot(HitRobotEvent e){
        if (e.getBearing() > -90 && e.getBearing() < 90) {
            moveDirection *= -1;
        }
        trackName = e.getName();
		doScanner();
    }

    public void onBulletHit(BulletHitEvent e){
        if(getEnergy() > 30)
            moveDirection *= -1;
        trackName = e.getName();
        doScanner();
    }

    public void onHitWall(HitWallEvent e){
        turnRight(90);
        doScanner();
    }
}
