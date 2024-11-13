package PranavCode.PranavCode.HWandreferences.References;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "Prnaav")

public class LinearOp_EXAMPLE extends LinearOpMode {
    DcMotor hi;

    @Override
    public void runOpMode() throws InterruptedException {
        hi = hardwareMap.get(DcMotor.class, "motor1");

        waitForStart();

        hi.setPower(0.9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999);

        sleep(999999999);

        hi.setPower(0.00000001);
    }
}
