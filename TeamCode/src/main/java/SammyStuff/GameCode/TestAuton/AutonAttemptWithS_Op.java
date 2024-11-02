


package SammyStuff.GameCode.TestAuton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import SammyStuff.S_OpVersions.S_LinOp;

@Autonomous(name = "AutonAttempt")
public class AutonAttemptWithS_Op extends S_LinOp {
//
//
//
//
//    //chassis motors
//    DcMotor fl,fr,br,bl;
//   //odometry
//    DcMotor fle/* Front Left encoder*/,fre /* Front Right encoder*/,bme /* Back Middle encoder*/;
//   // move chassis
//    double drive,turn,strafe;
//    double flpwr, frpwr, blpwr, brpwr;
//    double flc,frc,blc,brc;
//   //Imu
//    IMU imu;
//    //Telemetry
//    double YawAngle = getYaw();
//    double PitchAngle = getPitch();
//    double RollAngle = getRoll();
//    double FLOV /*Front Left Odomentry Value*/ = fle.getCurrentPosition();
//    double FROV /*Front Right Odomentry Value*/= fre.getCurrentPosition();
//    double BMOV /*Back Middle Odomentry Value*/ = bme.getCurrentPosition();
//   //servo
//    Servo servo1;
//    Servo servo2;
//    //LinearSlides
//    DcMotor fsls_1;
//    DcMotor fsls_2;
//    @Override
    public void runOpMode() throws InterruptedException {

        HardwareConfig(true);

    waitForStart();

    }
}

