package IntoTheDeep.PranavCode.HWandreferences.References;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "LinOp")

public class fourstageslides extends LinearOpMode {
    DcMotor fl = null;

    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.get(DcMotor.class, "fl");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setPower(0);

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fl.setTargetPosition(976);

        waitForStart();

        fl.setPower(0.25);
        while (opModeIsActive()) {
            idle();
        }

        fl.setPower(-0.25);

        fl.setPower(0);
    }
}