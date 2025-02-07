package IntoTheDeep.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "teleChallenge")
public class challengeTele1 extends OpMode {
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

    @Override
    public void loop() {

        if (gamepad1.right_trigger> 0.05){
            fl.setPower(0.5);
            fr.setPower(0.5);
            br.setPower(0.5);
            bl.setPower(0.5);
        }else{
            fl.setPower(0);
            fr.setPower(0);
            br.setPower(0);
            bl.setPower(0);
        }
        if (gamepad1.left_trigger> 0.05){
            fl.setPower(-0.75);
            fr.setPower(-0.75);
            br.setPower(-0.75);
            bl.setPower(-0.75);
        }else{
            fl.setPower(0);
            fr.setPower(0);
            br.setPower(0);
            bl.setPower(0);
        }
    }
}
