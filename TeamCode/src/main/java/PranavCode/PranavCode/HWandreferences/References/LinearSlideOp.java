package PranavCode.PranavCode.HWandreferences.References;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "LinTest! w/2")
public class LinearSlideOp extends LinearOpMode {

    DcMotor fl = null;
    DcMotor fr = null;

    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fl.setTargetPosition(3000);
        fr.setTargetPosition(3000);

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setPower(0);
        fr.setPower(0);

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                fl.setPower(0.25);
                fr.setPower(0.25);
            }

            while (opModeIsActive() && fl.isBusy()) {
                idle();
            }

            while (opModeIsActive() && fr.isBusy()) {
                idle();
            }

            if (gamepad1.b) {
                fl.setPower(-0.25);
                fr.setPower(-0.25);
            }

            fl.setPower(0);
            fr.setPower(0);
        }
    }
}