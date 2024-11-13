package PranavCode.PranavCode.HWandreferences.References;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

// VERY IMPORTANT, READ SLOWLY AND ABSORB EVERYTHING!!!

@Autonomous (name = "Pranpran+_is+_da+_best+_forever")

public class LinearOp_Encoder_EXAMPLE extends LinearOpMode {

    DcMotor smile;

    @Override
    public void runOpMode() throws InterruptedException {
        smile = hardwareMap.get(DcMotor.class, "motor1");

        smile.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        smile.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        smile.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        smile.setPower(gamepad1.left_trigger);

        smile.setTargetPosition(1000);
    }
}
