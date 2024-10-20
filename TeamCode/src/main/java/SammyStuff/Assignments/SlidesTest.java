package SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Ls_Test")
public class SlidesTest extends LinearOpMode {

    DcMotor LS_Slides;

    @Override
    public void runOpMode() throws InterruptedException {
   LS_Slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    LS_Slides = hardwareMap.get(DcMotor.class,"fl");
    LS_Slides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    LS_Slides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    LS_Slides.setTargetPosition(250);
    LS_Slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    waitForStart();

   LS_Slides.setPower(0.2);
   LS_Slides.setPower(0);

   while(opModeIsActive()&& LS_Slides.isBusy()){
       idle();
        }
    }
}
