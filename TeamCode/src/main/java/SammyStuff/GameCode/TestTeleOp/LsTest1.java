package SammyStuff.GameCode.TestTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class LsTest1 extends LinearOpMode {


    DcMotor fsls1;// four stage linear slide 1
    DcMotor fsls2;// four stage linear slide 2
    int LsDefaultPos = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        fsls1 = hardwareMap.get(DcMotor.class, "fl");
        fsls2 = hardwareMap.get(DcMotor.class, "fr");
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

    public void Movefsls1(int TargetPos, double pwr, String Direction) {
        if (Direction == "up") {
            fsls1.setTargetPosition(-TargetPos);
            fsls1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fsls1.setPower(pwr);
            while (opModeIsActive() && fsls1.isBusy()) {
                idle();
            }
            fsls1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls1.setPower(0);
        } else if (Direction == "down") {
            fsls1.setTargetPosition(TargetPos);
            fsls1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fsls1.setPower(pwr);
            while (opModeIsActive() && fsls1.isBusy()) {
                idle();
            }
            fsls1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls1.setPower(0);
        } else {
            fsls1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls1.setPower(0);
        }
    }


    public void Movefsls2(int TargetPos, double pwr, String Direction) {
        if (Direction == "up") {
            fsls2.setTargetPosition(-TargetPos);
            fsls2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fsls2.setPower(pwr);
            while (opModeIsActive() && fsls2.isBusy()) {
                idle();
            }
            fsls2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls2.setPower(0);
        } else if (Direction == "down") {
            fsls2.setTargetPosition(TargetPos);
            fsls2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fsls2.setPower(pwr);
            while (opModeIsActive() && fsls2.isBusy()) {
               idle();
            }
            fsls2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls2.setPower(0);
        } else {
            fsls2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls2.setPower(0);
        }
    }



    public void MoveLsUp(){

        if(LsDefaultPos >= 0 && LsDefaultPos <=4000) {
            LsDefaultPos = LsDefaultPos+1000;
            Movefsls1(LsDefaultPos, 0.3, "up");
            Movefsls2(LsDefaultPos, 0.3, "up");
            telemetry.addData("Ls stage",LsDefaultPos/1000);
            telemetry.update();
        }else{
            idle();
        }
    }
    public void MoveLsDown(){
        if (LsDefaultPos>=0&&LsDefaultPos<=4000) {
            LsDefaultPos = LsDefaultPos - 1000;
            Movefsls1(LsDefaultPos, 0.3, "down");
            Movefsls2(LsDefaultPos, 0.3, "down");
            telemetry.addData("Ls stage",LsDefaultPos/1000);
            telemetry.update();
        }else{
            idle();
        }
    }
}
