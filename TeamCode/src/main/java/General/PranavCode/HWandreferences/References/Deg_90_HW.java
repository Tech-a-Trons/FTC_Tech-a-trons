package General.PranavCode.HWandreferences.References;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous (name = "Pran90DegHW")

public class Deg_90_HW extends LinearOpMode {

    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor bl = null;
    DcMotor br = null;
    public IMU imu = null;

    public void Turn90 (double flp, double frp, double blp, double brp) {
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

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.FORWARD);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RevHubOrientationOnRobot.LogoFacingDirection lD = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  UD  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        RevHubOrientationOnRobot oOR = new RevHubOrientationOnRobot(lD, UD);

        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(oOR));

        imu.resetYaw();

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (YawAng() < 90) {
            Turn90(0.5, -0.5, 0.5 ,-0.5);
        }
    }
}
