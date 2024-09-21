package org.firstinspires.ftc.MyCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Autonomous(name = "7b")
public class _7b extends LinearOpMode {
    DcMotor fl = null;
    DcMotor fr = null;
    DcMotor br = null;
    DcMotor bl = null;
     private int L_pos;
     private int R_pos;
    @Override
    public void runOpMode() throws InterruptedException {
        L_pos = 0;
        R_pos = 0;
        HardwareMap();

      reverse();

       use_encoder();


        waitForStart();
        while (opModeIsActive()){
            drive(10, 10, 0.25);
            drive(10, -10, 0.5);
            drive(-10, +10, 0.75);
            drive(-10, -10, 1);
            drive(10,10, 1);
            drive(10,-10,0.75);
            drive(-10,10,0.5);
            drive(-10,-10, 0.25);


        }
    }



    public void HardwareMap(){
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
    }
    public void reverse(){
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void use_encoder(){
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    private void drive(int targetL,int targetR, double speed){

        R_pos = R_pos + targetR;
        L_pos = L_pos + targetL;


       fl.setTargetPosition(L_pos);
       fr.setTargetPosition(R_pos);
       bl.setTargetPosition(L_pos);
       br.setTargetPosition(R_pos);

       fl.setPower(speed);
       bl.setPower(speed);
       fr.setPower(speed);
       br.setPower(speed);


        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive()&& fl.isBusy()&& fr.isBusy()&& bl.isBusy()&&br.isBusy()){
            idle();
        }
    }
}


