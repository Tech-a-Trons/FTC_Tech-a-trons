package IntoTheDeep.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "telelin")
public class teleloplin extends LinearOpMode {
    DcMotor FrontLeft = null;
    DcMotor FrontRight = null;
    DcMotor BackLeft = null;
    DcMotor BackRight = null;

    @Override
    public void runOpMode() throws InterruptedException {
        FrontLeft = hardwareMap.get(DcMotor.class, "fl");
        BackLeft = hardwareMap.get(DcMotor.class, "bl");
        BackRight = hardwareMap.get(DcMotor.class, "bl");
        FrontRight = hardwareMap.get(DcMotor.class, "fr");

        FrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive())

            if (gamepad1.left_stick_x < 0.05) {
                FrontLeft.setPower(0.5);
                FrontRight.setPower(-0.5);
                BackLeft.setPower(-0.5);
                BackRight.setPower(0.5);
            } else {
                FrontLeft.setPower(0);
                FrontRight.setPower(0);
                BackLeft.setPower(0);
                BackRight.setPower(0);
            }
        if (gamepad1.left_stick_x < -0.05) {
            FrontLeft.setPower(-0.5);
            FrontRight.setPower(0.5);
            BackLeft.setPower(0.5);
            BackRight.setPower(-0.5);
        } else {
            FrontLeft.setPower(0);
            FrontRight.setPower(0);
            BackLeft.setPower(0);
            BackRight.setPower(0);
        }
        if (gamepad1.left_stick_y < 0.05) {
            FrontLeft.setPower(-0.25);
            FrontRight.setPower(-0.25);
            BackLeft.setPower(-0.25);
            BackRight.setPower(-0.25);
        } else {
            FrontLeft.setPower(0);
            FrontRight.setPower(0);
            BackLeft.setPower(0);
            BackRight.setPower(0);
        }
        if (gamepad1.left_stick_y < -0.05) {
            FrontLeft.setPower(0.25);
            FrontRight.setPower(0.25);
            BackLeft.setPower(0.25);
            BackRight.setPower(0.25);
        } else {
            FrontLeft.setPower(0);
            FrontRight.setPower(0);
            BackLeft.setPower(0);
            BackRight.setPower(0);
            }
        }
    }

