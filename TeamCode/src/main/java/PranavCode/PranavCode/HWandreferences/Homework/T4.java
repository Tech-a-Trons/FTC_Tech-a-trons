package PranavCode.PranavCode.HWandreferences.Homework;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous (name = "Ran2pran")
public class T4 extends LinearOpMode {
    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor bl = null;
    DcMotor br = null;


    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.FORWARD);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        fl.setPower(0.5);
        fr.setPower(0.5);
        bl.setPower(0.5);
        br.setPower(0.5);
        sleep(1000);

        fl.setPower(0.5);
        fr.setPower(-0.5);
        bl.setPower(0.5);
        br.setPower(-0.5);
        sleep(500);


        fl.setPower(0.5);
        fr.setPower(0.5);
        bl.setPower(0.5);
        br.setPower(0.5);
        sleep(1000);

        fl.setPower(-0.5);
        fr.setPower(0.5);
        bl.setPower(-0.5);
        br.setPower(0.5);
        sleep(500);

        fl.setPower(0.5);
        fr.setPower(0.5);
        bl.setPower(0.5);
        br.setPower(0.5);
        sleep(1000);

        fl.setPower(-0.5);
        fr.setPower(0.5);
        bl.setPower(-0.5);
        br.setPower(0.5);
        sleep(500);

        fl.setPower(0.5);
        fr.setPower(0.5);
        bl.setPower(0.5);
        br.setPower(0.5);
        sleep(1000);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }
}