package IntoTheDeep.PranavCode.HWandreferences.References;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "OpTestLinSli")
public class JellyOp extends LinearOpMode {

    DcMotor lin1 = null;

    @Override
    public void runOpMode() throws InterruptedException {
        lin1 = hardwareMap.get(DcMotor.class, "fl");

        lin1.setPower(0);

        lin1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lin1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lin1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lin1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lin1.setTargetPosition(976);
        
        waitForStart();

        lin1.setPower(0.25);

        while (opModeIsActive() && lin1.isBusy()) {
            idle();
        }

        lin1.setPower(-0.25);

        lin1.setPower(0);
        
    }
}
