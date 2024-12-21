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
    // DcMotor arm = null;
//    DcMotor fle = null;
//    DcMotor fre = null;
//    DcMotor bme = null;
    //Servo servo1;
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
        //arm = hm.get(DcMotor.class, "arm");
//        fle = hardwareMap.dcMotor.get("fl");
//        fre = hardwareMap.dcMotor.get("fr");
//        bme = hardwareMap.dcMotor.get("bl");
        //servo1 = hm.get(Servo.class, "claw");

        ls1 = hardwareMap.get(DcMotor.class, "lsl");
        ls2 = hardwareMap.get(DcMotor.class, "lsr");

        ls1.setDirection(DcMotorSimple.Direction.REVERSE);

        //arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

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

        telemetry.addLine("Init + Config : Press the start button");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            //1
            //TEST CODE
            //servo1.setPosition(0);
            fl.setTargetPosition(300);
            fr.setTargetPosition(300);
            bl.setTargetPosition(300);
            br.setTargetPosition(300);
            Pwr(0.1, 0.1, 0.1, 0.1);
            sleep(10);

            //2
            //arm.setTargetPosition(-670);
            //arm.setPower(0.3);
            fl.setTargetPosition(200);
            fr.setTargetPosition(200);
            bl.setTargetPosition(200);
            br.setTargetPosition(200);
            Pwr(0.3, 0.3, 0.3, 0.3);
            //servo1.setPosition(1);
            //arm.setTargetPosition(0);
            //arm.setPower(0.35);

            //3
            if (YawAng() < 90) {
                Pwr(0.25, -0.25, 0.25, -0.25);
            }
        }
        //Before this consult and check code
        //Then code rest
    }
}