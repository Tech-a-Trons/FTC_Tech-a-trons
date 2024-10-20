package SammyStuff.Assignments;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import SammyStuff.SammyOpVersions.SammyLinOp;

@Autonomous(name = "90 degree turn")
public class Turn90DegHw extends SammyLinOp {
    DcMotor fl;
    DcMotor fr;double YawAngle = getYaw();
    double PitchAngle = getPitch();
    double RollAngle = getRoll();
    DcMotor br;
    DcMotor bl;
    DcMotor fle, fre,bme;
    double drive;
    double turn;
    double strafe;
    double flpwr, frpwr, blpwr, brpwr;
    double flc,frc,blc,brc;
    IMU imu;


    double FLOV = fle.getCurrentPosition();
    double FROV = fre.getCurrentPosition();
    double BMOV = bme.getCurrentPosition();

    @Override

    public void runOpMode() throws InterruptedException{

     HardwareMap();
     reverse();
     telemetryInit();
     use_encoder();
     brake();
     imuInit();

     waitForStart();
    while (opModeIsActive()) {

        if (getYaw()!= 90&&getYaw() > 45){
        setChassisPwr(1,-1,1,-1);
        }else if(getYaw()!=90&&getYaw() <45){
            setChassisPwr(0.5,-0.5,0.5,-0.5);
        }else{
            setChassisPwr(0,0,0,0);
        }
         }
    }
}
