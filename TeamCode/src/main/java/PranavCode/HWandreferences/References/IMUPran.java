package PranavCode.HWandreferences.References;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous (name = "pranIMu")
@Disabled


public class IMUPran extends LinearOpMode {
    /* Declare OpMode members. */
    private DcMotor fl = null;
    private DcMotor fr  = null;
    private DcMotor bl = null;
    private DcMotor br = null;
    private IMU imu = null;      // Control/Expansion Hub IMU
    private double headingError  = 0;
    // These variable are declared here (as class members) so they can be updated in various methods,
    // but still be displayed by sendTelemetry()
    private double targetHeading = 0;
    private double driveSpeed    = 0;
    private double turnSpeed     = 0;
    private double fls=0,frs=0,bls=0,brs=0;
    private int flt=0,frt=0,blt=0,brt=0;
    // Calculate the COUNTS_PER_INCH for your specific drive train.
    // Go to your motor vendor website to determine your motor's COUNTS_PER_MOTOR_REV
    // For external drive gearing, set DRIVE_GEAR_REDUCTION as needed.
    // For example, use a value of 2.0 for a 12-tooth spur gear driving a 24-tooth spur gear.
    // This is gearing DOWN for less speed and more torque.
    // For gearing UP, use a gear ratio less than 1.0. Note this will affect the direction of wheel rotation.
    static final double COUNTS_PER_MOTOR_REV = 753.2 ;   //537.7 eg: GoBILDA 312 RPM Yellow Jacket
    static final double DRIVE_GEAR_REDUCTION = 26.9 ;     //1.0 No External Gearing.
    static final double WHEEL_DIAMETER_INCHES = 5.51181 ;     //4.0 For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    // These constants define the desired driving/control characteristics
    // They can/should be tweaked to suit the specific robot drive train.
    static final double DRIVE_SPEED = 0.4;     // Max driving speed for better distance accuracy.
    static final double TURN_SPEED = 0.2;     // Max Turn speed to limit turn rate
    static final double HEADING_THRESHOLD = 1.0 ;    // How close must the heading get to the target before moving to next step.
    // Requiring more accuracy (a smaller number) will often make the turn take longer to get into the final position.
    // Define the Proportional control coefficient (or GAIN) for "heading control".
    // We define one value when Turning (larger errors), and the other is used when Driving straight (smaller errors).
    // Increase these numbers if the heading does not corrects strongly enough (eg: a heavy robot or using tracks)
    // Decrease these numbers if the heading does not settle on the correct value (eg: very agile robot with omni wheels)
    static final double P_TURN_GAIN = 0.02;     // Larger is more responsive, but also less stable
    static final double P_DRIVE_GAIN = 0.03;
    // Larger is more responsive, but also less stable

    public void driveStraight(double maxDriveSpeed, int distance, double heading) {
        fl.setPower(maxDriveSpeed);
        fr.setPower(maxDriveSpeed);
        bl.setPower(maxDriveSpeed);
        br.setPower(maxDriveSpeed);

        fl.setTargetPosition(distance);
        fr.setTargetPosition(distance);
        bl.setTargetPosition(distance);
        br.setTargetPosition(distance);

        fl.setTargetPosition((int) heading);
        fr.setTargetPosition((int) -heading);
        bl.setTargetPosition((int) heading);
        br.setTargetPosition((int) -heading);

    }

    public void turnToHeading(double maxTurnSpeed, double heading) {
        fl.setPower(maxTurnSpeed);
        fr.setPower(-maxTurnSpeed);
        bl.setPower(maxTurnSpeed);
        br.setPower(-maxTurnSpeed);

        fl.setTargetPosition((int) heading);
        fr.setTargetPosition((int) -heading);
        bl.setTargetPosition((int) heading);
        br.setTargetPosition((int) -heading);

    }

    public void holdHeading(double maxTurnSpeed, double heading, double holdTime) {
        fl.setPower(maxTurnSpeed);
        fr.setPower(-maxTurnSpeed);
        bl.setPower(maxTurnSpeed);
        br.setPower(-maxTurnSpeed);

        fl.setTargetPosition((int) heading);
        fr.setTargetPosition((int) -heading);
        bl.setTargetPosition((int) heading);
        br.setTargetPosition((int) -heading);

        sleep((long) holdTime);
    }

