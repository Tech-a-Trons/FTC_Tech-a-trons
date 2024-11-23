package General.IshaanStuff;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "LS_class")
public class LS_class extends LinearOpMode {
    DcMotor LinearSlide = null;
    @Override
    public void runOpMode() throws InterruptedException {
        LinearSlide = hardwareMap.get(DcMotor.class, "FL");

        LinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        LinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LinearSlide.setTargetPosition(250);

        LinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        LinearSlide.setPower(0);
        waitForStart();
        LinearSlide.setPower(0.2);

        while (opModeIsActive() && LinearSlide.isBusy()) {
            idle();
        }
        LinearSlide.setPower(0);
    }

}
