package SammyStuff.SammyOpVersions;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeServices;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryInternal;
import org.firstinspires.ftc.robotcore.internal.system.Assert;

import java.util.concurrent.ExecutorService;

import SammyStuff.SammyOpVersions.Interfaces.LinOpDefault;
import SammyStuff.SammyOpVersions.Interfaces.SammyLinOpFunctions;

@SuppressWarnings("unused")
public abstract class SammyLinOp extends OpMode implements LinOpDefault, SammyLinOpFunctions {




    private volatile DcMotor fl;
    private volatile DcMotor fr;
    private volatile DcMotor br;
    private volatile DcMotor bl;
    private volatile DcMotor fle, fre, bme;
    private volatile double drive;
    private volatile double turn;
    private volatile double strafe;
    private volatile double flpwr, frpwr, blpwr, brpwr;
    private volatile double flc, frc, blc, brc;
    private volatile IMU imu;
    private final double YawAngle = getYaw();
    private final double PitchAngle = getPitch();
    private final double RollAngle = getRoll();

    private final double FLOV = fle.getCurrentPosition();
    private final double FROV = fre.getCurrentPosition();
    private final double BMOV = bme.getCurrentPosition();
    private volatile Servo servo1;
    private volatile DcMotor fsls1;
    private volatile DcMotor fsls2;

    //------------------------------------------------------------------------------------------------
    // State
    //------------------------------------------------------------------------------------------------

    private volatile boolean  userMethodReturned = false;
    private volatile boolean  userMonitoredForStart = false;
    private final Object      runningNotifier = new Object();

    //------------------------------------------------------------------------------------------------
    // Construction
    //------------------------------------------------------------------------------------------------

    @Override
    public void LinearOpMode() {
    }

    //------------------------------------------------------------------------------------------------
    // Operations
    //------------------------------------------------------------------------------------------------

