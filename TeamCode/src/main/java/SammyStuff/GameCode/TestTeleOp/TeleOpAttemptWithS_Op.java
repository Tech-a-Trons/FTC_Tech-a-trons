package SammyStuff.GameCode.TestTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import SammyStuff.Functions.S_functions.S_Functions;
import SammyStuff.S_OpVersions.S_LinOp;

public class TeleOpAttemptWithS_Op extends S_LinOp {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareConfig(true);
        telemetryInit();
        imuInit();

        waitForStart();

        MoveChassis();
        while(gamepad2.dpad_up){
            LiftSlidesAndOpenClawSpecimin();
        }
        while (gamepad2.dpad_down){
            DefaultSlideAndClaw();
        }
    }
}
