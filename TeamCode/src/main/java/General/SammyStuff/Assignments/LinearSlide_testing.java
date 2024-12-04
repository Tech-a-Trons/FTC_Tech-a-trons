package General.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "SlideTest")
public class LinearSlide_testing extends LinearOpMode {
    // Declare or Define the hardware here....
    DcMotor LS_motor = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        LS_motor = hardwareMap.get(DcMotor.class, "fl");
        LS_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LS_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LS_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LS_motor.setTargetPosition(2000);
        LS_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForStart();
        LS_motor.setPower(0.4);
        while (opModeIsActive() && LS_motor.isBusy()) {
            idle();
        }
        sleep(1000);
        LS_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LS_motor.setTargetPosition(0);
        LS_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive() && LS_motor.isBusy()){
        idle();
        }
        LS_motor.setPower(0);
    }
}
