package General.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class servo extends LinearOpMode {
    Servo claw;
    @Override
    public void runOpMode() throws InterruptedException {
        claw = hardwareMap.get(Servo.class,"claw");
        waitForStart();

        while(opModeIsActive()){
            if(gamepad2.left_bumper){
                //Close Claw
                claw.setPosition(0);
            }
            if (gamepad2.right_bumper){
                //Open Claw
                claw.setPosition(0.75);
            }

        }
    }
}
