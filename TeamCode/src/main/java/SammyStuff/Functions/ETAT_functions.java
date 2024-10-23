package SammyStuff.Functions;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

//@Disabled
public class ETAT_functions {
    private LinearOpMode myOpMode;
    public IMU imu;
    private ElapsedTime holdTimer = new ElapsedTime();  // User for any motion requiring a hold time or timeout.

    // Hardware interface Objects
    private DcMotor FrontLeft = null;     //  control the left front drive wheel
    private DcMotor FrontRight = null;    //  control the right front drive wheel
    private DcMotor BackLeft = null;      //  control the left back drive wheel
    private DcMotor BackRight = null;     //  control the right back drive wheel

    //private DcMotor driveEncoder;
    public DcMotor FrontLeftEncoder;        //  the left Axial (front/back) Odometry Module
    public DcMotor FrontRightEncoder;       //  the right Axial (front/back) Odometry Module
    public DcMotor BackMiddleEncoder;            //  the Lateral (left/right) Odometry Module

    // For linear slides
    public DcMotor LeftLinearSlide;
    public DcMotor RightLinearSlide;

    // For Claw Servo
    public Servo claw = null;
    public final static double CLAW_HOME = 0.0;
    public final static double CLAW_MIN_RANGE = 0.0;
    public final static double CLAW_MAX_RANGE = 0.5;
    public final static double CLAW_CLOSE = 0.0;
    public final static double CLAW_OPEN = 0.2;

    // Robot Constructor
    public ETAT_functions(LinearOpMode opmode) {
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
                                    String BackMiddleEncoderName, String IMUname,
                                    //String LeftLinearSlideName, String RightLinearSlideName,
                                    String claw_servo_name){
        FrontRight = myOpMode.hardwareMap.dcMotor.get(frName);
        BackRight = myOpMode.hardwareMap.dcMotor.get(brName);
        FrontLeft = myOpMode.hardwareMap.dcMotor.get(flName);
        BackLeft = myOpMode.hardwareMap.dcMotor.get(blName);

        FrontLeftEncoder = myOpMode.hardwareMap.dcMotor.get(FrontLeftEncoderName);
        FrontRightEncoder = myOpMode.hardwareMap.dcMotor.get(FrontRightEncoderName);
        BackMiddleEncoder = myOpMode.hardwareMap.dcMotor.get(BackMiddleEncoderName);

        // IMU Hardware initialize: Get the IMU from the configuration using hardwareMap. PLEASE UPDATE THIS VALUE TO MATCH YOUR CONFIGURATION
        imu = myOpMode.hardwareMap.get(IMU.class,IMUname);

//        // Linear slide Hardware initialize:
//        LeftLinearSlide = myOpMode.hardwareMap.dcMotor.get(LeftLinearSlideName);
//        RightLinearSlide = myOpMode.hardwareMap.dcMotor.get(RightLinearSlideName);

        // For Claw Servo Hardware initialize:
        claw = myOpMode.hardwareMap.servo.get(claw_servo_name);
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
        FrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        BackRight.setDirection(DcMotorSimple.Direction.FORWARD);

        //IMU sensor Hardware Configuration (Such as REV controlHub orientation)
        imu.initialize(
                new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT))
        );

        //IMU reset.
        imu.resetYaw();

//        //Linear SLide
//        LeftLinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        RightLinearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        LeftLinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        RightLinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        LeftLinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        RightLinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Claw Servo
        claw.setPosition(CLAW_HOME);

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

    //-------------------TURN Functions-----------------------
