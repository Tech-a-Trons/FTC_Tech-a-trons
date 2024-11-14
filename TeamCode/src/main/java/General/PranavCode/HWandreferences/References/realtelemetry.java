package General.PranavCode.HWandreferences.References;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "oh_boy")
public class realtelemetry extends LinearOpMode {
    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor bl = null;
    DcMotor br = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Init_Success");
        telemetry.setMsTransmissionInterval(100);
        telemetry.update();

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

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.left_stick_x != 0) {
                fl.setPower(gamepad1.left_stick_x);
                fr.setPower(-gamepad1.left_stick_x);
                bl.setPower(-gamepad1.left_stick_x);
                br.setPower(gamepad1.left_stick_x);
            } else {
                fl.setPower(0);
                fr.setPower(0);
                bl.setPower(0);
                br.setPower(0);
            }

            if (gamepad1.left_stick_y != 0) {
                fl.setPower(gamepad1.left_stick_y * (-1));
                fr.setPower(gamepad1.left_stick_y * (-1));
                bl.setPower(gamepad1.left_stick_y * (-1));
                br.setPower(gamepad1.left_stick_y * (-1));
            } else {
                fl.setPower(0);
                fr.setPower(0);
                bl.setPower(0);
                br.setPower(0);
            }

            telemetry.addData("fl_position", fl.getCurrentPosition());
            telemetry.addData("fr_position", fr.getCurrentPosition());
            telemetry.addData("bl_position", bl.getCurrentPosition());
            telemetry.addData("br_position", br.getCurrentPosition());

            telemetry.addData("fl_speed", "%.2f", fl.getPower());
            telemetry.addData("fr_speed", "%.2f", fr.getPower());
            telemetry.addData("bl_speed", "%.2f", bl.getPower());
            telemetry.addData("br_speed", "%.2f", br.getPower());

            telemetry.update();
        }
    }
}