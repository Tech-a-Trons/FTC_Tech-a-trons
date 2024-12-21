package General.PranavCode.HWandreferences.References;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.bosch.BHI260IMU;

import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous(name = "IMUSen")

public class IMUtest extends LinearOpMode {

    // Motors
    public  DcMotor leftFront, leftRear, rightFront, rightRear;

    public DcMotor encoderLeft;
    public DcMotor encoderRight;
    public DcMotor encoderAux;

    // IMU
    public  IMU imu;

    @Override
    public void runOpMode() {
        // Initialize hardware
        leftFront = hardwareMap.get(DcMotor.class, "fl");
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftRear = hardwareMap.get(DcMotor.class, "bl");
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightFront = hardwareMap.get(DcMotor.class, "fr");
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightRear = hardwareMap.get(DcMotor.class, "br");
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        encoderLeft = leftRear;
        encoderRight = rightRear;
        encoderAux = rightFront;

        imu = hardwareMap.get(BHI260IMU.class, "imu");


        // Reset encoders
        resetEncoders();
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        // IMU Parameters
        //BHI260IMU.Parameters imuParams = new BHI260IMU.Parameters();
        //imuParams.angleUnit = BHI260IMU.AngleUnit.DEGREES;
        //imu.initialize(imuParams);

        imu.initialize(new IMU.Parameters(orientationOnRobot));

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for start
        waitForStart();

        if (opModeIsActive()) {
            // Autonomous logic
            moveForward(1000); // Move forward for 1000 ticks
            // turnByAngle(90);  // Turn 90 degrees
            moveForward(500); // Move forward for 500 ticks
            stopMotors();     // Stop motors
        }
    }

    private void moveForward(int ticks) {
        // Set target position
        leftFront.setTargetPosition(ticks);
        leftRear.setTargetPosition(ticks);
        rightFront.setTargetPosition(ticks);
        rightRear.setTargetPosition(ticks);

        // Set motors to run to position
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Power motors
        setMotorPower(0.5);

        // Wait until motors reach position
        while (opModeIsActive() && leftFront.isBusy() && rightFront.isBusy()) {
            telemetry.addData("Moving Forward", "Target: %d", ticks);
            telemetry.update();
        }

        stopMotors();
    }

//    private void turnByAngle(double angle) {
//        // Get current heading
//        double initialHeading = getHeading();
//
//        // Calculate target heading
//        double targetHeading = initialHeading + angle;
//
//        // Turn the robot
//        if (angle > 0) { // Clockwise
//            setMotorPower(0.3, -0.3);
//        } else { // Counter-clockwise
//            setMotorPower(-0.3, 0.3);
//        }

        // Wait until the desired angle is reached
//        while (opModeIsActive() && Math.abs(getHeading() - targetHeading) > 1) {
//            telemetry.addData("Turning", "Current: %.2f, Target: %.2f", getHeading(), targetHeading);
//            telemetry.update();
//        }
//
//        stopMotors();
//    }
//
//    private double YawPitchRollAngles getHeading() {
//        return imu.getRobotYawPitchRollAngles();
//    }

    private void resetEncoders() {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void setMotorPower(double power) {
        leftFront.setPower(power);
        leftRear.setPower(power);
        rightFront.setPower(power);
        rightRear.setPower(power);
    }

    private void setMotorPower(double leftPower, double rightPower) {
        leftFront.setPower(leftPower);
        leftRear.setPower(leftPower);
        rightFront.setPower(rightPower);
        rightRear.setPower(rightPower);
    }

    private void stopMotors() {
        setMotorPower(0);
    }
}