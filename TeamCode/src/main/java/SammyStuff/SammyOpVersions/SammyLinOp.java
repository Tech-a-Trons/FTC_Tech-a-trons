package SammyStuff.SammyOpVersions;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public abstract class SammyLinOp extends LinearOpMode {
    public SammyLinOp() {
    }

    DcMotor fl;
    DcMotor fr;
    DcMotor br;
    DcMotor bl;
    DcMotor fle, fre,bme;
    double drive;
    double turn;
    double strafe;
    double flpwr, frpwr, blpwr, brpwr;
    double flc,frc,blc,brc;
    IMU imu;
    double YawAngle = getYaw();
    double PitchAngle = getPitch();
    double RollAngle = getRoll();

    double FLOV = fle.getCurrentPosition();
    double FROV = fre.getCurrentPosition();
    double BMOV = bme.getCurrentPosition();
    Servo servo1;
    Servo servo2;
    DcMotor fsls1;
    DcMotor fsls2;

    abstract public void runOpMode() throws InterruptedException;

    public void HardwareMap() {

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

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void use_encoder() {

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        fle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bme.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fre.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fle.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fre.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bme.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void DontUseEncoders() {

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fle.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fre.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bme.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void telemetryInit() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        telemetry.setMsTransmissionInterval(100);
    }

    public void telemetryAfterInit() {

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

    public void brake() {
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void MoveChassis(DcMotor fl,DcMotor fr,DcMotor br,DcMotor bl) {


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

    public void imuInit() {
        imu.initialize(
                new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT))
        );


        imu.resetYaw();
    }

    public void displaySmallOnDS(String Heading, double Data) {
        telemetry.addData(Heading, Data);
    }

    public void displayBigOnDS(String Heading) {
        telemetry.addLine(Heading);
    }


    public double getYaw() {
        assert imu != null;
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }


    public double getPitch() {
        assert imu != null;
        return imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES);
    }


    public double getRoll() {
        assert imu != null;
        return imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES);
    }

    public void SetTargetPosChassis(int flt, int frt, int blt, int brt) {
        fl.setTargetPosition(flt);
        fr.setTargetPosition(frt);
        bl.setTargetPosition(blt);
        br.setTargetPosition(brt);
    }

    public void setChassisPwr(double flp, double frp, double blp, double brp) {
        fl.setPower(flp);
        bl.setPower(blp);
        fr.setPower(frp);
        br.setPower(brp);

    }


    public void Movefsls1TargetPos(int TargetPos, double pwr) {
        fsls1.setTargetPosition(TargetPos);
        fsls1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fsls1.setPower(pwr);
    }


    public void Movefsls2TargetPos(int TargetPos, double pwr) {
        fsls2.setTargetPosition(TargetPos);
        fsls2.setPower(pwr);
    }
}



