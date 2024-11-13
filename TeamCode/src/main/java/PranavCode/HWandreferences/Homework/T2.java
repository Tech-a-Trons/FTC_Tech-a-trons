package PranavCode.HWandreferences.Homework;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous (name = "Task 2, By Pranpran")

public class T2 extends LinearOpMode {
    DcMotor umm;
    @Override
    public void runOpMode() throws InterruptedException {
        umm = hardwareMap.get(DcMotor.class, "motor1");
        umm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        umm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        umm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForStart();
        if (gamepad1.right_trigger > 0.5) {
            umm.setPower(0.8);
            umm.setTargetPosition(500);
        } else {
            umm.setPower(-0.5);
            umm.setTargetPosition(550);
        }
    }
}