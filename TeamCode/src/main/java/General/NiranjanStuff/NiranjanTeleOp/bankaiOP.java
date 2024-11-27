package General.NiranjanStuff.NiranjanTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import General.References.ETAT_functions_v1;

@TeleOp(name = "BankaiTeleOp", group = "ETAT")
public class bankaiOP extends LinearOpMode {
    private final ETAT_functions_v1 ETAT_Robot_functions = new ETAT_functions_v1(this);

    // Hardware Map Names
    String frName = "fr", brName = "br", flName = "fl", blName = "bl";
    String FrontLeftEncoderName = frName, FrontRightEncoderName = flName, BackMiddleEncoderName = blName;
    String IMUname = "imu";
    String LeftLinearSlideName = "fl", RightLinearSlideName = "fr";
    String ClawServoName = "claw";
    String ActiveIntakeCRServoName = "activeintake";
    String ArmName = "bl";

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

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize Telemetry
        ETAT_Robot_functions.TelemetryInit(250);
        ETAT_Robot_functions.TelemetryUpdate(10);

        // Robot Hardware Mapping
        ETAT_Robot_functions.myRobot_HardwareMap(
                frName, brName, flName, blName,
                FrontLeftEncoderName, FrontRightEncoderName, BackMiddleEncoderName,
                IMUname,
                LeftLinearSlideName, RightLinearSlideName,
                ClawServoName,
                ActiveIntakeCRServoName,
                ArmName
        );

        // Robot Hardware Configuration
        ETAT_Robot_functions.myRobot_HardwareConfig();

        ETAT_Robot_functions.TelemetryShowHeading("Initialization & Configuration : DONE");
        ETAT_Robot_functions.TelemetryShowHeading("> Wait for Start ......");
        ETAT_Robot_functions.TelemetryUpdate(10);

        waitForStart();

        while (opModeIsActive()) {
            // Drive Train Code
            ETAT_Robot_functions.Drivetrain(gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x, 0.4, 0.4, 0.4);

            // Linear Slide Code
            if (gamepad1.dpad_up) {
                ETAT_Robot_functions.set_Left_Right_LinearSlide_v2("UP", 2600, 0.7);
            }
            if (gamepad1.dpad_down) {
                ETAT_Robot_functions.set_Left_Right_LinearSlide_v2("DOWN", 3, 0.7);
            }

            // Claw Code
            if (gamepad1.a) {
                clawOperation = CLAW_CLOSE;
                clawOperation = Range.clip(clawOperation, CLAW_MIN_RANGE, CLAW_MAX_RANGE);
                ETAT_Robot_functions.Claw_Servo.setPosition(clawOperation);
            } else if (gamepad1.y) {
                clawOperation = CLAW_OPEN;
                clawOperation = Range.clip(clawOperation, CLAW_MIN_RANGE, CLAW_MAX_RANGE);
                ETAT_Robot_functions.Claw_Servo.setPosition(clawOperation);
            }

            // Arm Code
            if (gamepad1.b) {
                ETAT_Robot_functions.setArm_version2("HOME", 5, 250);
//            } else if (gamepad1.right_bumper) {
//                ETAT_Robot_functions.setArm_version2("DROP_or_CLIP", 655, 250);
//            } else if (gamepad1.left_bumper) {
//                ETAT_Robot_functions.setArm_version2("PICKUP", 950, 250);
//            }
//            ETAT_Robot_functions.moveToPositionWithClawAndSlides("PICKUP",true,"UP");
                if (gamepad1.left_bumper){ //close claw after
                    ETAT_Robot_functions.setArm_version2("PICKUP",950,250);
                    clawOperation = CLAW_OPEN;
                    clawOperation = Range.clip(clawOperation, CLAW_MIN_RANGE, CLAW_MAX_RANGE);
                    ETAT_Robot_functions.Claw_Servo.setPosition(clawOperation);
                    ETAT_Robot_functions.set_Left_Right_LinearSlide_v2("DOWN",3,0.7);
                }
                if (gamepad1.right_bumper){ //open claw after
                    ETAT_Robot_functions.setArm_version2("DROP_or_CLIP",655,250);
                    clawOperation = CLAW_CLOSE;
                    clawOperation = Range.clip(clawOperation, CLAW_MIN_RANGE, CLAW_MAX_RANGE);
                    ETAT_Robot_functions.Claw_Servo.setPosition(clawOperation);
                    ETAT_Robot_functions.set_Left_Right_LinearSlide_v2("UP",2600,0.7);

                }
                if (gamepad1.dpad_right){ //no claw adjustment
                    ETAT_Robot_functions.setArm_version2("HOME",5,250);
                    clawOperation = CLAW_CLOSE;
                    clawOperation = Range.clip(clawOperation, CLAW_MIN_RANGE, CLAW_MAX_RANGE);
                    ETAT_Robot_functions.Claw_Servo.setPosition(clawOperation);
                    ETAT_Robot_functions.set_Left_Right_LinearSlide_v2("DOWN",3,0.7);

                }
                // Active Intake Code
            /*
            while (gamepad1.x) {
                ETAT_Robot_functions.setActiveIntake("IN");
            }
            while (gamepad1.b) {
                ETAT_Robot_functions.setActiveIntake("OUT");
            }
            ETAT_Robot_functions.setActiveIntake("STOP");
            */

            }
        }
    }
}