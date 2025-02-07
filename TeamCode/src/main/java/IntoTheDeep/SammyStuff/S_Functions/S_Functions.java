package IntoTheDeep.SammyStuff.S_Functions;



import androidx.annotation.NonNull;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class S_Functions {
    public LinearOpMode SammyOpMode;
    public S_Functions(LinearOpMode opMode) {
        SammyOpMode = opMode;
    }
    private ElapsedTime holdTimer = new ElapsedTime();
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
//    public double YawAngle = getYaw();
//    public double PitchAngle = getPitch();
//    public double RollAngle = getRoll();
//    public double FLOV /*Front Left Odomentry Value*/ = fle.getCurrentPosition();
//    public double FROV /*Front Right Odomentry Value*/= fre.getCurrentPosition();
//    public double BMOV /*Back Middle Odomentry Value*/ = bme.getCurrentPosition();
    //servo
    public Servo claw;
    public CRServo ActiveIntake;
    public Servo clawPivot;
    public Servo ChannelSlides;
    //LinearSlides
    public DcMotor fslsLeft;
    public DcMotor fslsRight;
    double LsTickPerMM = 537.7/120;
    double highBasket = 483 * LsTickPerMM;
    double lowBasket = 241.5 * LsTickPerMM;
    double LsPos =  0;
    //names
    HardwareMap hmap;
   Telemetry telemetry;
   //arm
    public DcMotor arm;


    public void HardwareConfig(boolean useEncoder) {
    

        fl =  hmap.get(DcMotor.class, "fl");
        fr =  hmap.get(DcMotor.class, "fr");
        bl =  hmap.get(DcMotor.class, "bl");
        br =  hmap.get(DcMotor.class, "br");
        fle =  hmap.dcMotor.get("fl");
        fre =  hmap.dcMotor.get("fr");
        bme = hmap.dcMotor.get("bl");
        imu =  hmap.get(IMU.class, "imu");
        claw =  hmap.get(Servo.class, "claw");
        ChannelSlides = hmap.get(Servo.class,"channelSlides");
        clawPivot = hmap.get(Servo.class,"clawPivot");
        ActiveIntake =  hmap.get(CRServo.class, "activeintake");
        fslsLeft =  hmap.get(DcMotor.class, "lsl");
        fslsRight =  hmap.get(DcMotor.class, "Lsr");
        arm =  hmap.get(DcMotor.class,"arm");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        if (useEncoder){
            fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            fslsLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fslsRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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

        }

    }


    public void telemetryInit() {

         telemetry.addData("Status", "Initialized");
         telemetry.update();
         telemetry.setMsTransmissionInterval(100);
    }

    public void telemetryAfterInit() {

        flc = fl.getCurrentPosition();
        frc = fr.getCurrentPosition();
        brc = br.getCurrentPosition();
        blc = bl.getCurrentPosition();

        displayHeadingsOnDS("-------Telemetry activated--------------------------------");
        displayInfoOnDS("FlPos: ", flc);
        displayInfoOnDS("FrPos: ", frc);
        displayInfoOnDS("BlPos: ", blc);
        displayInfoOnDS("BrPos: ", brc);
        displayInfoOnDS("FlPwr", flpwr);
        displayInfoOnDS("FrPwr", frpwr);
        displayInfoOnDS("BlPwr", blpwr);
        displayInfoOnDS("BrPwr", brpwr);

//        displayHeadingsOnDS("-------IMU SENSOR DATA--------------------------------");
//        displaySmallOnDS("Yaw Angle: ", YawAngle);
//        displaySmallOnDS("Pitch Angle: ", PitchAngle);
//        displaySmallOnDS("Roll Angle: ", RollAngle);
//        displayHeadingsOnDS("-------ODOMETRY DATA--------------------------------");
//        displaySmallOnDS("FL-Odometry: ", FLOV);
//        displaySmallOnDS("FR-Odometry", FROV);
//        displaySmallOnDS("BM-Odometry", BMOV);


         telemetry.update();

    }





    public void imuInit() {
        imu.initialize(
                new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT))
        );


        imu.resetYaw();
    }

    public void displayInfoOnDS(String Heading, double Data) {
         telemetry.addData(Heading, Data);
    }

    public void displayHeadingsOnDS(String Heading) {
         telemetry.addLine(Heading);
    }


    public double getYaw (){
        double pitch = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        return pitch;
    }

    public double getPitch (){
        double pitch = imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES);
        return pitch;
    }
    public double getRoll () {
        double roll = imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES);
        return roll;
    }
public void SetTargetPosChassis(int flt,double flp, int frt,double frp, int blt,double blp, int brt,double brp) {
    fl.setTargetPosition(flt);
    fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    fr.setTargetPosition(frt);
    fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    bl.setTargetPosition(blt);
    bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    br.setTargetPosition(brt);
    br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    fl.setPower(flp);
    bl.setPower(blp);
    fr.setPower(frp);
    br.setPower(brp);
}


    public void MoveBothfsls(double TargetPos, double pwr, String Direction) {
        if (Direction.equals("up")) {
            fslsLeft.setTargetPosition((int) -TargetPos);
            fslsRight.setTargetPosition((int) -TargetPos);
            fslsRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fslsLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fslsRight.setPower(pwr);
            fslsLeft.setPower(pwr);
            while ( SammyOpMode.opModeIsActive() && fslsLeft.isBusy()) {
                SammyOpMode.idle();
            }
            fslsLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fslsRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fslsRight.setPower(0);
            fslsLeft.setPower(0);
        } else if (Direction.equals("down")) {
            fslsLeft.setTargetPosition((int) TargetPos);
            fslsRight.setTargetPosition((int) TargetPos);
            fslsLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fslsRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fslsLeft.setPower(pwr);
            fslsRight.setPower(pwr);
            while ( SammyOpMode.opModeIsActive() && fslsLeft.isBusy()) {
                SammyOpMode.idle();
            }
            fslsLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fslsRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fslsRight.setPower(0);
            fslsLeft.setPower(0);
        } else {
           fslsLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fslsRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fslsRight.setPower(0);
            fslsLeft.setPower(0);
        }
    }



    public void openClaw() {
    claw.setPosition(0.25);
    }

    public void closeClaw(){
        claw.setPosition(0);
    }

    public void LiftSlidesAndOpenClawSpecimin(){
        MoveBothfsls(2000,0.3,"up");
        SammyOpMode.sleep(1000);
        openClaw();


    }
    public void DefaultSlideAndClaw(){
        MoveBothfsls(0,0.3,"down");

        closeClaw();
    }

    public void MoveLsUp(){
        if(LsPos == 0) {
            LsPos= lowBasket;
            MoveBothfsls(LsPos, 0.3, "up");

        } else if(LsPos == lowBasket){
            LsPos= highBasket;
            MoveBothfsls(LsPos, 0.3, "up");
        }else if (LsPos == highBasket){
            SammyOpMode.idle();
        }
    }
    public void MoveLsDown(){
       if (LsPos == 0) {
           SammyOpMode.idle();
       }else if(LsPos == lowBasket) {
          LsPos = 0;
           MoveBothfsls(LsPos, 0.3, "down");
       }else if(LsPos == highBasket){
           LsPos = lowBasket;
           MoveBothfsls(LsPos, 0.3, "down");
       }
    }
    public void PlaceInLowBasket(){
        MoveBothfsls(2000,0.3,"up");

        SammyOpMode.sleep(500);
        openClaw();
    }
}