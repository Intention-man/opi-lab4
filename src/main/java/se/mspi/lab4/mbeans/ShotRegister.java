package se.mspi.lab4.mbeans;

import se.mspi.lab4.Shot;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class ShotRegister extends NotificationBroadcasterSupport implements ShotRegisterMBean {
    private long countOfAllShots = 0;
    private long countOfSuccessfulShots = 0;

    @Override
    public void addShot(Shot shot) {
        if (!shot.isInside()) {
            Notification notification = new Notification(
                    "a point outside the area ", this, countOfAllShots,
                    System.currentTimeMillis(),
                    String.format("Точка с координатами (%.5f, %.5f) не попала в область с радиусом (%.5f)",
                            shot.getX(), shot.getY(), shot.getR())
            );
            this.sendNotification(notification);
        }

        countOfAllShots++;
//        if (countOfAllShots % 15 == 0) {
//            Notification notification = new Notification(
//                    "countOfAllShotAreIsMultipleOf15", this, countOfAllShots,
//                    System.currentTimeMillis(), String.format("Количество (%d) попаданий кратно 15", countOfAllShots)
//            );
//            this.sendNotification(notification);
//        }
//
//        if (shot.isInside()) {
//            countOfSuccessfulShots++;
//        }
    }

}
