package General.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "5a&5b")
public class _5aAnd5b extends LinearOpMode {

    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor bl = null;
    DcMotor br = null;


    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()) {
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
            } else if (gamepad1.right_stick_y != 0) {

                fl.setPower(-gamepad1.right_stick_y);
                bl.setPower(-gamepad1.right_stick_y);
                fr.setPower(-gamepad1.right_stick_y);
                br.setPower(-gamepad1.right_stick_y);
            } else if (gamepad1.right_stick_x != 0) {

                fl.setPower(gamepad1.right_stick_x);
                bl.setPower(-gamepad1.right_stick_x);
                fr.setPower(-gamepad1.right_stick_x);
                br.setPower(gamepad1.right_stick_x);
            } else {

                fl.setPower(0);
                bl.setPower(0);
                fr.setPower(0);
                br.setPower(0);
            }
        }
    }
}