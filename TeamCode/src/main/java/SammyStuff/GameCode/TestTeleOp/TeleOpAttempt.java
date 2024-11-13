package SammyStuff.GameCode.TestTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import SammyStuff.Functions.S_functions.S_Functions;

public class TeleOpAttempt extends LinearOpMode {
    private  S_Functions functions = new S_Functions(this);

    @Override
    public void runOpMode() throws InterruptedException {
        functions.HardwareConfig(true);
        functions.telemetryInit();
        functions.imuInit();
        waitForStart();
        while (opModeIsActive()) {
            functions.MoveChassis();
            if (gamepad2.dpad_up) {
                functions.MoveLsUp();
            }
            if (gamepad2.dpad_down) {
                functions.MoveLsDown();
            }
            if (gamepad2.left_bumper){
                functions.openClaw();
            }
            if (gamepad2.right_bumper){
                functions.closeClaw();
            }
        }
    }

}
