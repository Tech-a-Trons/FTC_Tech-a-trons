package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
@Autonomous(name="EX_Odometry")
public class EX_Odometry extends LinearOpMode {
    //Drive motors
    DcMotor FrontRight, BackRight, FrontLeft, BackLeft;
    //Odometry Wheels
    DcMotor FrontLeftEncoder, FrontRightEncoder, BackMiddleEncoder;
    //Hardware Map Names for drive motors and odometry wheels.
    String frName = "fr", brName = "br", flName = "fl", blName = "bl";
    String FrontLeftEncoderName = flName, FrontRightEncoderName = frName, BackMiddleEncoderName = blName;
    double speed = 0.2;

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize Telemetry.
        TelemetryInit(250); // Default refresh rate is 250ms
        //Initialize hardware map. PLEASE UPDATE THESE VALUES TO MATCH YOUR CONFIGURATION
        myRobot_HardwareMap(frName, brName, flName, blName, FrontLeftEncoderName, FrontRightEncoderName, BackMiddleEncoderName);
        // Robot Hardware Configuration
        myRobot_HardwareConfig();
        TelemetryShowHeading("Odometry setup: DONE");
        waitForStart();
        while (opModeIsActive()) {
            // Continuously rotate robot
            set_myRobotSpeed(-speed, speed, -speed, speed);
            double FrontLeftOdometryValue = FrontLeftEncoder.getCurrentPosition();
            double FrontRightOdometryValue = FrontRightEncoder.getCurrentPosition();
            double BackMiddleOdometryValue = BackMiddleEncoder.getCurrentPosition();
            TelemetryShowHeading("-------ODOMETRY DATA--------------------------------");
            TelemetryShowHeadingData("FL-Odometry: ", FrontLeftOdometryValue);
            TelemetryShowHeadingData("FR-Odometry", FrontRightOdometryValue);
            TelemetryShowHeadingData("BM-Odometry", BackMiddleOdometryValue);
            TelemetryUpdate(0);
        }
    }

    public void myRobot_HardwareMap(String frName, String brName, String flName, String blName, String FrontLeftEncoderName, String FrontRightEncoderName, String BackMiddleEncoderName) {
        FrontRight = hardwareMap.dcMotor.get(frName);
        BackRight = hardwareMap.dcMotor.get(brName);
        FrontLeft = hardwareMap.dcMotor.get(flName);
        BackLeft = hardwareMap.dcMotor.get(blName);
        FrontLeftEncoder = hardwareMap.dcMotor.get(FrontLeftEncoderName);
        FrontRightEncoder = hardwareMap.dcMotor.get(FrontRightEncoderName);
        BackMiddleEncoder = hardwareMap.dcMotor.get(BackMiddleEncoderName);
    }

    public void myRobot_HardwareConfig() {
        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontLeftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackMiddleEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeftEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontRightEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackMiddleEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        set_myRobotAllMotorSpeedSame(0.0);
        TelemetryShowHeading("Status: Hardware Config: DONE");
    }

    public void TelemetryInit(int refreshrate_ms) {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        telemetry.setMsTransmissionInterval(refreshrate_ms);
    }

    public void TelemetryShowHeading(String Heading) {
        telemetry.addLine(Heading);
    }

    public void TelemetryShowHeadingData(String Heading, double Data) {
        telemetry.addData(Heading, Data);
    }

    public void TelemetryUpdate(int sleeptime) {
        telemetry.update();
        sleep(sleeptime);
    }

    //Set all motor speed/power same
    public void set_myRobotAllMotorSpeedSame(double AllMotorSpeed) {
        set_myRobotSpeed(AllMotorSpeed, AllMotorSpeed, AllMotorSpeed, AllMotorSpeed);
    }

    //Set each motor speed/power
    public void set_myRobotSpeed(double FLspeed, double FRspeed, double BLspeed, double BRspeed) {
        FrontLeft.setPower(FLspeed);
        FrontRight.setPower(FRspeed);
        BackLeft.setPower(BLspeed);
        BackRight.setPower(BRspeed);
    }
}