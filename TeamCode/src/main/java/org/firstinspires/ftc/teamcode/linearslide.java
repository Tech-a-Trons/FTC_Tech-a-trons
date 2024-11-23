package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "LinearSlide_testing", group = "TATrons")
public class linearslide extends LinearOpMode {
    // Declare or Define the hardware here....
    DcMotor LS_motor = null;
    DcMotor LS_motor2 = null;
    double LS_speed = 0.4;
    double LS_speed2 = 0.4;
    double LS_STOP = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Initialize Hardware Mapping here...
        LS_motor = hardwareMap.get(DcMotor.class, "fl");
        LS_motor2 = hardwareMap.get(DcMotor.class, "fr");

        // Set Motor RUN mode: RUN_USING_ENCODER
        LS_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LS_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LS_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        LS_motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LS_motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LS_motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        //  Wait until user hit the START button on the Driver Station App.
        waitForStart();


        LS_motor.setTargetPosition(-800);
        LS_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LS_motor2.setTargetPosition(800);
        LS_motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                LS_motor.setPower(LS_speed);
                LS_motor2.setPower(LS_speed2);

            while (opModeIsActive() && LS_motor.isBusy() && LS_motor2.isBusy()) {
                idle();
                }

            sleep(2000);

            if(gamepad1.dpad_down) {
                // Slide move downward direction :
                LS_motor.setTargetPosition(0);
                LS_motor.setPower(LS_speed);
                LS_motor.setPower(LS_STOP);

                // Slide move downward direction :
                LS_motor2.setTargetPosition(0);
                LS_motor2.setPower(LS_speed2);
                LS_motor2.setPower(LS_STOP);
            }

        }
    }

