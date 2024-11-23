package General.SammyStuff.GameCode.TestTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name ="lsTest1")
public class LsTest1 extends LinearOpMode {


    DcMotor fslsLeft;// four stage linear slide left
    DcMotor fslsRight;// four stage linear slide right
    int LsPos = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        fslsLeft = hardwareMap.get(DcMotor.class, "lsl");
        fslsRight = hardwareMap.get(DcMotor.class, "lsr");
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad2.dpad_up) {
                MoveLsUp();
            }
            if (gamepad2.dpad_down) {
                MoveLsDown();
            }
        }
    }

    public void MoveBothfsls(int TargetPos, double pwr, String Direction) {
        if (Direction.equals("up") ) {
            fslsLeft.setTargetPosition(-TargetPos);
            fslsRight.setTargetPosition(-TargetPos);
            fslsRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fslsLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fslsRight.setPower(pwr);
            fslsLeft.setPower(pwr);
            while (opModeIsActive() && fslsLeft.isBusy() && fslsRight.isBusy()) {
               idle();
            }
            fslsRight.setPower(0);
            fslsLeft.setPower(0);
            sleep(500);
        } else if (Direction.equals("down") ) {
            fslsLeft.setTargetPosition(TargetPos);
            fslsRight.setTargetPosition(TargetPos);
            fslsLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fslsRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fslsLeft.setPower(pwr);
            fslsRight.setPower(pwr);
            while (opModeIsActive() && fslsLeft.isBusy() && fslsRight.isBusy()) {
                idle();
            }
            fslsRight.setPower(0);
            fslsLeft.setPower(0);
            sleep(500);
        } else {

            fslsRight.setPower(0);
            fslsLeft.setPower(0);
        }
    }


    public void MoveLsUp(){

        if(LsPos >= 0 && LsPos <=4000) {
            LsPos = LsPos +1000;
            MoveBothfsls(LsPos, 0.3, "up");
            telemetry.addData("Ls stage", LsPos /1000);
            telemetry.update();
        }else{
            idle();
        }
    }
    public void MoveLsDown(){
        if (LsPos >=0&& LsPos <=4000) {
            LsPos = LsPos - 1000;
            MoveBothfsls(LsPos, 0.3, "down");
            telemetry.addData("Ls stage", LsPos /1000);
            telemetry.update();
        }else{
            idle();
        }
    }
}
