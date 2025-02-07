package IntoTheDeep.SammyStuff.GameCode.TeleOp;

import static java.lang.Math.abs;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import IntoTheDeep.SammyStuff.GameCode.subsystems.ClawPivot;
import IntoTheDeep.SammyStuff.GameCode.subsystems.LinearSlides;
import IntoTheDeep.SammyStuff.GameCode.subsystems.sampleClaw;
import IntoTheDeep.SammyStuff.GameCode.subsystems.specClaw;
import IntoTheDeep.SammyStuff.GameCode.subsystems.submersibleSlides;
@TeleOp(name = "S_teleOpw/FTCLIB")
public class S_TeleOpWithNotesFromPolaris extends LinearOpMode {

    /*what isnt working

        everything except claws
        use ftc lib gamepad for this(eliminates rising edge detector)
        1200 for dpad right
        2000 for hang spec
        300 for sub
        2550 for hang
        1900 for pulling up for hang


     */
    //toggles
    boolean isSampleClawClosed;
    boolean isSpecimenClawClosed;
    boolean GoingClockWise;
    int ChannelSlidePos;

    //subsystems
    specClaw specClaw;
    sampleClaw sampleClaw;
    ClawPivot clawPivot;
    LinearSlides linearSlides;
    submersibleSlides channelslides;
    //PID
    int target;


    DcMotor fl,fr,bl,br;


    double drive;
    double turn;
    double strafe;
    double drive_speed = 0.8;
    double turn_speed = 0.5;
    double strafe_speed = 0.5;
    double FLspeed, FRspeed, BLspeed, BRspeed;

//
//    Motor fl = new Motor(hardwareMap, "fl"), bl = new Motor(hardwareMap, "bl"),
//            fr = new Motor(hardwareMap, "fr"), br = new Motor(hardwareMap, "br");
//
//
//    MecanumDrive mecanumDrive = new MecanumDrive(fl, fr, bl, br);

    //gamepads
    GamepadEx driver;
    GamepadEx tools;


    @Override
    public void runOpMode() throws InterruptedException {
        specClaw = new specClaw(hardwareMap, "specimenclaw");
        sampleClaw = new sampleClaw(hardwareMap, "sampleclaw");
        clawPivot = new ClawPivot(hardwareMap, "wrist");
        linearSlides = new LinearSlides(hardwareMap, "lsl", "lsr");
        channelslides = new submersibleSlides(hardwareMap, "szoneslide");
        fl = hardwareMap.get(DcMotor.class,"fl");
        fr =hardwareMap.get(DcMotor.class,"fr");
        bl = hardwareMap.get(DcMotor.class,"bl");
        br = hardwareMap.get(DcMotor.class,"br");


        driver = new GamepadEx(gamepad1);
        tools = new GamepadEx(gamepad2);


        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();


        //Initial conditions
        isSampleClawClosed = false;
        isSpecimenClawClosed = false;
        GoingClockWise = true;
        target = 0;
        ChannelSlidePos = 0;
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();
        while (opModeIsActive()) {


            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);

            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            //Claw code
            //sampleClaw
            if (currentGamepad2.left_bumper && !previousGamepad2.left_bumper) {

                isSampleClawClosed = !isSampleClawClosed;
            }
            if (isSampleClawClosed) {
                sampleClaw.closeClaw();
            } else {
                sampleClaw.openClaw();
            }
            //specimen claw
            if (currentGamepad2.right_bumper && !previousGamepad2.right_bumper) {
                isSpecimenClawClosed = !isSpecimenClawClosed;
            }
            if (isSpecimenClawClosed) {
                specClaw.closeClaw();
            } else {
                specClaw.openClaw();
            }

            //Claw code end

            //Claw Pivot/wrist code

            if (currentGamepad2.y && !previousGamepad2.y) {
                if (GoingClockWise) {
                    clawPivot.PivotGoingClockWise();
                } else {
                    clawPivot.PivotGoingCounterClockwise();
                }
            }
            if (clawPivot.GetClawPivot() == 0.65 || clawPivot.GetClawPivot() == 0) {
                GoingClockWise = !GoingClockWise;
            }

        }
        //Claw Pivot/wrist code end

        //LS code start
        if (currentGamepad2.dpad_down && !previousGamepad2.dpad_down) {
            target = 0;
            sampleClaw.openClaw();
        }

        if (currentGamepad2.a && !previousGamepad2.a) {
            target = 0;
            sampleClaw.closeClaw();
        }

        if (currentGamepad2.dpad_up && !previousGamepad2.dpad_up) {
            target = 2000;
        }

