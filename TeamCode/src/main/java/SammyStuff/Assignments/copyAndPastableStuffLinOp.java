package SammyStuff.Assignments;/*This Class has been converted to S_LinOp
I will leave it here for reference(idk)







functions are down below




| | | | |
v v v v v
















 */





//package org.firstinspires.SammyStuff.MyCode;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
////add auton or tele
//public class copyAndPastableStuffLinOp extends LinearOpMode {
//    DcMotor fl = null;
//    DcMotor fr = null;
//    DcMotor br = null;
//    DcMotor bl = null;
//    double drive;
//    double turn;
//    double strafe;
//    double flpwr, frpwr, blpwr, brpwr;
//    double flc,frc,blc,brc;
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//
//
//        HardwareMap();
//
//        reverse();
//        telemetryInit();
//        useEncoder();
//        DontUseEncoders();
//        brake();
//
//
//        waitForStart();
//
//
//        while (opModeIsActive()) {
//           telemetryAfterInit();
//            teleOp();
//
//        }
//    }
//
//    public void HardwareMap() {
//        fl = hardwareMap.get(DcMotor.class, "fl");
//        fr = hardwareMap.get(DcMotor.class, "fr");
//        bl = hardwareMap.get(DcMotor.class, "bl");
//        br = hardwareMap.get(DcMotor.class, "br");
//    }
//
//    public void reverse() {
//        fl.setDirection(DcMotorSimple.Direction.REVERSE);
//        bl.setDirection(DcMotorSimple.Direction.REVERSE);
//    }
//
//    public void useEncoder() {
//        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//
//        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }
//
//    public void DontUseEncoders() {
//        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//    }
//
//    public void telemetryInit() {
//        telemetry.addData("status", "Initialize");
//        telemetry.update();
//        telemetry.setMsTransmissionInterval(100);
//    }
//
//    public void telemetryAfterInit() {
//       flc = fl.getCurrentPosition();
//       frc = fr.getCurrentPosition();
//       brc = br.getCurrentPosition();
//       blc = bl.getCurrentPosition();
//
//        telemetry.addLine("telemetry activated");
//        telemetry.addData("Pos","FL :%.0f,FR: %.0f, BL: %.0f,BR: %.0f",
//                flc,frc,blc,brc
//        );
//        telemetry.addData("Pwr", "FL :%.0f,FR: %.0f, BL: %.0f,BR: %.0f", flpwr,brpwr,blpwr,brpwr);
//        telemetry.update();
//
//    }
//    public void brake(){
//        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//    }
//    public void teleOp(){
//        drive = -gamepad1.left_stick_y;
//        turn = gamepad1.right_stick_x;
//        strafe = gamepad1.left_stick_x;
//
//
//        flpwr = drive + turn + strafe;
//        frpwr = drive - turn - strafe;
//        blpwr = drive + turn - strafe;
//        brpwr = drive - turn + strafe;
//
//        double maxf = Math.max((Math.abs(flpwr)), (Math.abs(frpwr)));
//        double maxb = Math.max((Math.abs(blpwr)), (Math.abs(brpwr)));
//        double maxfb_pwr = Math.max((Math.abs(maxf)), (Math.abs(maxb)));
//        if (maxfb_pwr > 1) {
//            flpwr = flpwr / maxfb_pwr;
//            frpwr = frpwr / maxfb_pwr;
//            blpwr = blpwr / maxfb_pwr;
//            brpwr = brpwr / maxfb_pwr;
//            fl.setPower(flpwr);
//            fr.setPower(frpwr);
//            bl.setPower(blpwr);
//            br.setPower(brpwr);
//        }
//    }
//}
//
