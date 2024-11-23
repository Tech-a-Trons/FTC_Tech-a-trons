package General.IshaanStuff;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class ETAT_Functions {
    private LinearOpMode myOpMode;
    public IMU imu;
    private ElapsedTime holdTimer = new ElapsedTime();  // User for any motion requiring a hold time or timeout.

    // Hardware interface Objects
    private DcMotor FrontLeft;     //  control the left front drive wheel
    private DcMotor FrontRight;    //  control the right front drive wheel
    private DcMotor BackLeft;      //  control the left back drive wheel
    private DcMotor BackRight;     //  control the right back drive wheel

    //private DcMotor driveEncoder;
    public DcMotor FrontLeftEncoder;        //  the left Axial (front/back) Odometry Module
    public DcMotor FrontRightEncoder;       //  the right Axial (front/back) Odometry Module
    public DcMotor BackMiddleEncoder;            //  the Lateral (left/right) Odometry Module

    // Robot Constructor
    public ETAT_Functions(LinearOpMode opmode) {
        myOpMode = opmode;
    }

    //Set all motor speed/power same
    public void set_myRobotAllMotorSpeedSame(double AllMotorSpeed){
        set_myRobotSpeed(AllMotorSpeed, AllMotorSpeed, AllMotorSpeed, AllMotorSpeed);
    }

    //Set each motor speed/power
    public void set_myRobotSpeed(double FLspeed, double FRspeed, double BLspeed, double BRspeed){
        FrontLeft.setPower(FLspeed);
        FrontRight.setPower(FRspeed);
        BackLeft.setPower(BLspeed);
        BackRight.setPower(BRspeed);
    }

    public void myRobot_HardwareMap(String frName, String brName, String flName, String blName,
                                    String FrontLeftEncoderName, String FrontRightEncoderName,
                                    String BackMiddleEncoderName, String IMUname){
        FrontRight = myOpMode.hardwareMap.dcMotor.get(frName);
        BackRight = myOpMode.hardwareMap.dcMotor.get(brName);
        FrontLeft = myOpMode.hardwareMap.dcMotor.get(flName);
        BackLeft = myOpMode.hardwareMap.dcMotor.get(blName);

        FrontLeftEncoder = myOpMode.hardwareMap.dcMotor.get(FrontLeftEncoderName);
        FrontRightEncoder = myOpMode.hardwareMap.dcMotor.get(FrontRightEncoderName);
        BackMiddleEncoder = myOpMode.hardwareMap.dcMotor.get(BackMiddleEncoderName);

        // IMU Hardware initialize: Get the IMU from the configuration using hardwareMap. PLEASE UPDATE THIS VALUE TO MATCH YOUR CONFIGURATION
        imu = myOpMode.hardwareMap.get(IMU.class,IMUname);
    }

    public void myRobot_HardwareConfig(){
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

        //IMU sensor Hardware Configuration (Such as REV controlHub orientation)
        imu.initialize(
                new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT))
        );

        //IMU reset.
        imu.resetYaw();

        //Set all the motor speed/power to 0
        set_myRobotAllMotorSpeedSame(0.0);
    }

    //-------------------Telemetry Functions------------------------
    public void TelemetryInit(int refreshrate_ms){
        myOpMode.telemetry.addData("Status", "Initialized");
        myOpMode.telemetry.update();
        myOpMode.telemetry.setMsTransmissionInterval(refreshrate_ms);
    }
    public void TelemetryShowHeading(String Heading) {
        myOpMode.telemetry.addLine(Heading);
    }
    public void TelemetryShowHeadingData(String Heading, double Data) {
        myOpMode.telemetry.addData(Heading,Data);
    }
    public void TelemetryUpdate(int sleeptime) {
        myOpMode.telemetry.update();
        myOpMode.sleep(sleeptime);
    }

    //-------------------IMU Functions------------------------
    public double get_myRobotYamAngle (){
        double myRobotYamAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        return myRobotYamAngle;
    }

    public double get_myRobotPitchAngle (){
        double myRobotPitchAngle = imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES);
        return myRobotPitchAngle;
    }
    public double get_myRobotRollAngle (){
        double myRobotRollAngle = imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES);
        return myRobotRollAngle;
    }

    //-------------------Rotate Function----------------------
    public void Rotate(double speed){
        set_myRobotSpeed(-speed, speed, -speed, speed);
    }
}