package SammyStuff.Assignments;

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
        // Initialize Hardware Mapping here...
        LS_motor = hardwareMap.get(DcMotor.class, "fl");

        // Set Motor RUN mode: RUN_USING_ENCODER
        LS_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Reset the encoder value to zero (reset encoder counts kept by motors).
        LS_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LS_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // set motor to run forward for 1000 encoder counts.
        LS_motor.setTargetPosition(2000);

        // set motor to run to target encoder position and stop with brakes on.
        LS_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //  Wait until user hit the START button on the Driver Station App.
        waitForStart();

        // set motor power to 0.2
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

        // set motor power to 0
        LS_motor.setPower(0);


    }
}
