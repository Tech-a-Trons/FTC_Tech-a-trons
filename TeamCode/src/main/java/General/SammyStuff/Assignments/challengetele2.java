package General.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp(name= "challengetele2TELEOP RIGHT NOT AUTONOMOUS CUS IM STUPID")
public class challengetele2 extends OpMode {
    DcMotor fr = null;
    DcMotor fl = null;
    DcMotor br = null;
    DcMotor bl = null;
    @Override
    public void init() {
        fr = hardwareMap.get(DcMotor.class,"fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {

    if (gamepad1.right_bumper) {
        fl.setPower(0.6);
        fr.setPower(0.6);
        br.setPower(0.6);
        bl.setPower(0.6);
    }else{
        fl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        bl.setPower(0);
    }
    if (gamepad1.left_bumper){
        fl.setPower(-0.35);
        fr.setPower(-0.35);
        br.setPower(-0.35);
        bl.setPower(-0.35);
    }else{
        fl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        bl.setPower(0);
         }
    }
}
