package SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "Sammy_motor_thingy_lin_no_encoder_:)")
public class lineartut extends LinearOpMode {
    DcMotor WEEWOOO;
    public void runOpMode() throws InterruptedException {
        WEEWOOO= hardwareMap.get(DcMotor.class, "motor1");




        waitForStart();
        WEEWOOO.setPower(1);
        sleep(219999999);
        WEEWOOO.setPower(0);

    }
}
