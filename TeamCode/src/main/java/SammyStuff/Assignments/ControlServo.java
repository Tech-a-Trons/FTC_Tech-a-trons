package SammyStuff.Assignments;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;


import SammyStuff.SammyOpVersions.SammyLinOp;
@TeleOp (name = "servotest")
public class ControlServo extends SammyLinOp {
    Servo servo_sammy;

    @Override

    public void runOpMode() throws InterruptedException {
        servo_sammy = hardwareMap.get(Servo.class,"claw");
        waitForStart();
        while (opModeIsActive()) {
            while (gamepad1.left_trigger > 0) {
                servo_sammy.setPosition(gamepad1.left_stick_y);
            }
            while(gamepad1.right_trigger>0 ){
                servo_sammy.setPosition(0);
            }
        }
    }
}