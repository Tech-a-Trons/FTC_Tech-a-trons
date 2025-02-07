
# SammyFunctions functions

	these are the same functions as SammyLinOp but SammyLinOp is not currently working



| Functions                  | Variables involved                                                                   | Additional comments and notes                                                                             |
|----------------------------|--------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| **init**                   |
| hardwareConfig             | `boolean useEncoder`                                                                 | n/a                                                                                                       |
| telemetryInit              | n/a                                                                                  | n/a                                                                                                       |
| imuInit                    | n/a                                                                                  | n/a                                                                                                       |
| **AfterInit**              |
| displayInfoOnDS            | String Heading, double Data                                                          | n/a                                                                                                       |
| displayHeadingsOnDS        | String Heading                                                                       | n/a                                                                                                       |
| telemetryAfterInit         | n/a                                                                                  | n/a                                                                                                       |
| SetTargetPosChassis        | `int flt, double flp, int frt, double frp, int blt, double blp, int brt, double brp` | n/a                                                                                                       |
| MoveBothfsls               | `int TargetPos, double pwr, String Direction`                                        | use Movefsls1 and Movefsls2 to access both                                                                |
| open/closeClaw             | n/a                                                                                  | used as openClaw or closeClaw                                                                             |
| DefaultSlideAndClaw        | n/a                                                                                  | It might bug out if you use it when its already at default during your teleOp period                      |
| LiftArmAndOpenClawSpecimin | n/a                                                                                  | n/a                                                                                                       |
| MoveLsUp                   | n/a                                                                                  | used to move slides up to low basket, then to high basket if clicked again(sorry for making that unclear) |
| MoveLsdown                 | n/a                                                                                  | used to move slides down from high basket to low basket (sorry for making that unclear)                   |
| PlaceInLowBasket           | n/a                                                                                  | n/a                                                                                                       |
| will add more later        |                                                                                      |                                                                                                           |




## **Perms**: *vedu(all)*















Copy and paste the stuff below to get access to 
all the commands(variables are commented out because I
dont know if you need them. so just in case) 
Imu is currenty not working and should be commented out
in teleOp because it is only used in Auton


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// chassis motors
        DcMotor fl = functions.fl,
            fr = functions.fr,
            br = functions.br,
            bl = functions.bl;
       //odometry
        DcMotor fle/* Front Left encoder*/ = functions.fle
               ,fre /* Front Right encoder*/ = functions. fle,
               bme /* Back Middle encoder*/ = functions. bme;
    //move chassis
        double drive = functions.drive,
            turn = functions.turn,
            strafe = functions.strafe;

        double flpwr = functions.flpwr,
                frpwr = functions.frpwr,
                blpwr = functions.blpwr,
                brpwr = functions.flpwr;
        double flc = functions.flc,
                frc = functions.frc,
                blc = functions.blc,
                brc = functions.brc;
       //Imu
        IMU imu = functions.imu;
        //Telemetry
//        double YawAngle = functions.getYaw(),
//               PitchAngle = functions.getPitch(),
//               RollAngle = functions.getRoll();
        double FLOV /*Front Left Odomentry Value*/ = functions.fle.getCurrentPosition();
        double FROV /*Front Right Odomentry Value*/= functions.fre.getCurrentPosition();
        double BMOV /*Back Middle Odomentry Value*/ = functions.bme.getCurrentPosition();
       //servo
        Servo claw = functions.claw;

        //Linear slides
        DcMotor fslsLeft = functions.fslsLeft;
        DcMotor fslsRight = functions.fslsRight;
    
    public void runOpMode() throws InterruptedException {
    // hardware init and other init goes here
    
     waitForStart();
    while (opModeIsActive()) {
    // other game code goes here

        drive =-gamepad1.left_stick_y;
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
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


to call any command,    type:

`functions.(command that you want to enter)`

EX:
`functions.HardwareConfig(true)`