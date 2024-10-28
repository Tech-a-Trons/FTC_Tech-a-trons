package SammyStuff.Functions.Sammyfunctions;



import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class SammyFunctions {
    public LinearOpMode SammyOpMode;
    private ElapsedTime holdTimer = new ElapsedTime();
    public SammyFunctions(LinearOpMode opMode) {
        SammyOpMode = opMode;
    }

    //chassis motors
   public DcMotor fl,fr,br,bl;
    //odometry
    public DcMotor fle/* Front Left encoder*/,fre /* Front Right encoder*/,
            bme /* Back Middle encoder*/;
    // move chassis
    public double drive,turn,strafe;
    public double flpwr, frpwr, blpwr, brpwr;
    public double flc,frc,blc,brc;
    //Imu
    public IMU imu;
    //Telemetry
    public double YawAngle = getYaw();
    public double PitchAngle = getPitch();
    public double RollAngle = getRoll();
    public double FLOV /*Front Left Odomentry Value*/ = fle.getCurrentPosition();
    public double FROV /*Front Right Odomentry Value*/= fre.getCurrentPosition();
    public double BMOV /*Back Middle Odomentry Value*/ = bme.getCurrentPosition();
    //servo
    public Servo servo1;
    public Servo servo2;
    //LinearSlides
    public DcMotor fsls1;
    public DcMotor fsls2;

    public void HardwareConfig(boolean useEncoder) {


        fl = SammyOpMode.hardwareMap.get(DcMotor.class, "fl");
        fr = SammyOpMode.hardwareMap.get(DcMotor.class, "fr");
        bl = SammyOpMode.hardwareMap.get(DcMotor.class, "bl");
        br = SammyOpMode.hardwareMap.get(DcMotor.class, "br");
        fle = SammyOpMode.hardwareMap.dcMotor.get("fl");
        fre = SammyOpMode.hardwareMap.dcMotor.get("fr");
        bme = SammyOpMode.hardwareMap.dcMotor.get("bl");
        imu = SammyOpMode.hardwareMap.get(IMU.class, "imu");
        servo1 = SammyOpMode.hardwareMap.get(Servo.class, "servo1");
        fsls1 = SammyOpMode.hardwareMap.get(DcMotor.class, "fl");
        fsls2 = SammyOpMode.hardwareMap.get(DcMotor.class, "fl");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        if (useEncoder){
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
        }else{
            fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            fle.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fre.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bme.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }


    public void telemetryInit() {

        SammyOpMode.telemetry.addData("Status", "Initialized");
        SammyOpMode.telemetry.update();
        SammyOpMode.telemetry.setMsTransmissionInterval(100);
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


        SammyOpMode.telemetry.update();

    }



    public void MoveChassis(DcMotor fl,DcMotor fr,DcMotor br,DcMotor bl) {


        drive = -SammyOpMode.gamepad1.left_stick_y;
        turn = SammyOpMode.gamepad1.right_stick_x;
        strafe = SammyOpMode.gamepad1.left_stick_x;

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
        SammyOpMode.telemetry.addData(Heading, Data);
    }

    public void displayBigOnDS(String Heading) {
        SammyOpMode.telemetry.addLine(Heading);
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
