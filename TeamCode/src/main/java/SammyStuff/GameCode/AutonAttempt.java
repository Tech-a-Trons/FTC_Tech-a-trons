package SammyStuff.GameCode;

import SammyStuff.SammyOpVersions.SammyLinOp;

public class AutonAttempt extends SammyLinOp {


    @Override
    public void runOpMode() throws InterruptedException {
    telemetryInit();
    HardwareMap();

    waitForStart();

    }
}
