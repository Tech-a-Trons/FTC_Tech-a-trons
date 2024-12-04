package General.SammyStuff.GameCode.TestTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "SammyFirstTest")
public class PickUpFromSpikeZoneAndSubmersible extends LinearOpMode {
    DcMotor arm;
    Servo claw;
    @Override
    public void runOpMode() throws InterruptedException {
        arm = hardwareMap.get(DcMotor.class,"arm");
        claw = hardwareMap.get(Servo.class, "claw");
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        while(opModeIsActive()){


           if (gamepad2.b){
              WithOpenClaw();
           }
           if (gamepad2.a){
              withCloseClaw();
           }
           if (gamepad2.right_bumper){
               claw.setPosition(0.60);
           }
           if (gamepad2.left_bumper){
               claw.setPosition(0);
           }
        }
    }
    public void WithOpenClaw(){
            claw.setPosition(0.60);
            arm.setTargetPosition(1000);
            arm.setPower(0.5);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void withCloseClaw(){
        claw.setPosition(0);
        arm.setTargetPosition(500);
        arm.setPower(0.5);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
