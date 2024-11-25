package General.PranavCode.RobotCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "PranCode")

public class PranavCode extends LinearOpMode{

    Servo servo1;
    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor bl = null;

    @Override
    public void runOpMode() throws InterruptedException {
        servo1 = hardwareMap.get(Servo.class, "servo1");
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setPower(0);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fr.setPower(0);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        bl.setPower(0);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setTargetPosition(0);

        waitForStart();

        // Pickup
        if (gamepad2.left_bumper) {
            bl.setTargetPosition(-955);
            servo1.setPosition(1);
            wait(50);
            servo1.setPosition(0);
            bl.setTargetPosition(-625);
        }

        // High Basket
        if (gamepad2.right_trigger > 0.25) {
            bl.setTargetPosition(-625);
            fl.setTargetPosition(1110);
            fr.setTargetPosition(1110);
            fl.setPower(0.25);
            fr.setPower(0.25);
            bl.setTargetPosition(-1000);
            servo1.setPosition(1);
            wait(20);
            bl.setTargetPosition(-625);
        }

        // Low Basket
        if (gamepad2.left_trigger > 0.25) {
            bl.setTargetPosition(-625);
            fl.setTargetPosition(670);
            fr.setTargetPosition(670);
            fl.setPower(0.25);
            fr.setPower(0.25);
            bl.setTargetPosition(-1000);
            servo1.setPosition(1);
            wait(20);
            bl.setTargetPosition(-625);
        }

        // Observation zone placing
        if (gamepad2.right_bumper) {
            bl.setTargetPosition(-955);
            servo1.setPosition(1);
        }

        // Slides reset
        if (gamepad2.dpad_down) {
            bl.setTargetPosition(-625);
            servo1.setPosition(0);
            fl.setTargetPosition(0);
            fr.setTargetPosition(0);
        }
    }
}