package NiranjanStuff.NiranjanTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="OptimizedSlideControl")
public class OptimizedSlideControl extends LinearOpMode {

    private DcMotor leftSlide;
    private DcMotor rightSlide;

    private static final int MAX_POSITION = 2000;
    private static final int MIN_POSITION = 0;
    private static final int TARGET_POSITION_INCREMENT = 100;
    private static final double SLIDE_SPEED = 0.3;

    @Override
    public void runOpMode() {

        leftSlide = hardwareMap.get(DcMotor.class, "left_slide");
        rightSlide = hardwareMap.get(DcMotor.class, "right_slide");

        leftSlide.setDirection(DcMotor.Direction.FORWARD);
        rightSlide.setDirection(DcMotor.Direction.FORWARD);

        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        while (opModeIsActive()) {
            int newTarget = leftSlide.getCurrentPosition();

            if (gamepad1.dpad_up && newTarget + TARGET_POSITION_INCREMENT <= MAX_POSITION) {
                newTarget += TARGET_POSITION_INCREMENT;
            } else if (gamepad1.dpad_down && newTarget - TARGET_POSITION_INCREMENT >= MIN_POSITION) {
                newTarget -= TARGET_POSITION_INCREMENT;
            }

            leftSlide.setTargetPosition(newTarget);
            rightSlide.setTargetPosition(newTarget);
            leftSlide.setPower(SLIDE_SPEED);
            rightSlide.setPower(SLIDE_SPEED);

            telemetry.addData("Left Slide Target", leftSlide.getTargetPosition());
            telemetry.addData("Right Slide Target", rightSlide.getTargetPosition());
            telemetry.addData("Left Slide Position", leftSlide.getCurrentPosition());
            telemetry.addData("Right Slide Position", rightSlide.getCurrentPosition());
            telemetry.update();
        }
    }
}