package General.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Autonomous( name ="7a" )
public class _7a extends LinearOpMode {
    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor br = null;
    DcMotor bl = null;

    @Override


    public void runOpMode() throws InterruptedException {

        double drive;
        double turn;
        double strafe;
        double flpwr, frpwr, blpwr, brpwr;
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        while (opModeIsActive()) {
            drive = -gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;
            strafe = gamepad1.left_stick_x;


            flpwr = drive + turn + strafe;
            frpwr = drive - turn - strafe;
            blpwr = drive + turn - strafe;
            brpwr = drive - turn + strafe;

            double maxf = Math.max((Math.abs(flpwr)), (Math.abs(frpwr)));
            double maxb = Math.max((Math.abs(blpwr)), (Math.abs(brpwr)));
            double maxfb_pwr = Math.max((Math.abs(maxf)), (Math.abs(maxb)));
            if (maxfb_pwr > 1) {
                flpwr = flpwr / maxfb_pwr;
                frpwr = frpwr / maxfb_pwr;
                blpwr = blpwr / maxfb_pwr;
                brpwr = brpwr / maxfb_pwr;
                fl.setPower(flpwr);
                fr.setPower(frpwr);
                bl.setPower(blpwr);
                br.setPower(brpwr);

            }
        }
    }
}




