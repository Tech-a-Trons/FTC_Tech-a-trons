package IntoTheDeep.SammyStuff.Assignments;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "servotest")
public class ControlServo extends LinearOpMode {
    Servo servo_sammy;
    public final static double ClawClose = 0;
    public final static double ClawOpen =  0.2;
    double ClawOperation;

    @Override

    public void runOpMode() throws InterruptedException {
        servo_sammy = hardwareMap.get(Servo.class,"claw");
        waitForStart();
        while (opModeIsActive()) {
            while (gamepad1.left_trigger > 0.005)
                ClawOperation = ClawOpen;

            while(gamepad1.right_trigger>0.005 )
                ClawOperation = ClawClose;
             servo_sammy.setPosition(ClawOperation);

        }
    }
}