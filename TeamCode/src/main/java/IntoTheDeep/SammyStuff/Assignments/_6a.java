package IntoTheDeep.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "6a", group = "teleOp")
public class _6a extends LinearOpMode {
    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor br = null;
    DcMotor bl = null;

    @Override


    public void runOpMode() throws InterruptedException {

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
        while(opModeIsActive()) {
            if (gamepad1.left_trigger > 0.005) {
                fl.setPower(0.1);
                fl.setTargetPosition(500);
                fr.setPower(0.1);
                fr.setTargetPosition(500);
                bl.setPower(0.1);
                bl.setTargetPosition(500);
                br.setPower(0.1);
                br.setTargetPosition(500);
            }
            if (gamepad1.right_trigger > 0.005) {
                fl.setPower(0.1);
                fl.setTargetPosition(1000);
                fr.setPower(0.1);
                fr.setTargetPosition(1000);
                bl.setPower(0.1);
                bl.setTargetPosition(1000);
                br.setPower(0.1);
                br.setTargetPosition(1000);
            }
        }
    }
}