        if (currentGamepad2.dpad_right && !previousGamepad2.dpad_right) {
            target = 1200;
            sampleClaw.closeClaw();
        }

        if (currentGamepad2.b && !previousGamepad2.b) {
            target = 300;
        }
        if (currentGamepad1.dpad_down && !previousGamepad1.dpad_down) {
            target = 300;
            clawPivot.setClawPivotManually(0.325);
        }
        if (currentGamepad1.dpad_up && !previousGamepad1.dpad_up) {
            target = 2550;
            sampleClaw.closeClaw();
            specClaw.openClawBig();
            clawPivot.setClawPivotManually(0.325);

        }
        if (currentGamepad1.dpad_right && !previousGamepad1.dpad_right) {
            target = 1950;
        }

        //linear slide code end

        //channel slide code start

        if (currentGamepad2.x && !previousGamepad2.x) {

            if (ChannelSlidePos == 0) {
                channelslides.retract();
                ChannelSlidePos = ChannelSlidePos + 1;
            } else if (ChannelSlidePos == 1) {
                channelslides.extendhalf();
                ChannelSlidePos = ChannelSlidePos + 1;
            } else if (ChannelSlidePos == 2) {
                channelslides.extend();
                ChannelSlidePos = 0;
            }
        }

        //channel slides code end


        //drive code starts

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

        //drive code end
        linearSlides.PIDloop(target);


//
//        // ftc lib gamepad version
//
//        //Claw code
//        //sampleClaw
//        if (tools.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)) {
//
//            isSampleClawClosed = !isSampleClawClosed;
//        }
//        if (isSampleClawClosed) {
//            sampleClaw.closeClaw();
//        } else {
//            sampleClaw.openClaw();
//        }
//        //specimen claw
//        if (tools.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)) {
//            isSpecimenClawClosed = !isSpecimenClawClosed;
//        }
//        if (isSpecimenClawClosed) {
//            specClaw.closeClaw();
//        } else {
//            specClaw.openClaw();
//        }
//
//        //Claw code end
//
//        //Claw Pivot/wrist code
//
//        if (tools.wasJustPressed(GamepadKeys.Button.Y)) {
//            if(GoingClockWise){
//                clawPivot.PivotGoingClockWise();
//            }else{
//                clawPivot.PivotGoingCounterClockwise();
//            }
//        }
//        if (clawPivot.GetClawPivot() == 0.65 || clawPivot.GetClawPivot() == 0) {
//            GoingClockWise = !GoingClockWise;
//        }
//
//
//    //Claw Pivot/wrist code end
//
//    //LS code start
//        if (tools.wasJustPressed(GamepadKeys.Button.DPAD_DOWN)){
//        target = 0;
//        sampleClaw.openClaw();
//    }
//
//        if (tools.wasJustPressed(GamepadKeys.Button.A)){
//        target = 0;
//        sampleClaw.closeClaw();
//    }
//
//        if (tools.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
//        target = 2000;
//    }
//
//        if (tools.getButton(GamepadKeys.Button.DPAD_RIGHT)){
//        target = 1200;
//        sampleClaw.closeClaw();
//    }
//
//        if(tools.wasJustPressed(GamepadKeys.Button.B)){
//        target = 300;
//    }
//        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
//        target = 300;
//        clawPivot.setClawPivotManually(0.325);
//    }
//        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_UP)){
//        target = 2550;
//        sampleClaw.closeClaw();
//        specClaw.openClawBig();
//        clawPivot.setClawPivotManually(0.325);
//
//    }
//        if (driver.wasJustPressed(GamepadKeys.Button.DPAD_RIGHT)){
//        target = 1950;
//    }
//
//    //linear slide code end
//
//    //channel slide code start
//
//        if (tools.wasJustPressed(GamepadKeys.Button.X)){
//
//        if (ChannelSlidePos == 0) {
//            channelslides.retract();
//            ChannelSlidePos =ChannelSlidePos +1;
//        } else if (ChannelSlidePos == 1) {
//            channelslides.extendhalf();
//            ChannelSlidePos =ChannelSlidePos +1;
//        } else if (ChannelSlidePos == 2) {
//            channelslides.extend();
//            ChannelSlidePos = 0;
//        }
//    }
//    //channel slides code end
//
//
//        //secondary drive code
//
//        mecanumDrive.driveRobotCentric(driver.getLeftX(),
//                -driver.getLeftY(),
//                driver.getRightX()
//        );
//
//       //secondary drive code end
    }
}
