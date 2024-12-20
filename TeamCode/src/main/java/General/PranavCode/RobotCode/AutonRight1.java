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

        functions.hw(true);

        waitForStart();

        //1
        //TEST CODE
        servo1.setPosition(0);
        fl.setTargetPosition(500);
        fr.setTargetPosition(500);
        bl.setTargetPosition(500);
        br.setTargetPosition(500);
        Pwr(0.25,0.25 ,0.25 ,0.25);

        //2
        if (YawAng() < 180) {
            Pwr(0.25, -0.25, 0.25, -0.25);
        }
        arm.setTargetPosition(-670);
        arm.setPower(0.3);
        fl.setTargetPosition(400);
        fr.setTargetPosition(400);
        bl.setTargetPosition(400);
        br.setTargetPosition(400);
        Pwr(0.3,0.3,0.3,0.3);
        servo1.setPosition(1);
        arm.setTargetPosition(0);
        arm.setPower(0.35);

        //3
        if (YawAng() > 90) {
            Pwr(0.25, -0.25, 0.25, -0.25);
        }

        //Before this consult and check code
        //Then code rest
    }
}
