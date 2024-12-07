package General.IshaanStuff;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "First_Teleop")
public class servocontrol2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo servo1 = hardwareMap.get(Servo.class,"servo1");
        waitForStart();



        while(opModeIsActive()){
            if(gamepad1.a){
                servo1.setPosition(-1);
            }
            if(gamepad1.b){
                servo1.setPosition(1);
            }
            while(gamepad1.atRest()){
                servo1.setPosition(0);
            }
        }
    }
}