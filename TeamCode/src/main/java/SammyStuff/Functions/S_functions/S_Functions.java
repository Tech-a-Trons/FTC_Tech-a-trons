package SammyStuff.Functions.S_functions;



import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

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
    public Servo servo2;
    //LinearSlides
    public DcMotor fsls1;
    public DcMotor fsls2;
    int LsDefaultPos = 0;

    public void HardwareConfig(boolean useEncoder) {


        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        fle = hardwareMap.dcMotor.get("fl");
        fre = hardwareMap.dcMotor.get("fr");
        bme = hardwareMap.dcMotor.get("bl");
        imu = hardwareMap.get(IMU.class, "imu");
        claw = hardwareMap.get(Servo.class, "claw");
        fsls1 = hardwareMap.get(DcMotor.class, "fl");
        fsls2 = hardwareMap.get(DcMotor.class, "fr");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        if (useEncoder){
            fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


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

            fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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

        displayBigOnDS("-------Telemetry activated--------------------------------");
        displaySmallOnDS("FlPos: ", flc);
        displaySmallOnDS("FrPos: ", frc);
        displaySmallOnDS("BlPos: ", blc);
        displaySmallOnDS("BrPos: ", brc);
        displaySmallOnDS("FlPwr", flpwr);
        displaySmallOnDS("FrPwr", frpwr);
        displaySmallOnDS("BlPwr", blpwr);
        displaySmallOnDS("BrPwr", brpwr);

//        displayBigOnDS("-------IMU SENSOR DATA--------------------------------");
//        displaySmallOnDS("Yaw Angle: ", YawAngle);
//        displaySmallOnDS("Pitch Angle: ", PitchAngle);
//        displaySmallOnDS("Roll Angle: ", RollAngle);
//        displayBigOnDS("-------ODOMETRY DATA--------------------------------");
//        displaySmallOnDS("FL-Odometry: ", FLOV);
//        displaySmallOnDS("FR-Odometry", FROV);
//        displaySmallOnDS("BM-Odometry", BMOV);


        telemetry.update();

    }



    public void MoveChassis() {


        drive = -gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;
        strafe = gamepad1.left_stick_x;

        flpwr = drive + turn + strafe;
        frpwr = drive - turn - strafe;
        blpwr = drive + turn - strafe;
        brpwr = drive - turn + strafe;

        double maxf = Math.max((Math.abs(flpwr)), (Math.abs(frpwr)));
        double maxb = Math.max((Math.abs(blpwr)), (Math.abs(brpwr)));
        double maxfb_pwr = Math.max((Math.abs(maxf)), (Math.abs(maxb)));
        if (maxfb_pwr > 1) {
            flpwr /= maxfb_pwr;
            frpwr /= maxfb_pwr;
            blpwr /= maxfb_pwr;
            brpwr /= maxfb_pwr;
            fl.setPower(flpwr);
            fr.setPower(frpwr);
            bl.setPower(blpwr);
            br.setPower(brpwr);
        }
    }

    public void imuInit() {
        imu.initialize(
                new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT))
        );


        imu.resetYaw();
    }

    public void displaySmallOnDS(String Heading, double Data) {
        telemetry.addData(Heading, Data);
    }

    public void displayBigOnDS(String Heading) {
        telemetry.addLine(Heading);
    }


//    public double getYaw (){
//        double pitch = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
//        return pitch;
//    }
//
//    public double getPitch (){
//        double pitch = imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES);
//        return pitch;
//    }
//    public double getRoll () {
//        double roll = imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES);
//        return roll;
//    }
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


    public void Movefsls1(int TargetPos, double pwr, String Direction) {
        if (Direction == "up") {
            fsls1.setTargetPosition(-TargetPos);
            fsls1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fsls1.setPower(pwr);
            while (opModeIsActive() && fsls1.isBusy()) {
                idle();
            }
            fsls1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls1.setPower(0);
        } else if (Direction == "down") {
            fsls1.setTargetPosition(TargetPos);
            fsls1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fsls1.setPower(pwr);
            while (opModeIsActive() && fsls1.isBusy()) {
                idle();
            }
            fsls1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls1.setPower(0);
        } else {
           fsls1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls1.setPower(0);
        }
    }


    public void Movefsls2(int TargetPos, double pwr, String Direction) {
        if (Direction == "up") {
            fsls2.setTargetPosition(-TargetPos);
            fsls2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fsls2.setPower(pwr);
            while (opModeIsActive() && fsls2.isBusy()) {
                idle();
            }
            fsls2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls2.setPower(0);
        } else if (Direction == "down") {
            fsls2.setTargetPosition(TargetPos);
            fsls2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fsls2.setPower(pwr);
            while (opModeIsActive() && fsls2.isBusy()) {
                idle();
            }
            fsls2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls2.setPower(0);
        } else {
            fsls2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsls2.setPower(0);
        }
    }
    public void openClaw() {
    claw.setPosition(1000);
    }

    public void closeClaw(){
        claw.setPosition(0);
    }

    public void LiftSlidesAndOpenClawSpecimin(){
        Movefsls1(2000,0.3,"up");
        Movefsls2(2000,0.3,"up");
        sleep(1000);
        openClaw();


    }
    public void DefaultSlideAndClaw(){
        Movefsls1(0,0.3,"down");
        Movefsls2(0,0.3,"down");
        closeClaw();
    }

    public void MoveLsUp(){
        Movefsls1(LsDefaultPos+1000,0.3,"up");
        Movefsls2(LsDefaultPos+1000,0.3,"up");
    }
    public void MoveLsDown(){
        Movefsls1(LsDefaultPos+1000,0.3,"down");
        Movefsls2(LsDefaultPos+1000,0.3,"down");
    }
    public void PlaceInLowBasket(){
        Movefsls1(2000,0.3,"up");
        Movefsls2(2000,0.3,"up");
        sleep(500);

    }
}
