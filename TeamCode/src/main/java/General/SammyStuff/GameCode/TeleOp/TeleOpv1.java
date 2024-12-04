package General.SammyStuff.GameCode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import General.SammyStuff.S_Functions.S_Functions;

public class TeleOpv1 extends LinearOpMode {
    private S_Functions functions = new S_Functions(this);


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
        DcMotor fsls1;
        DcMotor fsls2;


    public void runOpMode() throws InterruptedException {
        // hardware init and other init goes here
        functions.HardwareConfig(true);
        waitForStart();
        while (opModeIsActive()) {
            // other game code goes here

            drive = -gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;
            strafe =gamepad1.left_stick_x;

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
}
