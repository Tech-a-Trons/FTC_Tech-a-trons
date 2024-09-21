package org.firstinspires.ftc.MyCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "wewoo")
public class challenge2 extends OpMode {
    DcMotor frontleft = null;
    DcMotor frontright = null;
    DcMotor backleft = null;
    DcMotor backright = null;

    @Override
    public void init() {
        frontleft = hardwareMap.get(DcMotor.class, "fl");
        frontright = hardwareMap.get(DcMotor.class, "fr");
        backleft = hardwareMap.get(DcMotor.class, "bl");
        backright = hardwareMap.get(DcMotor.class, "br");
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        long st1 = System.currentTimeMillis();
        long ti1 = 500;
        long et1 = st1 + ti1;
        while (System.currentTimeMillis() < et1) {
            backleft.setPower(1);
            frontleft.setPower(1);
            backright.setPower(1);
            backleft.setPower(1);
        }
        long st2 = System.currentTimeMillis();
        long ti2 = 500;
        long et2 = st2 + ti2;
        while (System.currentTimeMillis() < et2) {
            backleft.setPower(-1);
            frontleft.setPower(1);
            frontright.setPower(-1);
            backright.setPower(1);

        }
         long st3 = System.currentTimeMillis();
         long ti3 = 500;
         long et3 = st3 + ti3;
         while (System.currentTimeMillis() < et3) {
            backleft.setPower(-1);
            frontleft.setPower(-1);
            frontright.setPower(-1);
            backright.setPower(-1);
        }
            long st4 = System.currentTimeMillis();

            long ti4 = 500;
            long et4 = st4 + ti4;
            while (System.currentTimeMillis() < et4) {
            backleft.setPower(1);
            frontleft.setPower(-1);
            frontright.setPower(1);
            backright.setPower(-1);
        }
    }
}
