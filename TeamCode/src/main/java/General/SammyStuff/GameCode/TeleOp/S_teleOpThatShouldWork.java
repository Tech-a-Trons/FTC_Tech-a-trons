package General.SammyStuff.GameCode.TeleOp;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class S_teleOpThatShouldWork extends LinearOpMode {

       //servos
    Servo ChannelSlides;
    Servo claw;
    Servo clawPivot;

    //Drive Train
    double drive; // drive: left joystick y-axis
    double turn;  // turn: right joystick x-axis
    double strafe;  // strafe: left joystick x-axis
    double drive_speed = 0.8;
    double turn_speed = 0.5;
    double strafe_speed = 0.5;
    double FLspeed, FRspeed, BLspeed, BRspeed;


    DcMotor fl,fr,bl,br;



    @Override
    public void runOpMode() throws InterruptedException {
        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();


        ChannelSlides = hardwareMap.get(Servo.class, "channelSlides");

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







    }
}
