package IntoTheDeep.IshaanStuff;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name="assignmentArmMovement")
public class assignmentArmMovement extends LinearOpMode {

    DcMotor ArmMotor = null;

    @Override
    public void runOpMode() throws InterruptedException {

        ArmMotor = hardwareMap.get(DcMotor.class, "bl");

        ArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ArmMotor.setTargetPosition(0);
        ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ArmMotor.setPower(0);

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.x) {
                int ArmPosition = 0;
                ArmMotor.setTargetPosition(ArmPosition);
                ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ArmMotor.setPower(0.2);
                while (opModeIsActive() && ArmMotor.isBusy()) {
                    idle();
                }
            } else if (gamepad1.dpad_up) {
                int ArmPosition = -650;
                ArmMotor.setTargetPosition(ArmPosition);
                ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ArmMotor.setPower(0.2);
                while (opModeIsActive() && ArmMotor.isBusy()) {
                    idle();
                }
            } else if (gamepad1.dpad_left) {
                int ArmPosition = -900;
                ArmMotor.setTargetPosition(ArmPosition);
                ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ArmMotor.setPower(0.2);
                while (opModeIsActive() && ArmMotor.isBusy()) {
                    idle();
                }

            } else if (gamepad1.dpad_down) {
                int ArmPosition = -1050;
                ArmMotor.setTargetPosition(ArmPosition);
                ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ArmMotor.setPower(0.2);
                while (opModeIsActive() && ArmMotor.isBusy()) {
                    idle();
                }
            }
        }
    }
}