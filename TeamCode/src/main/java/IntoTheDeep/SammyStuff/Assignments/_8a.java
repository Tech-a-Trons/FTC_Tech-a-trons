package IntoTheDeep.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "8a")
public class _8a extends LinearOpMode {
    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor br = null;
    DcMotor bl = null;

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareMap();

        reverse();

        telemetryInit();

        DontUseEncoders();


        waitForStart();
        while (opModeIsActive()) {
            telemetryAfterInit();

        }
    }

    public void HardwareMap() {
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
    }

    public void reverse() {
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void telemetryInit() {
        telemetry.addData("status", "Initialize");
        telemetry.setMsTransmissionInterval(100);
        telemetry.update();
    }

    public void DontUseEncoders() {
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void telemetryAfterInit() {
        telemetry.addLine("telemetry activated");
        telemetry.addData("Flpos", "*%0.4f", fl.getCurrentPosition());
        telemetry.addData("Frpos", "*%0.4f", fr.getCurrentPosition());
        telemetry.addData("blpos", "*%0.4f", bl.getCurrentPosition());
        telemetry.addData("brpos", "*%0.4f", br.getCurrentPosition());

        telemetry.addData("Flpwr", "*%0.4f", fl.getPower());
        telemetry.addData("Frpwr", "*%0.4f", fr.getPower());
        telemetry.addData("blpwr", "*%0.4f", bl.getPower());
        telemetry.addData("brpwr", "*%0.4f", br.getPower());
        telemetry.update();

    }

    public void teleOp() {
        if (gamepad1.left_stick_y != 0) {

            fl.setPower(-gamepad1.left_stick_y);
            bl.setPower(-gamepad1.left_stick_y);
            fr.setPower(-gamepad1.left_stick_y);
            br.setPower(-gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_x != 0) {

            fl.setPower(gamepad1.left_stick_x);
            bl.setPower(-gamepad1.left_stick_x);
            fr.setPower(-gamepad1.left_stick_x);
            br.setPower(gamepad1.left_stick_x);
        } else {
            fl.setPower(0);
            bl.setPower(0);
            fr.setPower(0);
            br.setPower(0);
        }
    }
}