    @Override
    public void waitForStart() {
        while (!isStarted()) {
            synchronized (runningNotifier) {
                try {
                    runningNotifier.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    @Override
    public final void idle() {
        // Otherwise, yield back our thread scheduling quantum and give other threads at
        // our priority level a chance to run
        Thread.yield();
    }

    @Override
    public final void Sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public final boolean opModeIsActive() {
        boolean isActive = !this.isStopRequested() && this.isStarted();
        if (isActive) {
            idle();
        }
        return isActive;
    }

    @Override
    public final boolean opModeInInit() {
        return !isStarted() && !isStopRequested();
    }

    /**
     * Determine if the OpMode has been started (the play button has been pressed).
     *
     * <p>To avoid difficult-to-debug deadlocks, this method will also return
     * {@code true} if the current thread has been interrupted (which typically
     * indicates that the OpMode has been told to stop), even if the play
     * button has not been pressed.</p>
     * @see #opModeIsActive()
     * @see #isStopRequested()
     */
    volatile ExecutorService executorService = null;
    volatile OpModeServices internalOpModeServices = null;
    volatile boolean isStarted = false;
    volatile boolean stopRequested = false;
    volatile boolean opModeThreadFinished = false;
    volatile RuntimeException exception = null;
    volatile NoClassDefFoundError noClassDefFoundError = null;
    @Override
    public final boolean isStarted() {

        /*
         * What we're looking for here is that the user polled until the
         * the start condition was occurred.
         */
        if(isStarted) userMonitoredForStart = true;

        return this.isStarted || Thread.currentThread().isInterrupted();
    }


    @Override
    public final boolean isStopRequested() {
        return this.stopRequested || Thread.currentThread().isInterrupted();
    }


    @Override final public void init() { }
    @Override final public void init_loop() { }
    @Override final public void start() { }
    @Override final public void loop() { }
    @Override final public void stop() { }
    //----------------------------------------------------------------------------------------------
    // OpModeInternal hooks (LinearOpMode MUST override ALL of them to not inherit behavior from OpMode)
    //----------------------------------------------------------------------------------------------

    // Package-private, called on the OpModeThread when the OpMode is initialized
    @Override
    public final void internalRunOpMode() throws InterruptedException {
        // Do NOT call super.internalRunOpMode().

        // We need to reset these fields because BlocksOpMode (which is a subclass of LinearOpMode)
        // instances are re-used.
        userMethodReturned = false;
        userMonitoredForStart = false;

        runOpMode();
        userMethodReturned = true;
        RobotLog.d("User runOpModeMethod exited");
        requestOpModeStop();
    }

    // Package-private, called on the main event loop thread
    @Override
    public final void internalOnStart() {
        synchronized (runningNotifier) {
            runningNotifier.notifyAll();
        }
    }

    // Package-private, called on the main event loop thread
    @Override
    public final void internalOnEventLoopIteration() {
        time = getRuntime();

        synchronized (runningNotifier) {
            runningNotifier.notifyAll();
        }

        if (telemetry instanceof TelemetryInternal) {
            ((TelemetryInternal)telemetry).tryUpdateIfDirty();
        }
    }

    // Package-private, called on the main event loop thread
    @Override
    public final void internalOnStopRequested() {
        /*
         * Is it ending because it simply... ended (e.g. end of auto), or
         * because the user failed to monitor for the start condition?
         *
         * We must check userMethodReturned, because if it didn't return,
         * but also !userMonitoredForStart, that means the opmode was aborted
         * during init. We don't want to show a warning in that case.
         */
        if(!userMonitoredForStart && userMethodReturned) {
            RobotLog.addGlobalWarningMessage("The OpMode which was just initialized ended prematurely as a result of not monitoring for the start condition. Did you forget to call waitForStart()?");
        }

        // executorService being null would indicate that this method was called on an OpMode that had not yet been initialized
        Assert.assertTrue(executorService != null);

        // For LinearOpMode, the OpMode thread needs to be interrupted, as the user may have called a
        // long-running method that can only be stopped early by interrupting the thread.
        executorService.shutdownNow();
    }

    @Override
    public void newGamepadDataAvailable(Gamepad latestGamepad1Data, Gamepad latestGamepad2Data) {
        // For LinearOpMode, we want the new gamepad data to be available to the user ASAP
        // We copy the data instead of replacing the gamepad instances because the gamepad instances
        // may contain queued effect data.
        gamepad1.copy(latestGamepad1Data);
        gamepad2.copy(latestGamepad2Data);
    }


    @Override
    public final void HardwareMap() {

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        fle = hardwareMap.dcMotor.get("fl");
        fre = hardwareMap.dcMotor.get("fr");
        bme = hardwareMap.dcMotor.get("bl");
        imu = hardwareMap.get(IMU.class, "imu");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        fsls1 = hardwareMap.get(DcMotor.class, "fl");
        fsls2 = hardwareMap.get(DcMotor.class, "fl");

    }
    @Override
    public final void reverse() {

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public final void use_encoder() {

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bme.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fre.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fle.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fre.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bme.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fle.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fre.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bme.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }
    @Override
    public final void DontUseEncoders() {

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fle.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fre.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bme.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    @Override
    public final void telemetryInit() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        telemetry.setMsTransmissionInterval(100);
    }
    @Override
    public final void telemetryAfterInit() {

        flc = fl.getCurrentPosition();
        frc = fr.getCurrentPosition();
        brc = br.getCurrentPosition();
        blc = bl.getCurrentPosition();

        displayBigOnDS("-------Telemetry activated--------------------------------");
        displaySmallOnDS("FlPos: ", flc);
        displaySmallOnDS("FrPos: ", frc);
        displaySmallOnDS("BlPos: ", blc);
        displaySmallOnDS("BrPos: ", brc);
        displaySmallOnDS("FlPwr", flpwr);
        displaySmallOnDS("FrPwr", frpwr);
        displaySmallOnDS("BlPwr", blpwr);
        displaySmallOnDS("BrPwr", brpwr);

        displayBigOnDS("-------IMU SENSOR DATA--------------------------------");
        displaySmallOnDS("Yaw Angle: ", YawAngle);
        displaySmallOnDS("Pitch Angle: ", PitchAngle);
        displaySmallOnDS("Roll Angle: ", RollAngle);
        displayBigOnDS("-------ODOMETRY DATA--------------------------------");
        displaySmallOnDS("FL-Odometry: ", FLOV);
        displaySmallOnDS("FR-Odometry", FROV);
        displaySmallOnDS("BM-Odometry", BMOV);


        telemetry.update();

    }
    @Override
    public final void brake() {
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    @Override
    public final void MoveChassis() {


        drive = -gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;
        strafe = gamepad1.left_stick_x;

        flpwr = drive + turn + strafe;
        frpwr = drive - turn - strafe;
        blpwr = drive + turn - strafe;
        brpwr = drive - turn + strafe;

        double maxf = Math.max((Math.abs(flpwr)), (Math.abs(frpwr)));
        double maxb = Math.max((Math.abs(blpwr)), (Math.abs(brpwr)));
        double maxfb_pwr = Math.max((Math.abs(maxf)), (Math.abs(maxb)));
        if (maxfb_pwr > 1) {
            flpwr /= maxfb_pwr;
            frpwr /= maxfb_pwr;
            blpwr /= maxfb_pwr;
            brpwr /= maxfb_pwr;
            fl.setPower(flpwr);
            fr.setPower(frpwr);
            bl.setPower(blpwr);
            br.setPower(brpwr);
        }
    }
    @Override
    public final void imuInit() {
        imu.initialize(
                new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT))
        );


        imu.resetYaw();
    }
    @Override
    public final void displaySmallOnDS(String Heading, double Data) {
        telemetry.addData(Heading, Data);
    }
    @Override
    public final void displayBigOnDS(String Heading) {
        telemetry.addLine(Heading);
    }

    @Override
    public final double getYaw() {
        assert imu != null;
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    @Override
    public final double getPitch() {
        assert imu != null;
        return imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES);
    }

    @Override
    public final double getRoll() {
        assert imu != null;
        return imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES);
    }
        @Override
    public final void SetTargetPosChassis(int flt, int frt, int blt, int brt) {
        fl.setTargetPosition(flt);
        fr.setTargetPosition(frt);
        bl.setTargetPosition(blt);
        br.setTargetPosition(brt);
    }
    @Override
    public final void setChassisPwr(double flp, double frp, double blp, double brp) {
        fl.setPower(flp);
        bl.setPower(blp);
        fr.setPower(frp);
        br.setPower(brp);

    }
    @Override
    public final void Movefsls1Pwr(double pwr) {

        fsls1.setPower(pwr);
    }
    @Override
    public final void Movefsls1TargetPos(int TargetPos, double pwr) {
        fsls1.setTargetPosition(TargetPos);
        fsls1.setPower(pwr);
    }
    @Override
    public  final void Movefsls2Pwr(double pwr) {
        fsls2.setPower(pwr);
    }
    @Override
    public final void Movefsls2TargetPos(int TargetPos, double pwr) {
        fsls2.setTargetPosition(TargetPos);
        fsls2.setPower(pwr);
    }
}



