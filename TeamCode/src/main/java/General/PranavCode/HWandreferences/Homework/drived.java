package General.PranavCode.HWandreferences.Homework;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "drivemycar7b")
public class drived extends LinearOpMode {
    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor bl = null;
    DcMotor br = null;

    private int LP;
    private int RP;

    @Override
    public void runOpMode() throws InterruptedException {


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

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LP = 0;
        RP = 0;

        waitForStart();

        drive (10, 10, 0.25);
        drive (10, -10, 0.5);
        drive (-10, 10, 0.75);
        drive (-10, -10, 1);

        // TRY 1
//        drive (10, 10, 0.75);

        // TRY 2
        drive(10, 10, 1);
        drive (-10, 10, 0.5);
        drive (10, 10, 0.25);

        // TRY 3

    }

    private void drive (int LT, int RT, double spd) {
        LP = LP + LT;
        RP = RP + RT;

        fl.setTargetPosition(LP);
        fr.setTargetPosition(RP);
        bl.setTargetPosition(LP);
        br.setTargetPosition(RP);

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fl.setPower(spd);
        fr.setPower(spd);
        bl.setPower(spd);
        br.setPower(spd);

        while (opModeIsActive() && fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy()) {
            idle();
        }
    }
}