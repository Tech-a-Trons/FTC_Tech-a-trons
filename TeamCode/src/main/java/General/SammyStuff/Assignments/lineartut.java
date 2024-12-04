package General.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "Sammy_motor_thingy_lin_no_encoder_:)")
public class lineartut extends LinearOpMode {
    DcMotor motor;
    public void runOpMode() throws InterruptedException {
        motor= hardwareMap.get(DcMotor.class, "motor1");




        waitForStart();
        motor.setPower(1);
        sleep(1000);
        motor.setPower(0);

    }
}
