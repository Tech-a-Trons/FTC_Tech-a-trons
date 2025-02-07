package IntoTheDeep.SammyStuff.S_Tests.FTClibTest;






import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.kinematics.Odometry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;



public class FTClibStuff extends LinearOpMode {


    Motor fl = new Motor(hardwareMap,"fl");
    Motor fr= new Motor(hardwareMap,"fr");
    Motor bl= new Motor(hardwareMap,"bl");
    Motor br= new Motor(hardwareMap,"br");
    GamepadEx gamepad1;
    @Override
    public void runOpMode() throws InterruptedException {
        fl.setRunMode(Motor.RunMode.PositionControl);


    }
}
