package PranavCode.PranavCode.HWandreferences.Homework;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous (name = "BetterPranavDrive")

public class T3 extends OpMode {
    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor bl = null;
    DcMotor br = null;

    @Override
    public void init() {
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
    }

    @Override
    public void loop() {
        long st = System.currentTimeMillis();
        long ti = 500;
        long et = st + ti;
        while (System.currentTimeMillis() < et) {
            fl.setPower(1);
            fr.setPower(1);
            bl.setPower(1);
            br.setPower(1);
        }
        long st2 = System.currentTimeMillis();
        long ti2 = 500;
        long et2 = st2 + ti2;
        while (System.currentTimeMillis() < et2) {
            fl.setPower(1);
            fr.setPower(-1);
            bl.setPower(-1);
            br.setPower(1);
        }
        long st3 = System.currentTimeMillis();
        long ti3 = 500;
        long et3 = st3 + ti3;
        while (System.currentTimeMillis() < et3) {
            fl.setPower(-1);
            fr.setPower(-1);
            bl.setPower(-1);
            br.setPower(-1);
        }
        long st4 = System.currentTimeMillis();
        long ti4 = 500;
        long et4 = st4 + ti4;
        while (System.currentTimeMillis() < et4) {
            fl.setPower(-1);
            fr.setPower(1);
            bl.setPower(1);
            br.setPower(-1);
        }
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }
}