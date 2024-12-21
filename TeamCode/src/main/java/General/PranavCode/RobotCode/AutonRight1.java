package General.PranavCode.RobotCode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous (name = "AutonRightPran")
public class AutonRight1 extends LinearOpMode {

    private PranFunctions functions = new PranFunctions(this);

    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor bl = null;
    DcMotor br = null;
    DcMotor ls1 = null;
    DcMotor ls2 = null;
    DcMotor arm = null;
//    DcMotor fle = null;
//    DcMotor fre = null;
//    DcMotor bme = null;
    Servo servo1;
    IMU imu;


    public void Pwr(double flp, double frp, double blp, double brp) {
        fl.setPower(flp);
        fr.setPower(frp);
        bl.setPower(blp);
        br.setPower(brp);
    }
    public double YawAng (){
        double YawAng = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        return YawAng;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status: ", "Init Complete!");
        telemetry.update();

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

        imu = hardwareMap.get(IMU.class, "imu");
        arm = hardwareMap.get(DcMotor.class, "arm");
//        fle = hardwareMap.dcMotor.get("fl");
//        fre = hardwareMap.dcMotor.get("fr");
//        bme = hardwareMap.dcMotor.get("bl");
        servo1 = hardwareMap.get(Servo.class, "claw");

        ls1 = hardwareMap.get(DcMotor.class, "lsl");
        ls2 = hardwareMap.get(DcMotor.class, "lsr");

        ls1.setDirection(DcMotorSimple.Direction.REVERSE);

        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        IMU.Parameters IMUp;

        IMUp = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                )
        );
        imu.initialize(IMUp);

        YawPitchRollAngles robotOrientation;
        robotOrientation = imu.getRobotYawPitchRollAngles();

        double Yaw   = robotOrientation.getYaw(AngleUnit.DEGREES);
        double Pitch = robotOrientation.getPitch(AngleUnit.DEGREES);
        double Roll  = robotOrientation.getRoll(AngleUnit.DEGREES);

        imu.resetYaw();

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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

        telemetry.addLine("Init + Config : Press the start button");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();

            //1
            //TEST CODE
            //servo1.setPosition(0);
            fl.setTargetPosition(300);
            fr.setTargetPosition(300);
            bl.setTargetPosition(300);
            br.setTargetPosition(300);
            Pwr(0.1, 0.1, 0.1, 0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //2
            //arm.setTargetPosition(-670);
            //arm.setPower(0.3);
            fl.setTargetPosition(200);
            fr.setTargetPosition(200);
            bl.setTargetPosition(200);
            br.setTargetPosition(200);
            Pwr(0.1, 0.1, 0.1, 0.1);
            //servo1.setPosition(1);
            //arm.setTargetPosition(0);
            //arm.setPower(0.35);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //3
            if (YawAng() < 90) {
                Pwr(-0.25, 0.25, -0.25, 0.25);
            }
            sleep(10);

            //4
            fl.setTargetPosition(350);
            fr.setTargetPosition(350);
            bl.setTargetPosition(350);
            br.setTargetPosition(350);
            Pwr(0.1,0.1, 0.1, 0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //5
            fl.setTargetPosition(600);
            fr.setTargetPosition(600);
            bl.setTargetPosition(600);
            br.setTargetPosition(600);
            Pwr(-0.1,0.1, 0.1, -0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //6
            fl.setTargetPosition(630);
            fr.setTargetPosition(630);
            bl.setTargetPosition(630);
            br.setTargetPosition(630);
            Pwr(0.1,0.1, 0.1, 0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //7
            if (YawAng() < 90) {
                Pwr(-0.25, 0.25, -0.25, 0.25);
            }
            sleep(10);

            //8
            fl.setTargetPosition(800);
            fr.setTargetPosition(800);
            bl.setTargetPosition(800);
            br.setTargetPosition(800);
            Pwr(0.1,0.1, 0.1, 0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //9
            fl.setTargetPosition(630);
            fr.setTargetPosition(630);
            bl.setTargetPosition(630);
            br.setTargetPosition(630);
            Pwr(-0.1,-0.1, -0.1, -0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //10
            fl.setTargetPosition(650);
            fr.setTargetPosition(650);
            bl.setTargetPosition(650);
            br.setTargetPosition(650);
            Pwr(0.1,-0.1, -0.1, 0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //11
            fl.setTargetPosition(820);
            fr.setTargetPosition(820);
            bl.setTargetPosition(820);
            br.setTargetPosition(820);
            Pwr(0.1,0.1, 0.1, 0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //12
            fl.setTargetPosition(650);
            fr.setTargetPosition(650);
            bl.setTargetPosition(650);
            br.setTargetPosition(650);
            Pwr(-0.1,-0.1, -0.1, -0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //13
            fl.setTargetPosition(670);
            fr.setTargetPosition(670);
            bl.setTargetPosition(670);
            br.setTargetPosition(670);
            Pwr(0.1,-0.1, -0.1, 0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //14
            fl.setTargetPosition(840);
            fr.setTargetPosition(840);
            bl.setTargetPosition(840);
            br.setTargetPosition(840);
            Pwr(0.1,0.1, 0.1, 0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //15
            fl.setTargetPosition(800);
            fr.setTargetPosition(800);
            bl.setTargetPosition(800);
            br.setTargetPosition(800);
            Pwr(-0.1,0.1, 0.1, -0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //16
            if (YawAng() < 90) {
                Pwr(-0.25, 0.25, -0.25, 0.25);
            }
            sleep(10);

            //17
            fl.setTargetPosition(600);
            fr.setTargetPosition(600);
            bl.setTargetPosition(600);
            br.setTargetPosition(600);
            Pwr(-0.1,0.1, 0.1, -0.1);
            telemetry.clear();
            telemetry.addData( "fl: ",String.valueOf(fl.getCurrentPosition()));
            telemetry.addData( "fr: ", String.valueOf(fr.getCurrentPosition()));
            telemetry.addData("bl: ", String.valueOf(bl.getCurrentPosition()));
            telemetry.addData("br: ",String.valueOf(br.getCurrentPosition()));
            telemetry.update();
            sleep(10);

            //18
            //arm.setTargetPosition(-670);
        }
    }
}