package PranavCode.RobotCode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Teleop1")

public class Teleop1 extends LinearOpMode {

    DcMotor fl = null;
    DcMotor bl = null;
    DcMotor br = null;
    Servo servo1;

    @Override
    public void runOpMode() throws InterruptedException {

        fl = hardwareMap.get(DcMotor.class, "bl");

//        bl = hardwareMap.get(DcMotor.class, "fl");
//        For Linear Slides
//        br = hardwareMap.get(DcMotor.class, "fr");

        servo1 = hardwareMap.get(Servo.class, "servo1");

        fl.setPower(0);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                fl.setTargetPosition(-960);
                fl.setPower(0.2);
                servo1.setPosition(1);
            }
            if(gamepad1.x) {
                fl.setTargetPosition(-625);
                fl.setPower(0.2);
                servo1.setPosition(0);
            }
            if(gamepad1.b) {
                fl.setTargetPosition(0);
                fl.setPower(0.1);
                servo1.setPosition(0);
            }
            if(gamepad1.y) {
                fl.setTargetPosition(-750);
                fl.setPower(0.05);
                servo1.setPosition(0);
            }
            if(gamepad1.left_bumper) {
                servo1.setPosition(1);
            }
            if(gamepad1.left_bumper) {
                servo1.setPosition(0);
            }
        }
     }
}