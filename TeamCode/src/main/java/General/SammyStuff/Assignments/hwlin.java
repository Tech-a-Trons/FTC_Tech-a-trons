package General.SammyStuff.Assignments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous ( name = "Hw_Task_Lin")
public class hwlin extends LinearOpMode {
   DcMotor task;
    @Override
    public void runOpMode() throws InterruptedException {
        task = hardwareMap.get(DcMotor.class, "motor1");

        task.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        task.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        task.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForStart();
     if (gamepad1.left_trigger>0.05){
       task.setPower(0.25);
     }else{
         task.setPower(0);
     }
        task.setTargetPosition(1750);
    }
}
