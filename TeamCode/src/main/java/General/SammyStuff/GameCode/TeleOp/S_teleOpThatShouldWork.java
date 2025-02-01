package General.SammyStuff.GameCode.TeleOp;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class S_teleOpThatShouldWork extends LinearOpMode {
       //servos
    public Servo SzoneSlide = null;

    Servo speciminClaw;
    Servo sampleClaw;
    Servo sampleClawPivot;

    //Drive Train
    double drive; // drive: left joystick y-axis
    double turn;  // turn: right joystick x-axis
    double strafe;  // strafe: left joystick x-axis
    double drive_speed = 0.8;
    double turn_speed = 0.5;
    double strafe_speed = 0.5;
    double FLspeed, FRspeed, BLspeed, BRspeed;


    DcMotor fl,fr,bl,br;
//sleides
    boolean SzoneSlideExtended = false;
    private boolean SzoneSlide_lastBump = false;
//slides
public DcMotor LeftLinearSlide = null;
    public DcMotor RightLinearSlide = null;
    final double LS_TICKS_PER_MM = 537.7 / 120.0;
    double LS_full_speed = 0.8;   //0.8 or 1.0
    double LS_half_speed = LS_full_speed / 2;   // div 2

    double LS_OneThird_speed = LS_full_speed / 3;   // div 3
    boolean LS_motorRunning = false;
    double LS_RESET_POSITION = 0;
    double LS_TOLERANCE = 2.0;

    //toggles
   public static boolean SpeciminClawOpened = true;
   public static boolean SampleClawOpened = true;
   public static boolean GoingClockWise = true;

   
   
   public static double SampleClawPivotPos =  0.325;
    @Override
    public void runOpMode() throws InterruptedException {
        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        sampleClawPivot.setPosition(0.325);
        speciminClaw = hardwareMap.get(Servo.class, "claw");
        
        sampleClaw.setPosition(0);
        

        waitForStart();

        previousGamepad1.copy(currentGamepad1);
        previousGamepad2.copy(currentGamepad2);

        currentGamepad1.copy(gamepad1);
        currentGamepad2.copy(gamepad2);


        //Drive Code starts here

        drive = (gamepad1.left_stick_y * -1) * drive_speed;
        turn = (gamepad1.right_stick_x) * turn_speed;
        strafe = (gamepad1.left_stick_x) * strafe_speed;

        FLspeed = drive + turn + strafe;
        FRspeed = drive - turn - strafe;
        BLspeed = drive + turn - strafe;
        BRspeed = drive - turn + strafe;

        // Scaling Drive Powers Proportionally
        double maxF = Math.max((abs(FLspeed)),(abs(FRspeed)));
        double maxB = Math.max((abs(BLspeed)),(abs(BRspeed)));
        double maxFB_speed = Math.max(abs(maxF), abs(maxB));

        if(maxFB_speed > 1){
            FLspeed = FLspeed / maxFB_speed;
            FRspeed = FRspeed / maxFB_speed;
            BLspeed = BLspeed / maxFB_speed;
            BRspeed = BRspeed / maxFB_speed;
        }

        fl.setPower(FLspeed);
        fr.setPower(FRspeed);
       bl.setPower(BLspeed);
       br.setPower(BRspeed);

        //Drive code ends Here

        //Claw code starts here

            //speciminCLaw
        if(currentGamepad2.right_bumper && !previousGamepad2.right_bumper){

            SpeciminClawOpened = !SpeciminClawOpened;
        }

        if (SpeciminClawOpened){
        speciminClaw.setPosition(0);//close
        }else if(!SpeciminClawOpened){
            speciminClaw.setPosition(0.3);//open
        }

            //sample claw

        if (currentGamepad2.left_bumper && !previousGamepad2.left_bumper){
            SampleClawOpened = !SampleClawOpened;
        }

        if (SampleClawOpened){
            sampleClaw.setPosition(0.4);//close
        }else if(!SampleClawOpened){
         sampleClaw.setPosition(0.15);//open
        }



        //Claw Code End


        // Claw Pivot code Start

        if (currentGamepad2.y&&!previousGamepad2.y) {

           if (GoingClockWise){
               SampleClawPivotPos = SampleClawPivotPos - 0.1625;// goes in intervals of 0.1625
               sampleClawPivot.setPosition(SampleClawPivotPos);

           }else if(!GoingClockWise){
               SampleClawPivotPos = SampleClawPivotPos + 0.1625;// goes in intervals of 0.1625
               sampleClawPivot.setPosition(SampleClawPivotPos);
           }
        }

        if (SampleClawPivotPos == 0.65|| SampleClawPivotPos == 0){
            GoingClockWise = !GoingClockWise;
        }


        // Claw Pivot code Start and is Signinficantly simplified :)


        //LS and Channel SLide will be used with PID so not in this one :)


    }
}
