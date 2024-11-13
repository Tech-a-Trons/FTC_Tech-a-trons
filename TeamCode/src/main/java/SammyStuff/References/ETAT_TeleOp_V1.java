package SammyStuff.References;

//import android.util.Range;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import SammyStuff.Functions.ETAT_functions;

//@Disabled
@TeleOp (name = "ETAT_TeleOp_V1", group = "ETAT")
public class ETAT_TeleOp_V1 extends LinearOpMode {
    // get an instance of the "ETAT_functions" class.
    private ETAT_functions ETAT_Robot_functions = new ETAT_functions(this);

    //Hardware Map Names for drive motors and odometry wheels.
    String frName = "fr", brName = "br", flName = "fl", blName = "bl";
    String FrontLeftEncoderName = frName, FrontRightEncoderName = flName, BackMiddleEncoderName = blName;
    String IMUname = "imu";
    String  LinearSlideName = "fl";
//    String LeftLinearSlideName = "LLS", RightLinearSlideName = "RLS";
    String ClawServoName = "claw";
    String ActiveIntakeCRServoName = "activeintake";

    // For Drive Train


    // For Linear Slide
    double SLIDE_UP_POWER = 0.5;
    double SLIDE_DOWN_POWER = -0.5;

    // For Claw
    public final static double CLAW_HOME = 0.0;
    public final static double CLAW_MIN_RANGE = 0.0;
    public final static double CLAW_MAX_RANGE = 0.5;
    public final static double CLAW_CLOSE = 0.0;
    public final static double CLAW_OPEN = 0.2;
    double clawOperation = CLAW_HOME;

    // For Active-Intake


    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize Telemetry.
        ETAT_Robot_functions.TelemetryInit(250); // Default refresh rate is 250ms
        ETAT_Robot_functions.TelemetryUpdate(10);

        //Robot Hardware Mapping.
        ETAT_Robot_functions.myRobot_HardwareMap(
                frName, brName, flName, blName,
                FrontLeftEncoderName, FrontRightEncoderName, BackMiddleEncoderName,
                IMUname,
                LinearSlideName,
//                LeftLinearSlideName, RightLinearSlideName,
                ClawServoName,
                ActiveIntakeCRServoName
                );

        // Robot Hardware Configuration
        ETAT_Robot_functions.myRobot_HardwareConfig();

        ETAT_Robot_functions.TelemetryShowHeading("Initialization & Configuration : DONE");
        ETAT_Robot_functions.TelemetryShowHeading("> Wait for Start ......");
        ETAT_Robot_functions.TelemetryUpdate(10);

        waitForStart();

        while (opModeIsActive()){

            //Drive Train code Start===============================================================
            ETAT_Robot_functions.Drivetrain(gamepad1.left_stick_y , gamepad1.right_stick_x, gamepad1.left_stick_x,
                                            0.2, 0.3, 0.4);
            // Drive Train code end

            // For Linear Slide Code start==========================================================
            if(gamepad1.dpad_up) {
                ETAT_Robot_functions.setLinearSlide("UP", 3500, 0.3);
            }
            if(gamepad1.dpad_down) {
                ETAT_Robot_functions.setLinearSlide("DOWN", 0, 0.3);
            }
            // For Linear Slide Code end

            //For Claw code start==================================================================
            if (gamepad1.a)
                clawOperation = CLAW_CLOSE;
            if (gamepad1.y)
                clawOperation = CLAW_OPEN;
            clawOperation = Range.clip(clawOperation, CLAW_MIN_RANGE, CLAW_MAX_RANGE);
            ETAT_Robot_functions.Claw_Servo.setPosition(clawOperation);
            sleep(100);
            // For Claw code end

            //For Active-Intake code start==================================================================
            while(gamepad1.b)
                ETAT_Robot_functions.setActiveIntake("IN");
            while(gamepad1.x)
                ETAT_Robot_functions.setActiveIntake("OUT");
            ETAT_Robot_functions.setActiveIntake("STOP");
            //For Active-Intake code End==================================================================
        }
    }
}
