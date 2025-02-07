package IntoTheDeep.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import IntoTheDeep.SammyStuff.S_Functions.S_Functions;

@TeleOp(name = "bothSlidesTest")
public class BothSlidesTest extends LinearOpMode {
    private S_Functions functions = new S_Functions(this);


     //chassis motors
        DcMotor fl,fr,br,bl;
       //odometry
        DcMotor fle/* Front Left encoder*/,fre /* Front Right encoder*/,bme /* Back Middle encoder*/;
        //move chassis
        double drive,turn,strafe;
        double flpwr, frpwr, blpwr, brpwr;
        double flc,frc,blc,brc;
       //Imu
        IMU imu;
       // Telemetry
//        double YawAngle = functions.getYaw();
//        double PitchAngle = functions.getPitch();
//        double RollAngle = functions.getRoll();
//        double FLOV /*Front Left Odomentry Value*/ = fle.getCurrentPosition();
//        double FROV /*Front Right Odomentry Value*/= fre.getCurrentPosition();
//        double BMOV /*Back Middle Odomentry Value*/ = bme.getCurrentPosition();
       //servo
        Servo servo1;
        Servo servo2;

        DcMotor fsls1;
        DcMotor fsls2;
    @Override
    public void runOpMode() throws InterruptedException {
    functions.HardwareConfig(true);
    functions.telemetryInit();
    waitForStart();
    functions.telemetryAfterInit();
       functions.MoveBothfsls(2000, 1,"up");
       sleep(1000);
       functions.MoveBothfsls(0,1,"down");
    }
}
