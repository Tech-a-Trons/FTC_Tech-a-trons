package General.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "assignment")
public class assignment extends LinearOpMode {
   DcMotor fl = null;
   DcMotor fr = null;
   DcMotor br = null;
   DcMotor bl = null;

    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr= hardwareMap.get(DcMotor.class, "fr");
        bl= hardwareMap.get(DcMotor.class, "bl");
        br= hardwareMap.get(DcMotor.class, "br");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();
        // sraight
        fl.setPower(0.2);
        fr.setPower(0.2);
        bl.setPower(0.2);
        br.setPower(0.2);
        sleep(2000);
       //turn clockwise
        fl.setPower(0.35);
        fr.setPower(-0.35);
        bl.setPower(0.35);
        br.setPower(-0.35);
        sleep(1000);
        //straight
        fl.setPower(0.2);
        fr.setPower(0.2);
        bl.setPower(0.2);
        br.setPower(0.2);
        sleep(2000);
        // turn counter clockwise
        fl.setPower(-0.35);
        fr.setPower(0.35);
        bl.setPower(-0.35);
        br.setPower(0.35);
        sleep(1000);
       //straight
        fl.setPower(0.2);
        fr.setPower(0.2);
        bl.setPower(0.2);
        br.setPower(0.2);
        sleep(2000);
        // turn counter clockwise
        fl.setPower(-0.35);
        fr.setPower(0.35);
        bl.setPower(-0.35);
        br.setPower(0.35);
        sleep(1000);
        //straight
        fl.setPower(0.2);
        fr.setPower(0.2);
        bl.setPower(0.2);
        br.setPower(0.2);
        sleep(2000);
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }
}
