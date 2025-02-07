package IntoTheDeep.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import IntoTheDeep.SammyStuff.S_OpVersions.S_LinOp;

@TeleOp(name = "bothSlidesTestS_Op")
public class BothSlidesTestWS_LinOp extends S_LinOp {



//     //chassis motors
//        DcMotor fl,fr,br,bl;
//       //odometry
//        DcMotor fle/* Front Left encoder*/,fre /* Front Right encoder*/,bme /* Back Middle encoder*/;
//        //move chassis
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
//        DcMotor fslsRight;
    @Override
    public void runOpMode() throws InterruptedException {
    HardwareConfig(true);
    telemetryInit();
    waitForStart();
    telemetryAfterInit();
   if (gamepad1.a) {
       Movefsls2(2000, 1,"up");
       Movefsls1(2000,1,"up");

       sleep(1000);

       Movefsls1(0,1,"down");
       Movefsls2(0,1,"down");

   }
    }
}
