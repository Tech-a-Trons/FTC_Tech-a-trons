package General.SammyStuff.GameCode.TestAuton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import General.SammyStuff.S_Functions.S_Functions;

@Autonomous(name = "AutonAttempt")
public class AutonAttempt extends LinearOpMode {



    private S_Functions functions = new S_Functions(this);
//     //chassis motors
//        DcMotor fl,fr,br,bl;
//       //odometry
//        DcMotor fle/* Front Left encoder*/,fre /* Front Right encoder*/,bme /* Back Middle encoder*/;
//  //  move chassis
//        double drive,turn,strafe;
//        double flpwr, frpwr, blpwr, brpwr;
//        double flc,frc,blc,brc;
//       //Imu
//        IMU imu;
//       // Telemetry
//        double YawAngle = functions.getYaw();
//        double PitchAngle = functions.getPitch();
//        double RollAngle = functions.getRoll();
//        double FLOV /*Front Left Odomentry Value*/ = fle.getCurrentPosition();
//        double FROV /*Front Right Odomentry Value*/= fre.getCurrentPosition();
//        double BMOV /*Back Middle Odomentry Value*/ = bme.getCurrentPosition();
//       //servo
//        Servo servo1;
//        Servo servo2;
//
//        DcMotor fsls1;
//        DcMotor fsls2;
//      @Override
    public void runOpMode() throws InterruptedException {

        functions.HardwareConfig(true);
        functions.imuInit();
        functions.telemetryInit();
        waitForStart();
        functions.telemetryAfterInit();
        functions.SetTargetPosChassis(1000,1,1000,1, 1000,1,1000,1);





    }
}