    @Override
    public void runOpMode() {
        // Initialize the drive system variables.
        // Initialize Hardware Mapping here...
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        // Reverse Left side motors.
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.FORWARD);
        /* The next two lines define Hub orientation.
         * The Default Orientation (shown) is when a hub is mounted horizontally with the printed logo pointing UP and the USB port pointing FORWARD.
         *
         * To Do:  EDIT these two lines to match YOUR mounting configuration.
         */
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        // Now initialize the IMU with this mounting orientation
        // This sample expects the IMU to be in a REV Hub and named "imu".
        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        // Ensure the robot is stationary.  Reset the encoders and set the motors to BRAKE mode
        //STOP_AND_RESET_ENCODER
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Set setZeroPowerBehavior : BRAKE
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Wait for the game to start (Display Gyro value while waiting)
        while (opModeInInit()) {
            telemetry.addData(">", "Robot Heading = %4.0f", getHeading());
            telemetry.update();
        }
        // Set the encoders for closed loop speed control, and reset the heading.
        // Set Motor RUN mode: RUN_WITHOUT_ENCODER
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        imu.resetYaw();
        // Step through each leg of the path,
        // Notes:   Reverse movement is obtained by setting a negative distance (not speed)
        //          holdHeading() is used after turns to let the heading stabilize
        //          Add a sleep(2000) after any step to keep the telemetry data visible for review
        driveStraight(DRIVE_SPEED, 24, 0.0);
        sleep(2000);// Drive Forward 24"
        turnToHeading(TURN_SPEED, -45.0);
        sleep(2000);// Turn  CW to -45 Degrees
        holdHeading(TURN_SPEED, -45.0, 0.5);
        sleep(2000);// Hold -45 Deg heading for a 1/2 second
        driveStraight(DRIVE_SPEED, 17, -45.0);
        sleep(2000);// Drive Forward 17" at -45 degrees (12"x and 12"y)
        turnToHeading(TURN_SPEED,  45.0);
        sleep(2000);// Turn  CCW  to  45 Degrees
        holdHeading(TURN_SPEED,  45.0, 0.5);
        sleep(2000);// Hold  45 Deg heading for a 1/2 second
        driveStraight(DRIVE_SPEED, 17, 45.0);
        sleep(2000);// Drive Forward 17" at 45 degrees (-12"x and 12"y)
        turnToHeading(TURN_SPEED,   0.0);
        sleep(2000);// Turn  CW  to 0 Degrees
        holdHeading(TURN_SPEED,   0.0, 1.0);
        sleep(2000);// Hold  0 Deg heading for 1 second
        driveStraight(DRIVE_SPEED,-48, 0.0);
        sleep(2000);// Drive in Reverse 48" (should return to approx. staring position)
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);  // Pause to display last telemetry message.
    }
    /**
     * Use a Proportional Controller to determine how much steering correction is required.
     *
     * @param desiredHeading        The desired absolute heading (relative to last heading reset)
     * @param proportionalGain      Gain factor applied to heading error to obtain turning power.
     * @return                      Turning power needed to get to required heading.
     */
    public double getSteeringCorrection(double desiredHeading, double proportionalGain) {
        targetHeading = desiredHeading;  // Save for telemetry
        // Determine the heading current error
        headingError = targetHeading - getHeading();
        // Normalize the error to be within +/- 180 degrees
        while (headingError > 180)  headingError -= 360;
        while (headingError <= -180) headingError += 360;
        // Multiply the error by the gain to determine the required steering correction/  Limit the result to +/- 1.0
        return Range.clip(headingError * proportionalGain, -1, 1);
    }
    /**
     * Take separate drive (fwd/rev) and turn (right/left) requests,
     * combines them, and applies the appropriate speed commands to the left and right wheel motors.
     * @param drive forward motor speed
     * @param turn  clockwise turning motor speed.
     */
    public void moveRobot(double drive, double turn) {
        driveSpeed = drive;     // save this value as a class member so it can be used by telemetry.
        turnSpeed  = turn;      // save this value as a class member so it can be used by telemetry.
        fls = drive + turn;
        frs = drive - turn;
        bls = drive + turn;
        brs = drive - turn;
        // Scale speeds down if either one exceeds +/- 1.0;
        // Scaling Drive Powers Proportionally
        double maxF = Math.max((Math.abs(fls)),(Math.abs(frs)));
        double maxB = Math.max((Math.abs(bls)),(Math.abs(brs)));
        double mfb = Math.max(Math.abs(maxF),Math.abs(maxB));
        if(mfb > 1){
            fls = fls / mfb;
            fls = frs / mfb;
            bls = bls / mfb;
            brs = brs / mfb;
        }
        fl.setPower(fls);
        fr.setPower(frs);
        bl.setPower(bls);
        br.setPower(brs);
    }
    /**
     *  Display the various control parameters while driving
     *
     * @param straight  Set to true if we are driving straight, and the encoder positions should be included in the telemetry.
     */
    private void sendTelemetry(boolean straight) {
        if (straight) {
            telemetry.addData("Motion", "Drive Straight");
            telemetry.addData("Target Pos", "FL:%7d, FR:%7d, BL:%7d, BR:%7d", flt, frt, blt, brt);
            telemetry.addData("Actual Pos", "FL:%7d, FR:%7d, BL:%7d, BR:%7d", fl.getCurrentPosition(), fr.getCurrentPosition(), bl.getCurrentPosition(), br.getCurrentPosition());
        } else {
            telemetry.addData("Motion", "Turning");
        }
        telemetry.addData("Heading", "Target:%5.2f, Current:%5.0f", targetHeading, getHeading());
        telemetry.addData("Error & Turn Speed",  "Error:%5.1f, Steer Pwr:%5.1f", headingError, turnSpeed);
        telemetry.addData("Wheel Speeds", "FL:%5.2f, FR:%5.2f, BL:%5.2f, BR:%5.2f", fls, frs, bls, brs);
        telemetry.update();
    }
    /**
     * read the Robot heading directly from the IMU (in degrees)
     */
    public double getHeading() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }
}
