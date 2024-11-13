package PranavCode.HWandreferences.Homework;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "4")

public class TASK4 extends OpMode {

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

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger > 0.5){
            fl.setPower(0.5);
            fr.setPower(0.5);
            bl.setPower(0.5);
            br.setPower(0.5);
        } else {
            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
        }
    }
}