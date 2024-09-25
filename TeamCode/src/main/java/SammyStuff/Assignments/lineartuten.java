package SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name ="sammy_motor_thingy_lin_encoder_:)")
public class lineartuten extends LinearOpMode {
        DcMotor WEWOOEN;
    @Override
    public void runOpMode() throws InterruptedException {

            WEWOOEN= hardwareMap.get(DcMotor.class, "motor1");

            WEWOOEN.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            WEWOOEN.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            WEWOOEN.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            waitForStart();


            WEWOOEN.setPower(gamepad1.left_trigger);
            WEWOOEN.setPower(0.1);
            WEWOOEN.setTargetPosition(3600);
    }
}