//    public void turn90(double targetDegree, double speed)
//    {
//        set_myRobotSpeed(speed,-speed,speed,-speed);
//        // Continue until robot yaws right by target degrees or stop is pressed on Driver Station.
//        double Yaw_Angle = get_myRobotYamAngle();
//        if ((Yaw_Angle >= 90) && (Yaw_Angle <= 45)){
//            speed = 0.75;
//
//        }
//
//        while ( !(Yaw_Angle >= targetDegree || myOpMode.isStopRequested()) ) {
//            // Update Yaw-Angle variable with current yaw.
//            Yaw_Angle = get_myRobotYamAngle();
//            // Report yaw orientation to Driver Station.
//            TelemetryShowHeadingData("Yaw value", Yaw_Angle);
//            TelemetryUpdate(100);
//        }
//        // Target reached. Turn off motors
//        set_myRobotAllMotorSpeedSame(0);
//    }

    public void turnRight(double targetDegree, double speed)
    {
        set_myRobotSpeed(speed,-speed,speed,-speed);
        // Continue until robot yaws right by target degrees or stop is pressed on Driver Station.
        double Yaw_Angle = get_myRobotYamAngle();
        while ( !(Yaw_Angle >= targetDegree || myOpMode.isStopRequested()) ) {
            // Update Yaw-Angle variable with current yaw.
            Yaw_Angle = get_myRobotYamAngle();
            // Report yaw orientation to Driver Station.
            TelemetryShowHeadingData("Yaw value", Yaw_Angle);
            TelemetryUpdate(100);
        }
        // Target reached. Turn off motors
        set_myRobotAllMotorSpeedSame(0);
    }

    public void turnLeft(double targetDegree, double speed)
    {
        set_myRobotSpeed(-speed,speed,-speed,speed);
        // Continue until robot yaws left by target degrees or stop is pressed on Driver Station.
        double Yaw_Angle = get_myRobotYamAngle();
        while ( !(Yaw_Angle >= targetDegree || myOpMode.isStopRequested()) ) {
            // Update Yaw-Angle variable with current yaw.
            Yaw_Angle = get_myRobotYamAngle();
            // Report yaw orientation to Driver Station.
            TelemetryShowHeadingData("Yaw value", Yaw_Angle);
            TelemetryUpdate(100);
        }
        // Target reached. Turn off motors
        set_myRobotAllMotorSpeedSame(0);
    }


    // RelativeTurn : From where ever robot is facing turn from that point.
    public void RelativeTurn(double TargetDegrees){
        imu.resetYaw();
//        double TurnError = TargetDegrees;
        double TurnError = TargetDegrees - get_myRobotYamAngle();
        while (myOpMode.opModeIsActive() && (Math.abs(TurnError) > 2)){
            double RobotSpeed = (TurnError < 0 ? -0.3 : 0.3);
            set_myRobotSpeed(-RobotSpeed, RobotSpeed, -RobotSpeed, RobotSpeed);
            TurnError = TargetDegrees - get_myRobotYamAngle();
            TelemetryShowHeadingData("How Far Away From Traget Degrees : ", TurnError);
            TelemetryUpdate(0);
        }
        set_myRobotAllMotorSpeedSame(0);
    }

    //AbsoluteTurn To target Degree/Angle based on initial robot heading
    public void AbsoluteTurnTo(double TargetDegrees){
        double YamAngle_Current = get_myRobotYamAngle();
        double TurnError = TargetDegrees - YamAngle_Current;

        if (TurnError > 180) { TurnError = TurnError - 360;}
        else if (TurnError < -180) {TurnError = TurnError + 360;}

        RelativeTurn(TurnError);
    }

    public double getAbsoluteAngle(){
        return imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }

    public void turnPID(double degrees) {
        turnToPID(degrees + getAbsoluteAngle());
    }

    void turnToPID(double targetAngle) {
        ETAT_functions.TurnPIDController pid = new ETAT_functions.TurnPIDController(targetAngle, 0.01, 0, 0.003);
        myOpMode.telemetry.setMsTransmissionInterval(50);
        // Checking lastSlope to make sure that it's not oscillating when it quits
        while (Math.abs(targetAngle - getAbsoluteAngle()) > 0.5 || pid.getLastSlope() > 0.75) {
            double motorPower = pid.update(getAbsoluteAngle());
            set_myRobotSpeed(-motorPower/2, motorPower/2, -motorPower/2, motorPower/2);

            myOpMode.telemetry.addData("Current Angle", getAbsoluteAngle());
            myOpMode.telemetry.addData("Target Angle", targetAngle);
            myOpMode.telemetry.addData("Slope", pid.getLastSlope());
            myOpMode.telemetry.addData("Power", motorPower);
            myOpMode.telemetry.update();
        }
        set_myRobotAllMotorSpeedSame(0);
    }

    // Single use per object
    public class TurnPIDController {
        private double kP, kI, kD;
        private ElapsedTime timer = new ElapsedTime();
        private double targetAngle;
        private double lastError = 0;
        private double accumulatedError = 0;
        private double lastTime = -1;
        private double lastSlope = 0;

        public TurnPIDController(double target, double p, double i, double d) {
            kP = p;
            kI = i;
            kD = d;
            targetAngle = target;
        }

        public double update(double currentAngle) {
            // TODO: make sure angles are within bounds and are in same format (e.g., 0 <= | angle | <= 180)
            //   and ensure direction is correct

            // P
            double error = targetAngle - currentAngle;
            error %= 360;
            error += 360;
            error %= 360;
            if (error > 180) {
                error -= 360;
            }

            // I
            accumulatedError *= Math.signum(error);
            accumulatedError += error;
            if (Math.abs(error) < 2) {
                accumulatedError = 0;
            }

            // D
            double slope = 0;
            if (lastTime > 0) {
                slope = (error - lastError) / (timer.milliseconds() - lastTime);
            }
            lastSlope = slope;
            lastError = error;
            lastTime = timer.milliseconds();

            double motorPower = 0.1 * Math.signum(error)
//                    + 0.9 * Math.tanh(kP * error + kI * accumulatedError - kD * slope);
                    + 0.9 * Math.tanh(kP * error + kI * accumulatedError + kD * slope);

            return motorPower;
        }

        public double getLastSlope() {
            return lastSlope;
        }
    }

    // another method PID
    private double integralSum = 0;
    private double Kp = 2.0;
    private double Ki = 0.0;
    private double Kd = 0.0;
    private double lastError = 0;
    ElapsedTime timer = new ElapsedTime();
    public double TurnPIDControl_v2(double target, double current_state) {
        double error = angleWrap(target - current_state);
        myOpMode.telemetry.addData("Error: ", error);
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / (timer.seconds());
        lastError = error;
        timer.reset();
        double output = (error * Kp) + (derivative * Kd) + (integralSum * Ki);
        return output;
    }
    public double angleWrap(double radians){
        while(radians > Math.PI){
            radians -= 2 * Math.PI;
        }
        while(radians < -Math.PI){
            radians += 2 * Math.PI;
        }
        return radians;
    }
    //----------------------------------------------------
    // Linear Slider functions

    public void setLinearSlidePosition(int Position, double Power) {
        LeftLinearSlide.setTargetPosition(Position);
        RightLinearSlide.setTargetPosition(Position);

        LeftLinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightLinearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        LeftLinearSlide.setPower(Power);
        RightLinearSlide.setPower(Power);
    }

    public void setLinearSlidePower(double power) {
        // If the motor is busy, don't allow this function to command the motor.
        // However, if the motor is busy, update telemetry.
        if( LeftLinearSlide.isBusy() && RightLinearSlide.isBusy())
        {
            // This function always gets called, so it makes sense for the telemetry
            // to be here whenever this function isn't commanding the arm.
            myOpMode.telemetry.addData("Current POS","LLS: %.0f, RLS: %.0f", LeftLinearSlide.getCurrentPosition(), RightLinearSlide.getCurrentPosition());
            myOpMode.telemetry.addData("Target POS", "LLS: %.0f, RLS: %.0f", LeftLinearSlide.getTargetPosition(),RightLinearSlide.getTargetPosition());
            myOpMode.telemetry.update();
        }
        else
        {
            // Only command the Linear slide if the firmware isn't currently using it.
            LeftLinearSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            RightLinearSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            LeftLinearSlide.setPower(power);
            RightLinearSlide.setPower(power);
        }
    }
    //----------------------------------------------------
    // Claw functions

    //----------------------------------------------------
}